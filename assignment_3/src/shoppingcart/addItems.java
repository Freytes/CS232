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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class addItems implements Initializable {

    // Fields used to add items to cart
    @FXML
    private TextField productName;
    @FXML
    private TextField productQty;
    @FXML
    private TextField productPrice;
    @FXML
    private ChoiceBox productPriority;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void handleitemAdd(ActionEvent event) throws IOException {

        Products newProduct = new Products(
                productPriority.toString(),
                productName.getText(),
                Double.parseDouble(productPrice.getText()),
                Integer.parseInt(productQty.getText()));
        // shoppingCartController.getItems().add(newProduct);


      /*  FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("additems.fxml"));
        Parent addItem_page = loader.load();


        shoppingCartController controller = loader.getController();
        ObservableList<Products> list = FXCollections.observableArrayList();
        list.add(new Products(
                productPriority.toString(),
                productName.getText(),
                Double.parseDouble(productPrice.getText()),
                Integer.parseInt(productQty.getText())));
        controller.item_Table.setItems(list);*/

        System.out.println("Displaying information to consoles: Ensuring the addItem method worked as expected.");
    }

    public void handleitemReturnCart(ActionEvent event) throws IOException {

        Parent shoppingCart_page = FXMLLoader.load(getClass().getResource("shoppingcart.fxml"));
        Scene shoppingCart_scene = new Scene(shoppingCart_page);
        Stage shoppingCart_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        shoppingCart_stage.setScene(shoppingCart_scene);
        shoppingCart_stage.show();

        System.out.println("Displaying information to console: Ensuring that user returned to main page");

    }

}
