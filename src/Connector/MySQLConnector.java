/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Lucas Fernandes
 */
public class MySQLConnector {
    
    private Connection connection;
    
    public MySQLConnector(){

        this.connection = null;
    }

    /**
     *
     * @return
     * @throws Exception
     */
    public Connection connect() throws Exception {
        try {
            if(connection != null)
                return connection;
            else {
                Class.forName("com.mysql.jdbc.Driver"); // lucas port = 8889, cristhian port = 3306
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Detran","root","root");
                return connection;
            }
        } catch (ClassNotFoundException ex) {
            throw new Exception("Error finding the driver!\n(" + ex.getMessage() + ")");
        } catch (SQLException e) {
            throw new Exception("Error in the connection!\n(" + e.getMessage() + ")");
        }
    }

    public void disconnect() throws Exception {
        try {
            connection.close();
        } catch (SQLException ex) {
            throw new Exception("Error disconnecting!\n(" + ex.getMessage() + ")");
        }
    }
}
