package com.example.scanner.view.activities;

import android.widget.Button;
import android.widget.LinearLayout;

import com.example.scanner.R;
import com.example.scanner.logic.datatypes.responseTypes.RequestData;
import com.example.scanner.logic.datatypes.responseTypes.ShortRequestDescription;
import com.example.scanner.view.ViewManager;

import java.util.List;

public class RequestsView extends AbstractViewHolder {
    private List<ShortRequestDescription> data;
    private ReqListAdapter adapter;

    public RequestsView(ViewManager viewManager) {
        super(viewManager);
        adapter = new ReqListAdapter(getApp().getApplicationContext(),
                R.layout.req_item, new ShortRequestDescription[]{});
    }

    @Override
    protected void makeView() {
        view = getApp().findViewById(R.id.req_data_view);
        view.addView(listView);

        Button exit = getApp().findViewById(R.id.exitButton);
        exit.setOnClickListener((e) -> {System.exit(0);});

        Button refresh = getApp().findViewById(R.id.update);

        listView.setAdapter(adapter);
    }

    void refresh(){
        manager.requestProductRequests(this::setItems);
    }

    private void setItems(List<ShortRequestDescription> requestsList){
        if (adapter == null) return;
        data = requestsList;
        adapter.clear();
        adapter.addAll(requestsList);
    }
}
