package Controller;

import Model.AutuacaoModel;
import java.util.ArrayList;
import valueObject.Autuacao;

/**
 *
 * @author cristhian
 */
public class AutuacaoController {
    
    public static ArrayList<Autuacao>  buscarAutuacao(Autuacao autuacao, String tipo) {
        ArrayList<Autuacao> newList = AutuacaoModel.buscarAutuacao(autuacao, tipo);
        
        // Se n�o houve nenhum erro na pesquisa, e ainda assim, a lista est� vazia
        if(!autuacao.isError() && newList == null)
        {
            autuacao.setError(true);
            autuacao.setMessage("Nenhuma autua��o foi encontrada.");
            return null;
        }
        else
            return newList;
    }
    public static void cadastrarAutuacao(Autuacao autuacao) {
        boolean verifica = AutuacaoController.verificarCampos(autuacao);
        if(!verifica) {
            autuacao.setError(true);
            // Algum dado informado � inv�lido
            return;
        }
        AutuacaoModel.cadastrarAutuacao(autuacao);
    }
    public static void alterarAutuacao(Autuacao autuacao) {
        boolean verifica = AutuacaoController.verificarCampos(autuacao);
        if(!verifica) {
            autuacao.setError(true);
            // Algum dado informado � inv�lido
            return;
        }
        AutuacaoModel.alterarAutuacao(autuacao);
    }
    public static void excluirAutuacao(Autuacao autuacao) {
        AutuacaoModel.excluirAutuacao(autuacao);
    }
    
    private static boolean verificarCampos(Autuacao autuacao) {
        String mensagem = "";
        
        // titulo
        if(autuacao.getTitulo().length() == 0)
            mensagem = mensagem.concat("T�tulo n�o pode estar vazio\n");
        if(autuacao.getTitulo().length() > 50)
            mensagem = mensagem.concat("T�tulo deve ter 50 caracteres ou menos\n");
        
        // descricao
        if(autuacao.getDescricao().length() == 0)
            mensagem = mensagem.concat("Descri��o n�o pode estar vazia\n");
        if(autuacao.getDescricao().length() > 250)
            mensagem = mensagem.concat("Descri��o deve ter 250 caracteres ou menos\n");
        
        // custo
        if(autuacao.getCustoStr().length() == 0) {
            mensagem = mensagem.concat("Custo n�o pode estar vazio\n");
        } else {
            try {
                double custo = Double.parseDouble( autuacao.getCustoStr() );
                autuacao.setCusto(custo);
                if(custo <= 0.0) {
                    mensagem = mensagem.concat("Custo deve ser um valor real maior que 0\n");
                }
            } catch (Exception e) {
                mensagem = mensagem.concat("Custo deve ser um valor real v�lido\n");
            }
        }
        
        // prazo
        if(autuacao.getPrazo() <= 0)
            mensagem = mensagem.concat("Prazo deve ser maior do que 0\n");
        
        // pontuacao
        if(autuacao.getPontuacao() <= 0)
            mensagem = mensagem.concat("Pontuacao deve ser maior do que 0\n");
        
        // A nova mensagem dentro do objeto ser� a mensagem atual 
        // com as novas informa��es
        // Verifica de erro para quando mensagem estiver vazia
        String mensagemAtual = ( autuacao.getMessage() == null )? "" : autuacao.getMessage();
        String mensagemNova = mensagemAtual.concat(mensagem);
        autuacao.setMessage(mensagemNova);
        
        // Caso alguma regra n�o tenha sido cumprida, h� erro e a mensagem n�o � vazia
        return mensagem.equals("");
    }

    public static Autuacao buscaAutuacaoID(Autuacao autuacao) {
        ArrayList<Autuacao> newList = buscarAutuacao(autuacao, "ID");
        
        if(newList == null) 
            return null;
        else {
            // Seleciona o primeiro item da Array

            Autuacao newAutuacao;
            newAutuacao = (Autuacao) newList.get(0);
            return newAutuacao;
        }
    }
    
}
