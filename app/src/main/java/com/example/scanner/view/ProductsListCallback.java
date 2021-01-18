package com.example.scanner.view;

import com.example.scanner.logic.datatypes.responseTypes.ProductRequestLine;

import java.util.List;

public interface ProductsListCallback {
    void action(List<ProductRequestLine> lst);
}
