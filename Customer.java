package com.OOP;

import java.util.ArrayList;
/*
 *  class Customer defines a registered customer. It keeps track of the customer's name and address.
 *  A unique id is generated when when a new customer is created.
 */
public class Customer
{
    private String id;
    private String name;
    private Cart cart;
    private String shippingAddress;

    public Customer(String id)
    {
        this.id = id;
        this.name = "";
        this.cart = new Cart();
        this.shippingAddress = "";
    }

    public Customer(String id, String name, String address)
    {
        this.id = id;
        this.cart = new Cart();
        this.name = name;
        this.shippingAddress = address;
    }

    public String getId()
    {
        return id;
    }
    public void setId(String id)
    {
        this.id = id;
    }
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
    public Cart getCart()
    {
        return cart;
    }

    public void setCart(Cart cart)
    {
        this.cart = cart;
    }

    public void addItem(CartItem cartItem)
    {
        this.cart.addItem(cartItem);
    }

    public void removeItem(int index)
    {
        this.cart.removeItem(index);
    }

    public String getShippingAddress()
    {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress)
    {
        this.shippingAddress = shippingAddress;
    }

    public void print()
    {
        System.out.printf("\nName: %-20s ID: %3s Address: %-35s", name, id, shippingAddress);
    }

    public boolean equals(Object other)
    {
        Customer otherC = (Customer) other;
        return this.id.equals(otherC.id);
    }

}