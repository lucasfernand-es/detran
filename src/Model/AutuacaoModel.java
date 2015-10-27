package Model;

import Connector.MySQLConnector;
import Controller.Util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import valueObject.Automovel;
import valueObject.Autuacao;

/**
 *
 * @author cristhian
 */
public class AutuacaoModel {
    
    public static ArrayList<Autuacao>  buscarAutuacao(Autuacao autuacao, String tipo) {
        try {
            // Connect with database
            MySQLConnector mCon = new MySQLConnector();
            Connection con = mCon.connect();
            
            // List of Buses
            ArrayList<Autuacao> autuacaoList = new ArrayList<>();
            
            ResultSet rs;
            PreparedStatement stm;
            /*
            DEFAULT - Busca todas as possíveis
            ID - Busca todas as autuacoes com o idAutuacao = ?
            TITULO - Busca todas as autuacoes com titulo parecido com ?
            */
            switch(tipo) {
                case "ID":
                    stm = con.prepareStatement("SELECT * FROM autuacao WHERE idAutuacao = ?");
                    stm.setInt(1, autuacao.getIdAutuacao());
                    break;
                case "TITULO":
                    stm = con.prepareStatement("SELECT * FROM autuacao WHERE titulo LIKE ?");
                    stm.setString(1, '%' + autuacao.getTitulo() + '%');
                    break;
                default: 
                    stm = con.prepareStatement("SELECT * FROM autuacao");
                    break;
            }
            
            rs = stm.executeQuery();
            
            while (rs.next()) {
                Autuacao autuacaoVO = Util.criarAutuacao(rs);
                autuacaoList.add(autuacaoVO);
            }
            
            mCon.disconnect();
            return autuacaoList;
        }
        catch(Exception e) {
            autuacao.setError(true);
            autuacao.setMessage("\tFalha Técnica\n\t" + e.getMessage());
            return null;
        }
    }
    public static void cadastrarAutuacao(Autuacao autuacao){
        try {
            // Connect with database
            MySQLConnector mCon = new MySQLConnector();
            Connection con = mCon.connect();
            
            // SQL que vai ser executada
            // INSERT INTO autuacao (titulo, descricao, pontuacao, 
            //                       custo, prazo
            //               values (?, ?, ?, ?, ?)
            String query = (
                    "insert into autuacao ("
                            + "titulo, descricao, pontuacao, "
                            + "custo, prazo) "
                    + "values(?, ?, ?, ?, ?)");
            
            PreparedStatement stm;
            // Statement.RETURN_GENERATED_KEYS retorna os ID gerados pelo Statement no BD
            stm = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            
            stm.setString(1, autuacao.getTitulo());
            stm.setString(2, autuacao.getDescricao());
            stm.setInt(3, autuacao.getPontuacao());
            stm.setDouble(4, autuacao.getCusto());
            stm.setInt(5, autuacao.getPrazo());
            
            // Confere se alguma linha do BD foi modificada
            int status = stm.executeUpdate();
            
            if(status == 1) {
                autuacao.setError(false);
                autuacao.setMessage("Cadastrado com Sucesso!");
                // Reebendo o ID recentemente adicionado no BD
                ResultSet keys = stm.getGeneratedKeys();    
                keys.next();  
                
                int key = keys.getInt(1);
                System.out.println("key: " + key);
                // Adicionando pro Objeto o devido ID
                autuacao.setIdAutuacao(key);
            }
            else {
                autuacao.setMessage("Falha ao Cadastrar!");
            }
            
            mCon.disconnect();
        }
        catch(Exception e) {
            autuacao.setError(true);
            autuacao.setMessage("\tFalha Técnica\n\t" + e.getMessage());
        }
    }
    
}
