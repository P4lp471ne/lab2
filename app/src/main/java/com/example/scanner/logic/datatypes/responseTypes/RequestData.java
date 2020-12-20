package com.example.scanner.logic.datatypes.responseTypes;

import java.util.List;

public class RequestData extends ShortRequestDescription {
    private List<ProductRequestLine> lines;

    public List<ProductRequestLine> getLines() {
        return lines;
    }

    public void setLines(List<ProductRequestLine> lines) {
        this.lines = lines;
    }
}
