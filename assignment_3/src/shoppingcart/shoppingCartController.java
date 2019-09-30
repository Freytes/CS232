package shoppingcart;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

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

    // Fields used to add items to cart
    @FXML private TextField productName;
    @FXML private TextField productQty;
    @FXML private TextField productPrice;
    @FXML private ChoiceBox productPriority;


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

    // Method used to get the list of products
    public ObservableList<Products> getProduct() {

    //Obseravable list which can be used to collect items
    ObservableList<Products> products = FXCollections.observableArrayList();
        return products;
    }

        public void addItems(ActionEvent event) throws IOException {

            Products newProduct = new Products(
                    productPriority.toString(),
                    productName.getText(),
                    Double.parseDouble(productPrice.getText()),
                    Integer.parseInt(productQty.getText()));
            item_Table.getItems().add(newProduct);

        System.out.println("Displaying information to consoles: Ensuring the addItem method worked as expected.");
    }

    public void handleitemDelete(ActionEvent event) throws IOException {

        System.out.println("Displaying information to consoles: Deleting Selected Item");
    }


}
