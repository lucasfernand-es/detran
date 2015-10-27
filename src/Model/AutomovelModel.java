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
import java.util.ArrayList;
import valueObject.Automovel;

/**
 *
 * @author cristhian
 */
public class AutomovelModel {
    
    public static void cadastrarAutomovel(Automovel automovel){
        try {
            // Connect with database
            MySQLConnector mCon = new MySQLConnector();
            Connection con = mCon.connect();
            
            // SQL que vai ser executada
            // INSERT INTO automovel (renavam, marca, modelo, cor, 
            //                        placa, chassi, idPessoa, ano, 
            //                        status) 
            //                values (?, ?, ?, ?, ?, ?, ?, ?, ?)
            String query = (
                    "insert into automovel("
                            + "renavam, marca, modelo, "
                            + "cor, placa, chassi, "
                            + "idPessoa, ano, status) "
                    + "values(?, ?, ?, ?, ?, ?, ?, ?, ?)");
            
            PreparedStatement stm;
            // Statement.RETURN_GENERATED_KEYS retorna os ID gerados pelo Statement no BD
            stm = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            
            stm.setString(1, automovel.getRenavam());
            stm.setString(2, automovel.getMarca());
            stm.setString(3, automovel.getModelo());
            stm.setString(4, automovel.getCor());
            stm.setString(5, automovel.getPlaca());
            stm.setString(6, automovel.getChassi());
            stm.setInt(7, automovel.getProprietario().getIdPessoa());
            stm.setString(8, automovel.getAno());
            stm.setBoolean(9, automovel.isStatus());
            
            // Confere se alguma linha do BD foi modificada
            int status = stm.executeUpdate();
            
            if(status == 1) {
                automovel.setError(false);
                automovel.setMessage("Cadastrado com Sucesso!");
                // Reebendo o ID recentemente adicionado no BD
                ResultSet keys = stm.getGeneratedKeys();    
                keys.next();  
                
                int key = keys.getInt(1);
                // Adicionando pro Objeto o devido ID
                automovel.setIdAutomovel(key);
            }
            else {
                automovel.setMessage("Falha ao Cadastrar!");
            }
            
            mCon.disconnect();
        }
        catch(Exception e) {
            automovel.setError(true);
            automovel.setMessage("\tFalha Técnica\n\t" + e.getMessage());
        }
    }
    public static void alterarAutomovel(Automovel automovel){
        try {
            // Connect with database
            MySQLConnector mCon = new MySQLConnector();
            Connection con = mCon.connect();
            
            // SQL que vai ser executada
            // UPDATE automovel SET modelo = focuss WHERE idAutomovel = ?;
            String query = 
                    "UPDATE automovel SET "
                            + "renavam = ?, "
                            + "marca = ?, "
                            + "modelo = ?, "
                            + "cor = ?, "
                            + "placa = ?, "
                            + "chassi = ?, "
                            + "idPessoa = ?, "
                            + "ano = ?, "
                            + "status = ? "
                    + "WHERE idAutomovel = ?";
            
            PreparedStatement stm;
            stm = con.prepareStatement(query);
            
            stm.setString(1, automovel.getRenavam());
            stm.setString(2, automovel.getMarca());
            stm.setString(3, automovel.getModelo());
            stm.setString(4, automovel.getCor());
            stm.setString(5, automovel.getPlaca());
            stm.setString(6, automovel.getChassi());
            stm.setInt(7, automovel.getProprietario().getIdPessoa());
            stm.setString(8, automovel.getAno());
            stm.setBoolean(9, automovel.isStatus());
            stm.setInt(10, automovel.getIdAutomovel());
            
            // Confere se alguma linha do BD foi modificada
            int status = stm.executeUpdate();
            
            if(status == 1) {
                automovel.setError(false);
                automovel.setMessage("Alterado com Sucesso!");
            }
            else {
                automovel.setMessage("Falha ao Alterar!");
            }
            
            mCon.disconnect();
        }
        catch(Exception e) {
            automovel.setError(true);
            automovel.setMessage("\tFalha Técnica\n\t" + e.getMessage());
        }
    }
    public static void excluirAutomovel(Automovel automovel){
        try {
            // Connect with database
            MySQLConnector mCon = new MySQLConnector();
            Connection con = mCon.connect();
            
            int multas_pendentes = Util.multasPendentesCount(automovel.getIdAutomovel());
            if(multas_pendentes > 0) {
                automovel.setError(true);
                automovel.setMessage("Há multas pendentes neste veículo\n");
                return;
            }
            
            // SQL que vai ser executada
            // UPDATE automovel SET status = false WHERE idAutomovel = ?
            String query = 
                    "UPDATE automovel SET "
                            + "status = false "
                    + "WHERE idAutomovel = ?";
            
            PreparedStatement stm;
            stm = con.prepareStatement(query);
            
            stm.setInt(1, automovel.getIdAutomovel());
            
            // Confere se alguma linha do BD foi modificada
            int status = stm.executeUpdate();
            
            if(status == 1) {
                automovel.setError(false);
                automovel.setMessage("Excluído com Sucesso!");
            }
            else {
                automovel.setMessage("Falha ao Excluir!");
            }
            
            mCon.disconnect();
        }
        catch(Exception e) {
            automovel.setError(true);
            automovel.setMessage("\tFalha Técnica\n\t" + e.getMessage());
        }
    }
    
    public static int  buscarNAutomovel(Automovel automovel, String tipo) {
    
            return 0;
    }
    
    public static ArrayList<Automovel>  buscarAutomovel(Automovel automovel, String tipo) {
        try {
            // Connect with database
            MySQLConnector mCon = new MySQLConnector();
            Connection con = mCon.connect();
            
            // List of Buses
            ArrayList<Automovel> automovelList = new ArrayList<>();
            
            ResultSet rs;
            PreparedStatement stm;
            /*
            DEFAULT - Busca todos os possíveis
            STATUS - Busca todas os automoveis com o status == ? 
            RENAVAM - Busca todos os automoveis com o renavam LIKE ?
            ID - Busca todos os automoveis com o idAutomovel == ?
            */
            switch(tipo) {
                case "RENAVAM":
                    stm = con.prepareStatement("SELECT * FROM automovel WHERE renavam LIKE ? AND status = true");
                    stm.setString(1,'%' + automovel.getRenavam() + '%');
                    break;
                case "STATUS":
                    stm = con.prepareStatement("SELECT * FROM automovel WHERE status = ?");
                    stm.setBoolean(1, automovel.isStatus());
                    break;
                case "ID":
                    stm = con.prepareStatement("SELECT * FROM automovel WHERE idAutomovel = ? AND status = true");
                    stm.setInt(1, automovel.getIdAutomovel());
                    break;
                case "PROPRIETARIO":
                    stm = con.prepareStatement("SELECT * FROM automovel WHERE idPessoa = ? AND status = true");
                    stm.setInt(1, automovel.getProprietario().getIdPessoa());
                    break;
                case "NAUTOMOVEIS":
                    stm = con.prepareStatement("SELECT * FROM automovel WHERE idPessoa = ? AND status = true");
                    stm.setInt(1, automovel.getProprietario().getIdPessoa());
                    break;
                default: 
                    stm = con.prepareStatement("SELECT * FROM automovel WHERE status = true");
                    break;
            }
            
            rs = stm.executeQuery();
            
            while (rs.next()) {
                Automovel automovelVO;
                if(tipo.equals("NAUTOMOVEIS"))
                    automovelVO = new Automovel();
                else
                    automovelVO = Util.criarAutomovel(rs);
                automovelList.add(automovelVO);
            }
            
            mCon.disconnect();
            return automovelList;
        }
        catch(Exception e) {
            automovel.setError(true);
            automovel.setMessage("\tFalha Técnica\n\t" + e.getMessage());
            return null;
        }
    }
    
    
}
