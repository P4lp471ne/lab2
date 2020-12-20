package com.example.scanner.logic;

import com.example.scanner.logic.datatypes.requestTypes.Report;
import com.example.scanner.logic.datatypes.responseTypes.Product;
import com.example.scanner.logic.datatypes.responseTypes.ProductRequestLine;
import com.example.scanner.logic.datatypes.responseTypes.RequestData;

import java.util.List;

public class Scanner {
    private List<ProductRequestLine> requestLines;
    private boolean isActive;
    private String id;

    protected void scan(Product product){}

    protected void setRequest(RequestData data){
        requestLines = data.getLines();
        id = data.getExtId();
    }

    protected void start(){
        isActive = true;
    }

    protected Report getReport(){
        return null;
    }

    protected void cancel(){
        requestLines = null;
        isActive = false;
    }
}
