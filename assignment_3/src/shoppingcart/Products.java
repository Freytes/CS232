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

    public Products(String itemName, double itemPrice, String itemPriority, int itemQty) {
        this.itemName = new SimpleStringProperty(itemName);
        this.itemPrice = new SimpleDoubleProperty(itemPrice);
        this.itemPriority = new SimpleStringProperty(itemPriority);
        this.itemQty = new SimpleIntegerProperty(itemQty);
    }

    public String getItemName() {
        return itemName.get();
    }

    public void setItemName(String itemName) {
        this.itemName = new SimpleStringProperty(itemName);
    }

    public String getItemPriority() {
        return itemPriority.get();
    }

    public void setItemPriority(String itemPriority) {
        this.itemPriority = new SimpleStringProperty(itemPriority);
    }

    public int getItemQty() {
        return itemQty.get();
    }

    public void setItemQty(int itemQty) {
        this.itemQty = new SimpleIntegerProperty(itemQty);
    }

    public double getItemPrice() {
        return itemPrice.get();
    }
    public void setItemPrice(double itemPrice) {
        this.itemPrice = new SimpleDoubleProperty(itemPrice);
    }

    public String toString() {
        return String.format("%s %s", itemName, itemPriority);
    }
}