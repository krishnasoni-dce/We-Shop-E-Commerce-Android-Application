package com.example.weshopapplication;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.regex.Pattern;

// Author: Sabin Constantin Lungu.
// Matriculation Number: 40397517
// Purpose of Activity: To allow users to register an account.
// Any errors? N/A


public class RegisterActivity extends AppCompatActivity { // Register class
    private static final String CHANNEL_ID = "register_channel";
    private EditText usernameField;
    private EditText emailAddressField;
    private static final int NOTIFICATION_CODE = 1;
    private static final int PERMISSION_CODE = 1;
    private TextView registerText; // The register text
    private EditText passwordField;
    private RadioButton termsAndConditions;
    private Button registerButton; // Register button
    private FirebaseFirestore authentication;

    private boolean hasDigits; // True or false if the inputs have numbers
    private boolean startsWithUppercase; // True or false if the inputs start with an upper case.
    private boolean hasCharacters; // True or false if the input has characters
    private boolean hasRegex;

    private boolean isEmpty;
    private boolean isValid;
    private boolean isRegistered;
    private NotificationManagerCompat notificationManager; // Notification manager variable

    private Pattern regexPatterns = Pattern.compile("[$&+,:;=\\\\?@#|/'<>.^*()%!-]"); // Regex patterns

    @Override
    protected void onCreate(Bundle savedInstanceState) { // Android Lifecycle method 1
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialise components
        this.usernameField = findViewById(R.id.usernameField);
        this.emailAddressField = findViewById(R.id.emailAddressField);
        this.registerText = findViewById(R.id.registerTxt);
        this.passwordField = findViewById(R.id.passwordField);


        this.termsAndConditions = findViewById(R.id.termsAndConditionsBox);
        this.registerButton = findViewById(R.id.registerBtn);
        this.authentication = FirebaseFirestore.getInstance(); // Get an instance of the connection

        notificationManager = NotificationManagerCompat.from(this);
        FirebaseApp.initializeApp(this); // Initialise the firebase app

        this.registerButton.setOnClickListener(new View.OnClickListener() { // Add listener to the button
            @Override
            public void onClick(View buttonView) {
                requestNotificationPermission();
                validateUsername(); // Call method to validate username
                validatePassword();

                validateEmailAddress();
                validateTermsAndConditions();

                if (isValid == validateEmailAddress() || isValid == validatePassword() || isValid == validateUsername()) {
                    sendNotification();
                }

                writeToDatabase();
                transitionToLogin(); // Take user to login after registration

            }
        });

    }


    public void requestNotificationPermission() { // Routine that requests the user to use permissions

    }

    public void onStart() { // Android Lifecycle method 2.
        super.onStart();
    }

    private boolean validateUsername() { // Routine that validates the username entered by the user against specific criteria
        String usernameInputField = usernameField.getText().toString().trim();

        if (usernameInputField.isEmpty()) { // If the input field is left empty
            AlertDialog.Builder emptyDialog = new AlertDialog.Builder(RegisterActivity.this).setTitle("Username Error")
                    .setMessage("Re-enter username please").setNegativeButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (dialog != null) {
                                dialog.dismiss();
                            }
                        }
                    });

            emptyDialog.show();
            usernameField.setError("Can't be left empty");
            usernameField.setText("");
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

                isValid = true; // Username valid
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

        if (emailAddressInputField.isEmpty()) {
            emailAddressField.setError("E-mail Field cannot be left empty");
            isEmpty = true;
        }

        if (emailAddressInputField.length() <= 0 || emailAddressInputField.length() > 25) {
            emailAddressField.setError("E-mail can't have less than 0 characters or more than 25");
            return false;
        }


        // Loop over the e-mail field

        if (!regexPatterns.matcher(emailAddressInputField).find()) {

            emailAddressField.setError("E-mail Address must contain @ symbol");
            AlertDialog.Builder emailRegexWarning = new AlertDialog.Builder(RegisterActivity.this).setTitle("E-mail Regex Warning").setMessage("E-mail must contain @ symbol")
                    .setNegativeButton("OK", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (dialog != null) { // If the dialog is not empty
                                dialog.dismiss();
                                dialog.cancel();
                            }
                        }
                    });

            emailRegexWarning.show();
            emailAddressField.setText("");

            return false;

        } else {

            // Otherwise no errors
            emailAddressField.setError(null);
            return true;
        }

    }

    private boolean validatePassword() {
        String passwordEntryField = passwordField.getText().toString().trim();

        if (passwordEntryField.isEmpty() && !regexPatterns.matcher(passwordEntryField).matches()) { // If the password is empty and there are no regex characters found
            AlertDialog.Builder passwordWarning = new AlertDialog.Builder(RegisterActivity.this).setTitle("Password Warning")
                    .setMessage("Re-enter Password Please").setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (dialog != null) {
                                dialog.dismiss();
                            }
                        }
                    });

            passwordWarning.show();
            passwordField.setText("");

            passwordField.setError("Password cannot be left empty & must contain special characters");
            isEmpty = true;
            hasRegex = false;
        }


        for (int i = 0; i < passwordEntryField.length(); i++) {
            if (!Character.isUpperCase(passwordEntryField.charAt(0))) { // If the password does not start with an upper case character
                AlertDialog.Builder pwUpperCase = new AlertDialog.Builder(RegisterActivity.this).setTitle("Password Error")
                        .setMessage("Re-enter Password").setNegativeButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (dialog != null) {
                                    dialog.dismiss();
                                }
                            }
                        });

                pwUpperCase.show();
                passwordField.setText("");
                passwordField.setError("Password must start with upper case character");
                break;
            }
        }

        return false;
    }

    private void validateTermsAndConditions() {
        if (!termsAndConditions.isChecked()) { // If the terms and conditions box is not ticked
            AlertDialog.Builder boxError = new AlertDialog.Builder(RegisterActivity.this).setTitle("T&C Box Not Checked")
                    .setMessage("Please tick terms and conditions box")
                    .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        @Override

                        public void onClick(DialogInterface dialog, int which) {
                            if (dialog != null) {
                                dialog.dismiss();
                            }
                        }
                    });

            boxError.show();
        }
    }


    private void writeToDatabase() { // Writes to database
        final boolean isWritten = false;

        try {
            HashMap<String, String> userData = new HashMap<>(); // A HashMap of a key and pair value for the user data

            userData.put("username", usernameField.getText().toString());
            userData.put("emailAddress", emailAddressField.getText().toString());
            userData.put("password", passwordField.getText().toString());

            authentication.collection("Users").document("Registered_Users").set(userData)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void argNull) {
                            AlertDialog.Builder writeSuccess = new AlertDialog.Builder(RegisterActivity.this).setTitle("Success")
                                    .setMessage("Data written success").setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (dialog != null) {
                                                dialog.dismiss();
                                            }
                                        }
                                    });

                            writeSuccess.show();
                        }
                    });

        } catch (IllegalArgumentException il) {
            Log.d("Error : ", il.getMessage());

        }
    }

    private void sendNotification() {
        String notification_message = "Register Success"; // Message to display

        NotificationCompat.Builder builder = new NotificationCompat.Builder(RegisterActivity.this, CHANNEL_ID) // Create a notification builder by passing the activity and channel id
                .setContentTitle(notification_message) // Set the content title of the notification
                .setSmallIcon(R.drawable.ic_message_black_24dp) // Displays the icon.
                .setContentText("You have successfully registered") // Sets the content text
                .setAutoCancel(true); // Automatically cancels

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(NOTIFICATION_CODE, builder.build()); // Build the notification with a code.
    }

    private void transitionToLogin() { // Take user to login after registration
        try {

            Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class); // A new intent.
            startActivity(loginIntent);

        } catch (ActivityNotFoundException act) {
            Log.d("Cause of error : ", act.getMessage()); // Displays error message in the log if an activity is not found.
        }
    }
}