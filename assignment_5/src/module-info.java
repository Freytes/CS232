module assignment_5 {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.swing;
    requires java.sql;
    requires sqlite.jdbc;

    opens main.java.shoppingcart;
    opens main.java.shoppingcart.model;
    opens main.java.shoppingcart.view;

}