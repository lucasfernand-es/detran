/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Addons.ValidaCPF;
import Model.PessoaModel;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.text.MaskFormatter;
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
        PessoaModel.cadastrarPessoa(pessoa);
        
    }
    
    //www.desenvolvimentoweb.blog.br/2013/06/java-aplicando-mascara-cep-cnpj-cpf-com-maskformatter/#sthash.whaVm0ng.dpuf
    
    private static String format(String pattern, Object value) { 
        MaskFormatter mask; 
        try { 
            mask = new MaskFormatter(pattern); 
            mask.setValueContainsLiteralCharacters(false); 
            return mask.valueToString(value); 
        } catch (ParseException e) { 
            return "ERROR";
        } 
    }
    
    // Trata o texto digitado e aplica a máscara para ser usada na pesquisa no banco
    private static boolean mascaraCPF(Pessoa pessoa) {
        String pattern = "###.###.###-##";
        String cpf = pessoa.getCpf();
        // Retirar pontos ou traços
        cpf = cpf.replace("-", "");
        cpf = cpf.replace(".", "");
        
        // Caso a pesquisa por CPF for 'vazia', pesquisa todos
        if(cpf.equals("")) 
            return true;
        // Se o CPF não contém nenhum número ou não estar vazio
        // Se o CPF tiver mais de 11 caracteres
        else if((!cpf.matches("[0-9]+")) || (cpf.length() > 11) ) 
            return false;
        
        if(cpf.length() < 11)
        {
            //Adicionando 0's para fazer a função Pattern dar certo
            for (int i = cpf.length(); i <= 11; i++)
                cpf = cpf.concat("0");
        }
        
        
        String newCPF = format(pattern, cpf);
        //System.out.println(newCPF + " com mask \t" + cpf + " sem mask");
        if(newCPF.equals("ERROR"))
            return false;
        
        // Retirando caracteres 'a mais'
        // Para cada três caracteres no CPF, um a mais é adicionado. (originCPF.length()/3)
        // Se o número digitado pelo usuário tiver menos de 11 dígitos, tem que retirar alguns
        // CPF digitado originalmente está no objeto Pessoa
        String originCPF = pessoa.getCpf();
        int lenghtOCPF = originCPF.length();
        if(lenghtOCPF < 11)
        {
            int beginIndex = 0;
            //System.out.println(originCPF.length()/3);
            int endIndex = lenghtOCPF + (lenghtOCPF/3);
            //System.out.println("com "+ newCPF.length()+ " j");
            newCPF = newCPF.substring(beginIndex, endIndex);
            //System.out.println("olha o new cpf denovoooo-" + newCPF + "-tamanho velho " + originCPF.length() + "  ou " + lenghtOCPF);
        }
        
        pessoa.setCpf(newCPF);
            
        return true;
    }
    
    // Objeto1 (da Classe) tem os critérios da busca, caso algum existe
    // Objeto2 (String) especifica o tipo da pesquisa
    public static ArrayList<Pessoa>  buscarPessoa(Pessoa pessoa, String tipo) {
        // Tratando pesquisa por CPF
        // No banco está armazenado com Máscara
        if(tipo.equals("CPF")) {
            boolean cpfValido  = mascaraCPF(pessoa);
            // Se o que foi digitado na pesquisa não for válido, não é necessário fazer a busca
            if(!cpfValido)
                return null;
        }
        
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
    
    public static Pessoa  buscarPessoaID(Pessoa pessoa) {
        
        ArrayList<Pessoa> newList = buscarPessoa(pessoa, "UNIQUE_ID");
        
        if(newList == null) 
            return null;
        else {
            // Seleciona o primeiro item da Array

            Pessoa newPessoa;
            newPessoa = (Pessoa) newList.get(0);
            return newPessoa;
        }
    }

    public static void alterarPessoa(Pessoa pessoa) {
        boolean verifica = PessoaController.verificarCampos(pessoa);
        
        if(!verifica) {
            pessoa.setError(true);
            // Algum dado informado é inválido
            return;
        }
        PessoaModel.alterarPessoa(pessoa);
    }

    public static void excluirPessoa(Pessoa pessoa) {
        PessoaModel.excluirPessoa(pessoa);
    }
    
    // Tes
}
