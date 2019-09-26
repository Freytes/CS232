package shoppingcart;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Main extends Application {

    Stage window;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;

        window.setTitle("Shopping Cart");

        Parent root = FXMLLoader.load(getClass().getResource("shoppingcart.fxml"));


        window.setScene(new Scene(root, 300, 275));
        window.show();
    }

}