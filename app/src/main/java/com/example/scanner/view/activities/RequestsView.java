package com.example.scanner.view.activities;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import com.example.scanner.R;
import com.example.scanner.logic.datatypes.responseTypes.ShortRequestDescription;
import com.example.scanner.view.ViewManager;

import java.util.ArrayList;
import java.util.List;

public class RequestsView extends AbstractViewHolder {
    private List<ShortRequestDescription> data = new ArrayList<>();
    private ReqListAdapter adapter;

    public RequestsView(ViewManager viewManager) {
        super(viewManager);
    }

    @SuppressLint("ResourceType")
    @Override
    protected void makeView() {

        view = LayoutInflater.from(getApp().getApplicationContext())
                .inflate(R.layout.req_list_view_activity, null).findViewById(R.id.start_view);
        listView = LayoutInflater.from(getApp().getApplicationContext())
                .inflate(R.layout.list_view, null).findViewById(R.id.listview);
        view.addView(listView);

        Button exit = LayoutInflater.from(getApp().getApplicationContext())
                .inflate(R.layout.req_list_view_activity, null).findViewById(R.id.exitButton);
        exit.setOnClickListener((e) -> {
            System.exit(0);
        });

        Button refresh = LayoutInflater.from(getApp().getApplicationContext())
                .inflate(R.layout.req_list_view_activity, null).findViewById(R.id.update);
        refresh.setOnClickListener(this::refresh);

        adapter = new ReqListAdapter(getApp().getApplicationContext(),
                R.layout.req_item, new ShortRequestDescription[]{});
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                manager.productsView(String.valueOf(data.get(position).getId()));
            }
        }
        ));

        refresh(view);
    }

    private void refresh(View view) {
        manager.requestProductRequests(this::setItems);
    }


    private void setItems(List<ShortRequestDescription> requestsList) {
        if (adapter == null) return;
        data.clear();
        data.addAll(requestsList);
        getApp().runOnUiThread(() -> {
            adapter.notifyDataSetChanged();
        });

    }
}
