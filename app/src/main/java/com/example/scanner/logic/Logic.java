package com.example.scanner.logic;

import android.content.Context;

import com.example.scanner.logic.datatypes.responseTypes.Product;
import com.example.scanner.logic.datatypes.responseTypes.ShortRequestDescription;
import com.example.scanner.logic.datatypes.responseTypes.Warehouse;
import com.example.scanner.utils.CallbackProvider;
import com.example.scanner.view.Consumer;
import com.example.scanner.view.ProductsListCallback;
import com.example.scanner.view.ReqListCallback;
import com.example.scanner.view.ViewManager;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Logic implements Producer{
    private ViewManager viewManager;
    private Context context;
    private API api;
    private Scanner scanner;
    private String buffer;
    private Consumer consumer;

    public Logic(ViewManager viewManager, Context context) throws IOException {
        this.viewManager = viewManager;
        this.context = context;
        api = new WarehouseAPI(context);
        scanner = new Scanner();
    }

    public void requestRequestsList(ReqListCallback callback) {
        api.getRequestsList(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callback.action(ServerResponseProcessor.parseReqListResponse(response));
            }
        });
    }

    public void requestProductRequestData(String requestId, ProductsListCallback productsListCallback) {
        Callback callback = new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                productsListCallback.action(ServerResponseProcessor.parseReqDataResponse(response).getLines());
            }
        };
        api.getRequestData(requestId, callback);
    }

    public void requestStart(String requestID, Callback callback) {
        api.startCollecting(requestID, callback);
        scanner = new Scanner();
    }

    public void requestCancel(String requestID, Callback callback) {
        api.cancelCollecting(requestID, callback);
    }

    public void requestFinnish(String requestID, Callback callback) {
        api.finishCollecting(scanner.getReport(), callback);
    }

    public void scan(String reqv) {
        if (scanner != null && scanner.isActive()) {
            if (scanner.isDoubleScan()) {
                if (buffer != null) {
                    reqv = buffer + reqv;
                    return;
                }
                buffer = reqv;
            }

            api.scan(reqv, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    consumer.listen(ServerResponseProcessor.parseScanResult(response));
                }
            });
        }
    }

    @Override
    public void subscribe(Consumer consumer) {
        this.consumer = consumer;
    }
}
