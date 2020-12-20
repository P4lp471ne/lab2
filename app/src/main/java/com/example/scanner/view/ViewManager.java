package com.example.scanner.view;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.scanner.R;
import com.example.scanner.logic.Logic;
import com.example.scanner.utils.CallbackProvider;
import com.example.scanner.view.activities.ProductsView;
import com.example.scanner.view.activities.RequestsView;

public class ViewManager {
    private Logic logic;
    private Context context;
    private AppCompatActivity app;

    ViewManager(Context context){
        this.context = context;
    }

    protected void setLogic(Logic logic) {
        this.logic = logic;
    }

    protected void setApp(AppCompatActivity app){
        this.app = app;
    }

    void startScreen() {
        requestsView();
    }

    void productsView(){
        ProductsView view = new ProductsView(this);
        app.setContentView(view.getView());
    }

    void requestsView(){
        LinearLayout linearLayout = app.findViewById(R.id.start_view);
        ListView lst = app.findViewById(R.id.listview);
        RequestsView view = new RequestsView(this);
        app.setContentView(view.getView());
    }

    public void requestProducts(String id, ProductsListCallback setItems){
        CallbackProvider.createProductsListCallback(setItems, null);
    }

    private AppCompatActivity getApp(){
        return app;
    }

    public void requestProductRequests(ReqListCallback setItems) {
        CallbackProvider.createReqListCallback(setItems, null);
    }
}
