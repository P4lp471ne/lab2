package com.example.scanner.view.activities;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.scanner.App;
import com.example.scanner.view.ViewManager;

public abstract class AbstractViewHolder {
    protected ViewManager manager;
    protected LinearLayout view;
    protected ListView listView;
    private App app;

    AbstractViewHolder(ViewManager viewManager) {
        this.manager = viewManager;
    }

    public View getView() {
        if (view == null) {
            makeView();
        }
        return view;
    }

    protected AppCompatActivity getApp() {
        return app.get();
    }

    public void setApp(App app) {
        this.app = app;
    }

    protected abstract void makeView();
}
