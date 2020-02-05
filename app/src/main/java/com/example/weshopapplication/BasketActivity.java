package com.example.weshopapplication;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class BasketActivity extends AppCompatActivity {
    private TechActivity techActivity;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        ArrayList<String> prod = new ArrayList<>();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(BasketActivity.this, android.R.layout.simple_list_item_1, prod);
        ListView view = findViewById(R.id.listViewBasket);
        view.setAdapter(arrayAdapter);


    }

    protected void onDestroy() {
        super.onDestroy();
    }

    protected void onStop() {
        super.onStop();
    }

    public void onBackPressed() {
        super.onBackPressed();
    }

    protected void onResume() {
        super.onResume();
    }
}