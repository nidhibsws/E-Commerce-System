package com.OOP;
import java.util.ArrayList;
public class Cart
{
    private ArrayList<CartItem> items;

    public Cart()
    {
        items = new ArrayList<CartItem>();
    }

    public Cart(ArrayList<CartItem> item)
    {
        this.items = item;
    }

    public void setItems(ArrayList<CartItem> item)
    {
        this.items = item;
    }

    public ArrayList<CartItem> getItems()
    {
        return items;
    }

    public void addItem(CartItem cartItem)
    {
        items.add(cartItem);
    }

    public void removeItem(int cartItem)
    {
        items.remove(cartItem);
    }
    public int findItem(String productItem)
    {
        for(int i=0;i<items.size();i++)
        {
            if(items.get(i).getItem().getId().equals(productItem))
            {
                return i;
            }
        }
        return -1;
    }
    public void print()
    {
        for(int i=0;i<items.size();i++)
        {
            items.get(i).print();
        }
    }
}