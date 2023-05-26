package com.OOP;

/* A book IS A product that has additional information - e.g. title, author

 	 A book also comes in different formats ("Paperback", "Hardcover", "EBook")

 	 The format is specified as a specific "stock type" in get/set/reduce stockCount methods.

*/
public class Book extends Product
{
    private String author;
    private String title;



    // Stock related information NOTE: inherited stockCount variable is used for EBooks
    int paperbackStock;
    int hardcoverStock;
    int year;

    public Book(String name, String id, double price, int paperbackStock, int hardcoverStock, String title, String author,int year, int stats)
    {
        // Make use of the constructor in the super class Product. Initialize additional Book instance variables.
        // Set category to BOOKS
        super(name,id,price,100000,Product.Category.BOOKS,0);
        this.title=title;
        this.author=author;
        this.paperbackStock=paperbackStock;
        this.hardcoverStock=hardcoverStock;

    }

    // Check if a valid format
    public boolean validOptions(String productOptions)
    {
        // check productOptions for "Paperback" or "Hardcover" or "EBook"
        // if it is one of these, return true, else return false
        if ((!productOptions.equals("Paperback")) && (!productOptions.equals("Hardcover")) && (!productOptions.equals("EBook"))){
            return false;

        }
        return true;
    }

    // Override getStockCount() in super class.
    public int getStockCount(String productOptions)
    {
        // Use the productOptions to check for (and return) the number of stock for "Paperback" etc
        // Use the variables paperbackStock and hardcoverStock at the top.
        // For "EBook", use the inherited stockCount variable.
        if(productOptions.equals("Paperback")){
            return this.paperbackStock;
        }
        if(productOptions.equals("Hardcover")){
            return this.hardcoverStock;
        }
        if(productOptions.equals("EBook")){
            return super.getStockCount(productOptions);
        }



        return 1;
    }

    public void setStockCount(int stockCount, String productOptions)
    {
        // Use the productOptions to check for (and set) the number of stock for "Paperback" etc
        // Use the variables paperbackStock and hardcoverStock at the top.
        // For "EBook", set the inherited stockCount variable.
        if(productOptions.equals("Paperback")){
            this.paperbackStock = stockCount;
        }
        if(productOptions.equals("Hardcover")){
            this.hardcoverStock=stockCount;
        }
        if(productOptions.equals("EBooks")){
            super.setStockCount(stockCount,productOptions);
        }

    }

    /*
     * When a book is ordered, reduce the stock count for the specific stock type
     */
    public void reduceStockCount(String productOptions)
    {
        // Use the productOptions to check for (and reduce) the number of stock for "Paperback" etc
        // Use the variables paperbackStock and hardcoverStock at the top.
        // For "EBook", set the inherited stockCount variable.
        if(productOptions.equals("Paperback")){
            this.paperbackStock--;
        }
        if(productOptions.equals("Hardcover")){
            this.hardcoverStock--;
        }
        if(productOptions.equals("EBooks")){
            super.reduceStockCount(productOptions);
        }


    }
    /*
     * Print product information in super class and append Book specific information title and author
     */
    public void print()
    {
        // Replace the line below.
        // Make use of the super class print() method and append the title and author info. See the video
        super.print();
        System.out.print(" Book Title: " + title + " Author: " + author);
    }
    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }
}