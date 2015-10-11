/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Addons.ValidaCPF;
import Model.PessoaModel;
import valueObject.Pessoa;

/**
 *
 * @author Lucas
 */
public class PessoaController {
    
    
    
    // Fun��o verifica os dados do objeto. Se sim, retorna true, se n�o retorna false
    // Altera a mensagem do objeto para especificar os erros
    private static boolean verificarCampos(Pessoa pessoa) {
        
        String mensagem = "";
        if(pessoa.getNome().equals(""))
            mensagem = mensagem.concat("Nome n�o pode estar vazio\n");
        //if(pessoa.getRg().equals("") || pessoa.getRg().equals("12.345.678-X"))
        if(pessoa.getRg().equals(""))
            mensagem = mensagem.concat("RG n�o pode estar vazio\n");
        if(pessoa.getCpf().equals(""))
            mensagem = mensagem.concat("CPF n�o pode estar vazio\n");
        if(pessoa.getOrgaoEmissor().equals(""))
            mensagem = mensagem.concat("Org�o Emissor n�o pode estar vazio\n");
        if(pessoa.getRgEstado().equals(""))
            mensagem = mensagem.concat("Estado Emissor n�o pode estar vazio\n");
        if(pessoa.getDataNascimento() == null)
            mensagem = mensagem.concat("Data de Nascimento n�o pode estar vazio\n");
        
        if(pessoa.getLogradouro().equals(""))
            mensagem = mensagem.concat("Logradouro n�o pode estar vazio\n");
        if(pessoa.getNumeroLogradouro().equals(""))
            mensagem = mensagem.concat("N�mero do Logradouro n�o pode estar vazio\n");
        /*
        if(pessoa.getComplementoLogradouro().equals(""))
            mensagem = mensagem.concat("Complemento do Logradouro n�o pode estar vazio\n");
        */
        if(pessoa.getBairro().equals(""))
            mensagem = mensagem.concat("Bairro n�o pode estar vazio\n");
        if(pessoa.getCidade().equals(""))
            mensagem = mensagem.concat("Cidade n�o pode estar vazio\n");
        if(pessoa.getEstado().equals(""))
            mensagem = mensagem.concat("Estado n�o pode estar vazio\n");
        if(pessoa.getCep().equals("") || pessoa.getCep().equals("12.345-678"))
            mensagem = mensagem.concat("CEP n�o pode estar vazio\n");
        
        
        String newCpf;
        newCpf = pessoa.getCpf().replaceAll("(\\.|-)", "");
        //System.out.println("newCpf " + newCpf);
        boolean validaCPF = ValidaCPF.isCPF(newCpf);
        if(!validaCPF)
            mensagem = mensagem.concat("CPF informado � inv�lido\n");
        
        // A nova mensagem dentro do objeto ser� a mensagem atual 
        // com as novas informa��es
        // Verifica de erro para quando mensagem estiver vazia
        String mensagemAtual = ( pessoa.getMessage() == null )? "": pessoa.getMessage();
        String mensagemNova = mensagemAtual.concat(mensagem);
        pessoa.setMessage(mensagemNova);
        
        
        // Caso alguma regra n�o tenha sido cumprida, h� erro e a mensagem n�o � vazia
        return mensagem.equals("");
    }

    public static void cadastrarPessoa(Pessoa pessoa) {
        
        boolean verifica = PessoaController.verificarCampos(pessoa);
        
        if(!verifica) {
            pessoa.setError(true);
            // Algum dado informado � inv�lido
            return;
        }
        
        System.out.println("Mandando pro Model");
        pessoa.setError(true);
        PessoaModel.cadastrarPessoa(pessoa);
        
    }

    
    
}
