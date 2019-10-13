package shoppingcart.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import shoppingcart.Main;
import shoppingcart.model.Products;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class shoppingCartController implements Initializable {


    //Table used for Shopping Cart
    @FXML
    public TableView<Products> item_Table;
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

    //References Main Application to Obtain Table
    private Main mainApp;

    // The data placed in an ObservableList
    public static ObservableList<Products> products = FXCollections.observableArrayList();


    @Override
    //When the program intializes it checks performs the following: set headers, adds new items
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //sets the column headers
        setColumns();
        // loads data into the Observable List
        loadData(products);
        // Calculates the sum of all the items price.
        getPriceSum();
        // Calculates & Displays budget
        getBudget();
    }

    // setColumns header information
    public void setColumns() {
        item_Priority.setCellValueFactory(cellData -> cellData.getValue().itemPriorityProperty());
        item_Name.setCellValueFactory(cellData -> cellData.getValue().itemNameProperty());
        item_Qty.setCellValueFactory(cellData -> cellData.getValue().itemQtyProperty());
        item_Price.setCellValueFactory(cellData -> cellData.getValue().itemPriceProperty());

    }

    //Sets arraylist and adds items//
    public void loadData(ObservableList<Products> products) {
        item_Table.refresh();
        item_Table.getItems().addAll(products);
    }

    public double getPriceSum() {
        double sum = 0;

        for (Products products : item_Table.getItems()) {
            sum += products.getItemPrice();
        }
        productGrandtotal.setText(String.valueOf(sum));

        return sum;
    }

    public String getProductNames() {
        String name = null;
        for (Products nameProducts : item_Table.getItems()) {
            name = name + nameProducts.getItemName();
        }
        return name;
    }

    public double getBudget() {
        cartBudget.setText(String.valueOf(Double.valueOf((double) 59.00)));
        return Double.parseDouble(cartBudget.getText());
    }

    public String completedCart() {
        String errorMessage = "";
        String purchases = "Purchases:\n";
        String notPurchases = "Items cannot be purchased:\n";
        double budgetAmount = getBudget();
        double productTotal = getPriceSum();

        for (int a = 0; a < products.size(); a++) {
            System.out.println(products.get(a).getItemPrice() + "-" + budgetAmount);
            if (products.get(a).getItemPrice() + products.get(a).getItemPrice() <= budgetAmount) {
                purchases += "Item Name:" + products.get(a).getItemName() + " | " + "Item Price:" + products.get(a).getItemPrice() + " | " + "Item Qty:" + products.get(a).getItemQty() + "\n";
            } else {
                notPurchases += "Item Name:" + products.get(a).getItemName() + " | " + "Item Price:" + products.get(a).getItemPrice() + " | " + "Item Qty:" + products.get(a).getItemQty() + "\n";
            }
        }

        Alert alert = null;
        String dollarMatch = "\"\\\\d{0,7}([\\\\.]\\\\d{0,4})?\"";
        if (budgetAmount < 0) {
            errorMessage += "Not a valid Budget, budget must be greater than 0!\n";
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            //return errorMessage;
        } else if (productTotal < 100.00 || productGrandtotal.getText().matches((dollarMatch))) {
            errorMessage += "Not a valid total, GrandTotal must be greater than 100!\n";
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            //return errorMessage;
        }


        if (budgetAmount > 0 && productTotal >= 100) {

            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setTitle("Success");
            alert.setHeaderText("Success");
            alert.setContentText("\n\n" + purchases + "\n" + notPurchases);
            alert.showAndWait();
        }
        return null;
    }

    public void handlecartCheckout(ActionEvent event) throws IOException {
        //evaluates budget and checks to see if what you can purchase
        String result = completedCart();

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
    public void handleitemDelete(ActionEvent event) throws IOException {

        int selectedIndex = item_Table.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            // Remove all items in the table
            products.removeAll(products);
            item_Table.getItems().clear();
            item_Table.refresh();

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
}