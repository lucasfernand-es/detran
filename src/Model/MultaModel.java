package Model;

import Connector.MySQLConnector;
import Controller.Util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import valueObject.Automovel;
import valueObject.Multa;

/**
 *
 * @author cristhian
 */
public class MultaModel {
    
    public static ArrayList<Multa>  buscarMulta(Multa multa, String tipo) {
        try {
            // Connect with database
            MySQLConnector mCon = new MySQLConnector();
            Connection con = mCon.connect();
            
            // List of Buses
            ArrayList<Multa> multaList = new ArrayList<>();
            
            ResultSet rs;
            PreparedStatement stm;
            /*
            DEFAULT - Busca todos os possíveis
            ID - Busca todas as multas com idMulta == ?
            IDAUTOMOVEL - Busca todas as multas com idAutomovel == ?
            */
            switch(tipo) {
                case "ALLMULTA":
                    stm = con.prepareStatement(
                              "SELECT M.* FROM Multa M "
                                      + "INNER JOIN Pessoa P ON M.idPessoa = P.idPessoa "
                                      + "WHERE P.cpf LIKE ? "
                            + "UNION SELECT M.* FROM Multa M "
                                      + "INNER JOIN Carteira C ON M.idCarteira = C.idCarteira "
                                      + "WHERE C.nRegistro LIKE ? "
                            + "UNION "
                            + "SELECT M.* FROM Multa M "
                                      + "INNER JOIN Automovel A ON M.idAutomovel = A.idAutomovel "
                                      + "WHERE A.renavam LIKE ?");
                    stm.setInt(1, multa.getIdMulta());
                    
                    stm.setString(1, '%' + multa.getPessoa().getCpf() + '%');
                    stm.setString(2, '%' + multa.getCarteira().getnRegistro() + '%');
                    stm.setString(3, '%' + multa.getAutomovel().getRenavam() + '%');
                    break;
                case "ID":
                    stm = con.prepareStatement("SELECT * FROM multa WHERE idMulta = ?");
                    stm.setInt(1, multa.getIdMulta());
                    break;
                case "IDAUTOMOVEL":
                    stm = con.prepareStatement("SELECT * FROM multa WHERE idAutomovel = ?");
                    stm.setInt(1, multa.getAutomovel().getIdAutomovel());
                    break;
                case "IDAUTUACAO":
                    stm = con.prepareStatement("SELECT * FROM multa WHERE idAutuacao = ?");
                    stm.setInt(1, multa.getAutuacao().getIdAutuacao());
                    break;
                case "IDPESSOA":
                    stm = con.prepareStatement("SELECT * FROM multa WHERE idPessoa = ? OR idPessoa = ?");
                    stm.setInt(1, multa.getAutomovel().getProprietario().getIdPessoa());
                    stm.setInt(2, multa.getPessoa().getIdPessoa());
                    break;
                case "IDCARTEIRA":
                    stm = con.prepareStatement("SELECT * FROM multa WHERE idCarteira = ?");
                    stm.setInt(1, multa.getCarteira().getIdCarteira());
                    break;
                default: 
                    stm = con.prepareStatement("SELECT * FROM multa");
                    break;
            }
            
            rs = stm.executeQuery();
            
            while (rs.next()) {
                Multa multaVO = Util.criarMulta(rs);
                //System.out.println( multaVO.toString() );
                multaList.add(multaVO);
            }
            
            mCon.disconnect();
            return multaList;
        }
        catch(Exception e) {
            multa.setError(true);
            multa.setMessage("\tFalha Técnica\n\t" + e.getMessage());
            return null;
        }
    }

    public static void cadastrarMulta(Multa multa) {
        
        try {
            // Connect with database
            MySQLConnector mCon = new MySQLConnector();
            Connection con = mCon.connect();
            
            // SQL que vai ser executada
            // Depende se é carteira ou pessoa
            String query;
            if (multa.getPessoa() == null) 
            {
                query = (
                    "Insert into multa(dataEmissao, taxaAcrescimo, dataPagamento," 
                    + " idCarteira , idAutuacao, idAutomovel)"
                    + "values(?, ?, ?, ?, ?, ?)");  
            }
            else
                query = (
                    "Insert into multa(dataEmissao, taxaAcrescimo, dataPagamento," 
                    + " idPessoa, idAutuacao, idAutomovel )"
                    + "values(?, ?, ?, ?, ?, ?)");
            
            PreparedStatement stm;
            
            
            // Statement.RETURN_GENERATED_KEYS retorna os ID gerados pelo Statement no BD
            stm = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
         
            Timestamp dataEmissao = new Timestamp(multa.getDataEmissao().getTime());
            stm.setTimestamp(1, dataEmissao);
            
            stm.setFloat(2, multa.getTaxaAcrescimo());
            
            if(multa.getDataPagamento() != null) {
                Timestamp dataPagamento = new Timestamp(multa.getDataPagamento().getTime());
                stm.setTimestamp(3, dataPagamento);
            }
            else
                stm.setTimestamp(3, null);
            
            if(multa.getPessoa() != null)
                stm.setInt(4, multa.getPessoa().getIdPessoa());
            
            
            if(multa.getCarteira() != null)
                stm.setInt(4, multa.getCarteira().getIdCarteira());
            
            stm.setInt(5, multa.getAutuacao().getIdAutuacao());
            
            stm.setInt(6, multa.getAutomovel().getIdAutomovel());
           
            // Confere se alguma linha do BD foi modificada
            int status = stm.executeUpdate();
            
            if(status == 1) {
                multa.setError(false);
                multa.setMessage("Cadastrado com Sucesso!");
                // Reebendo o ID recentemente adicionado no BD
                ResultSet keys = stm.getGeneratedKeys();    
                keys.next();  
                
                int key = keys.getInt(1);
                //System.out.println(key);
                // Adicionando pro Objeto o devido ID
                multa.setIdMulta(key);
            }
            else {
                multa.setError(true);
                multa.setMessage("Falha ao Cadastrar!");
            }
            
            mCon.disconnect();
        }
        catch(Exception e) {
            multa.setError(true);
            multa.setMessage("\tFalha Técnica\n\t" + e.getMessage());
        }
    }

    public static void alterarMulta(Multa multa) {
        
        try {
            // Connect with database
            MySQLConnector mCon = new MySQLConnector();
            Connection con = mCon.connect();
            
            // SQL que vai ser executada
            // Depende se é carteira ou pessoa
            String query;
            if (multa.getPessoa() == null) 
            {
                query = (
                    "UPDATE multa SET "
                        + "dataEmissao = ?, "
                        + "taxaAcrescimo = ?, "
                        + "dataPagamento = ?, "
                        + "idCarteira = ?, "
                        + "idPessoa = null, "
                        + "idAutuacao = ?, "
                        + "idAutomovel = ? "
                        + "WHERE idMulta = ?");  
            }
            else
                query = (
                    "UPDATE multa SET "
                        + "dataEmissao = ?, "
                        + "taxaAcrescimo = ?, "
                        + "dataPagamento = ?, "
                        + "idPessoa = ?, "
                        + "idCarteira = null, "
                        + "idAutuacao = ?, "
                        + "idAutomovel = ? "
                        + "WHERE idMulta = ?");  
            
            PreparedStatement stm;
            stm = con.prepareStatement(query);
            
         
            Timestamp dataEmissao = new Timestamp(multa.getDataEmissao().getTime());
            stm.setTimestamp(1, dataEmissao);
            
            stm.setFloat(2, multa.getTaxaAcrescimo());
            
            if(multa.getDataPagamento() != null) {
                Timestamp dataPagamento = new Timestamp(multa.getDataPagamento().getTime());
                stm.setTimestamp(3, dataPagamento);
            }
            else
                stm.setTimestamp(3, null);
            
            if(multa.getPessoa() != null)
                stm.setInt(4, multa.getPessoa().getIdPessoa());
            
            
            if(multa.getCarteira() != null)
                stm.setInt(4, multa.getCarteira().getIdCarteira());
            
            stm.setInt(5, multa.getAutuacao().getIdAutuacao());
            
            stm.setInt(6, multa.getAutomovel().getIdAutomovel());
            
            stm.setInt(7, multa.getIdMulta());
           
            // Confere se alguma linha do BD foi modificada
            int status = stm.executeUpdate();
            
            if(status == 1) {
                multa.setError(false);
                multa.setMessage("Alterado com Sucesso!");
            }
            else {
                multa.setError(true);
                multa.setMessage("Falha ao Cadastrar!");
            }
            
            mCon.disconnect();
        }
        catch(Exception e) {
            multa.setError(true);
            multa.setMessage("\tFalha Técnica\n\t" + e.getMessage());
        }
        
    }
    
}
