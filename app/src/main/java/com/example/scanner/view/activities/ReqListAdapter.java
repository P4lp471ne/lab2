package com.example.scanner.view.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.scanner.R;
import com.example.scanner.logic.datatypes.responseTypes.ShortRequestDescription;

public class ReqListAdapter extends ArrayAdapter<ShortRequestDescription> {
    public ReqListAdapter(@NonNull Context context, int resource, @NonNull ShortRequestDescription[] objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View reqView, ViewGroup parent) {
        ShortRequestDescription req = getItem(position);

        if (reqView == null) {
            reqView = LayoutInflater.from(getContext())
                    .inflate(R.layout.req_item, parent);
        }

        ((TextView) reqView.findViewById(R.id.reqNameView))
                .setText(req.getNameView());
        ((TextView) reqView.findViewById(R.id.ID))
                .setText(req.getId());
        ((TextView) reqView.findViewById(R.id.Status))
                .setText(req.getStatus());
        ((TextView) reqView.findViewById(R.id.Date))
                .setText(req.getCollectionDate());

        return reqView;
    }
}
