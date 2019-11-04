package shoppingcart.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserLogin extends shoppingCartController {

    @FXML
    public TextField username  = new TextField();

    @FXML
    public TextField password = new TextField();

    @FXML
    public Button submit = new Button();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //connect to database to create USERS table
        db.userconnect();
    }

    public void handleuserLogin(ActionEvent event) throws IOException {
        if (isInputValid()) {

            //Captures username from textfield
            String uname = username.getText();

            //Captures password from textfield
            String upassword = password.getText();

        }
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (username.getText() == null || username.getText().length() == 0 || !username.getText().matches("[a-zA-Z]*")  ) {
            errorMessage += "Not a valid User Name!\n";
        }
        if (password.getText() == null || password.getText().length() == 0 ) {
            errorMessage += "Password cannot be left blank!\n";
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