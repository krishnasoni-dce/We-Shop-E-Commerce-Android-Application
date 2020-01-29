package com.example.weshopapplication;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

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
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialise components
        this.welcomeImage = findViewById(R.id.welcomeImg);
        this.text = findViewById(R.id.welcomeTxt);

        this.registerButton = findViewById(R.id.registerBtn);
        this.loginButton = findViewById(R.id.loginBtn);

        this.auth = FirebaseAuth.getInstance();

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
                try {

                    Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(loginIntent);

                } catch (ActivityNotFoundException exc) {
                    Log.d("Error : ", exc.getMessage());
                }
            }
        });
    }


    public boolean onOptionsItemSelected(MenuItem item) { // Determines which item is selected from the menu
        try {
            if (item == null) { // If there is no item to choose
                return false; // Return false
            }

            switch (item.getItemId()) {
                case R.id.sportsAndOutdoorsCategory: // If the sports and outdoors category is chosen
                    Intent sportsIntent = new Intent(MainActivity.this, SportsAndOutdoorsActivity.class); // Take user to the sports and outdoors activity
                    startActivity(sportsIntent);

                    return true; // Return true.

                case R.id.techCategory:
                    Intent techIntent = new Intent(MainActivity.this, TechActivity.class);
                    startActivity(techIntent);

                    return true;


                case R.id.clothingCategory: // If the clothing category is chosen

                    Intent clothingIntent = new Intent(MainActivity.this, ClothingCategory.class);
                    startActivity(clothingIntent); // Go to the clothing intent

                    return true;

                case R.id.diyCategory:

                    Intent intent = new Intent(MainActivity.this, DIYActivity.class);
                    startActivity(intent);

                    return true; // Return true;

                case R.id.logoutFeature:
                    logout();
                    finish();

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

    private void logout() {
        auth.signOut();
        finish();
    }
}