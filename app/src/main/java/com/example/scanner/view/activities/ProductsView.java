package com.example.scanner.view.activities;

import android.view.KeyEvent;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.scanner.R;
import com.example.scanner.logic.datatypes.responseTypes.Product;
import com.example.scanner.logic.datatypes.responseTypes.ProductRequestLine;
import com.example.scanner.view.ViewManager;

import java.util.List;

public class ProductsView extends AbstractViewHolder {
    private List<ProductRequestLine> data;
    private ProductsListAdapter adapter;
    private String id;

    public ProductsView(ViewManager viewManager) {
        super(viewManager);
        adapter = new ProductsListAdapter(getApp().getApplicationContext(),
                R.layout.req_item, new ProductRequestLine[]{});
    }

    ProductsView(ViewManager viewManager, List<Product> products) {
        super(viewManager);
    }

    @Override
    protected void makeView() {
        view = getApp().findViewById(R.id.req_data_view);
        view.addView(listView);

        listView.setAdapter(adapter);
//        view.addView();
    }

    private void setItems(List<ProductRequestLine> products){
        if (adapter == null) return;
        data = products;
        adapter.clear();
        adapter.addAll(products);
    }

    void refresh(){
        manager.requestProducts(id, this::setItems);
    }

    void listen(){
    }
}
