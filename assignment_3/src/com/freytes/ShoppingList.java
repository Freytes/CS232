package com.freytes;

import java.util.ArrayList;
import java.util.Scanner;

class ShoppingList
{
    ArrayList<ShoppingItem> obj=new ArrayList<ShoppingItem>();
    public void addItem()
    {
        System.out.println();

        System.out.println("Enter the name of item");

        Scanner sc = new Scanner(System.in);

        String ItemName = sc.nextLine();

        System.out.println("Enter the price of your item");

        double ItemPrice = sc.nextDouble();

        System.out.println("Enter the Priority of your item");

        int ItemQty = sc.nextInt();

        ShoppingItem Item = new ShoppingItem(ItemName, ItemPrice,ItemQty);

        obj.add(Item);
// sc.close();
    }
    public void displayItem()
    {
        System.out.println( obj.size()+ " items. ");
        for (ShoppingItem x : obj)
        {
            System.out.println(x.toString());
        }

    }
}