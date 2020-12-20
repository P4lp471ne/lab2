package com.example.scanner.logic;

import android.content.Context;

import com.example.scanner.logic.datatypes.requestTypes.Report;
import com.example.scanner.logic.datatypes.responseTypes.Product;
import com.example.scanner.logic.datatypes.responseTypes.RequestData;
import com.example.scanner.logic.datatypes.responseTypes.ShortRequestDescription;
import com.example.scanner.logic.datatypes.responseTypes.Warehouse;
import com.example.scanner.utils.PropertiesLoader;

import java.io.IOException;
import java.util.List;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.RequestBody;

class WarehouseAPI implements API{
    String urlBase;
    private String token;

    WarehouseAPI(Context context) throws IOException {
        urlBase = PropertiesLoader.getInstance().getProperty("URL");
        token = PropertiesLoader.getInstance().getProperty("TOKEN");
    }

    public void getRequestsList(Callback callback) {
        HttpClientWrapper.aget(urlBase + "/product_requests/short", callback, token);
    }

    @Override
    public void getRequestDetails(String requestID, Callback callback) {
        HttpClientWrapper.aget(urlBase + requestID, callback, token);
    }

    @Override
    public void startCollecting(String requestID, Callback callback) {
        HttpClientWrapper.aget(urlBase + requestID + "/start", callback, token);
    }

    @Override
    public void cancelCollecting(String requestID, Callback callback) {
        HttpClientWrapper.aget(urlBase + requestID + "/cancel", callback, token);
    }

    public void finishCollecting(Report report, Callback callback) {
        HttpClientWrapper.apost(urlBase + report.getId() + "/finnish",
                null, callback, token);
    }

    public void scan(String barcode, Callback callback) {
        HttpClientWrapper.aget(urlBase + "/scan", callback, token);
    }
}
