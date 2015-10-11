/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Connector.MySQLConnector;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import valueObject.Pessoa;

/**
 *
 * @author Lucas
 */
public class PessoaModel {
    

    public static void cadastrarPessoa(Pessoa pessoa) {
        try {
            System.out.println("chegou no model");
            // Connect with database
            Connection con = MySQLConnector.connect();
            
            PreparedStatement stm = con.prepareStatement(
                    "insert into pessoa("
                            + "nome, cpf, rg, orgaoEmissor, rgEstado, "
                            + "dataNascimento, logradouro, numeroLogradouro, "
                            + "complementoLogradouro, bairro, cidade, estado, cep, "
                            + "nomeMae, nomePai, status)"
                    + "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            
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
            
            int status = stm.executeUpdate();
            
            if(status == 1) {
                pessoa.setError(false);
                pessoa.setMessage("Cadastrado com Sucesso!");
            }
            else {
                pessoa.setMessage("Falha ao Cadastrar!");
            }
            
            MySQLConnector.disconnect();
        }
        catch(Exception e) {
                pessoa.setError(true);
                pessoa.setMessage("\tFalha Técnica\n\t" + e.getMessage());
        }
    }
    
}
