package com.example.weshopapplication;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.regex.Pattern;

// Author of Application: Sabin Constantin Lungu
// Purpose of Activity: To login a registered user
// Date of last modified: 28th January 2020
// Any Bugs?: N/A

public class LoginActivity extends AppCompatActivity {
    private TextView loginText; // The login text at the top of the application
    private EditText emailAddressField;
    private EditText passwordField;

    private Button loginButton;
    private FirebaseFirestore firebaseFirestore;

    private FirebaseAuth auth; // Firebase authentication variable
    private Pattern regexPatterns = Pattern.compile("[$&+,:;=\\\\?@#|/'<>.^*()%!-]"); // Regex patterns
    private final int logout_button_id = 101;
    private boolean isAdded = false;
    public boolean isLoggedIn = false;

    private Button logoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.emailAddressField = findViewById(R.id.emailAddressField);
        this.passwordField = findViewById(R.id.passwordField);
        this.loginButton = findViewById(R.id.loginBtn);

        this.firebaseFirestore = FirebaseFirestore.getInstance();
        this.auth = FirebaseAuth.getInstance();

        this.logoutBtn = findViewById(R.id.logout_button);

        this.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateEmailAddress();
                validatePassword();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater categoriesMenu = getMenuInflater();
        categoriesMenu.inflate(R.menu.homepagemenu, menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        String errorMessage = "Error Caused By : ";

        try {

            switch (item.getItemId()) {

                case R.id.sportsAndOutdoorsCategory: // If the sports and outdoors category is chosen

                    // Create intent to go to sports and outdoors category

                    Intent sportsIntent = new Intent(LoginActivity.this, SportsAndOutdoorsActivity.class);
                    startActivity(sportsIntent);

                case R.id.techCategory:

                    Intent techCategoryIntent = new Intent(LoginActivity.this, TechActivity.class);
                    startActivity(techCategoryIntent);

                    return true;

                case R.id.clothingCategory:

                    Intent clothingCategory = new Intent(LoginActivity.this, ClothingCategory.class);
                    startActivity(clothingCategory);

                    return true;

                case R.id.diyCategory:

                    Intent diyIntent = new Intent(LoginActivity.this, DIYActivity.class);
                    startActivity(diyIntent);

                    return true;


                default:

                    return super.onOptionsItemSelected(item);
            }

        }
        // Catch the activity not found exception
        catch (ActivityNotFoundException act) {
            Log.d(errorMessage, act.toString()); // Log the error as to why it occurred.
        }

        return true;

    }

    private boolean validateEmailAddress() { // Routine that validates the e-mail address when logging in.
        String emailEntry = emailAddressField.getText().toString().trim(); // Get the email address entry
        String errorMessage = "Please re-enter E-mail Address";


        if (!regexPatterns.matcher(emailEntry).find() && emailEntry.isEmpty()) { // If there is no @ symbol and the email field is empty

            AlertDialog.Builder emailError = new AlertDialog.Builder(LoginActivity.this).setTitle("Error")
                    .setMessage(errorMessage).setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (dialog != null) {
                                dialog.dismiss();
                            }
                        }
                    });

            emailError.show();
            emailAddressField.setError("E-mail must contain @ symbol");

            emailAddressField.setText("");
            return false;

        } else {

            emailAddressField.setError(null); // Set no error otherwise
            login();
            return true;
        }
    }


    private boolean validatePassword() { // Routine that validates the password when logging in.

        String passwordEntry = passwordField.getText().toString().trim(); // Get the password entry
        String flushedString = "";

        if (passwordEntry.isEmpty()) { // If the password field is left empty
            AlertDialog.Builder passwordError = new AlertDialog.Builder(LoginActivity.this).setTitle("Password Error")
                    .setMessage("Password should not be left empty")
                    .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (dialog != null) {
                                dialog.dismiss();
                            }
                        }
                    });

            passwordError.show();
            passwordField.setError("Password cannot be left empty");
            passwordField.setText(flushedString);

            return false;

        } else {
            passwordField.setError(null);
            login();
            showLoginDialogue();
            return true;
        }
    }

    private void showLoginDialogue() {
        // Create the progress dialogue
        final ProgressDialog dialog = new ProgressDialog(LoginActivity.this);
        dialog.setTitle("Logging in..");
        dialog.setMessage("Please Wait..");

        dialog.setCancelable(false);

        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        new Thread(new Runnable() { // Create a new thread
            @Override
            public void run() {
                try {
                    Thread.sleep(2400);

                } catch (InterruptedException exc) {

                    Log.d("Error : ", exc.toString());
                }

                dialog.dismiss();
            }
        }).start();

        dialog.show(); // Show the progress bar
    }

    private void login() { // Logs the user in
        final String emailInput = emailAddressField.getText().toString(); // Get the e-mail input
        String passwordInput = passwordField.getText().toString(); // Get the password input

        auth.signInWithEmailAndPassword(emailInput, passwordInput).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) { // If the task is successful
                    isLoggedIn = true;

                    Toast.makeText(LoginActivity.this, "You are logged in as " + emailInput, Toast.LENGTH_LONG).show();
                    transitionToHomepage(); // Take user to homepage
                    setVisibilityOfLogout();

                }

                else if (!task.isSuccessful()) { // If the task is not successful, i.e the credentials do not match
                    Toast.makeText(LoginActivity.this, "Invalid credentials", Toast.LENGTH_LONG).show(); // Show error message
                    isLoggedIn = false;
                }
            }
        });
    }

    private void setVisibilityOfLogout() {

         if(isLoggedIn) {

             LayoutInflater inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

             //layout = (LinearLayout)inflater.inflate(R.menu.logout_menu);

             logoutBtn = findViewById(R.id.logout_button);
             logoutBtn.setVisibility(View.VISIBLE);
         }

    }

    public void transitionToHomepage() { // Routine that takes user to home page
        try {
            Intent homeIntent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(homeIntent);

        } catch (ActivityNotFoundException act) {
            Log.d("Error", act.toString());
        }
    }
}