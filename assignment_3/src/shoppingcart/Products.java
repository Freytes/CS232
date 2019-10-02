package shoppingcart;

import javafx.beans.property.*;

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
    }

    public String getItemName() {
        return itemName.get();
    }

    public SimpleStringProperty itemNameProperty() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName.set(itemName);
    }

    public String getItemPriority() {
        return itemPriority.get();
    }

    public SimpleStringProperty itemPriorityProperty() {
        return itemPriority;
    }

    public void setItemPriority(String itemPriority) {
        this.itemPriority.set(itemPriority);
    }

    public int getItemQty() {
        return itemQty.get();
    }

    public SimpleIntegerProperty itemQtyProperty() {
        return itemQty;
    }

    public void setItemQty(int itemQty) {
        this.itemQty.set(itemQty);
    }

    public double getItemPrice() {
        return itemPrice.get();
    }

    public SimpleDoubleProperty itemPriceProperty() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice.set(itemPrice);
    }
}