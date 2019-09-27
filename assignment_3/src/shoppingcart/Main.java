package shoppingcart;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

 /*   public ObservableList<Products> getProduct(){

            /*        item_Priority.setCellValueFactory(new PropertyValueFactory<>("ItemPriority"));
        item_Name.setCellValueFactory(new PropertyValueFactory<>("ItemName"));
        item_Qty.setCellValueFactory(new PropertyValueFactory<>("ItemQty"));
        item_Price.setCellValueFactory(new PropertyValueFactory<>("ItemPrice"));
        item_Table.setItems(observableList);*/

    /*
    ObservableList<Products> observableList = FXCollections.observableArrayList(
            new Products("Cookies", 2.00, "2", 1)
    );

    }*/
}
