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
import javafx.scene.control.ChoiceBox;
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

    private ObservableList<Products> productItems;


    //The Initializer used to load data prior to loading view.

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        item_Priority.setCellValueFactory(cellData -> cellData.getValue().itemPriorityProperty());
        item_Name.setCellValueFactory(cellData -> cellData.getValue().itemNameProperty());
        item_Qty.setCellValueFactory(cellData -> cellData.getValue().itemQtyProperty());
        item_Price.setCellValueFactory(cellData -> cellData.getValue().itemPriceProperty());

        //Display all items in table
        item_Table.setItems(getProduct());

    }

    public void setItem_Table(ObservableList<Products> productItems) {
        this.productItems = productItems;
    }

    // Method used to get the list of products
    public ObservableList<Products> getProduct() {

    //Obseravable list which can be used to collect items
    ObservableList<Products> products = FXCollections.observableArrayList();
        return products;
    }


    public void handleitemAddition(ActionEvent event) throws IOException {

        Parent addItem_page = FXMLLoader.load(getClass().getResource("addItems.fxml"));
        Scene addItem_scene = new Scene(addItem_page);
        Stage addItem_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        addItem_stage.setScene(addItem_scene);
        addItem_stage.show();

        System.out.println("Displaying information to consoles: Deleting Selected Item");
    }

    public void handleitemDelete(ActionEvent event) throws IOException {

        System.out.println("Displaying information to consoles: Deleting Selected Item");
    }


}
