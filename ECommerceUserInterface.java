package com.OOP;
import java.io.IOException;
import java.util.Scanner;

// Simulation of a Simple E-Commerce System (like Amazon)

public class ECommerceUserInterface extends ErrorHandling
{
    public static void main(String[] args) throws IOException {
        // Create the system
        ECommerceSystem amazon = new ECommerceSystem();

        Scanner scanner = new Scanner(System.in);
        System.out.print(">");

        // Process keyboard actions
        while (scanner.hasNextLine())
        {
            try {
                String action = scanner.nextLine();

                if (action == null || action.equals("")) {
                    System.out.print("\n>");
                    continue;
                } else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
                    return;

                else if (action.equalsIgnoreCase("PRODS"))    // List all products for sale
                {
                    amazon.printAllProducts();
                } else if (action.equalsIgnoreCase("BOOKS"))    // List all books for sale
                {
                    amazon.printAllBooks();
                } else if (action.equalsIgnoreCase("CUSTS"))    // List all registered customers
                {
                    amazon.printCustomers();
                } else if (action.equalsIgnoreCase("ORDERS")) // List all current product orders
                {
                    amazon.printAllOrders();
                } else if (action.equalsIgnoreCase("SHIPPED"))    // List all orders that have been shipped
                {
                    amazon.printAllShippedOrders();
                } else if (action.equalsIgnoreCase("NEWCUST"))    // Create a new registered customer
                {
                    String name = "";
                    String address = "";

                    System.out.print("Name: ");
                    if (scanner.hasNextLine())
                        name = scanner.nextLine();

                    System.out.print("\nAddress: ");
                    if (scanner.hasNextLine())
                        address = scanner.nextLine();

                    boolean success = amazon.createCustomer(name, address);
                    if (!success) {
                        System.out.println(amazon.getErrorMessage());
                    }
                } else if (action.equalsIgnoreCase("SHIP"))    // ship an order to a customer
                {
                    String orderNumber = "";

                    System.out.print("Order Number: ");
                    // Get order number from scanner
                    if (scanner.hasNextLine())
                        orderNumber = scanner.nextLine();
                    // Ship order to customer (see ECommerceSystem for the correct method to use
                    boolean success = amazon.shipOrder(orderNumber);
                    if (!success) {
                        System.out.println(amazon.getErrorMessage());
                    }
                } else if (action.equalsIgnoreCase("CUSTORDERS")) // List all the current orders and shipped orders for this customer id
                {
                    String customerId = "";

                    System.out.print("Customer Id: ");
                    // Get customer Id from scanner
                    if (scanner.hasNext())
                        customerId = scanner.next();
                    // Print all current orders and all shipped orders for this customer
                    boolean success = amazon.printOrderHistory(customerId);
                    if (!success) {
                        System.out.println(amazon.getErrorMessage());
                    }
                } else if (action.equalsIgnoreCase("ORDER")) // order a product for a certain customer
                {
                    String productId = "";
                    String customerId = "";

                    System.out.print("Product Id: ");
                    // Get product Id from scanner
                    if (scanner.hasNext())
                        productId = scanner.next();

                    System.out.print("\nCustomer Id: ");
                    // Get customer Id from scanner
                    if (scanner.hasNext())
                        customerId = scanner.next();
                    // Order the product. Check for valid orderNumber string return and for error message set in ECommerceSystem
                    String prodOrder = amazon.orderProduct(productId, customerId, "");
                    if (prodOrder == null) {
                        System.out.println(amazon.getErrorMessage());
                    }
                    // Print Order Number string returned from method in ECommerceSystem
                    else {
                        System.out.println(prodOrder);
                    }
                } else if (action.equalsIgnoreCase("ORDERBOOK")) // order a book for a customer, provide a format (Paperback, Hardcover or EBook)
                {
                    String productId = "";
                    String customerId = "";
                    String options = "";

                    System.out.print("Product Id: ");
                    // get product id
                    if (scanner.hasNext())
                        productId = scanner.next();
                    System.out.print("\nCustomer Id: ");
                    // get customer id
                    if (scanner.hasNext())
                        customerId = scanner.next();
                    System.out.print("\nFormat [Paperback Hardcover EBook]: ");
                    // get book format and store in options string
                    if (scanner.hasNext())
                        options = scanner.next();
                    // Order product. Check for error message set in ECommerceSystem
                    // Print order number string if order number is not null
                    String bookOrder = amazon.orderProduct(productId, customerId, options);
                    if (bookOrder == null) {
                        System.out.println(amazon.getErrorMessage());
                    } else {
                        System.out.println(bookOrder);
                    }

                } else if (action.equalsIgnoreCase("ORDERSHOES")) // order shoes for a customer, provide size and color
                {
                    String productId = "";
                    String customerId = "";
                    String options = "";

                    System.out.print("Product Id: ");
                    // get product id
                    if (scanner.hasNext())
                        productId = scanner.next();
                    System.out.print("\nCustomer Id: ");
                    // get customer id
                    if (scanner.hasNext())
                        customerId = scanner.next();
                    System.out.print("\nSize: \"6\" \"7\" \"8\" \"9\" \"10\": ");
                    // get shoe size and store in options
                    if (scanner.hasNext())
                        options = scanner.next();
                    System.out.print("\nColor: \"Black\" \"Brown\": ");
                    // get shoe color and append to options
                    if (scanner.hasNext())
                        options = options + " " + scanner.next();
                    //order shoes
                    String shoeOrder = amazon.orderProduct(productId, customerId, options);
                    if (shoeOrder == null) {
                        System.out.println(amazon.getErrorMessage());
                    } else {
                        System.out.println(shoeOrder);
                    }

                } else if (action.equalsIgnoreCase("CANCEL")) // Cancel an existing order
                {
                    String orderNumber = "";

                    System.out.print("Order Number: ");
                    // get order number from scanner
                    if (scanner.hasNext()) {
                        orderNumber = scanner.next();
                    }
                    // cancel order. Check for error
                    boolean success = amazon.cancelOrder(orderNumber);
                    if (!success) {
                        System.out.println(amazon.getErrorMessage());
                    }
                } else if (action.equalsIgnoreCase("BOOKSBYAUTHOR")) {
                    String authorName = "";
                    System.out.print("Author's Name: ");
                    if (scanner.hasNext()) // gets the author name from the user
                        authorName = scanner.next();
                    amazon.printAuthorBooks(authorName);
                } else if (action.equalsIgnoreCase("PRINTBYPRICE")) // sort products by price
                {
                    amazon.printByPrice();
                } else if (action.equalsIgnoreCase("PRINTBYNAME")) // sort products by name (alphabetic)
                {
                    amazon.printByName();
                } else if (action.equalsIgnoreCase("SORTCUSTS")) // sort products by name (alphabetic)
                {
                    amazon.sortCustomersByName();
                } else if (action.equalsIgnoreCase("STATS")) {
                    amazon.printStats();
                } else if (action.equalsIgnoreCase("ADDTOCART")) {
                    String productId = "";
                    String customerId = "";
                    String productOptions = "";

                    System.out.print("Product Id: ");
                    if (scanner.hasNextLine())
                        productId = scanner.nextLine();

                    System.out.print("\nCustomer Id: ");
                    if (scanner.hasNextLine())
                        customerId = scanner.nextLine();

                    System.out.print("\nSize (6, 7, 8, 9, 10) and Color (Black or Brown): ");
                    System.out.print("\nIf product is not a shoe, press enter! ");
                    if (scanner.hasNextLine())
                        productOptions = scanner.nextLine();

                    System.out.print("\nBook Options: Paperback  HardCover EBook : ");
                    System.out.print("\nIf product is not a book, press enter! ");
                    if (scanner.hasNextLine())
                        productOptions = scanner.nextLine();

                    String cart = amazon.addToCart(productId, customerId, productOptions);
                    System.out.println(cart);
                } else if (action.equalsIgnoreCase("REMCARTITEM")) {
                    String productId = "";
                    String customerId = "";

                    System.out.print("Product Id: ");
                    if (scanner.hasNextLine())
                        productId = scanner.nextLine();

                    System.out.print("\nCustomer Id: ");
                    if (scanner.hasNextLine())
                        customerId = scanner.nextLine();
                    String cart = amazon.removeCart(productId, customerId);
                    System.out.println(cart);
                } else if (action.equalsIgnoreCase("PRINTCART")) {
                    String customerId = "";
                    System.out.println("Customer Id: ");
                    if (scanner.hasNext()) {
                        customerId = scanner.next();
                    }
                    amazon.printCart(customerId);
                    System.out.println();
                } else if (action.equalsIgnoreCase("ORDERITEMS"))
                {
                    String customerId = "";

                    System.out.print("Customer Id: ");
                    if (scanner.hasNextLine())
                        customerId = scanner.nextLine();

                    // Prints all current orders and all shipped orders for this customer
                    boolean success = amazon.orderItems(customerId);
                    if (!success)
                    {
                        System.out.println(amazon.getErrorMessage());
                    }
                }
                System.out.print("\n>");
            }catch (customerNotFoundMessage | invalidCustomerAddressMessage | invalidCustomerNameMessage | productNotFoundMessage | invalidProductOptionsMessage | productOutOfStockMessage | orderNotFoundMessage x)
            {
                System.out.println(x.getMessage());
            }

        }
    }
}
