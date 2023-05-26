package com.OOP;
public class ErrorHandling
{
    class customerNotFoundMessage extends RuntimeException
    {
        public customerNotFoundMessage()
        {
            super("Customer Not Found!");
        }
    }
    class productNotFoundMessage extends RuntimeException
    {
        public productNotFoundMessage()
        {
            super("Product Not Found!");
        }
    }
    class invalidProductOptionsMessage extends RuntimeException
    {
        public invalidProductOptionsMessage()
        {
            super("Invalid Product Options Entered!");
        }
    }
    class invalidCustomerNameMessage extends RuntimeException
    {
        public invalidCustomerNameMessage()
        {
            super ("Invalid Customer Name!");
        }
    }
    class invalidCustomerAddressMessage extends RuntimeException
    {
        public invalidCustomerAddressMessage()
        {
            super ("Invalid Customer address!");
        }
    }

    class productOutOfStockMessage extends RuntimeException
    {
        public productOutOfStockMessage()
        {
            super ("Product Currently Out of Stock!");
        }
    }
    class orderNotFoundMessage extends RuntimeException
    {
        public orderNotFoundMessage()
        {
            super ("Order Number Not Found!");
        }
    }
    class cartItemNotFoundMessage extends RuntimeException
    {
        public cartItemNotFoundMessage()
        {
            super ("Cart Item Not Found!");
        }
    }
}
