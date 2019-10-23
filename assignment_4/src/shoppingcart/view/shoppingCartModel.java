package shoppingcart.view;

import shoppingcart.database.DBConnector;

import java.sql.Connection;
import java.sql.SQLException;
public class shoppingCartModel {
    Connection connection;
    public shoppingCartModel() {
        connection = DBConnector.Connector();
        if (connection == null){
            System.out.println("Database Connection not Successful");
            System.exit(1);
        }
    }
        public  boolean isDbConnected() {
            try{
                return !connection.isClosed();
            } catch (SQLException e){
                e.printStackTrace();
                return false;
            }
        }
}
