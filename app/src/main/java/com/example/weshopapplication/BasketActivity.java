package com.example.weshopapplication;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class BasketActivity extends AppCompatActivity {
    private ArrayList<String> listOfProducts;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private Button placeOrder;
    private String[] techProducts = {"iPhone X 256GB", "Apple Watch Series 5", "AirPods Pro", "Samsung Galaxy Note"};
    private TechActivity activity;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        this.listOfProducts = new ArrayList<>();
        this.listView = findViewById(R.id.listViewBasket);
        this.adapter = new ArrayAdapter<>(BasketActivity.this, R.layout.activity_basket, listOfProducts);

        this.listView.setAdapter(adapter);


    }

    protected void onDestroy() {
        super.onDestroy();
    }

    protected void onStop() {
        super.onStop();
    }
}
