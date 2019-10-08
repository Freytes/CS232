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
import javafx.stage.Stage;
import shoppingcart.Main;
import shoppingcart.model.Products;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class shoppingCartController implements Initializable {


    //Table used for Shopping Cart
    @FXML
    private TableView<Products> item_Table;
    @FXML
    private TableColumn<Products, String> item_Priority;
    @FXML
    private TableColumn<Products, String> item_Name;
    @FXML
    private TableColumn<Products, Number> item_Qty;
    @FXML
    private TableColumn<Products, Number> item_Price;
    @FXML
    private TextField productGrandtotal = new TextField();
    @FXML
    private TextField cartBudget = new TextField();

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

    public void getPriceSum() {
        int sum = 0;

        for (Products products : item_Table.getItems()) {
            if (item_Table.getItems().isEmpty()) {
                sum = 0;

            } else
                sum = (int) (sum + products.getItemPrice());

        }
        productGrandtotal.setText(Integer.toString(sum));
    }

    public int getBudget() {

        cartBudget.setText(Integer.toString((int) 59.00));

        return 0;
    }

    public boolean includedinBudget() {
        String errorMessage = "";

        if (productGrandtotal.getText() == null || productGrandtotal.getText().length() == 0 || !productGrandtotal.getText().matches("[1-9]*")) {
            errorMessage += "Not a valid total, GrandTotal cannot be 0!\n";
        } else {
            // try to parse the GrandTotal into an int.
            try {
                Integer.parseInt(productGrandtotal.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Not a valid total, GrandTotal must be greater than 100!\n";
            }
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Checkout");
            alert.setHeaderText("Checkout Error");
            alert.setContentText(errorMessage);
            alert.showAndWait();

            return false;
        }
    }

    public void handlecartCheckout(ActionEvent event)throws IOException{

        //TODO calculates budget and checks to see if what you can purchase

        int budgetAmount = getBudget();
        if (budgetAmount >= 0) {

            if(includedinBudget()){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Success");
                alert.setHeaderText("Success");
                alert.setContentText("Success");

                alert.showAndWait();
            }

        } else {
            // Budget not greater than zero
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("CheckOut");
            alert.setHeaderText("Budget must be greater than 0");
            alert.setContentText("Please input a budget greater than 0");

            alert.showAndWait();
        }
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