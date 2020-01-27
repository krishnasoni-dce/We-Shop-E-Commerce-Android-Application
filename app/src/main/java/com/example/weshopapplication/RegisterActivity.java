package com.example.weshopapplication;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

// Author: Sabin Constantin Lungu.
// Matriculation Number: 40397517
// Purpose of Activity: To allow users to register an account.
// Any errors? N/A


public class RegisterActivity extends AppCompatActivity { // Register class
    private static final String REGISTER_CHANNEL_ID = "register_channel";
    private EditText usernameField;
    private EditText emailAddressField;
    private static final int NOTIFICATION_CODE = 1;
    private TextView registerText; // The register text
    private EditText passwordField;
    private RadioButton termsAndConditions;
    private Button registerButton; // Register button
    private FirebaseAuth authentication;

    private boolean hasDigits; // True or false if the inputs have numbers
    private boolean startsWithUppercase; // True or false if the inputs start with an upper case.
    private boolean hasCharacters; // True or false if the input has characters
    private boolean hasRegex;

    private boolean isEmpty;
    private boolean isValid;
    private boolean isRegistered;
    private NotificationManager notificationManager; // Notification manager variable

    private Pattern regexPatterns = Pattern.compile("[$&+,:;=\\\\?@#|/'<>.^*()%!-]"); // Regex patterns

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialise components
        this.usernameField = findViewById(R.id.usernameField);
        this.emailAddressField = findViewById(R.id.emailAddressField);
        this.registerText = findViewById(R.id.registerTxt);
        this.passwordField = findViewById(R.id.passwordField);

        this.termsAndConditions = findViewById(R.id.termsAndConditionsBox);
        this.registerButton = findViewById(R.id.registerBtn);
        this.authentication = FirebaseAuth.getInstance(); // Get an instance of the connection

        this.registerButton.setOnClickListener(new View.OnClickListener() { // Add listener to the button
            @Override
            public void onClick(View buttonView) {
                validateUsername(); // Call method to validate username
                validatePassword();
                validateEmailAddress();
            }
        });


    }

    public void requestNotificationPermission() { // Routine that requests the user to use permissions

    }

    public void onStart() {
        super.onStart();

        FirebaseUser currentUser = authentication.getCurrentUser(); // Get current user
    }

    private boolean validateUsername() { // Routine that validates the username entered by the user against specific criteria
        String usernameInputField = usernameField.getText().toString().trim();

        if (usernameInputField.isEmpty()) { // If the input field is left empty

            usernameField.setError("Can't be left empty");
            isEmpty = true;

            return false;
        }

        for (int i = 0; i < usernameInputField.length(); i++) { // Loop over the username

            if (!Character.isDigit(usernameInputField.charAt(i)) && usernameInputField.length() > 10) {

                usernameField.setError("Username must contain digits and length must not be bigger than 10");

                AlertDialog.Builder usernameError = new AlertDialog.Builder(RegisterActivity.this).setMessage("Please re-enter Username")
                        .setTitle("Username Error").setNegativeButton("OK", new DialogInterface.OnClickListener() {
                            @Override

                            public void onClick(DialogInterface dialog, int which) {
                                if (dialog != null) {
                                    dialog.dismiss();
                                }
                            }
                        });

                usernameError.show(); // Show the dialogue
                usernameField.setText(""); // Flush out the data

                hasDigits = false; // Has digits is false.
                isValid = false;

                return false;
            }

            if (regexPatterns.matcher(usernameInputField).find()) { // If the username has a regex character.
                usernameField.setError("Username should not contain regex character");

                AlertDialog.Builder regexWarning = new AlertDialog.Builder(RegisterActivity.this).setMessage("Please re-enter Username.")
                        .setTitle("Username Regex Warning").setNegativeButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (dialog != null) {
                                    dialog.dismiss();
                                }
                            }
                        });

                regexWarning.show();
                usernameField.setText("");

                return false;
            } else {

                isValid = true;
                hasDigits = true;
                hasRegex = true;
                usernameField.setError(null);
                return true;
            }

        }

        return false;
    }

    private boolean validateEmailAddress() {

        String emailAddressInputField = emailAddressField.getText().toString().trim(); // Get the input for the emailAddress

        for (int i = 0; i < emailAddressInputField.length(); i++) {
            if (!regexPatterns.matcher(emailAddressInputField).matches()) {
                emailAddressField.setError("E-mail Address must contain @ symbol");
            } else {
                // Otherwise no errors
                emailAddressField.setError(null);
                return true;
            }
        }

        return false;
    }

    private boolean validatePassword() {


        return false;
    }

    private void validateTermsAndConditions() {

    }

    private void writeToDatabase() { // Routine to write the registration details.

    }

    private void sendNotification() { // Routine that sends notification once the registration is successful

    }


}