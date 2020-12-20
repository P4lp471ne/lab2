package com.example.scanner.logic;

import android.content.Context;

import com.example.scanner.utils.CallbackProvider;
import com.example.scanner.view.ReqListCallback;
import com.example.scanner.view.ViewManager;

import java.io.IOException;

import okhttp3.Callback;

public class Logic {
    ViewManager viewManager;
    Context context;
    API api;
    Scanner scanner;

    public Logic(ViewManager viewManager,Context context) throws IOException {
        this.viewManager = viewManager;
        this.context = context;
        api = new WarehouseAPI(context);
        scanner = new Scanner();
    }

    public void requestRequestsList(ReqListCallback callback){
        api.getRequestsList(CallbackProvider.createReqListCallback(callback, null));
    }

    public void requestProductRequestData(String requestId, Callback callback){
        api.getRequestDetails(requestId, callback);
    }

    public void requestStart(String requestID, Callback callback){
        api.startCollecting(requestID, callback);
    }

    public void requestCancel(String requestID, Callback callback){
        api.cancelCollecting(requestID, callback);
    }

    public void requestFinnish(String requestID, Callback callback){}
}
