package shoppingcart;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {


    public void displayItems() {

        Label cartEmpty = new Label("Currently Empty");
        VBox mainScreen_Layout = new VBox(20);
        mainScreen_Layout.getChildren().addAll(cartEmpty);

    }

    public void addItems() {
        System.out.println("Displaying addItem page");

    }

    public void handleitemAdd() {
        System.out.println("Added item to cart test");
    }

    //The Initializer used to load data prior to loading view.
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Loading user data.");


    }


}
