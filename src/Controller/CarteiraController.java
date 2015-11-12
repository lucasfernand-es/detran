/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.CarteiraModel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import valueObject.Carteira;
import valueObject.Pessoa;

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
        
        
        if(carteira.getnRegistro().length() == 11 || carteira.getnRegistro().length() == 9)
            mensagem = mensagem.concat("Número de Registro tem que ter 9 ou 11 dígitos.\n");
        
        //getting current date and time using Date class
        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date maioridade = new Date();
        //System.out.println(df.format(maioridade));
        // Maioridade
        if(!isMaioridade(maioridade, carteira.getTitular().getDataNascimento()) )
            mensagem = mensagem.concat("Tem que ser maior de Idade\n");
        
        // A nova mensagem dentro do objeto será a mensagem atual 
        // com as novas informações
        // Verifica de erro para quando mensagem estiver vazia
        String mensagemAtual = ( carteira.getMessage() == null )? "": carteira.getMessage();
        String mensagemNova = mensagemAtual.concat(mensagem);
        carteira.setMessage(mensagemNova);
        
        // Caso alguma regra não tenha sido cumprida, há erro e a mensagem não é vazia
        return mensagem.equals("");
    }
    
    private static boolean isMaioridade(Date hoje, Date titular) {
        
        long minutes = getDateDiff(hoje, titular, TimeUnit.MINUTES);
        
        long idade = 60 * 24 * 365 * 18;
        
        if(minutes - idade >= 0)
            return true;
        else
            return false;
        
    }
    
    private static long getDateDiff(Date hoje, Date titular, TimeUnit timeUnit) {
        long diffnMills = hoje.getTime() - titular.getTime();
        return timeUnit.convert(diffnMills, timeUnit.MILLISECONDS);
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

    public static int buscaIDTitutar(ArrayList<Pessoa> pessoaList, Carteira carteira) {
        int id = 0;
        for (int i = 0; i < pessoaList.size(); i ++)
        {
            Pessoa pessoaVO = (Pessoa) pessoaList.get(i);
            if(pessoaVO.getIdPessoa() == carteira.getTitular().getIdPessoa()){
                id = i;
                break;
            }
            
        }
        //System.out.println("id " + id);
        return id;
    }

    public static void alterarCarteira(Carteira carteira) {
        boolean verifica = CarteiraController.verificarCampos(carteira);
        
        if(!verifica) {
            carteira.setError(true);
            // Algum dado informado é inválido
            return;
        }
       
        CarteiraModel.alterarCarteira(carteira);
    }

    public static void excluirCarteira(Carteira carteira) {
        CarteiraModel.excluirCarteira(carteira);
    }

    public static int buscaIDCarteira(ArrayList<Carteira> carteiraList, Carteira carteira) {
        int id = 0;
        for (int i = 0; i < carteiraList.size(); i ++)
        {
            Carteira carteiraVO = (Carteira) carteiraList.get(i);
            if( carteiraVO.getIdCarteira() == carteira.getIdCarteira() ){
                id = i;
                break;
            }
            
        }
        //System.out.println("id " + id);
        return id;
    }


    
}
