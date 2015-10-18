/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.AutomovelModel;
import Model.PessoaModel;
import static java.lang.String.format;
import java.util.ArrayList;
import valueObject.Automovel;
import valueObject.Pessoa;

/**
 *
 * @author cristhian
 */
public class AutomovelController {
    
    public static void cadastrarAutomovel(Automovel automovel) {
        boolean verifica = AutomovelController.verificarCampos(automovel);
        if(!verifica) {
            automovel.setError(true);
            // Algum dado informado é inválido
            return;
        }
        AutomovelModel.cadastrarAutomovel(automovel);
    }
    public static void alterarAutomovel(Automovel automovel) {
        boolean verifica = AutomovelController.verificarCampos(automovel);
        if(!verifica) {
            automovel.setError(true);
            // Algum dado informado é inválido
            return;
        }
        AutomovelModel.alterarAutomovel(automovel);
    }
    public static void excluirAutomovel(Automovel automovel) {
        AutomovelModel.excluirAutomovel(automovel);
    }
    
    private static boolean verificarCampos(Automovel automovel) {
        String mensagem = "";
        
        // vazios
        if(automovel.getRenavam().equals(""))
            mensagem = mensagem.concat("Renavam não pode estar vazio\n");
        if(automovel.getMarca().equals(""))
            mensagem = mensagem.concat("Marca não pode estar vazio\n");
        if(automovel.getModelo().equals(""))
            mensagem = mensagem.concat("Modelo não pode estar vazio\n");
        if(automovel.getCor().equals(""))
            mensagem = mensagem.concat("Cor não pode estar vazio\n");
        if(automovel.getPlaca().equals(""))
            mensagem = mensagem.concat("Placa não pode estar vazio\n");
        if(automovel.getChassi().equals(""))
            mensagem = mensagem.concat("Chassi não pode estar vazio\n");
        if(automovel.getAno().equals(""))
            mensagem = mensagem.concat("Ano não pode estar vazio\n");
        if(automovel.getProprietario() == null)
            mensagem = mensagem.concat("Proprietário não pode estar vazio\n");
        
        // tamanhos especiais
        if(automovel.getRenavam().length() != 11)
            mensagem = mensagem.concat("Renavam deve conter 11 dígitos\n");
        if(automovel.getPlaca().length() != 8)
            mensagem = mensagem.concat("Placa deve conter 7 caracteres\n");
        if(automovel.getChassi().length() != 17)
            mensagem = mensagem.concat("Chassi deve conter 17 caracteres\n");
        
        // casos especiais
        try {
            int ano = Integer.decode( automovel.getAno() );
            if(ano < 1900 || ano > 2015) {
                mensagem = mensagem.concat("Ano deve ser entre 1900 e 2015\n");
            }
        } catch (Exception e) {
            mensagem = mensagem.concat("Ano deve ser um inteiro válido\n");
        }
        
        // máscaras
        if(!automovel.getChassi().matches("[0-9A-Za-z]{17}")) {
            mensagem = mensagem.concat("Chassi deve conter apenas letras e dígitos\n");
        }
        
        // A nova mensagem dentro do objeto será a mensagem atual 
        // com as novas informações
        // Verifica de erro para quando mensagem estiver vazia
        String mensagemAtual = ( automovel.getMessage() == null )? "": automovel.getMessage();
        String mensagemNova = mensagemAtual.concat(mensagem);
        automovel.setMessage(mensagemNova);
        
        // Caso alguma regra não tenha sido cumprida, há erro e a mensagem não é vazia
        return mensagem.equals("");
    }
    
    // Objeto1 (da Classe) tem os critérios da busca, caso algum existe
    // Objeto2 (String) especifica o tipo da pesquisa
    public static ArrayList<Automovel>  buscarAutomovel(Automovel automovel, String tipo) {
        // Tratando pesquisa por Renavam
        if(tipo.equals("RENAVAM")) {
            boolean renavamValido = checaFormatoRenavam(automovel.getRenavam());
            // Se o que foi digitado na pesquisa não for válido, não é necessário fazer a busca
            if(!renavamValido)
                return null;
        }
        
        ArrayList<Automovel> newList = AutomovelModel.buscarAutomovel(automovel, tipo);
        
        // Se não houve nenhum erro na pesquisa, e ainda assim, a lista está vazia
        if(!automovel.isError() && newList == null)
        {
            automovel.setError(true);
            automovel.setMessage("Nenhum possível titular foi encontrado.");
            return null;
        }
        else
            return newList;
    }
    
    private static boolean checaFormatoRenavam(String renavam) {
        return renavam.matches("[0-9]+");
    }
    
}
