package com.example.scanner.logic.datatypes.requestTypes;

import java.util.List;

public class Report {
    private String id;
    private ProductLine productLine;

    public ProductLine getProductLine() {
        return productLine;
    }

    public void setProductLine(ProductLine productLine) {
        this.productLine = productLine;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
