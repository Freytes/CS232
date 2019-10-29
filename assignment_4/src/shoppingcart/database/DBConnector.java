package shoppingcart.database;

import java.sql.Connection;
import java.sql.DriverManager;
public class DBConnector {
    public static Connection Connector(){
        try {
            Class.forName("org.sqlite.JDBC");
            //Creates a connection to the shoppingcart.db database
            Connection conn = DriverManager.getConnection("jdbc:sqlite:src/shoppingcart/database/DB/shoppingcart.db");
            return conn;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}