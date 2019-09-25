package shoppingcart;

public class cartItems {
    private String itemName;
    private String itemQty;
    private String itemPriority;


    //Getter [Item Name]
    public String getName() {
        return itemName;
    }

    //Setter [Item Name]
    public void setName(String newName) {
        this.itemName = newName;
    }

    //Getter [Item Quantity]
    public String getQty() {
        return itemQty;
    }

    //Setter [Item Quantity]
    public void setQty(String newQty) {
        this.itemQty = newQty;
    }

    //Getter [Item Priority]
    public String getPriority() {
        return itemPriority;
    }

    //Setter [Item Priority]
    public void setPriority(String newPriority) {
        this.itemPriority = newPriority;
    }

}
