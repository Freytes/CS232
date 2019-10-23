package shoppingcart.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import shoppingcart.model.Products;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class addItemsController extends shoppingCartController {

    // Priority Choice Values
    private ArrayList<String> priorityChoice = new ArrayList<>();

    // Fields used to add items to cart
    @FXML
    private TextField productName = new TextField();
    @FXML
    private TextField productQty = new TextField();
    @FXML
    private TextField productPrice = new TextField();
    @FXML
    private ComboBox<String> productPriority = new ComboBox<String>(FXCollections.observableArrayList(Arrays.toString(priorityChoice.toArray())));

    public addItemsController() throws SQLException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Used to add items to the priorityChoice array list and removes it if used
        for (int a = 0; a < 7; a++) {
            productPriority.getItems().add((a + 1) + "");
        }
        for (int a = 0; a < products.size(); a++) {
            for (int b = 0; b < productPriority.getItems().size(); b++) {
                if (products.get(a).getItemPriority().equalsIgnoreCase(productPriority.getItems().get(b))) {
                    productPriority.getItems().remove(productPriority.getItems().get(b));
                }
            }
        }
    }

    //Returns user to shoppingCartController.java
    public void handleitemReturnCart(ActionEvent event) throws IOException {

        Parent shoppingCart_page = FXMLLoader.load(getClass().getResource("shoppingcart.fxml"));
        Scene shoppingCart_scene = new Scene(shoppingCart_page);
        Stage shoppingCart_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        shoppingCart_stage.setScene(shoppingCart_scene);
        shoppingCart_stage.show();

        System.out.println("Displaying information to console: Ensuring that user returned to main page");
    }

    //Confirms if item is unique
    public static boolean itemUnique(ObservableList<Products> products, Products obj) {

        if (!products.isEmpty()) {
            for (Products item : products) {
                if (obj.getItemName().equalsIgnoreCase(item.getItemName())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Duplicate Items");
                    alert.setHeaderText("Duplicate Items");
                    alert.setContentText("Item already exists!");
                    alert.showAndWait();
                    return false;
                }
            }
        }
        return true;
    }

    //Used when the add button is hit on the addItemsController scene
    public void handleitemAdd(ActionEvent event) throws IOException {

        //Used to connect addItems Scene to shoppingCartController Scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("shoppingcart.fxml"));
        Scene shoppingCart_scene = new Scene(loader.load());
        shoppingCartController controller = loader.getController();

        //Confirm if input is valid
        if (isInputValid()) {
            Products p = new Products(productPriority.getValue(),
                    productName.getText(),
                    Double.parseDouble(productPrice.getText()),
                    Integer.parseInt(productQty.getText()));
            //Confirm that item is unique before adding to table
            if (itemUnique(products, p)) {
                products.add(p);
                productPriority.getItems().remove(productPriority.getValue());
            }

            controller.loadData(products);

            // Clear values after submitted
            productPriority.getSelectionModel().clearSelection();
            productName.clear();
            productPrice.clear();

            //Loop used to order the items listed in the table via priority allowing to be purchased in order
            for (int i = 0; i < products.size(); i++) {
                for (int j = i + 1; j < products.size(); j++) {
                    if (Integer.parseInt(products.get(i).getItemPriority()) > Integer.parseInt(products.get(j).getItemPriority())) {
                        Products temp = products.get(i);
                        products.set(i, products.get(j));
                        products.set(j, temp);
                    }
                }
            }
        }
        System.out.println("Displaying information to consoles: Ensuring the addItem method worked as expected.");
    }

    //Confirms if user input is valid
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