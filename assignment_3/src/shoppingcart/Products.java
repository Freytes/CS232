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

    public void setItemName(String itemName) {
        this.itemName = new SimpleStringProperty(itemName);
    }
    public StringProperty itemNameProperty() {
        return itemName;
    }

    public String getItemPriority() {
        return itemPriority.get();
    }

    public void setItemPriority(String itemPriority) {
        this.itemPriority = new SimpleStringProperty(itemPriority);
    }
    public StringProperty itemPriorityProperty() {
        return itemPriority;
    }
    public int getItemQty() {
        return itemQty.get();
    }

    public void setItemQty(int itemQty) {
        this.itemQty = new SimpleIntegerProperty(itemQty);
    }
    public IntegerProperty itemQtyProperty() {
        return itemQty;
    }

    public double getItemPrice() {
        return itemPrice.get();
    }
    public void setItemPrice(double itemPrice) {
        this.itemPrice = new SimpleDoubleProperty(itemPrice);
    }

    public DoubleProperty itemPriceProperty() {
        return itemPrice;
    }

    public String toString() {
        return String.format("%s %s", itemName, itemPriority);
    }
}