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
    
    // Função verifica os dados do objeto. Se sim, retorna true, se não retorna false
    // Altera a mensagem do objeto para especificar os erros
    private static boolean verificarCampos(Carteira carteira) {
        String mensagem = "";
        
        // Verificar campos nulos
        if(carteira.getDataVencimento() == null)
            mensagem = mensagem.concat("Data de Vencimento não pode estar vazio\n");
        if(carteira.getDataEmissao() == null)
            mensagem = mensagem.concat("Data de Emissão não pode estar vazio\n");
        if(carteira.getnRegistro().equals(""))
            mensagem = mensagem.concat("Número de Registro não pode estar vazio\n");
        if(carteira.getTitular() == null)
            mensagem = mensagem.concat("Titular não pode estar vazio\n");
        
        // Comparar Data de Vencimento com Data de Emissão
        // Se as duas não estão vazias e a data de vencimento for menor que a data de Emissão
        if( (carteira.getDataVencimento() != null) && (carteira.getDataEmissao() != null)
                && (carteira.getDataVencimento().before( carteira.getDataEmissao() ) ))
            mensagem = mensagem.concat("Data de Vencimento não pode ser menor que Data de Emissão\n");
        
        // Verificar se Titular possui alguma carteira, caso o status seja TRUE
        if( carteira.isStatus() ){
            boolean isCarteira = CarteiraController.verificarCarteiraTitularValida(carteira);
            if(isCarteira)
                mensagem = mensagem.concat("Não é possível haver duas carteiras válidas para o mesmo titular\n");
        }
        
        // A nova mensagem dentro do objeto será a mensagem atual 
        // com as novas informações
        // Verifica de erro para quando mensagem estiver vazia
        String mensagemAtual = ( carteira.getMessage() == null )? "": carteira.getMessage();
        String mensagemNova = mensagemAtual.concat(mensagem);
        carteira.setMessage(mensagemNova);
        
        // Caso alguma regra não tenha sido cumprida, há erro e a mensagem não é vazia
        return mensagem.equals("");
    }
    
    public static void cadastrarCarteira(Carteira carteira) {
        
        boolean verifica = CarteiraController.verificarCampos(carteira);
        
        if(!verifica) {
            carteira.setError(true);
            // Algum dado informado é inválido
            return;
        }
       
        CarteiraModel.cadastrarCarteira(carteira);
    }
    // Retorna TRUE se existir uma carteira válida para o titular, caso contrário retorna FALSE 
    private static boolean verificarCarteiraTitularValida(Carteira carteira) {
        ArrayList<Carteira> newList = CarteiraController.buscarCarteira(carteira, "CARTEIRA_TITULAR_VALIDA");
        
        boolean temItem = true;
        
        if(newList == null || newList.isEmpty())
            temItem = false;
        
        return temItem;
    }
    
    public static ArrayList<Carteira> buscarCarteira(Carteira carteira, String tipo) {
        
        ArrayList<Carteira> newList = CarteiraModel.buscarCarteira(carteira, tipo);
        
        // Se não houve nenhum erro na pesquisa, e ainda assim, a lista está vazia
        if(!carteira.isError() && newList == null)
        {
            carteira.setError(true);
            carteira.setMessage("Nenhum possível titular foi encontrado.");
            return null;
        }
        else
            return newList;
    }
    
}
