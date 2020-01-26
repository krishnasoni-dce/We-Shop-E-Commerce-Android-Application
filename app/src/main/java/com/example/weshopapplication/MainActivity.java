package com.example.weshopapplication;

import android.content.ActivityNotFoundException;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

// Author of Application: Sabin Constantin Lungu
// Purpose of Class: Main Activity implements homepage functionality of application.
// Last modified: 26 January 2020
// Any Bugs?: N/A

public class MainActivity extends AppCompatActivity {
    private ImageView welcomeImage;
    private TextView text;

    private Button registerButton;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialise components
        this.welcomeImage = findViewById(R.id.welcomeImg);
        this.text = findViewById(R.id.welcomeTxt);

        this.registerButton = findViewById(R.id.registerBtn);
        this.loginButton = findViewById(R.id.loginBtn);

        this.registerButton.setOnClickListener(new View.OnClickListener() { // Listener added to register button
            @Override
            public void onClick(View v) {

            }
        });

        this.loginButton.setOnClickListener(new View.OnClickListener() { // Listener for login button
            @Override
            public void onClick(View v) {

            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Create a menu inflater

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.homepagemenu, menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        try {

            if (item == null) {
                return false;
            }

            switch (item.getItemId()) {
                case R.id.sportsAndOutdoorsCategory:
                    //    Intent intent = new Intent(MainActivity.this, );

                    return true;

                case R.id.techCategory:
                    //  Intent intent = new Intent(MainActivity.this, );

                    return true;


                case R.id.clothingCategory:

                    return true;

                case R.id.diyCategory:

                    // Intent intent = new Intent(MainActivity.this, );

                    return true; // Return true;

                default:


            }
        } catch (ActivityNotFoundException exc) { // Catch exception
            exc.printStackTrace();
        }

        return true;
    }
}
