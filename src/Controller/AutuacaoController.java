package Controller;

import Model.AutomovelModel;
import Model.AutuacaoModel;
import java.util.ArrayList;
import valueObject.Automovel;
import valueObject.Autuacao;

/**
 *
 * @author cristhian
 */
public class AutuacaoController {
    
    public static ArrayList<Autuacao>  buscarAutuacao(Autuacao autuacao, String tipo) {
        ArrayList<Autuacao> newList = AutuacaoModel.buscarAutuacao(autuacao, tipo);
        
        // Se não houve nenhum erro na pesquisa, e ainda assim, a lista está vazia
        if(!autuacao.isError() && newList == null)
        {
            autuacao.setError(true);
            autuacao.setMessage("Nenhuma autuação foi encontrada.");
            return null;
        }
        else
            return newList;
    }
    public static void cadastrarAutuacao(Autuacao autuacao) {
        boolean verifica = AutuacaoController.verificarCampos(autuacao);
        if(!verifica) {
            autuacao.setError(true);
            // Algum dado informado é inválido
            return;
        }
        AutuacaoModel.cadastrarAutuacao(autuacao);
    }
    
    private static boolean verificarCampos(Autuacao autuacao) {
        String mensagem = "";
        
        // vazios
        if(autuacao.getTitulo().length() == 0)
            mensagem = mensagem.concat("Título não pode estar vazio\n");
        if(autuacao.getDescricao().length() == 0)
            mensagem = mensagem.concat("Descrição não pode estar vazio\n");
        if(autuacao.getPontuacaoStr().length() == 0)
            mensagem = mensagem.concat("Pontuação não pode estar vazio\n");
        if(autuacao.getCustoStr().length() == 0)
            mensagem = mensagem.concat("Custo não pode estar vazio\n");
        if(autuacao.getPrazoStr().length() == 0)
            mensagem = mensagem.concat("Prazo não pode estar vazio\n");
        
        // números
        try {
            int pontuacao = Integer.decode( autuacao.getPontuacaoStr() );
            if(pontuacao < 1 || pontuacao > 10)
                mensagem = mensagem.concat("Pontuação deve estar entre 1 e 10\n");
            autuacao.setPontuacao(pontuacao);
        } catch (Exception e) {
            mensagem = mensagem.concat("Pontuação deve ser um inteiro\n");
        }
        try {
            double custo = Double.parseDouble( autuacao.getCustoStr() );
            if(custo < 1.0 || custo > 1000.0)
                mensagem = mensagem.concat("Custo deve estar entre 1 e 1000\n");
            autuacao.setCusto(custo);
        } catch (Exception e) {
            mensagem = mensagem.concat("Custo deve ser um número decimal\n");
        }
        try {
            int prazo = Integer.decode( autuacao.getPrazoStr() );
            if(prazo < 1 || prazo > 60)
                mensagem = mensagem.concat("Prazo deve estar entre 1 e 60\n");
            autuacao.setPrazo(prazo);
        } catch (Exception e) {
            mensagem = mensagem.concat("Prazo deve ser um inteiro\n");
        }
        
        // A nova mensagem dentro do objeto será a mensagem atual 
        // com as novas informações
        // Verifica de erro para quando mensagem estiver vazia
        String mensagemAtual = ( autuacao.getMessage() == null )? "" : autuacao.getMessage();
        String mensagemNova = mensagemAtual.concat(mensagem);
        autuacao.setMessage(mensagemNova);
        
        // Caso alguma regra não tenha sido cumprida, há erro e a mensagem não é vazia
        return mensagem.equals("");
    }
    
}
