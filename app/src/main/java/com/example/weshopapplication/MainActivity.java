package com.example.weshopapplication;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Create a menu inflater

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.homepagemenu, menu);

        return true;
    }

}
