package com.example.weshopapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


public class BasketActivity extends AppCompatActivity {
    private TechActivity techActivity;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

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