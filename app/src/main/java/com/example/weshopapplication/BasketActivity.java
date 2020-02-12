package com.example.weshopapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class BasketActivity extends AppCompatActivity {
    private TechActivity techActivity;
    private ListView basketList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        Intent intent = getIntent();
        HashMap<Integer, Products> hashMap = (HashMap<Integer, Products>) intent.getSerializableExtra("map");

        ArrayList<String> prod = new ArrayList<>();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(BasketActivity.this, android.R.layout.simple_list_item_1, prod);
        ListView view = findViewById(R.id.listViewBasket);
        view.setAdapter(arrayAdapter);

        for (Map.Entry<Integer, Products> entry : hashMap.entrySet()) {
            arrayAdapter.add(entry.toString());
        }

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