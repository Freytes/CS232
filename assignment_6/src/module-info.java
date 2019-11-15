module assignment_6 {
    requires javafx.fxml;
    requires javafx.controls;
    requires java.sql;

    opens main.java.shoppingcart;
    opens main.java.shoppingcart.model;
    opens main.java.shoppingcart.view;
}