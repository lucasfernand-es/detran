package Model;

import Connector.MySQLConnector;
import Controller.Util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import valueObject.Automovel;
import valueObject.Multa;

/**
 *
 * @author cristhian
 */
public class MultaModel {
    
    public static ArrayList<Multa>  buscarMulta(Multa multa, String tipo) {
        try {
            // Connect with database
            MySQLConnector mCon = new MySQLConnector();
            Connection con = mCon.connect();
            
            // List of Buses
            ArrayList<Multa> multaList = new ArrayList<>();
            
            ResultSet rs;
            PreparedStatement stm;
            /*
            DEFAULT - Busca todos os possíveis
            ID - Busca todas as multas com idMulta == ?
            IDAUTOMOVEL - Busca todas as multas com idAutomovel == ?
            */
            switch(tipo) {
                case "ID":
                    stm = con.prepareStatement("SELECT * FROM multa WHERE idMulta = ?");
                    stm.setInt(1, multa.getIdMulta());
                    break;
                case "IDAUTOMOVEL":
                    stm = con.prepareStatement("SELECT * FROM multa WHERE idAutomovel = ?");
                    stm.setInt(1, multa.getAutomovel().getIdAutomovel());
                    break;
                default: 
                    stm = con.prepareStatement("SELECT * FROM multa");
                    break;
            }
            
            rs = stm.executeQuery();
            
            while (rs.next()) {
                System.out.println("about to instance");
                Multa multaVO = Util.criarMulta(rs);
                System.out.println( multaVO.toString() );
                multaList.add(multaVO);
            }
            
            mCon.disconnect();
            return multaList;
        }
        catch(Exception e) {
            multa.setError(true);
            multa.setMessage("\tFalha Técnica\n\t" + e.getMessage());
            return null;
        }
    }
    
}
