package shoppingcart;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Main extends Application {

    //Calling Controller Class
    Controller callClass = new Controller();

    //Initializing Main Window
    Stage mainWindow;

    //Initializing Main Scene
    Scene mainScene;

    //Initializing addItem Scene
    Scene addItem_Scene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        mainWindow = primaryStage;

        // Setting Window Title
        mainWindow.setTitle("Shopping Cart");

        Parent root = FXMLLoader.load(getClass().getResource("shoppingcart.fxml"));
        mainWindow.setScene(new Scene(root));

        mainWindow.show();
    }
}
