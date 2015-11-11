/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Connector.MySQLConnector;
import Controller.Util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import valueObject.Carteira;

/**
 *
 * @author Lucas
 */
public class CarteiraModel {


    public static void cadastrarCarteira(Carteira carteira){
        try {
            // Connect with database
            MySQLConnector mCon = new MySQLConnector();
            Connection con = mCon.connect();
            
            // SQL que vai ser executada
            String query = (
                    "insert into carteira(dataVencimento, dataEmissao, nRegistro, "
                    + "permissao, tipo, idPessoa, status) "
                    + "values(?, ?, ?, ?, ?, ?, ?)");
            
            PreparedStatement stm;
            // Statement.RETURN_GENERATED_KEYS retorna os ID gerados pelo Statement no BD
            stm = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            
            Timestamp dataVencimento = new Timestamp(carteira.getDataVencimento().getTime());
            stm.setTimestamp(1, dataVencimento);
            Timestamp dataEmissao = new Timestamp(carteira.getDataEmissao().getTime());
            stm.setTimestamp(2, dataEmissao);
            
            stm.setString(3, carteira.getnRegistro());
            stm.setBoolean(4, carteira.isPermissao());
            stm.setString(5, carteira.getTipo());
            stm.setInt(6, carteira.getTitular().getIdPessoa());
            
            stm.setBoolean(7, carteira.isStatus());
            
            // Confere se alguma linha do BD foi modificada
            int status = stm.executeUpdate();
            
            if(status == 1) {
                carteira.setError(false);
                carteira.setMessage("Cadastrado com Sucesso!");
                // Reebendo o ID recentemente adicionado no BD
                ResultSet keys = stm.getGeneratedKeys();    
                keys.next();  
                
                int key = keys.getInt(1);
                //System.out.println(key);
                // Adicionando pro Objeto o devido ID
                carteira.setIdCarteira(key);
            }
            else {
                carteira.setError(true);
                carteira.setMessage("Falha ao Cadastrar!");
            }
            
            mCon.disconnect();
        }
        catch(Exception e) {
            carteira.setError(true);
            carteira.setMessage("\tFalha Técnica\n\t" + e.getMessage());
        }
    }


    public static ArrayList<Carteira> buscarCarteira(Carteira carteira, String tipo) {
        try {
            // Connect with database
            MySQLConnector mCon = new MySQLConnector();
            Connection con = mCon.connect();
            
            // List of Buses
            ArrayList<Carteira> carteiraList = new ArrayList<>();
            
            ResultSet rs;
            PreparedStatement stm;
            /*
            DEFAULT - Busca todos as possíveis
            STATUS - Busca todas as pessoas com o status == ? 
            */
            switch(tipo) {
                case "CARTEIRA_TITULAR_VALIDA":
                    stm  = con.prepareStatement("SELECT * FROM carteira WHERE idPessoa = ? AND status = TRUE");
                    stm.setInt(1, carteira.getTitular().getIdPessoa());
                    break;
                case "TITULAR":
                    stm  = con.prepareStatement("SELECT * FROM carteira WHERE idPessoa = ?");
                    stm.setInt(1, carteira.getTitular().getIdPessoa());
                    break;
                case "REGISTRO":
                    stm  = con.prepareStatement("SELECT * FROM carteira WHERE nRegistro LIKE ?");
                    stm.setString(1, '%' + carteira.getnRegistro() + '%');
                    break;
                case "NCARTEIRAS":
                    stm  = con.prepareStatement("SELECT * FROM carteira WHERE idPessoa = ?");
                    stm.setInt(1, carteira.getTitular().getIdPessoa());
                    break;
                case "UNIQUE_ID":
                    stm  = con.prepareStatement("SELECT * FROM Carteira WHERE idcarteira = ?");
                    stm.setInt(1, carteira.getIdCarteira());
                    break;
                case "STATUS":
                    stm  = con.prepareStatement("SELECT * FROM carteira WHERE status = ?");
                    stm.setBoolean(1, carteira.isStatus());
                    break;
                case "CARTEIRASATIVAS":
                    stm  = con.prepareStatement("SELECT * "
                            + "FROM Carteira AS c "
                            + "INNER JOIN Pessoa AS p ON c.idPessoa = p.idPessoa "
                            + "WHERE c.Status = 1 AND p.Status = 1 "
                            + "GROUP BY p.idPessoa");
                    break;
                default: 
                    stm  = con.prepareStatement("SELECT * FROM carteira");
                    break;
            }
            
            rs = stm.executeQuery();
                    
            while (rs.next()) {
                Carteira carteiraVO;
                if(tipo.equals("NCARTEIRAS"))
                    carteiraVO = new Carteira();
                else
                    carteiraVO = Util.criarCarteira(rs);
                
                carteiraList.add(carteiraVO); 
            }
            
            mCon.disconnect();
            return carteiraList;
        }
        catch(Exception e) {
            carteira.setError(true);
            carteira.setMessage("\tFalha Técnica\n\t" + e.getMessage());
            return null;
        }
    
    }

    public static void alterarCarteira(Carteira carteira) {
        try {
            // Connect with database
            MySQLConnector mCon = new MySQLConnector();
            Connection con = mCon.connect();
            
            // SQL que vai ser executada
            String query = (
                    "UPDATE carteira SET "
                    + "dataVencimento = ?, "
                    + "dataEmissao = ?, "
                    + "nRegistro = ?, "
                    + "permissao = ?, "
                    + "tipo = ?, "
                    + "idPessoa = ?, "
                    + "status = ? "
                    + "WHERE idCarteira = ?");
            
            PreparedStatement stm;
            stm = con.prepareStatement(query);
            
            Timestamp dataVencimento = new Timestamp(carteira.getDataVencimento().getTime());
            stm.setTimestamp(1, dataVencimento);
            Timestamp dataEmissao = new Timestamp(carteira.getDataEmissao().getTime());
            stm.setTimestamp(2, dataEmissao);
            
            stm.setString(3, carteira.getnRegistro());
            stm.setBoolean(4, carteira.isPermissao());
            stm.setString(5, carteira.getTipo());
            stm.setInt(6, carteira.getTitular().getIdPessoa());
            
            stm.setBoolean(7, carteira.isStatus());
            
            stm.setInt(8, carteira.getIdCarteira());
            // Confere se alguma linha do BD foi modificada
            int status = stm.executeUpdate();
            
            if(status == 1) {
                carteira.setError(false);
                carteira.setMessage("Alterado com Sucesso!");
            }
            else {
                carteira.setError(true);
                carteira.setMessage("Falha ao Cadastrar!");
            }
            
            mCon.disconnect();
        }
        catch(Exception e) {
            carteira.setError(true);
            carteira.setMessage("\tFalha Técnica\n\t" + e.getMessage());
        }
    }

    public static void excluirCarteira(Carteira carteira) {
        try {
            // Connect with database
            MySQLConnector mCon = new MySQLConnector();
            Connection con = mCon.connect();
            
            int multas_pendentes = Util.multasPendentesCount(carteira);
            if(multas_pendentes > 0) {
                carteira.setError(true);
                carteira.setMessage("Há multas pendentes neste veículo\n");
                return;
            }
            
            // SQL que vai ser executada
            // UPDATE automovel SET status = false WHERE idAutomovel = ?
            String query = 
                    "UPDATE carteira SET "
                            + "status = false "
                    + "WHERE idCarteira = ?";
            
            PreparedStatement stm;
            stm = con.prepareStatement(query);
            
            stm.setInt(1, carteira.getIdCarteira());
            
            // Confere se alguma linha do BD foi modificada
            int status = stm.executeUpdate();
            
            if(status == 1) {
                carteira.setError(false);
                carteira.setMessage("Excluído com Sucesso!");
            }
            else {
                carteira.setMessage("Falha ao Excluir!");
            }
            
            mCon.disconnect();
        }
        catch(Exception e) {
            carteira.setError(true);
            carteira.setMessage("\tFalha Técnica\n\t" + e.getMessage());
        }
    }
    
}
