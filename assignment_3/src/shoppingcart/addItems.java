package shoppingcart;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import static shoppingcart.shoppingCartController.products;


public class addItems implements Initializable {

    // Priority Choice Values
   private String[] priorityChoice =
           {"1", "2", "3", "4", "5", "6", "7"};

    //ArrayList<String> priorityChoice = new ArrayList<>();


    // Fields used to add items to cart//
    @FXML
    private TextField productName = new TextField();
    @FXML
    private TextField productQty = new TextField();
    @FXML
    private TextField productPrice = new TextField();
    @FXML
    private ComboBox<String> productPriority = new ComboBox<String>(FXCollections.observableArrayList(Arrays.toString(priorityChoice)));

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Populating items for the ProductPriority Array
        arrayItems();
    }

    public void handleitemAdd(ActionEvent event) throws IOException {

        //Used to connect addItems Scene to shoppingCartController Scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("shoppingcart.fxml"));
        Scene shoppingCart_scene = new Scene(loader.load());
        shoppingCartController controller = loader.getController();

     if(isInputValid()){
         products.addAll(new Products(productPriority.getValue(),
                 productName.getText(),
                 Double.parseDouble(productPrice.getText()),
                 Integer.parseInt(productQty.getText())));

         controller.loadData(products);

         // Clear values
         productPriority.setPromptText("Please Select Priority");
         productName.clear();
         productPrice.clear();
         productQty.clear();

     }

            System.out.println("Displaying information to consoles: Ensuring the addItem method worked as expected.");
        }

        public void handleitemReturnCart (ActionEvent event) throws IOException {

            Parent shoppingCart_page = FXMLLoader.load(getClass().getResource("shoppingcart.fxml"));
            Scene shoppingCart_scene = new Scene(shoppingCart_page);
            Stage shoppingCart_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            shoppingCart_stage.setScene(shoppingCart_scene);
            shoppingCart_stage.show();

            System.out.println("Displaying information to console: Ensuring that user returned to main page");

        }

        public String arrayItems () {

            //Used to Initialize the Scene
            productPriority.getItems().addAll(priorityChoice);
            return String.valueOf(productPriority);
        }

        public boolean isInputValid() {
            String errorMessage = "";

            if (productName.getText() == null || productName.getText().length() == 0 || !productName.getText().matches("\"|[-\\\\+]?|[-\\\\+]?\\\\d+\\\\.?|[-\\\\+]?\\\\d+\\\\.?\\\\d+\"")) {
                errorMessage += "Not a valid Product Name!\n";
            }
            if (productPrice.getText() == null || productPrice.getText().length() == 0) {
                errorMessage += "Not a valid Product Price!\n";
            } else {
                // try to parse the postal code into an int.
                try {
                    Integer.parseInt(productPrice.getText());
                } catch (NumberFormatException e) {
                    errorMessage += "Not a valid Product Price (must be an integer)!\n";
                }
            }
            if (productQty.getText() == null || productQty.getText().length() == 0) {
                errorMessage += "Not a valid Product Qty!\n";
            } else {
                // try to parse the postal code into an int.
                try {
                    Integer.parseInt(productQty.getText());
                } catch (NumberFormatException e) {
                    errorMessage += "Not a valid Product Qty (must be an integer)!\n";
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