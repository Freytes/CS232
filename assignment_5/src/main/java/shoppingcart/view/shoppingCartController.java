package main.java.shoppingcart.view;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import main.java.shoppingcart.database.DBConnector;
import main.java.shoppingcart.model.Products;
import main.java.shoppingcart.model.Products;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class shoppingCartController implements Initializable {

    //Table used for Shopping Cart
    @FXML
    public TableView<Products> item_Table;
    @FXML
    public TableColumn<Products, String> item_ID;
    @FXML
    public TableColumn<Products, String> item_Priority;
    @FXML
    public TableColumn<Products, String> item_Name;
    @FXML
    public TableColumn<Products, Number> item_Qty;
    @FXML
    public TableColumn<Products, Number> item_Price;
    @FXML
    public TextField productGrandtotal = new TextField();
    @FXML
    public TextField cartBudget = new TextField();
    @FXML
    public Label lbusername = new Label();

    //Database instance
    public static DBConnector db = new DBConnector();

    @Override
    //When the program initializes it checks performs the following: set headers, adds new items
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //connect to database
        db.connect();
        //sets the column headers
        setColumns();
        // loads data into the Observable List
        loadData(db.get());
        // Calculates the sum of all the items price.
        getPriceSum();
        //Retrieves logged-in username
        getUser();

    }

    // setColumns header information
    public void setColumns() {
        item_ID.setCellValueFactory(cellData -> cellData.getValue().itemIDProperty());
        item_Priority.setCellValueFactory(cellData -> cellData.getValue().itemPriorityProperty());
        item_Name.setCellValueFactory(cellData -> cellData.getValue().itemNameProperty());
        item_Qty.setCellValueFactory(cellData -> cellData.getValue().itemQtyProperty());
        item_Price.setCellValueFactory(cellData -> cellData.getValue().itemPriceProperty());

    }

    public void getUser(){
        lbusername.setText(main.java.shoppingcart.view.UserLogin.uname);
    }
    //Sets arraylist and adds items//
    public void loadData(ObservableList<Products> products) {
        item_Table.refresh();
        item_Table.getItems().addAll(db.get());
    }

    public double getPriceSum() {

        double sum = 0;

        for (Products products : item_Table.getItems()) {
            sum += products.getItemPrice() * products.getItemQty();
        }
        productGrandtotal.setText(String.valueOf(sum));

        return sum;
    }

    public double getBudget() {

        double budget;

        if(cartBudget.getText().equals("") || !cartBudget.getText().matches("\\d{0,7}([\\.]\\d{0,4})?")) {

            budget = 0;

        } else{

            budget = Double.parseDouble(cartBudget.getText());
        }

        return budget;
    }

    public String completedCart() {
        String errorMessage = "";

        String purchased = "Purchased:\n";

        String notPurchased = "Items could not be purchased:\n";

        double budgetAmount = getBudget();

        double productTotal = getPriceSum();

        if(budgetAmount > 0) {

            double sum = 0;

            for (int a = 0; a < db.get().size(); a++) {

                sum += db.get().get(a).getItemPrice();

                int q = db.get().get(a).getItemQty() - 1;

                int Q = 1;

                System.out.println("Quantity: " + q);

                while (true) {

                    System.out.println("SUM: " + sum);

                    if (sum + db.get().get(a).getItemPrice() <= budgetAmount && q != 0) {

                        sum += db.get().get(a).getItemPrice();

                        Q++;

                    } else if (sum == budgetAmount) {

                        break;

                    } else

                        break;

                    if (q == 0) {

                        break;
                    }
                    q--;
                }

                System.out.println("Sum Now: " + sum);

                if (sum <= budgetAmount) {

                    System.out.println("Purchased...");

                    purchased += db.get().get(a).getItemName() + " | " + db.get().get(a).getItemPrice()

                            + " | " + Q + "\n";
                } else {

                    sum -= db.get().get(a).getItemPrice();

                    notPurchased += db.get().get(a).getItemName() + " | " + db.get().get(a).getItemPrice()

                            + " | " + db.get().get(a).getItemQty() + "\n";
                }
            }
        }

        Alert alert = null;
        String dollarMatch = "\"\\\\d{0,7}([\\\\.]\\\\d{0,4})?\"";

        if (budgetAmount <= 0 || !cartBudget.getText().matches("\\d{0,7}([\\.]\\d{0,4})?")) {
            errorMessage += "Not a valid Budget or the budget must be greater than 0!\n";
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            //return errorMessage;

        } else if (productTotal <= 100.00 || productGrandtotal.getText().matches((dollarMatch))) {
            errorMessage += "Not a valid total, GrandTotal must be greater than 100!\n";
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            //return errorMessage;
        }
        else{
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setTitle("Success");
            alert.setHeaderText("Success");
            alert.setContentText(purchased+"\n"+notPurchased);
            alert.showAndWait();
        }
        return null;
    }

    public void handlecartCheckout(ActionEvent event) throws IOException {

        completedCart();

        System.out.println("Displaying information to console: Checkout Button Selected");
    }
    //Changes to AddScene
    public void handleitemAddition(ActionEvent event) throws IOException {

        Parent addItem_page = FXMLLoader.load(getClass().getResource("addItems.fxml"));
        Scene addItem_scene = new Scene(addItem_page);
        Stage addItem_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        addItem_stage.setScene(addItem_scene);
        addItem_stage.show();

        System.out.println("Displaying information to console: Add Item Button Selected");
    }

    //Deletes items
    public void handleitemDelete(ActionEvent event) throws IOException, SQLException {

        int selectedIndex = item_Table.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0) {

            db.delete(Integer.parseInt(db.get().get(selectedIndex).getItemID()));

        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);

            alert.setTitle("No Selection");

            alert.setHeaderText("No Product Selected");

            alert.setContentText("Please select an item from the table.");

            alert.showAndWait();
        }

        // Refreshes TableView after items have been deleted.
        Parent shoppingCart_page = FXMLLoader.load(getClass().getResource("shoppingcart.fxml"));
        Scene shoppingCart_scene = new Scene(shoppingCart_page);
        Stage shoppingCart_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        shoppingCart_stage.setScene(shoppingCart_scene);
        shoppingCart_stage.show();

        System.out.println("Displaying information to console: Delete Item Button Selected");
    }
    public void handlelogout(ActionEvent event) throws IOException {
        // Logs user out of the system
        Parent login_page = FXMLLoader.load(getClass().getResource("userLogin.fxml"));
        Scene login_scene = new Scene(login_page);
        Stage login_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        login_stage.setScene(login_scene);
        login_stage.show();
    }
}