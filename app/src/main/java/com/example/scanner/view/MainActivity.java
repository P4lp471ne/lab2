package com.example.scanner.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;

import com.example.scanner.R;
import com.example.scanner.logic.Logic;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    ViewManager viewManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewManager = new ViewManager(getApplicationContext());
        viewManager.setApp(this);
        try {
            Logic logic = new Logic(viewManager, getApplicationContext());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        setContentView(R.layout.activity_main);
        viewManager.startScreen();
    }
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getCharacters() != null && !event.getCharacters().isEmpty())
            //Add more code...
            return super.dispatchKeyEvent(event);
        return false;
    }
}