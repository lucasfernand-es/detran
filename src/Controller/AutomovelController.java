/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.AutomovelModel;
import java.util.ArrayList;
import valueObject.Automovel;

/**
 *
 * @author cristhian
 */
public class AutomovelController {
    
    public static void cadastrarAutomovel(Automovel automovel) {
        boolean verifica = AutomovelController.verificarCampos(automovel);
        if(!verifica) {
            automovel.setError(true);
            // Algum dado informado � inv�lido
            return;
        }
        AutomovelModel.cadastrarAutomovel(automovel);
    }
    public static void alterarAutomovel(Automovel automovel) {
        boolean verifica = AutomovelController.verificarCampos(automovel);
        if(!verifica) {
            automovel.setError(true);
            // Algum dado informado � inv�lido
            return;
        }
        AutomovelModel.alterarAutomovel(automovel);
    }
    public static void excluirAutomovel(Automovel automovel) {
        AutomovelModel.excluirAutomovel(automovel);
    }
    
    private static boolean verificarCampos(Automovel automovel) {
        String mensagem = "";
        
        // renavam
        if(!automovel.getRenavam().matches("[0-9]{11}"))
            mensagem = mensagem.concat("Renavam deve conter 11 d�gitos\n");
        
        // ano
        if(automovel.getAno().equals(""))
            mensagem = mensagem.concat("Ano n�o pode estar vazio\n");
        else {
            try {
                int ano = Integer.decode( automovel.getAno() );
                if(ano < 1900 || ano > 2015) {
                    mensagem = mensagem.concat("Ano deve ser entre 1900 e 2015\n");
                }
            } catch (Exception e) {
                mensagem = mensagem.concat("Ano deve ser um inteiro v�lido\n");
            }
        }
        
        // marca
        if(automovel.getMarca().equals(""))
            mensagem = mensagem.concat("Marca n�o pode estar vazio\n");
        
        // modelo
        if(automovel.getModelo().equals(""))
            mensagem = mensagem.concat("Modelo n�o pode estar vazio\n");
        
        // cor
        if(automovel.getCor().equals(""))
            mensagem = mensagem.concat("Cor n�o pode estar vazio\n");
        
        // placa
        if(!automovel.getPlaca().substring(0, 3).matches("[A-Za-z]{3}")
                || !automovel.getPlaca().substring(4, 8).matches("[0-9]{4}"))
            mensagem = mensagem.concat("Placa deve conter 7 caracteres\n");
        
        // chassi
        if(!automovel.getChassi().matches("[0-9A-Za-z]{17}"))
            mensagem = mensagem.concat("Chassi deve conter 17 caracteres\n");
        
        // propriet�rio
        if(automovel.getProprietario() == null)
            mensagem = mensagem.concat("Propriet�rio n�o pode estar vazio\n");
        
        // A nova mensagem dentro do objeto ser� a mensagem atual 
        // com as novas informa��es
        // Verifica de erro para quando mensagem estiver vazia
        String mensagemAtual = ( automovel.getMessage() == null )? "": automovel.getMessage();
        String mensagemNova = mensagemAtual.concat(mensagem);
        automovel.setMessage(mensagemNova);
        
        // Caso alguma regra n�o tenha sido cumprida, h� erro e a mensagem n�o � vazia
        return mensagem.equals("");
    }
    
    // Objeto1 (da Classe) tem os crit�rios da busca, caso algum existe
    // Objeto2 (String) especifica o tipo da pesquisa
    public static ArrayList<Automovel>  buscarAutomovel(Automovel automovel, String tipo) {
        // Tratando pesquisa por Renavam
        if(tipo.equals("RENAVAM") && automovel.getRenavam().length() > 0) {
            boolean renavamValido = checaFormatoRenavam(automovel.getRenavam());
            // Se o que foi digitado na pesquisa n�o for v�lido, n�o � necess�rio fazer a busca
            if(!renavamValido)
                return null;
        }
        
        ArrayList<Automovel> newList = AutomovelModel.buscarAutomovel(automovel, tipo);
        
        // Se n�o houve nenhum erro na pesquisa, e ainda assim, a lista est� vazia
        if(!automovel.isError() && newList == null)
        {
            automovel.setError(true);
            automovel.setMessage("Nenhum poss�vel titular foi encontrado.");
            return null;
        }
        else
            return newList;
    }
    
    private static boolean checaFormatoRenavam(String renavam) {
        return renavam.matches("[0-9]+");
    }
    
}
