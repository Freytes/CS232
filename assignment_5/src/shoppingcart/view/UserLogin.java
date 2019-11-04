package shoppingcart.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UserLogin extends shoppingCartController {


    @FXML
    public TextField username  = new TextField();

    @FXML
    public TextField password = new TextField();

    @FXML
    public Button submit = new Button();

    static String uname = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //connect to database to create USERS table
        db.userconnect();
    }

    public void handleuserLogin(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        if (isInputValid()) {

            //captures current database connection to USERS table
            Connection connection = db.getuserConnection();

            //initializes a prepared statement
            PreparedStatement ps = null;

            //initializes a resultset
            ResultSet rs = null;

            //Captures username from textfield
            uname = username.getText();

            //Captures password from textfield
            String upassword = password.getText();

            //Creates the prepared statement
            ps = connection.prepareStatement("select * from users where username = ? and password = ? ");
            //Adds the Username field to the SQL Query
            ps.setString(1,uname);
            //Adds the password field to the SQL Query
            ps.setString(2,upassword);
            //Executes the entire SQL query
            rs = ps.executeQuery();

            if (rs.next()){
               Parent addItem_page = FXMLLoader.load(getClass().getResource("shoppingcart.fxml"));
                Scene addItem_scene = new Scene(addItem_page);
                Stage addItem_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                addItem_stage.setScene(addItem_scene);
                addItem_stage.show();

                username.clear();
                password.clear();

                System.out.println("Displaying information to console: Login was successful");
            }
            else{

                String errorMessage = "User"+ " " + uname + " " + "does not exist, or password is incorrect!";

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Fields");
                alert.setHeaderText("Please correct invalid fields");
                alert.setContentText(errorMessage);
                alert.showAndWait();

                username.clear();
                password.clear();
            }


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