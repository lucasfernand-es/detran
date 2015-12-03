package Controller;

import Model.MultaModel;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import valueObject.Autuacao;
import valueObject.Multa;

/**
 *
 * @author cristhian
 */
public class MultaController {
    
    public static ArrayList<Multa>  buscarMulta(Multa multa, String tipo) {
        ArrayList<Multa> newList = MultaModel.buscarMulta(multa, tipo);
        
        // Se n�o houve nenhum erro na pesquisa, e ainda assim, a lista est� vazia
        if(!multa.isError() && newList == null)
        {
            multa.setError(true);
            multa.setMessage("Nenhuma multa foi encontrada.");
            return null;
        }
        else
            return newList;
    }

    public static void cadastrarMulta(Multa multa) {
        
        boolean verifica = MultaController.verificarCampos(multa);
        
        if(!verifica) {
            multa.setError(true);
            // Algum dado informado � inv�lido
            return;
        }
       
        MultaModel.cadastrarMulta(multa);
    }

    // Fun��o verifica os dados do objeto. Se sim, retorna true, se n�o retorna false
    // Altera a mensagem do objeto para especificar os erros
    private static boolean verificarCampos(Multa multa) {
        String mensagem = "";
        
        // Verificar campos nulos
        if(multa.getDataEmissao()== null)
            mensagem = mensagem.concat("Data de Emiss�o n�o pode estar vazio\n");
        
        if(multa.getTaxaAcrescimo() < 0 || multa.getTaxaAcrescimo() > 100)
            mensagem = mensagem.concat("Taxa de Acr�scimo deve ser <= a 0 e >= a 100.\n");
        
        if(multa.getAutomovel() == null)
            mensagem = mensagem.concat("Autom�vel n�o pode estar vazio\n");
        
        if(multa.getAutuacao() == null)
            mensagem = mensagem.concat("Autua��o n�o pode estar vazio\n");
        
        if(multa.getCarteira() == null && multa.getPessoa() == null)
            mensagem = mensagem.concat("Deve existir Carteira ou Pessoa\n");
        
        // Comparar Data de Vencimento com Data de Emiss�o
        // Se as duas n�o est�o vazias e a data de vencimento for menor que a data de Emiss�o
        if( (multa.getDataEmissao()!= null) && (multa.getDataPagamento() != null)
                && (  multa.getDataEmissao().after( multa.getDataPagamento() ) ))
            mensagem = mensagem.concat("Data de Emiss�o deve ser antes de Data de Pagamento\n");
        
        
        
        
        // A nova mensagem dentro do objeto ser� a mensagem atual 
        // com as novas informa��es
        // Verifica de erro para quando mensagem estiver vazia
        String mensagemAtual = ( multa.getMessage() == null )? "": multa.getMessage();
        String mensagemNova = mensagemAtual.concat(mensagem);
        multa.setMessage(mensagemNova);
        
        // Caso alguma regra n�o tenha sido cumprida, h� erro e a mensagem n�o � vazia
        return mensagem.equals("");
              
    }

    public static void alterarMulta(Multa multa) {
        
        boolean verifica = MultaController.verificarCampos(multa);
        
        if(!verifica) {
            multa.setError(true);
            // Algum dado informado � inv�lido
            return;
        }
       
        MultaModel.alterarMulta(multa);
    }

    public static Double calcularMulta(Multa multaVO, boolean temAtraso) {
        
        Autuacao autuacao = multaVO.getAutuacao();
        double custo =  multaVO.getAutuacao().getCusto();
        
        
        if(!temAtraso)
        {
            return custo;
        }
        
        float acrescimo = multaVO.getTaxaAcrescimo();
        
        custo = custo * ( 1 + ( acrescimo/100 ) );
        
        return round(custo , 2) ;
        
    }
    public static double round(double value, int places) {
        if (places < 0) return 0;

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
    
    public static double round2(double value, int places) {
        if (places < 0) return 0;

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
    
        return (double) tmp / factor;
    }
    
    
}
