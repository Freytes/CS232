package shoppingcart.database;

import java.sql.Connection;
import java.sql.DriverManager;
public class DBConnector {
    public static Connection Connector(){
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn =DriverManager.getConnection("jdbc:sqlite:src/shoppingcart/database/DB/shoppingcart.sqlite");
            return conn;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}