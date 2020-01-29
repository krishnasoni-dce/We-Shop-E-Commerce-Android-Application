package com.example.weshopapplication;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    private FirebaseAuth auth;
    private Pattern regexPatterns = Pattern.compile("[$&+,:;=\\\\?@#|/'<>.^*()%!-]"); // Regex patterns

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.emailAddressField = findViewById(R.id.emailAddressField);
        this.passwordField = findViewById(R.id.passwordField);
        this.loginButton = findViewById(R.id.loginBtn);

        this.firebaseFirestore = FirebaseFirestore.getInstance();
        this.auth = FirebaseAuth.getInstance();

        this.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateEmailAddress();
                validatePassword();
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        if (menu != null) { // If there is currently no menu
            MenuInflater menuInflater = getMenuInflater();
            menuInflater.inflate(R.menu.login_menu, menu);
        } else {
            return false;
        }

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
            }

        } catch (ActivityNotFoundException act) {
            Log.d(errorMessage, act.toString());
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
            passwordField.setText(flushedString);

            return false;

        } else {
            passwordField.setError(null);
            login();
            return true;
        }
    }

    private void login() { // Logs the user in
        final String emailInput = emailAddressField.getText().toString();
        String passwordInput = passwordField.getText().toString();

        auth.signInWithEmailAndPassword(emailInput, passwordInput).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, "You are logged as " + emailInput, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
