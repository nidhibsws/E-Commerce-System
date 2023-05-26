package com.OOP;
public class Shoes extends Product{
    int size6BlackStock;
    int size6BrownStock;
    int size7BlackStock;
    int size7BrownStock;
    int size8BlackStock;
    int size8BrownStock;
    int size9BlackStock;
    int size9BrownStock;
    int size10BlackStock;
    int size10BrownStock;

    public Shoes(String name,String id, double price, int size6BlackStock, int size6BrownStock, int size7BlackStock, int size7BrownStock, int size8BlackStock,
                 int size8BrownStock, int size9BlackStock, int size9BrownStock, int size10BlackStock, int size10BrownStock, int stat)

    {
        super(name, id, price, 100000, Product.Category.CLOTHING,0);
        this.size6BlackStock = size6BlackStock;
        this.size6BrownStock = size6BrownStock;
        this.size7BlackStock = size7BlackStock;
        this.size7BrownStock = size7BrownStock;
        this.size8BlackStock = size8BlackStock;
        this.size8BrownStock = size8BrownStock;
        this.size9BlackStock = size9BlackStock;
        this.size9BrownStock = size9BrownStock;
        this.size10BlackStock = size10BlackStock;
        this.size10BrownStock = size10BrownStock;
    }

    public boolean validOptions(String productOptions)
    {
        switch (productOptions)
        {
            case "6 Black": return true;
            case "7 Black": return true;
            case "8 Black": return true;
            case "9 Black": return true;
            case "10 Black": return true;
            case "6 Brown": return true;
            case "7 Brown": return true;
            case "8 Brown": return true;
            case "9 Brown": return true;
            case "10 Brown": return true;
            default: return false;
        }
    }

    @Override
    public int getStockCount(String productOptions)
    {
        switch (productOptions)
        {
            case "6 Black": return size6BlackStock;
            case "6 Brown": return size6BrownStock;
            case "7 Black": return size7BlackStock;
            case "7 Brown": return size7BrownStock;
            case "8 Black": return size8BlackStock;
            case "8 Brown": return size8BrownStock;
            case "9 Black": return size9BlackStock;
            case "9 Brown": return size9BrownStock;
            case "10 Black": return size10BlackStock;
            case "10 Brown": return size10BrownStock;
            default: return super.getStockCount(productOptions);
        }
    }

    @Override
    public void setStockCount(int stockCount, String productOptions)
    {
        switch (productOptions)
        {
            case "6 Black": this.size6BlackStock = stockCount; break;
            case "6 Brown": this.size6BrownStock = stockCount; break;
            case "7 Black": this.size7BlackStock = stockCount; break;
            case "7 Brown": this.size7BrownStock = stockCount; break;
            case "8 Black": this.size8BlackStock = stockCount; break;
            case "8 Brown": this.size8BrownStock = stockCount; break;
            case "9 Black": this.size9BlackStock = stockCount; break;
            case "9 Brown": this.size9BrownStock = stockCount; break;
            case "10 Black": this.size10BlackStock = stockCount; break;
            case "10 Brown": this.size10BrownStock = stockCount; break;
        }
    }

    @Override
    public void reduceStockCount(String productOptions)
    {
        switch (productOptions)
        {
            case "6 Black": size6BlackStock--; break;
            case "6 Brown": size6BrownStock--; break;
            case "7 Black": size7BlackStock--; break;
            case "7 Brown": size7BrownStock--; break;
            case "8 Black": size8BlackStock--; break;
            case "8 Brown": size8BrownStock--; break;
            case "9 Black": size9BrownStock--; break;
            case "9 Brown": size9BlackStock--; break;
            case "10 Black": size10BrownStock--; break;
            case "10 Brown": size10BlackStock--; break;

        }
    }

    public void print()
    {
        super.print();
    }
}