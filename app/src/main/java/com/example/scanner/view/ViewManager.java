package com.example.scanner.view;

import android.content.Context;

import com.example.scanner.App;
import com.example.scanner.logic.Logic;
import com.example.scanner.utils.CallbackProvider;
import com.example.scanner.view.activities.AbstractViewHolder;
import com.example.scanner.view.activities.ProductsView;
import com.example.scanner.view.activities.RequestsView;

public class ViewManager {
    private Logic logic;
    private Context context;
    private App app;
    private AbstractViewHolder gui;

    public ViewManager(Context context) {
        this.context = context;
    }

    public void setLogic(Logic logic) {
        this.logic = logic;
    }

    public void startScreen() {
        requestsView();
    }

    public void productsView(String requestId) {
        gui = new ProductsView(this);
        update();
    }

    void requestsView() {
        gui = new RequestsView(this);
        update();
    }

    private void update() {
        gui.setApp(app);
        app.get().setContentView(gui.getView());
    }

    public void requestProducts(String id, ProductsListCallback setItems) {
        logic.requestProductRequestData(id, setItems);
    }

    private App getApp() {
        return app;
    }

    public void setApp(App app) {
        this.app = app;
    }

    public void requestProductRequests(ReqListCallback setItems) {
        logic.requestRequestsList(setItems);
    }

    public void cancel(String id) {
        logic.requestCancel(id, null);
    }
}
