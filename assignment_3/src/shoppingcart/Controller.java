package shoppingcart;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    //Initialize variables
    public Button itemAdd;

    //The Initializer used to load data prior to loading view.
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Loading user data.");
    }

    public void addItems() {
        System.out.println("Displaying addItem page");
    }

    public void handleitemAdd() {
        System.out.println("Added item to cart test");
    }

}
