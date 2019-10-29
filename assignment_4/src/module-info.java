module assignment_4 {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.swing;
    requires java.sql;

    opens shoppingcart;
    opens shoppingcart.model;
    opens shoppingcart.view;

}