package com.example.scanner.utils;

import com.example.scanner.logic.datatypes.responseTypes.ProductRequestLine;
import com.example.scanner.logic.datatypes.responseTypes.ShortRequestDescription;
import com.example.scanner.view.ProductsListCallback;
import com.example.scanner.view.ReqListCallback;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class CallbackProvider {

    //variable name == short description of on-success action
    static Callback printRequestsList = new Callback() {
        public void onResponse(Call call, Response response) throws IOException {
        }

        public void onFailure(Call call, IOException e) {
//            fail();
        }
    };
    static Callback printProducts = new Callback() {
        public void onResponse(Call call, Response response) throws IOException {
        }

        public void onFailure(Call call, IOException e) {
//            fail();
        }
    };
    static Callback scan = new Callback() {
        public void onResponse(Call call, Response response) throws IOException {
        }

        public void onFailure(Call call, IOException e) {
//            fail();
        }
    };
    static Callback finnish = new Callback() {
        public void onResponse(Call call, Response response) throws IOException {
        }

        public void onFailure(Call call, IOException e) {
//            fail();
        }
    };
    static Callback start = new Callback() {
        public void onResponse(Call call, Response response) throws IOException {
        }

        public void onFailure(Call call, IOException e) {
//            fail();
        }
    };
    static Callback cancel = new Callback() {
        public void onResponse(Call call, Response response) throws IOException {
        }

        public void onFailure(Call call, IOException e) {
//            fail();
        }
    };

    public static Callback createReqListCallback(ReqListCallback onSuccess, ReqListCallback onFailure) {
        return new Callback() {
            public void onResponse(Call call, Response response) throws IOException {
                ObjectMapper objectMapper = new ObjectMapper();
                List<ShortRequestDescription> lst = objectMapper.readValue(response.body().string()
                        , objectMapper.getTypeFactory().constructCollectionType(List.class,
                                ShortRequestDescription.class));
                onSuccess.action(lst);
            }

            public void onFailure(Call call, IOException e) {
//            fail();
            }
        };
    }

    public static Callback createProductsListCallback(ProductsListCallback onSuccess, ProductsListCallback onFailure) {
        return new Callback() {
            public void onResponse(Call call, Response response) throws IOException {
                ObjectMapper objectMapper = new ObjectMapper();
                List<ProductRequestLine> lst = objectMapper.readValue(response.body().string()
                        , objectMapper.getTypeFactory().constructCollectionType(List.class,
                                ShortRequestDescription.class));
                onSuccess.action(lst);
            }

            public void onFailure(Call call, IOException e) {
//            fail();
            }
        };
    }
}
