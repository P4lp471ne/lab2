package com.example.scanner.view.activities;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Switch;

import com.example.scanner.R;
import com.example.scanner.logic.datatypes.responseTypes.Product;
import com.example.scanner.logic.datatypes.responseTypes.ProductRequestLine;
import com.example.scanner.view.Consumer;
import com.example.scanner.view.ViewManager;

import java.util.List;

public class ProductsView extends AbstractViewHolder implements Consumer {
    private List<ProductRequestLine> data;
    private ProductsListAdapter adapter;
    private String id;
    private Switch doubleScanSwitch;
    private LayoutInflater inflater;

    public ProductsView(ViewManager viewManager) {
        super(viewManager);
    }

    ProductsView(ViewManager viewManager, List<Product> products) {
        super(viewManager);
    }

    @Override
    protected void makeView() {
        inflater = LayoutInflater.from(getApp().getApplicationContext());

        view = inflater.inflate(R.layout.req_data_view_activity, null)
                .findViewById(R.id.req_data_view);

        setListView();
        setSwitch();
//        view.addView();
    }

    private void setCancelButton(){
        Button btn = inflater.inflate(R.layout.req_data_view_activity, null)
                .findViewById(R.id.cancel);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.cancel(id);
                manager.startScreen();
            }
        });
    }

    private void setListView() {
        listView = inflater.inflate(R.layout.list_view, null).findViewById(R.id.listview);
        view.addView(listView);
        adapter = new ProductsListAdapter(getApp().getApplicationContext(),
                R.layout.prod_item, (ProductRequestLine[]) data.toArray());
        listView.setAdapter(adapter);
    }

    private void setSwitch() {
        doubleScanSwitch = inflater.inflate(R.layout.req_data_view_activity, null)
                .findViewById(R.id.doubleScanSwitch);
        doubleScanSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) doubleScanOn();
            else doubleScanOff();
        });
    }

    private void doubleScanOn() {
    }

    private void doubleScanOff() {
    }

    private void setItems(List<ProductRequestLine> products) {
        if (adapter == null) return;
        data = products;
        adapter.clear();
        adapter.addAll(products);
    }

    void refresh() {
        manager.requestProducts(id, this::setItems);
    }

    @Override
    public void listen(Product product) {
        for (ProductRequestLine line: data) {
            if (line.getProduct().equals(product)){
                int quantity = line.getQuantity();
                if (quantity > 0) line.setQuantity(quantity - 1);
                adapter.notifyDataSetChanged();
            }
        }
    }
}
