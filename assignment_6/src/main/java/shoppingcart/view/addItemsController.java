package main.java.shoppingcart.view;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;


public class addItemsController extends shoppingCartController {

    // Priority Choice Values
    private ArrayList<String> priorityChoice = new ArrayList<>();

    // Fields used to add items to cart//
    @FXML
    private TextField productName = new TextField();
    @FXML
    private TextField productQty = new TextField();
    @FXML
    private TextField productPrice = new TextField();
    @FXML
    private ComboBox<String> productPriority = new ComboBox<String>(FXCollections.observableArrayList(Arrays.toString(priorityChoice.toArray())));

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (int a = 0; a < 10; a++) {
            productPriority.getItems().add((a+1)+"");
        }
    }

    public void handleitemAdd(ActionEvent event) throws IOException, SQLException {

        //Used to connect addItems Scene to shoppingCartController Scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("shoppingcart.fxml"));
        Scene shoppingCart_scene = new Scene(loader.load());
        shoppingCartController controller = loader.getController();

        //First confirms if the input was valid, then adds it to the database.
        if (isInputValid()) {
            //Captures, then parses priority value to string
            int priority = Integer.parseInt(productPriority.getValue());

            //Captures productName from textfield
            String name = productName.getText();

            //Captures, then parses productPrice value to string
            double price = Double.parseDouble(productPrice.getText());

            //Captures, then parses productQty value to string
            int quantity = Integer.parseInt(productQty.getText());

            //Confirms first if parsed values were added to database
            boolean added = db.insert(priority, name, quantity, price);

            //Parsed values failed due to duplicate items existing within the database.
            if(added == false){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.setTitle("Error");
                alert.setHeaderText("Error");
                alert.setContentText("Duplicate Item Name Entered");
                alert.showAndWait();
            }

            //Initializes connection to database, and adds items to ITEMS table
            controller.loadData(db.get());

            // Clear values
            productPriority.getSelectionModel().clearSelection();
            productQty.clear();
            productName.clear();
            productPrice.clear();
        }
        System.out.println("Displaying information to consoles: Ensuring the addItem method worked as expected.");
    }

    public void handleitemReturnCart(ActionEvent event) throws IOException {

        //Returns user back to shoppingCart.
        Parent shoppingCart_page = FXMLLoader.load(getClass().getResource("shoppingcart.fxml"));
        Scene shoppingCart_scene = new Scene(shoppingCart_page);
        Stage shoppingCart_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        shoppingCart_stage.setScene(shoppingCart_scene);
        shoppingCart_stage.show();

        System.out.println("Displaying information to console: Ensuring that user returned to main page");
    }

    //Input Validation
    public boolean isInputValid() {
        String errorMessage = "";

        if (productName.getText() == null || productName.getText().length() == 0 || !productName.getText().matches("[a-zA-Z]*")  ) {
            errorMessage += "Not a valid Product Name!\n";
        }

        if (productPrice.getText() == null || productPrice.getText().length() == 0 || !productPrice.getText().matches("\\d{0,7}([\\.]\\d{0,4})?")) {
            errorMessage += "Not a valid Product Price!\n";
        } else {
            // try to parse the Product Price into an Double.
            try {
                Double.parseDouble(productPrice.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Not a valid Product Price!\n";
            }
        }
        if (productQty.getText() == null || productQty.getText().length() == 0 || !productQty.getText().matches("[0-9]*")) {
            errorMessage += "Not a valid Product Qty!\n";
        } else {
            // try to parse the Product Qty into an int.
            try {
                Integer.parseInt(productQty.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Not a valid Product Qty (must be an integer)!\n";
            }
        }
        if (productPriority.getValue() == null || productPriority.getValue().length() == 0 || !productPriority.getValue().matches("[0-9]*")) {
            errorMessage += "Not a valid Product Priority!\n";
        } else {
            // try to parse the Product Priority into an int.
            try {
                Integer.parseInt(productPriority.getValue());
            } catch (NumberFormatException e) {
                errorMessage += "Not a valid Product Priority (must be an integer)!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            alert.showAndWait();

            return false;
        }
    }

}