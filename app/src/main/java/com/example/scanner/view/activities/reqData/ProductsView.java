package com.example.scanner.view.activities;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.example.scanner.R;
import com.example.scanner.logic.datatypes.responseTypes.Product;
import com.example.scanner.logic.datatypes.responseTypes.ProductRequestLine;
import com.example.scanner.logic.datatypes.responseTypes.RequestData;
import com.example.scanner.view.Consumer;
import com.example.scanner.view.ProductsListCallback;
import com.example.scanner.view.ViewManager;import com.example.scanner.view.activities.reqData.ProductsListAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ProductsView extends AbstractViewHolder implements Consumer {
    private List<ProductRequestLine> lines = new ArrayList<>();
    private com.example.scanner.view.activities.reqData.ProductsListAdapter adapter;
    private String id;
    private Switch doubleScanSwitch;
    private RequestData data;
    private Runnable upd;
    Button btn;

    public ProductsView(ViewManager viewManager, String id) {
        super(viewManager);
        this.id = id;
        refresh();
    }

    public void setUpd(Runnable upd){
        this.upd = upd;
    }

    ProductsView(ViewManager viewManager, List<Product> products) {
        super(viewManager);
    }

    @Override
    protected void makeView() {
        view = inflater.inflate(R.layout.req_data_view_activity, null)
                .findViewById(R.id.req_data_view);

        ((TextView) view.findViewById(R.id.req_name))
                .setText(data.getNameView());
        ((TextView) view.findViewById(R.id.status))
                .setText(data.getStatus() == 0? "new":"in_progress");

        setListView();
        setSwitch();
        setControlButtonToStart();
//        view.addView();
    }

    private void setControlButtonToCancel(){
        Button btn = view.findViewById(R.id.control);
        btn.setText("Cancel");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.cancel(id);
                manager.startScreen();
            }
        });
    }

    private void setControlButtonToStart(){
        btn.setText("Start");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.start(id);
            }
        });
    }

    private void setControlButtonToFinish(){
        Button btn = view.findViewById(R.id.action);
        btn.setText("Finish");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.finish(id);
                manager.startScreen();
            }
        });
    }

    private void setListView() {
        listView = inflater.inflate(R.layout.list_view, null).findViewById(R.id.listview);
        ((LinearLayout)view).addView(listView);
        adapter = new ProductsListAdapter(getApp().getApplicationContext(),
                R.layout.prod_item, lines);
        listView.setAdapter(adapter);
    }

    private void setSwitch() {
        doubleScanSwitch = view.findViewById(R.id.doubleScanSwitch);
        doubleScanOff();
        doubleScanSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) doubleScanOn();
            else doubleScanOff();
        });
    }

    private void doubleScanOn() {
        manager.doublescan(true);
        doubleScanSwitch.setText("double\nscan");
    }

    private void doubleScanOff() {
        manager.doublescan(false);
        doubleScanSwitch.setText("single\nscan");
    }

    private void setData(RequestData data){
        this.data = data;
        setItems(data.getLines());
        upd.run();
    }

    private void setItems(List<ProductRequestLine> products) {
        if (adapter == null) adapter = new ProductsListAdapter(getApp().getApplicationContext(),
                R.layout.prod_item, lines);
        lines.clear();
        lines.addAll(products);
        getApp().runOnUiThread(() -> {
            adapter.notifyDataSetChanged();
        });
    }

    void refresh() {
        manager.requestProducts(id, this::setData);
    }

    @Override
    public void listen(Product product) {
        for (ProductRequestLine line: lines) {
            if (line.getProduct().equals(product)){
                int quantity = line.getQuantity();
                if (quantity > 0) line.setQuantity(quantity - 1);
                adapter.notifyDataSetChanged();
                if (lines.stream().allMatch(lin -> lin.getQuantity() == 0))
                    setControlButtonToFinish();
            }
        }
    }
}
