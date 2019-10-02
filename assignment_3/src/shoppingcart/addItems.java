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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static shoppingcart.shoppingCartController.products;


public class addItems implements Initializable {

    // Fields used to add items to cart//
    @FXML private TextField productName = new TextField();
    @FXML private TextField productQty = new TextField();
    @FXML private TextField productPrice = new TextField();
    @FXML private ChoiceBox productPriority = new ChoiceBox();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Used to Initialize the Scene
    }

    public void handleitemAdd(ActionEvent event) throws IOException {


        //Used to connect addItems Scene to shoppingCartController Scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("shoppingcart.fxml"));
        Scene shoppingCart_scene = new Scene(loader.load());
        shoppingCartController controller = loader.getController();

        products.addAll(new Products(productPriority.toString(),
                productName.getText(),
                Double.parseDouble(productPrice.getText()),
                Integer.parseInt(productQty.getText())));

        controller.loadData(products);

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