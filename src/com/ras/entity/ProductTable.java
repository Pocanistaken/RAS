
package com.ras.entity;


public class ProductTable {
    private int amount,productTableID;
    private Product product;
    private Table table;
    
    public ProductTable(Product product, Table table, int amount, int productTableID) {
        this.product = product;
        this.table = table;
        this.amount = amount;
        this.productTableID = productTableID;
    }

    /**
     * @return the amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * @return the productTableID
     */
    public int getProductTableID() {
        return productTableID;
    }

    /**
     * @return the product
     */
    public Product getProduct() {
        return product;
    }

    /**
     * @return the table
     */
    public Table getTable() {
        return table;
    }
    
    
}
