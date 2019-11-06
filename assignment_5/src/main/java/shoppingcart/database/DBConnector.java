package main.java.shoppingcart.database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.java.shoppingcart.model.Products;

import java.sql.*;

public class DBConnector {

    public Connection getuserConnection() throws ClassNotFoundException, SQLException {

        Connection c = null;

        Statement stmt = null;

        Class.forName("org.sqlite.JDBC");

        try {
            //Directs DriverManager to the correct SQL database
            c = DriverManager.getConnection("jdbc:sqlite:src/main/java/shoppingcart/database/DB/shoppingcart.db");

        }catch (SQLException e){
            e.printStackTrace();
        }
        return c;
    }

    //Creates Initial Connection to the USERS database and creates it if it doesn't exist.
    public void userconnect() {

        Connection c = null;

        Statement stmt = null;

        try {
            //Calls the class needed for the SQLite library.
            Class.forName("org.sqlite.JDBC");

            //Directs DriverManager to the correct SQL database
            c = DriverManager.getConnection("jdbc:sqlite:src/main/java/shoppingcart/database/DB/shoppingcart.db");

            //Initializes the DriverManager to build a SQL statement.
            stmt = c.createStatement();

            //Builds SQL query to create ITEMS table if it doesn't exist.
            String sql = "CREATE TABLE IF NOT EXISTS USERS"
                    + "( ID INTEGER PRIMARY KEY AUTOINCREMENT ,"
                    + " USERNAME TEXT NOT NULL,"
                    + " PASSWORD TEXT NOT NULL)";

            //Updates ITEM table
            stmt.executeUpdate(sql);

            //Completes SQL Query Execution
            stmt.close();

            //Completes connection
            c.close();

        } catch (Exception e) {

            System.err.println(e.getMessage());

            System.exit(0);
        }
    }


    //Creates Initial Connection to the ITEMS database and creates it if it doesn't exist.
    public void connect() {

        Connection c = null;

        Statement stmt = null;

        try {
            //Calls the class needed for the SQLite library.
            Class.forName("org.sqlite.JDBC");

            //Directs DriverManager to the correct SQL database
            c = DriverManager.getConnection("jdbc:sqlite:src/main/java/shoppingcart/database/DB/shoppingcart.db");

            //Initializes the DriverManager to build a SQL statement.
            stmt = c.createStatement();

            //Builds SQL query to create ITEMS table if it doesn't exist.
            String sql = "CREATE TABLE IF NOT EXISTS ITEMS"
                    + "( ID INTEGER PRIMARY KEY AUTOINCREMENT ,"
                    + " PRIORITY INT NOT NULL,"
                    + " NAME TEXT NOT NULL, "
                    + " QUANTITY INT NOT NULL, "
                    + " PRICE REAL)";

            //Updates ITEM table
            stmt.executeUpdate(sql);

            //Completes SQL Query Execution
            stmt.close();

            //Completes connection
            c.close();

        } catch (Exception e) {

            System.err.println(e.getMessage());

            System.exit(0);
        }
    }

    //Inserts priority, name, quantity and price into database.
    public boolean insert(int priority, String name, int quantity, double price) {

        //Requests the items from the Products ObservableList
        ObservableList<Products> productsList = get();

        //Displays error message to user if itemName already exists in Table
        for (int i = 0; i < productsList.size(); i++) {

            if(productsList.get(i).getItemName().equalsIgnoreCase(name)){

                return false;
            }
        }

        Connection c = null;

        PreparedStatement stmt = null;

        try {
            //Calls the class needed for the SQLite library.
            Class.forName("org.sqlite.JDBC");

            //Directs DriverManager to the correct SQL database
            c = DriverManager.getConnection("jdbc:sqlite:src/main/java/shoppingcart/database/DB/shoppingcart.db");

            //Initializes query as running uncommitted
            c.setAutoCommit(false);

            //Builds SQL query String
            String sql = "INSERT INTO ITEMS (PRIORITY,NAME,QUANTITY,PRICE) "
                    + "VALUES (?, ?, ?, ? );";
            stmt = c.prepareStatement(sql);
            stmt.setInt(1, priority);
            stmt.setString(2, name);
            stmt.setInt(3, quantity);
            stmt.setDouble(4, price);

            //Updates ITEM table
            stmt.executeUpdate();

            //Completes SQL Query Execution
            stmt.close();

            //Commits query changes
            c.commit();

            //Closes query connection
            c.close();

            return true;

        } catch (Exception e) {

            System.err.println(e.getMessage());

            return false;
        }
    }

    //Creates the ObservableArrayList called Products
    public ObservableList<Products> get() {

        //Initializes ObservableArrayList called productsList
        ObservableList<Products> productsList = FXCollections.observableArrayList();

        Connection c = null;
        Statement stmt = null;

        try {
            //Calls the class needed for the SQLite library.
            Class.forName("org.sqlite.JDBC");

            //Directs DriverManager to the correct SQL database
            c = DriverManager.getConnection("jdbc:sqlite:src/main/java/shoppingcart/database/DB/shoppingcart.db");

            //Initializes query as running uncommitted
            c.setAutoCommit(false);

            //Initializes the DriverManager to build a SQL statement.
            stmt = c.createStatement();

            //Runs SQL query to return all of the results within the ITEMS table.
            ResultSet rs = stmt.executeQuery("SELECT * FROM ITEMS;");

            //Collects all of the items from within each column of the ITEMS table.
            while (rs.next()) {

                int ID = rs.getInt("ID");

                int priority = rs.getInt("priority");

                String name = rs.getString("name");

                int quantity = rs.getInt("quantity");

                double price = rs.getFloat("price");

                String s = String.format("%.2f", price);

                //Collects all of the items ITEMS table
                Products p = new Products(String.valueOf(ID),String.valueOf(priority), name, Double.parseDouble(s), quantity);

                //Inserts the productsList into the ITEMS table
                productsList.add(p);
            }

            //Completes SQL collection
            rs.close();

            //Completes SQL Query Execution
            stmt.close();

            //Closes SQL connection
            c.close();

        } catch (Exception e) {

            System.err.println(e.getMessage());

            System.exit(0);
        }

        // Bubble sorting the items within the Table by itemPriority
        for (int i = 0; i < productsList.size(); i++) {
            for (int j = i + 1; j < productsList.size(); j++) {
                if (Integer.parseInt(productsList.get(i).getItemPriority()) > Integer.parseInt(productsList.get(j).getItemPriority())) {
                    Products temp = productsList.get(i);
                    productsList.set(i, productsList.get(j));
                    productsList.set(j, temp);
                }
            }
        }
        //Returns all of the items within the ITEMS table.
        return productsList;
    }


    public void delete(int ID) {

        Connection c = null;

        Statement stmt = null;

        try {
            //Calls the class needed for the SQLite library.
            Class.forName("org.sqlite.JDBC");

            //Directs DriverManager to the correct SQL database
            c = DriverManager.getConnection("jdbc:sqlite:src/main/java/shoppingcart/database/DB/shoppingcart.db");

            //Initializes query as running uncommitted
            c.setAutoCommit(false);

            System.out.println("Opened database successfully");

            //Initializes the DriverManager to build a SQL statement.
            stmt = c.createStatement();

            //Builds SQL query to delete the selected item from the ITEMS table based on unique ID.
            String sql = "DELETE from ITEMS where ID = "+ID+";";

            //Updates ITEM table with SQL query
            stmt.executeUpdate(sql);

            //Commits query changes
            c.commit();

            //Completes SQL Query Execution
            stmt.close();

            //Closes query connection
            c.close();

        } catch (Exception e) {

            System.err.println(e.getMessage());

            System.exit(0);
        }
    }
}