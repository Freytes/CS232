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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    //Capturing textfields used on Form
    @FXML
    private TextField productName;

    @FXML
    private TextField productQty;

    @FXML
    private TextField productPrice;

    @FXML
    private TextField productPriority;

    @FXML
    private AnchorPane productPage;

    @FXML
    private Button productAdd;

    @FXML
    private Button productHome;

    @FXML
    private Text labelitemName;

    @FXML
    private Text labelitemPriority;

    @FXML
    private Text labelitemQty;

    @FXML
    private Text labelitemPrice;

    //Table used for Shopping Cart
    @FXML
    private TableView<Products> item_Table;
    @FXML
    private TableColumn<Products, String> item_Priority;
    @FXML
    private TableColumn<Products, String> item_Name;
    @FXML
    private TableColumn<Products, Integer> item_Qty;
    @FXML
    private TableColumn<Products, Double> item_Price;

    //The Initializer used to load data prior to loading view.
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        item_Priority.setCellValueFactory(new PropertyValueFactory<Products, String>("itemPriority"));
        item_Name.setCellValueFactory(new PropertyValueFactory<Products, String>("itemName"));
        item_Qty.setCellValueFactory(new PropertyValueFactory<Products, Integer>("itemQty"));
        item_Price.setCellValueFactory(new PropertyValueFactory<Products, Double>("itemPrice"));
        item_Table.setItems(getProduct());

        System.out.println("Loading user data.");
    }

    // Method used to get the list of products
    public ObservableList<Products> getProduct() {
        ObservableList<Products> products = FXCollections.observableArrayList();
        products.add(new Products("Orange", 2.00, "1", 1));
        return products;
    }

    public void addItems(ActionEvent event) throws IOException {

        Parent addItem_page = FXMLLoader.load(getClass().getResource("addItem.fxml"));
        Scene addItem_scene = new Scene(addItem_page);
        Stage addItem_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        addItem_stage.setScene(addItem_scene);
        addItem_stage.show();

        System.out.println("Displaying information to console: Ensuring the addItem method worked as expected.");
    }

    public void handleitemAdd() {
        System.out.println("Added item to cart test");
    }

    public void handleitemReturnCart(ActionEvent event) throws IOException {

        Parent shoppingCart_page = FXMLLoader.load(getClass().getResource("shoppingcart.fxml"));
        Scene shoppingCart_scene = new Scene(shoppingCart_page);
        Stage shoppingCart_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        shoppingCart_stage.setScene(shoppingCart_scene);
        shoppingCart_stage.show();

        System.out.println("Displaying information to console: Ensuring that user returned to main page");

    }

    public void handleitemDelete (ActionEvent event) throws IOException {

        System.out.println("Displaying information to console: Deleting Selected Item");
    }

}
