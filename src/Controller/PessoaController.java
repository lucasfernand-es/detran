/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Addons.ValidaCPF;
import Model.PessoaModel;
import java.util.ArrayList;
import valueObject.Pessoa;

/**
 *
 * @author Lucas
 */
public class PessoaController {
    
    
    
    // Função verifica os dados do objeto. Se sim, retorna true, se não retorna false
    // Altera a mensagem do objeto para especificar os erros
    private static boolean verificarCampos(Pessoa pessoa) {
        
        String mensagem = "";
        if(pessoa.getNome().equals(""))
            mensagem = mensagem.concat("Nome não pode estar vazio\n");
        //if(pessoa.getRg().equals("") || pessoa.getRg().equals("12.345.678-X"))
        if(pessoa.getRg().equals(""))
            mensagem = mensagem.concat("RG não pode estar vazio\n");
        if(pessoa.getCpf().equals(""))
            mensagem = mensagem.concat("CPF não pode estar vazio\n");
        if(pessoa.getOrgaoEmissor().equals(""))
            mensagem = mensagem.concat("Orgão Emissor não pode estar vazio\n");
        if(pessoa.getRgEstado().equals(""))
            mensagem = mensagem.concat("Estado Emissor não pode estar vazio\n");
        if(pessoa.getDataNascimento() == null)
            mensagem = mensagem.concat("Data de Nascimento não pode estar vazio\n");
        
        if(pessoa.getLogradouro().equals(""))
            mensagem = mensagem.concat("Logradouro não pode estar vazio\n");
        if(pessoa.getNumeroLogradouro().equals(""))
            mensagem = mensagem.concat("Número do Logradouro não pode estar vazio\n");
        /*
        if(pessoa.getComplementoLogradouro().equals(""))
            mensagem = mensagem.concat("Complemento do Logradouro não pode estar vazio\n");
        */
        if(pessoa.getBairro().equals(""))
            mensagem = mensagem.concat("Bairro não pode estar vazio\n");
        if(pessoa.getCidade().equals(""))
            mensagem = mensagem.concat("Cidade não pode estar vazio\n");
        if(pessoa.getEstado().equals(""))
            mensagem = mensagem.concat("Estado não pode estar vazio\n");
        if(pessoa.getCep().equals("") || pessoa.getCep().equals("12.345-678"))
            mensagem = mensagem.concat("CEP não pode estar vazio\n");
        
        
        String newCpf;
        newCpf = pessoa.getCpf().replaceAll("(\\.|-)", "");
        //System.out.println("newCpf " + newCpf);
        boolean validaCPF = ValidaCPF.isCPF(newCpf);
        if(!validaCPF)
            mensagem = mensagem.concat("CPF informado é inválido\n");
        
        // A nova mensagem dentro do objeto será a mensagem atual 
        // com as novas informações
        // Verifica de erro para quando mensagem estiver vazia
        String mensagemAtual = ( pessoa.getMessage() == null )? "": pessoa.getMessage();
        String mensagemNova = mensagemAtual.concat(mensagem);
        pessoa.setMessage(mensagemNova);
        
        
        // Caso alguma regra não tenha sido cumprida, há erro e a mensagem não é vazia
        return mensagem.equals("");
    }

    public static void cadastrarPessoa(Pessoa pessoa) {
        
        boolean verifica = PessoaController.verificarCampos(pessoa);
        
        if(!verifica) {
            pessoa.setError(true);
            // Algum dado informado é inválido
            return;
        }
        
        System.out.println("Mandando pro Model");
        pessoa.setError(true);
        PessoaModel.cadastrarPessoa(pessoa);
        
    }
    
    // Objeto1 (da Classe) tem os critérios da busca, caso algum existe
    // Objeto2 (String) especifica o tipo da pesquisa
    public static ArrayList<Pessoa>  buscarPessoa(Pessoa pessoa, String tipo) {
        
        ArrayList<Pessoa> newList = PessoaModel.buscarPessoa(pessoa, tipo);
        
        // Se não houve nenhum erro na pesquisa, e ainda assim, a lista está vazia
        if(!pessoa.isError() && newList == null)
        {
            pessoa.setError(true);
            pessoa.setMessage("Nenhum possível titular foi encontrado.");
            return null;
        }
        else
            return newList;
    }

    
    
}
