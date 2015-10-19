package Controller;

import Model.AutomovelModel;
import Model.MultaModel;
import java.util.ArrayList;
import valueObject.Automovel;
import valueObject.Multa;

/**
 *
 * @author cristhian
 */
public class MultaController {
    
    public static ArrayList<Multa>  buscarMulta(Multa multa, String tipo) {
        ArrayList<Multa> newList = MultaModel.buscarMulta(multa, tipo);
        
        // Se não houve nenhum erro na pesquisa, e ainda assim, a lista está vazia
        if(!multa.isError() && newList == null)
        {
            multa.setError(true);
            multa.setMessage("Nenhuma multa foi encontrada.");
            return null;
        }
        else
            return newList;
    }
    
}
