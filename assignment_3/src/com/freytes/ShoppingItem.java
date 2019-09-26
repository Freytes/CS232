package com.freytes;

class ShoppingItem
{
    private String ItemName;
    private double ItemPrice;
    private int ItemQty;
    public ShoppingItem()
    {
        ItemName = "Apple";
        ItemPrice = 100;
        ItemQty = 1;
    }
    public ShoppingItem(String ItemName, double ItemPrice, int ItemQty )
    {
        this.ItemName = ItemName;
        this.ItemPrice = ItemPrice;
        this.ItemQty = ItemQty;
    }
    public String getItemName()
    {
        return ItemName;
    }
    public double getItemPrice()
    {
        return ItemPrice;
    }
    public double getItemTotalPrice()
    {
        return ItemPrice * ItemQty;
    }
    public int getItemQty()
    {
        return ItemQty;
    }

    public void setItemName(String ItemName)
    {
        this.ItemName = ItemName;
    }

    public void setItempPrice(double ItemPrice)
    {
        this.ItemPrice = ItemPrice;
    }
    public void setItemQty(int ItemQty)
    {
        this.ItemQty = ItemQty;
    }
    public String toString()
    {
        String state = ItemName + " - $" + ItemPrice + " x " + ItemQty;
        return state;
    }
}