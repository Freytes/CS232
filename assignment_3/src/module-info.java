module assignment_3 {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.swing;

    opens shoppingcart;
    opens shoppingcart.model;
    opens shoppingcart.view;

}