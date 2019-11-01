package shoppingcart.database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shoppingcart.model.Products;

import java.sql.*;

public class DBConnector {

    public void connect() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:src/shoppingcart/database/DB/shoppingcart.db");
            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS ITEMS"
                    + "( ID INT PRIMARY KEY AUTOINCREMENT NOT NULL ,"
                    + " PRIORITY INT NOT NULL,"
                    + " NAME TEXT NOT NULL, "
                    + " QUANTITY INT NOT NULL, "
                    + " PRICE REAL)";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }

    public boolean insert(int priority, String name, int quantity, double price) {
        ObservableList<Products> productsList = get();
        for (int i = 0; i < productsList.size(); i++) {
            if(productsList.get(i).getItemName().equalsIgnoreCase(name)){
                return false;
            }
        }


        Connection c = null;
        PreparedStatement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:src/shoppingcart/database/DB/shoppingcart.db");
            c.setAutoCommit(false);
            String sql = "INSERT INTO ITEMS (PRIORITY,NAME,QUANTITY,PRICE) "
                    + "VALUES (?, ?, ?, ? );";
            stmt = c.prepareStatement(sql);
            stmt.setInt(1, priority);
            stmt.setString(2, name);
            stmt.setInt(3, quantity);
            stmt.setDouble(4, price);
            stmt.executeUpdate();

            stmt.close();
            c.commit();
            c.close();
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public ObservableList<Products> get() {
        ObservableList<Products> productsList = FXCollections.observableArrayList();

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:src/shoppingcart/database/DB/shoppingcart.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ITEMS;");

            while (rs.next()) {
                int ID = rs.getInt("ID");
                int priority = rs.getInt("priority");
                String name = rs.getString("name");
                int quantity = rs.getInt("quantity");
                double price = rs.getFloat("price");
                String s = String.format("%.2f", price);
                Products p = new Products(String.valueOf(ID),String.valueOf(priority), name, Double.parseDouble(s), quantity);
                productsList.add(p);
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
        //sorting the array
        for (int i = 0; i < productsList.size(); i++) {
            for (int j = i + 1; j < productsList.size(); j++) {
                if (Integer.parseInt(productsList.get(i).getItemPriority()) > Integer.parseInt(productsList.get(j).getItemPriority())) {
                    Products temp = productsList.get(i);
                    productsList.set(i, productsList.get(j));
                    productsList.set(j, temp);
                }
            }
        }
        return productsList;
    }

    public void delete(int ID) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:src/shoppingcart/database/DB/shoppingcart.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "DELETE from ITEMS where PRIORITY = "+ID+";";
            stmt.executeUpdate(sql);
            c.commit();

            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }
}