package com.example.scanner.view.activities;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.scanner.R;
import com.example.scanner.view.App;
import com.example.scanner.view.ViewManager;

public abstract class AbstractViewHolder {
    protected ViewManager manager;
    protected LinearLayout view;
    protected ListView listView;
    private App app;

    AbstractViewHolder(ViewManager viewManager){
        this.manager = viewManager;
        listView = getApp().findViewById(R.id.listview);
    }

    public View getView(){
        return view;
    }

    void setApp(App app){
        this.app = app;
    }

    protected AppCompatActivity getApp(){
        return app.get();
    }

    protected abstract void makeView();
}
