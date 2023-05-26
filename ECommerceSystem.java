package com.OOP;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/*
 * Models a simple ECommerce system. Keeps track of products for sale, registered customers, product orders and
 * orders that have been shipped to a customer
 */
public class ECommerceSystem extends ErrorHandling
{
    private HashMap<String,Product>  products = new HashMap<>();
    private ArrayList<Customer> customers = new ArrayList<Customer>();

    private ArrayList<ProductOrder> orders   = new ArrayList<ProductOrder>();
    private ArrayList<ProductOrder> shippedOrders   = new ArrayList<ProductOrder>();
    private ArrayList<Book> booksByAuthor   = new ArrayList<Book>(); // List to store books by author
    private Map <Product,Integer> statsMap = new LinkedHashMap<>();


    // These variables are used to generate order numbers, customer id's, product id's
    private int orderNumber = 500;
    private int customerId = 900;
    private int productId = 700;

    // General variable used to store an error message when something is invalid (e.g. customer id does not exist)
    String errMsg = null;

    // Random number generator
    Random random = new Random();

    public ECommerceSystem()
    {
        // NOTE: do not modify or add to these objects!! - the TAs will use for testing
        // If you do the class Shoes bonus, you may add shoe products

        // Adding the shoe products from assignment 1
        products.put(generateProductId(),new Shoes("Nike Air Force 1", generateProductId(), 135.0, 35, 26, 46, 54, 29, 11,47,32,53,24, 0));
        productId--;
        products.put(generateProductId(),new Shoes("Nike Dunk Low", generateProductId(), 135.0, 34, 17, 34, 56, 11, 27,34,70,12,43, 0));
        productId--;
        products.put(generateProductId(),new Shoes("Nike Blazer Mid '77", generateProductId(), 155.0, 45, 67, 23, 44, 25,11,56,56,65,65, 0));
        productId--;
        products.put(generateProductId(),new Shoes("Nike Blazer Low", generateProductId(), 115.0, 47, 12, 99, 80, 50, 23,71,32,45,67, 0));

        // If an IOException occurs in the private method, print out the IOException message and exit program
        try{
            productsFileReader();
        }
        catch(IOException e){
            e.printStackTrace();
            System.exit(1);
        }
        // Create some customers. Notice how generateCustomerId() method is used
        customers.add(new Customer(generateCustomerId(),"Inigo Montoya", "1 SwordMaker Lane, Florin"));
        customers.add(new Customer(generateCustomerId(),"Prince Humperdinck", "The Castle, Florin"));
        customers.add(new Customer(generateCustomerId(),"Andy Dufresne", "Shawshank Prison, Maine"));
        customers.add(new Customer(generateCustomerId(),"Ferris Bueller", "4160 Country Club Drive, Long Beach"));
    }
    private HashMap<String, Product> productsFileReader () throws FileNotFoundException
    {
        FileReader productsReader = new FileReader("products.txt");
        Scanner scan = new Scanner(productsReader);
        while (scan.hasNextLine()){
            String line = scan.nextLine();
            if(line.equals("GENERAL")){
                String name = scan.nextLine();
                Double price = Double.parseDouble(scan.nextLine());
                int stock = Integer.parseInt(scan.nextLine());
                String productId = generateProductId();
                products.put(productId,new Product(name, productId, price, stock, Product.Category.GENERAL, 0));
            }
            else if(line.equals("COMPUTERS")){
                String name = scan.nextLine();
                Double price = Double.parseDouble(scan.nextLine());
                int stock = Integer.parseInt(scan.nextLine());
                String productId = generateProductId();
                products.put(productId,new Product(name, productId, price, stock, Product.Category.COMPUTERS, 0));
            }
            else if(line.equals("FURNITURE")){
                String name = scan.nextLine();
                Double price = Double.parseDouble(scan.nextLine());
                int stock = Integer.parseInt(scan.nextLine());
                String productId = generateProductId();
                products.put(productId,new Product(name, productId, price, stock, Product.Category.FURNITURE, 0));
            }
            else if(line.equals("CLOTHING")) {
                String name = scan.nextLine();
                Double price = Double.parseDouble(scan.nextLine());
                int stock = Integer.parseInt(scan.nextLine());
                String productId = generateProductId();
                products.put(productId, new Product(name, productId, price, stock, Product.Category.CLOTHING, 0));
            }
            else if (line.equals("BOOKS")){
                String name = scan.nextLine();
                double price = Double.parseDouble(scan.nextLine());
                String stock = scan.nextLine();
                Scanner scan2 = new Scanner(stock);
                scan2.useDelimiter(" ");
                int hardcoverStock = Integer.parseInt(scan2.next());
                int paperbackStock = Integer.parseInt(scan2.next());
                String BookDetails = scan.nextLine();
                Scanner scan3 = new Scanner(BookDetails);
                scan3.useDelimiter(":");
                String title = scan3.next();
                String authorName = scan3.next();
                int year = Integer.parseInt(scan3.next());
                String productId = generateProductId();
                products.put(productId,new Book(name,productId,price,paperbackStock,hardcoverStock,title,authorName,year,0));
            }
        }
        return products;
    }
    private String generateOrderNumber()
    {
        return "" + orderNumber++;
    }

    private String generateCustomerId()
    {
        return "" + customerId++;
    }

    private String generateProductId()
    {
        return "" + productId++;
    }

    public String getErrorMessage()
    {
        return errMsg;
    }

    public void printAllProducts ()
    {
        for (Map.Entry <String, Product> entry : products.entrySet()){
            entry.getValue().print();
        }
    }

    // Print all products that are books. See getCategory() method in class Product
    public void printAllBooks ()
    {
        for (Map.Entry <String, Product> entry : products.entrySet()){
            if(entry.getValue().getCategory().equals(Product.Category.BOOKS)){
                entry.getValue().print();
            }
        }
    }

    // Print all current orders
    public void printAllOrders()
    {
        for (ProductOrder p : orders) {
            p.print();
        }
    }

    public void printAlLBooksByAuthor()
    {
        for (Book b : booksByAuthor) {
            b.print();
        }
    }
    // Print all shipped orders
    public void printAllShippedOrders()
    {
        for (ProductOrder p : shippedOrders)
        {
            p.print();
        }
    }

    // Print all customers
    public void printCustomers()
    {
        for(Customer c : customers){
            c.print();
        }
    }
    /*
     * Given a customer id, print all the current orders and shipped orders for them (if any)
     */
    public boolean printOrderHistory(String customerId)
    {
        // Make sure customer exists - check using customerId
        // If customer does not exist, set errMsg String and return false
        // see video for an appropriate error message string
        // ... code here
        Customer cust = null;
        for (Customer c : customers)
        {
            if (c.getId().equals(customerId))
            {
                cust = c;
            }
            else
            {
                throw new customerNotFoundMessage();
            }
        }
        if (cust == null)
        {
            return false;
        }
        // Print current orders of this customer
        System.out.println("Current Orders of Customer " + customerId);
        // enter code here
        for (ProductOrder p : orders)
        {
            if (p.getCustomer().equals(cust))
            {
                p.print();
            }
        }
        // Print shipped orders of this customer
        System.out.println("\nShipped Orders of Customer " + customerId);
        //enter code here
        for (ProductOrder p : shippedOrders)
        {
            if (p.getCustomer().equals(cust))
            {
                p.print();
            }
        }
        return true;
    }

    public String orderProduct(String productId, String customerId, String productOptions)
    {
        Customer cust = null;
        for (Customer c : customers)
        {
            if (c.getId().equals(customerId))
            {
                cust = c;
            }
            else
            {
                errMsg = "Customer " + customerId + " Not Found";
            }
        }
        if (cust == null)
        {
            return null;
        }
        // Check to see if product object with productId exists in array list of products
        // if it does not, set errMsg and return null (see video for appropriate error message string)
        // else get the Product object
        Product prod = null;
        for (Map.Entry <String, Product> entry : products.entrySet()){ // iterating through the products list
            if (entry.getValue().getId().equals(productId)) {
                prod = entry.getValue();
            } else {
                errMsg = "Product " + productId + " Not Found";
            }
        }
        if (prod == null)
            return null;
        // Check if the options are valid for this product (e.g. Paperback or Hardcover or EBook for Book product)
        // See class Product and class Book for the method validOptions()
        // If options are not valid, set errMsg string and return null;
        if (productOptions != "")
        {
            if (prod.validOptions(productOptions) == false) // Checking if options are not valid
            {
                // Displays error message if user selects invalid option for book product
                if (prod.getCategory().equals(Product.Category.BOOKS))
                {
                    errMsg = "Product Book ProductId " + productId + " Invalid Options: " + productOptions;
                    return null;
                }
                // Displays error message if user selects invalid option for shoe product
                else if (prod.getCategory().equals(Product.Category.CLOTHING))
                {
                    errMsg = "Product Shoe ProductId " + productId + " Invalid Options: " + productOptions;
                    return null;
                }
                // Displays error message if user selects productID for wrong product category
                else
                {
                    errMsg = "Invalid Product Category for ProductID " + productId;
                    return null;
                }

            }
        }

        else if (productOptions == "")
        {
            if (prod.getCategory().equals(Product.Category.BOOKS)) // Checks to see if user selects book product using order action
            {
                errMsg = "Invalid Product Category for ProductID " + productId;
                return null;
            }
        }
        // Check if the product has stock available (i.e. not 0)
        // See class Product and class Book for the method getStockCount()
        // If no stock available, set errMsg string and return null

        if (prod.getStockCount(productOptions) == 0)
        {
            errMsg = "No " + productOptions + " Stock Available for ProductId " + productId; // Custom error message(not provided on video) if no stock available
            return null;
        }

        // Create a ProductOrder, (make use of generateOrderNumber() method above)
        // reduce stock count of product by 1 (see class Product and class Book)
        // Add to orders list and return order number string
        orders.add(new ProductOrder(generateOrderNumber(), prod, cust, productOptions)); // Adding new order to array list
        prod.reduceStockCount(productOptions);
        for (ProductOrder o : orders)
        {
            if(orders.size() == 1){
                statsMap.put(prod, prod.getStats());
                prod.increaseStatsCount(prod.getId());
            }
            else if(prod.getId().equals(o.getProduct().getId())){
                prod.increaseStatsCount(prod.getId());
                break;
            }
            else{
                statsMap.put(prod, prod.getStats());
            }
        }
        return "Order #"+ (orderNumber-1);
    }

    /*
     * Create a new Customer object and add it to the list of customers
     */

    public boolean createCustomer(String name, String address)
    {
        // Check name parameter to make sure it is not null or ""
        // If it is not a valid name, set errMsg (see video) and return false
        // Repeat this check for address parameter
        if (name == null || name == "") {
            throw new invalidCustomerNameMessage();
        }
        if (address == null || address == "") {
            throw new invalidCustomerAddressMessage();
        }
        // Create a Customer object and add to array list
        else {
            customers.add(new Customer(generateCustomerId(),name, address)); //Adding new customer to array list
            return true;
        }

    }

    public boolean shipOrder(String orderNumber)
    {
        // Check if order number exists first. If it doesn't, set errMsg to a message (see video)
        // and return false
        // Retrieve the order from the orders array list, remove it, then add it to the shippedOrders array list
        // return a reference to the order
        ArrayList<ProductOrder> removeShippedOrders   = new ArrayList<ProductOrder>(); // Temporary list used to hold element we want to remove
        ProductOrder temp = null;
        for (ProductOrder p : orders)
        {
            if (orderNumber.equals(p.getOrderNumber()))
            {
                removeShippedOrders.add(p);
                shippedOrders.add(p);
                p.print();
                temp = p;
                break;
            }
            else
            {
                throw new orderNotFoundMessage();
            }

        }
        orders.removeAll(removeShippedOrders);
        if (temp == null)
        {
            return false;
        }

        return true;
    }
    /*
     * Cancel a specific order based on order number
     */
    public boolean cancelOrder(String orderNumber) {
        // Check if order number exists first. If it doesn't, set errMsg to a message (see video)
        // and return false
        if (orders.isEmpty()) // Displays error message if no orders were originally placed
        {
            throw new orderNotFoundMessage();
        }
        ArrayList<ProductOrder> removedOrders = new ArrayList<ProductOrder>(); // Temporary list to hold element we want to remove
        ProductOrder temp = null;
        for (ProductOrder p : orders) {
            if (orderNumber.equals(p.getOrderNumber())) {
                removedOrders.add(p);
                temp = p;
                break;
            } else {
                throw new orderNotFoundMessage();
            }
        }
        orders.removeAll(removedOrders); // Removing element/s from original list
        if (temp == null) {
            return false;
        }
        return true;

    }
    public void printAuthorBooks (String authorName) {
        ArrayList<Product> product = new ArrayList<Product>();
        for (Map.Entry <String, Product> entry : products.entrySet()) {
            product.add(entry.getValue());
        }
        ArrayList<Book> Bks = new ArrayList<Book>();
        Book bks = null;
        for (Product b : product) {
            if (b.getCategory().equals(Product.Category.BOOKS)) { // checking to find all the products of category BOOKS
                bks = (Book) b;
                if (bks.getAuthor().equals(authorName))
                    Bks.add(bks);
            }
        }
        Comparator<Book> ByAuthorNAME = new Comparator<Book>() { // This is the code for sorting the year of the books in ascending order
            @Override
            public int compare(Book o1, Book o2) {
                if (o1.getYear() > o2.getYear())
                    return 1;
                else
                    return -1;
            }
        };
        Bks.sort(ByAuthorNAME);
        for (Book b : Bks)
            b.print();
    }


    // Sort products by increasing price
    public void printByPrice ()
    {
        List <Map.Entry <String, Product>> list = new LinkedList<Map.Entry<String, Product>>(products.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Product>>() {
            @Override
            public int compare(Map.Entry<String, Product> o1, Map.Entry<String, Product> o2) {
                if (o1.getValue().getPrice() > o2.getValue().getPrice())
                    return 1;
                else
                    return -1;
            }
        });
        Map <String,Product> sortedByPrice = new LinkedHashMap<String, Product>();
        for(Map.Entry <String, Product> entry : list){
            sortedByPrice.put(entry.getKey(), entry.getValue());
        }
        for (Map.Entry <String, Product> entry : sortedByPrice.entrySet()){
            entry.getValue().print();
        }
    }


    // Sort products alphabetically by product name
    public void printByName ()
    {
        List <Map.Entry <String, Product>> list = new LinkedList<Map.Entry<String, Product>>(products.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Product>>() {
            @Override
            public int compare(Map.Entry<String, Product> o1, Map.Entry<String, Product> o2) {
                return o1.getValue().getName().compareTo(o2.getValue().getName());
            }
        });
        Map <String,Product> sortedByName = new LinkedHashMap<String, Product>();
        for(Map.Entry <String, Product> entry : list){
            sortedByName.put(entry.getKey(), entry.getValue());
        }
        for (Map.Entry <String, Product> entry : sortedByName.entrySet()){
            entry.getValue().print();
        }
    }
    // Sort products alphabetically by product name
    public void sortCustomersByName()
    {
        Comparator<Customer> comByCustName = new Comparator<Customer>()
        {
            public int compare(Customer cust1, Customer cust2)
            {
                return cust1.getName().compareTo(cust2.getName());
            }
        };
        Collections.sort(customers, comByCustName);
    }

    // New methods of assignment 2:
    public String addToCart(String productId, String customerId, String productOptions)
    {
        // Checks if customer exists
        int indexes = customers.indexOf(new Customer(customerId));
        if (indexes == -1)
        {
            throw new customerNotFoundMessage();
        }
        Customer customer = customers.get(indexes);

        // Checks if product exists
        boolean index = products.containsKey(productId);
        if (!index )
        {
            throw new productNotFoundMessage();
        }
        Product product = products.get(productId);

        // Checks if the options are valid for this product
        if (!product.validOptions(productOptions))
        {
            System.out.println("\nProduct " + product.getName() + " ProductId " + productId);
            throw new invalidProductOptionsMessage();
        }
        // Checks to see if product is in stock
        if (product.getStockCount(productOptions) == 0)
        {
            System.out.println("Product " + product.getName() + " ProductId " + productId);
            throw new productOutOfStockMessage();
        }
        // Places a ProductOrder
        ProductOrder order = new ProductOrder(generateOrderNumber(), product, customer, productOptions);
        product.reduceStockCount(productOptions);

        // Adds the item to cart
        customers.get(indexes).addItem(new CartItem(product,productOptions));

        return "Product Has Been Added to Cart";
    }
    // Method to remove an item from a customer's cart
    public String removeCart(String productId, String customerId)
    {
        // Checks to see if customer exists
        int indexes = customers.indexOf(new Customer(customerId));
        if (indexes == -1)
        {
            throw new customerNotFoundMessage();
        }
        Customer customer = customers.get(indexes);

        // Checks to see if product exists

        boolean index = products.containsKey(productId);
        if (!index)
        {
            System.out.println("Product " + productId);
            throw new productNotFoundMessage();
        }
        Product product = products.get(index);

        // Gets the cart item
        int cartIndex = customers.get(indexes).getCart().findItem(productId);
        if (cartIndex == -1)
        {
            System.out.println("Cart Product " + productId);
            throw new cartItemNotFoundMessage();
        }

        // Removes the product from cart
        customers.get(indexes).removeItem(indexes);

        return "Product Removed from Cart";
    }
    public boolean printCart(String customerId)
    {
        // Checking to see if customer exists
        int index = customers.indexOf(new Customer(customerId));
        if (index == -1)
        {
            throw new customerNotFoundMessage();
        }
        System.out.println("Current Cart of Customer " + customerId);
        customers.get(index).getCart().print();
        return true;
    }

    public boolean orderItems(String customerId)
    {
        // Checking to see if customer exists
        int index = customers.indexOf(new Customer(customerId));
        if (index == -1)
        {
            errMsg = "Customer " + customerId + " Not Found";
            return false;
        }
        // Checking to see if no item was added to the cart yet
        if (customers.get(index).getCart().getItems().isEmpty())
        {
            errMsg = "Cart Is Empty!";
            return false;
        }
        for(int i=0;i<customers.get(index).getCart().getItems().size();i++) {
            orderProduct(customers.get(index).getCart().getItems().get(i).getItem().getId(), customerId,customers.get(index).getCart().getItems().get(i).getProductOption());
        }
        customers.get(index).getCart().getItems().clear();
        return true;
    }
    // Method goes through the statistics map of products and prints them out in descending order
    public void printStats() {
        ArrayList<Product> list = new ArrayList<Product>(statsMap.keySet());
        Comparator<Product> comByPrice = new Comparator<Product>()
        {
            public int compare(Product prod1, Product prod2)
            {
                if(prod1.getStats()>prod2.getStats())
                {
                    return -1;
                }
                else
                {
                    return 1;
                }
            }
        };
        Collections.sort(list, comByPrice);
        for (Product p : list){
            p.statPrint();
        }

    }
}