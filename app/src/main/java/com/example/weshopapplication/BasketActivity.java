package com.example.weshopapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// Author of Application: Sabin Constantin Lungu
// Purpose of Application & Class: To store the products added to the basket in a List View.
// Date of Last Modification: 13/02/2020.
// Any Errors: N/A


public class BasketActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        Intent intent = getIntent();
        HashMap<Integer, Products> hashMap = (HashMap<Integer, Products>) intent.getSerializableExtra("map"); // Get the hash map from the tech activity
        ArrayList<String> prod = new ArrayList<>();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(BasketActivity.this, android.R.layout.simple_list_item_1, prod) {

            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView tv = view.findViewById(android.R.id.text1);

                tv.setTextColor(Color.WHITE); // Change the colour of the text

                return view;
            }
        };

        ListView view = findViewById(R.id.listViewBasket);
        view.setAdapter(arrayAdapter);

        for (Map.Entry<Integer, Products> entry : hashMap.entrySet()) { // Loop over the hash map of products
            arrayAdapter.add(entry.toString()); // Add the entries to the adapter list
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