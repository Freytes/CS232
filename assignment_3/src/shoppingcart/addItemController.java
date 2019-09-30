package shoppingcart;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class addItemController implements Initializable {

    //Capturing textfields used on Form
    @FXML private TextField productName;
    @FXML private TextField productQty;
    @FXML private TextField productPrice;
    @FXML private TextField productPriority;
    @FXML private AnchorPane productPage;
    @FXML private Button productAdd;
    @FXML private Button productHome;
    @FXML private Text labelitemName;
    @FXML private Text labelitemPriority;
    @FXML private Text labelitemQty;
    @FXML private Text labelitemPrice;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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
}
