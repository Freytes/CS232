package shoppingcart;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    //Initializing Main Window
    public Stage mainWindow;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        mainWindow = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("shoppingcart.fxml"));
        mainWindow.setScene(new Scene(root));
        mainWindow.show();
    }
}