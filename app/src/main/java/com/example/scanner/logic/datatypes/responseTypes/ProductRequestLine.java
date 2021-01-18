package com.example.scanner.logic.datatypes.responseTypes;

public class ProductRequestLine {
    private Product product;
    private int quantity;

    public ProductRequestLine(Product product, Integer integer) {
        this.product = product;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}
