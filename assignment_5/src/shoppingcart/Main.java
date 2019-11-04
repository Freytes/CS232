package shoppingcart;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {

    //Initializing Main Window
    public Stage mainWindow;

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.mainWindow = primaryStage;
        this.mainWindow.setTitle("Shopping Cart");

        //Loads Initial Layout
        initLayout();
    }

    //Creates Initial Layout, displays error message if couldn't load
    public void initLayout(){
        try{
            // Load root layout from fxml file.
            Parent root = FXMLLoader.load(getClass().getResource("view/userLogin.fxml"));

            // Show the scene from the root layout.
            mainWindow.setScene(new Scene(root));
            mainWindow.show();

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}