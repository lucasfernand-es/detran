/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.CarteiraModel;
import java.util.ArrayList;
import valueObject.Carteira;

/**
 *
 * @author Lucas
 */
public class CarteiraController {
    
    // Fun��o verifica os dados do objeto. Se sim, retorna true, se n�o retorna false
    // Altera a mensagem do objeto para especificar os erros
    private static boolean verificarCampos(Carteira carteira) {
        String mensagem = "";
        
        // Verificar campos nulos
        if(carteira.getDataVencimento() == null)
            mensagem = mensagem.concat("Data de Vencimento n�o pode estar vazio\n");
        if(carteira.getDataEmissao() == null)
            mensagem = mensagem.concat("Data de Emiss�o n�o pode estar vazio\n");
        if(carteira.getnRegistro().equals(""))
            mensagem = mensagem.concat("N�mero de Registro n�o pode estar vazio\n");
        if(carteira.getTitular() == null)
            mensagem = mensagem.concat("Titular n�o pode estar vazio\n");
        
        // Comparar Data de Vencimento com Data de Emiss�o
        // Se as duas n�o est�o vazias e a data de vencimento for menor que a data de Emiss�o
        if( (carteira.getDataVencimento() != null) && (carteira.getDataEmissao() != null)
                && (carteira.getDataVencimento().before( carteira.getDataEmissao() ) ))
            mensagem = mensagem.concat("Data de Vencimento n�o pode ser menor que Data de Emiss�o\n");
        
        // Verificar se Titular possui alguma carteira, caso o status seja TRUE
        if( carteira.isStatus() ){
            boolean isCarteira = CarteiraController.verificarCarteiraTitularValida(carteira);
            if(isCarteira)
                mensagem = mensagem.concat("N�o � poss�vel haver duas carteiras v�lidas para o mesmo titular\n");
        }
        
        // A nova mensagem dentro do objeto ser� a mensagem atual 
        // com as novas informa��es
        // Verifica de erro para quando mensagem estiver vazia
        String mensagemAtual = ( carteira.getMessage() == null )? "": carteira.getMessage();
        String mensagemNova = mensagemAtual.concat(mensagem);
        carteira.setMessage(mensagemNova);
        
        // Caso alguma regra n�o tenha sido cumprida, h� erro e a mensagem n�o � vazia
        return mensagem.equals("");
    }
    
    public static void cadastrarCarteira(Carteira carteira) {
        
        boolean verifica = CarteiraController.verificarCampos(carteira);
        
        if(!verifica) {
            carteira.setError(true);
            // Algum dado informado � inv�lido
            return;
        }
       
        CarteiraModel.cadastrarCarteira(carteira);
    }
    // Retorna TRUE se existir uma carteira v�lida para o titular, caso contr�rio retorna FALSE 
    private static boolean verificarCarteiraTitularValida(Carteira carteira) {
        ArrayList<Carteira> newList = CarteiraController.buscarCarteira(carteira, "CARTEIRA_TITULAR_VALIDA");
        
        boolean temItem = true;
        
        if(newList == null || newList.isEmpty())
            temItem = false;
        
        return temItem;
    }
    
    public static ArrayList<Carteira> buscarCarteira(Carteira carteira, String tipo) {
        
        ArrayList<Carteira> newList = CarteiraModel.buscarCarteira(carteira, tipo);
        
        // Se n�o houve nenhum erro na pesquisa, e ainda assim, a lista est� vazia
        if(!carteira.isError() && newList == null)
        {
            carteira.setError(true);
            carteira.setMessage("Nenhum poss�vel titular foi encontrado.");
            return null;
        }
        else
            return newList;
    }
    
}
