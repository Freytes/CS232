package shoppingcart;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class Main extends Application {

    //Initializing Main Window
    Stage mainWindow;

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
