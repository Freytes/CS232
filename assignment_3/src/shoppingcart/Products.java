package shoppingcart;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Products {
    //Variables used to populated Shopping Cart Table
    private SimpleStringProperty itemName;
    private SimpleStringProperty itemPriority;
    private SimpleIntegerProperty itemQty;
    private SimpleDoubleProperty itemPrice;

    public Products( String itemPriority, String itemName, double itemPrice, int itemQty) {
        this.itemPriority = new SimpleStringProperty(itemPriority);
        this.itemName = new SimpleStringProperty(itemName);
        this.itemPrice = new SimpleDoubleProperty(itemPrice);
        this.itemQty = new SimpleIntegerProperty(itemQty);

        // Initial dummy data

    this.itemPriority = new SimpleStringProperty("-1");
    this.itemName = new SimpleStringProperty("dummy data");
    this.itemPrice = new SimpleDoubleProperty(0.0);
    this.itemQty = new SimpleIntegerProperty(0);
}



    public String getItemName() {
        return itemName.get();
    }

    public void setItemName(String itemName) {
        this.itemName.set(itemName);
    }

    public SimpleStringProperty itemNameProperty() {
        return itemName;
    }

    public String getItemPriority() {
        return itemPriority.get();
    }

    public void setItemPriority(String itemPriority) {
        this.itemPriority.set(itemPriority);
    }

    public SimpleStringProperty itemPriorityProperty() {
        return itemPriority;
    }

    public int getItemQty() {
        return itemQty.get();
    }

    public void setItemQty(int itemQty) {
        this.itemQty.set(itemQty);
    }

    public SimpleIntegerProperty itemQtyProperty() {
        return itemQty;
    }
    public double getItemPrice() {
        return itemPrice.get();
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice.set(itemPrice);
    }
    public SimpleDoubleProperty itemPriceProperty() {
        return itemPrice;
    }
}