/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Connector.MySQLConnector;
import Controller.Util;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import valueObject.Pessoa;

/**
 *
 * @author Lucas
 */
public class PessoaModel {
    

    public static void cadastrarPessoa(Pessoa pessoa){
        try {
            // Connect with database
            MySQLConnector mCon = new MySQLConnector();
            Connection con = mCon.connect();
            
            // SQL que vai ser executada
            String query = (
                    "insert into pessoa("
                            + "nome, cpf, rg, orgaoEmissor, rgEstado, "
                            + "dataNascimento, logradouro, numeroLogradouro, "
                            + "complementoLogradouro, bairro, cidade, estado, cep, "
                            + "nomeMae, nomePai, status)"
                    + "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            
            PreparedStatement stm;
            // Statement.RETURN_GENERATED_KEYS retorna os ID gerados pelo Statement no BD
            stm = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            
            stm.setString(1, pessoa.getNome());
            stm.setString(2, pessoa.getCpf());
            stm.setString(3, pessoa.getRg());
            stm.setString(4, pessoa.getOrgaoEmissor());
            stm.setString(5, pessoa.getRgEstado());
            
            Timestamp dataNascimento = new Timestamp(pessoa.getDataNascimento().getTime());
            stm.setTimestamp(6, dataNascimento);
            
            stm.setString(7, pessoa.getLogradouro());
            stm.setString(8, pessoa.getNumeroLogradouro());
            stm.setString(9, pessoa.getComplementoLogradouro());
            stm.setString(10, pessoa.getBairro());
            stm.setString(11, pessoa.getCidade());
            stm.setString(12, pessoa.getEstado());
            stm.setString(13, pessoa.getCep());
            
            stm.setString(14, pessoa.getNomeMae());
            stm.setString(15, pessoa.getNomePai());
            
            stm.setBoolean(16, pessoa.isStatus());
            
            // Confere se alguma linha do BD foi modificada
            int status = stm.executeUpdate();
            
            if(status == 1) {
                pessoa.setError(false);
                pessoa.setMessage("Cadastrado com Sucesso!");
                // Reebendo o ID recentemente adicionado no BD
                ResultSet keys = stm.getGeneratedKeys();    
                keys.next();  
                
                int key = keys.getInt(1);
                //System.out.println(key);
                // Adicionando pro Objeto o devido ID
                pessoa.setIdPessoa(key);
            }
            else {
                pessoa.setMessage("Falha ao Cadastrar!");
            }
            
            mCon.disconnect();
        }
        catch(Exception e) {
            pessoa.setError(true);
            pessoa.setMessage("\tFalha Técnica\n\t" + e.getMessage());
        }
    }
    
    public static ArrayList<Pessoa>  buscarPessoa(Pessoa pessoa, String tipo) {

        try {
            // Connect with database
            MySQLConnector mCon = new MySQLConnector();
            Connection con = mCon.connect();
            
            // List of Buses
            ArrayList<Pessoa> pessoaList = new ArrayList<>();
            
            ResultSet rs;
            PreparedStatement stm;
            /*
            DEFAULT - Busca todos as possíveis
            STATUS - Busca todas as pessoas com o status == ? 
            */
            switch(tipo) {
                case "STATUS":
                    stm  = con.prepareStatement("SELECT * FROM pessoa WHERE status = ?");
                    stm.setBoolean(1, pessoa.isStatus());
                    break;
                default: 
                    stm  = con.prepareStatement("SELECT * FROM pessoa");
                    break;
            }
            
            rs = stm.executeQuery();
                    
            while (rs.next()) {
                Pessoa pessoaVO = Util.criarPessoa(rs);
                pessoaList.add(pessoaVO);
            }
            
            mCon.disconnect();
            return pessoaList;
        }
        catch(Exception e) {
            pessoa.setError(true);
            pessoa.setMessage("\tFalha Técnica\n\t" + e.getMessage());
            return null;
        }
    }
}

/*
Assim Funciona
PreparedStatement stm = con.prepareStatement(
                    "insert into pessoa("
                            + "nome, cpf, rg, orgaoEmissor, rgEstado, "
                            + "dataNascimento, logradouro, numeroLogradouro, "
                            + "complementoLogradouro, bairro, cidade, estado, cep, "
                            + "nomeMae, nomePai, status)"
                    + "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            
          
            
            int status;
            status = stm.executeUpdate();
*/