package shoppingcart;

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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class shoppingCartController implements Initializable {


    //Table used for Shopping Cart
    @FXML private TableView<Products> item_Table;
    @FXML private TableColumn<Products, String> item_Priority;
    @FXML private TableColumn<Products, String> item_Name;
    @FXML private TableColumn<Products, Number> item_Qty;
    @FXML private TableColumn<Products, Number> item_Price;
    @FXML private TextField productGrandtotal = new TextField();
    @FXML private TextField cartBudget = new TextField();


    //Set as static so it can be shared between classes
    static ObservableList<Products> products = FXCollections.observableArrayList();

    @Override
    //When the program intializes it checks performs the following: set headers, adds new items
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setColumns();
        loadData(products);
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

    //Changes to AddScene//
    public void handleitemAddition(ActionEvent event) throws IOException {

        Parent addItem_page = FXMLLoader.load(getClass().getResource("addItems.fxml"));
        Scene addItem_scene = new Scene(addItem_page);

        Stage addItem_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        addItem_stage.setScene(addItem_scene);
        addItem_stage.show();

        System.out.println("Displaying information to consoles: Deleting Selected Item");
    }

    //Deletes items
    public void handleitemDelete(ActionEvent event) throws IOException {

        int selectedIndex = item_Table.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
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







        System.out.println("Displaying information to consoles: Deleting Selected Item");
        }
}