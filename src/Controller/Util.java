/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.sql.ResultSet;
import java.util.Date;
import valueObject.Pessoa;

/**
 *
 * @author Lucas
 */
public class Util {

    public static Pessoa criarPessoa(ResultSet rs) {
        Pessoa pessoa;
        try {
            // Criando objeto para receber os dados preenchidos na tela
            String nome = rs.getString("nome");
            String cpf = rs.getString("cpf");
            String rg = rs.getString("rg");
            String orgaoEmissor = rs.getString("orgaoEmissor");
            String rgEstado = rs.getString("rgEstado");
            Date dataNascimento = rs.getDate("dataNascimento");
            String logradouro = rs.getString("logradouro");
            String numeroLogradouro = rs.getString("numeroLogradouro");
            String complementoLogradouro = rs.getString("complementoLogradouro");
            String bairro = rs.getString("bairro");
            String cidade = rs.getString("cidade");
            String estado = rs.getString("estado");
            String cep = rs.getString("cep");
            String nomeMae = rs.getString("nomeMae");
            String nomePai = rs.getString("nomePai");
            boolean status = rs.getBoolean("status");
            int idPessoa = rs.getInt("idPessoa");
            
            pessoa = new Pessoa(nome, cpf, rg, orgaoEmissor, rgEstado, 
                    dataNascimento, logradouro,  numeroLogradouro, complementoLogradouro, 
                    bairro, cidade, estado, cep, nomeMae, nomePai, status, idPessoa);
            return pessoa;
        }
        catch(Exception e) {
            pessoa = new Pessoa();
            pessoa.setError(true);
            pessoa.setMessage(e.getMessage());
            return null;
        }
    }
    
}
