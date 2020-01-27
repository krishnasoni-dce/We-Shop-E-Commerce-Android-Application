package com.example.weshopapplication;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

// Author of Application: Sabin Constantin Lungu
// Purpose of Class: Main Activity implements homepage functionality of application.
// Last modified: 26 January 2020
// Any Bugs?: N/A

public class MainActivity extends AppCompatActivity {
    // Encapsulated variables
    private ImageView welcomeImage;
    private TextView text; //

    private Button registerButton; // Variable Button to register
    private Button loginButton; // Variable to store the login button

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
                try {

                    if (v.getId() == R.id.registerBtn) { // If the register button is clicked
                        Intent registerIntent = new Intent(MainActivity.this, RegisterActivity.class);
                        startActivity(registerIntent); // Take user to the register page

                    }

                } catch (ActivityNotFoundException act) { // Catch exception if the activity is not found
                    act.printStackTrace();
                    Log.d("Cause : ", act.getMessage());
                }
            }
        });

        this.loginButton.setOnClickListener(new View.OnClickListener() { // Listener for login button
            @Override
            public void onClick(View v) {

            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) { // Routine that creates the main menu
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

    public void onBackPressed() { // Routine that determines if the back button is pressed
        moveTaskToBack(true); // Move back a task
    }

    public void onNothingSelected(AdapterView<?> parent) {
    }
}
