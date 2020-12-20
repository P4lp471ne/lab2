package com.example.scanner.logic;


import android.os.Handler;

import com.example.scanner.logic.datatypes.requestTypes.Report;
import com.example.scanner.logic.datatypes.responseTypes.Product;
import com.example.scanner.logic.datatypes.responseTypes.RequestData;
import com.example.scanner.logic.datatypes.responseTypes.ShortRequestDescription;

import java.util.List;

import okhttp3.Callback;

interface API {
    void getRequestsList(Callback callback);

    void getRequestDetails(String requestID, Callback callback);

    void startCollecting(String requestID, Callback callback);

    void cancelCollecting(String requestID, Callback callback);

    void finishCollecting(Report report, Callback callback);

    void scan(String barcode, Callback callback);
}
