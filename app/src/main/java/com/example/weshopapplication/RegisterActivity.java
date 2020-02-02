package com.example.weshopapplication;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.regex.Pattern;

// Author: Sabin Constantin Lungu.
// Matriculation Number: 40397517
// Purpose of Activity: To allow users to register an account and write their registration data to a Firebase database.
// Any errors? Pending testing..

public class RegisterActivity extends AppCompatActivity { // Register class
    private static final String CHANNEL_ID = "register_channel";
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private EditText usernameField;

    private TextView registerText; // The register text
    private EditText passwordField;
    private RadioButton termsAndConditions;

    private Button registerButton; // Register button
    private EditText emailAddressField;

    private boolean hasDigits; // True or false if the inputs have numbers
    private boolean startsWithUppercase; // True or false if the inputs start with an upper case.
    private boolean hasCharacters; // True or false if the input has characters
    private boolean hasRegex;

    private boolean isEmpty;
    private boolean isValid;
    private boolean isRegistered;

    private NotificationManagerCompat notificationManager; // Notification manager variable
    private FirebaseAuth authentication = FirebaseAuth.getInstance();
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
        notificationManager = NotificationManagerCompat.from(this); // Register the notification manager


        this.registerButton.setOnClickListener(new View.OnClickListener() { // Add listener to the button
            @Override
            public void onClick(View buttonView) {
                validateUsername(); // Call method to validate username
                validateEmailAddress();

                validatePassword(); // Call method to validate the password
                validateTermsAndConditions();
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) { // Routine that creates the menu
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.register_menu, menu); // Inflate the menu

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) { // Routine that determines which menu item is chosen

        try {
            switch (item.getItemId()) {

                case R.id.sportsAndOutdoorsCategory: // If the sports and outdoors category is clicked on
                    Intent sportsActivity = new Intent(RegisterActivity.this, SportsAndOutdoorsActivity.class); // Create intent for sports activity
                    startActivity(sportsActivity);

                    return true;


                case R.id.techCategory:
                    Intent techActivity = new Intent(RegisterActivity.this, TechActivity.class);
                    startActivity(techActivity);

                    return true;

                case R.id.clothingCategory:
                    Intent clothingCategory = new Intent(RegisterActivity.this, ClothingCategory.class);
                    startActivity(clothingCategory);

                    return true;

                case R.id.diyCategory:
                    Intent diyCategory = new Intent(RegisterActivity.this, DIYActivity.class);
                    startActivity(diyCategory);

                    return true;

                default:

                    return super.onOptionsItemSelected(item); // Return the base item selected
            }

        } catch (ActivityNotFoundException act) {
            Log.d("Error : ", act.toString()); // Get the cause of the error.
        }

        return true;
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
            usernameField.setText(""); // Flush the empty field out
            isEmpty = true; // The field is empty

            isValid = !usernameInputField.isEmpty();

            return false; // Return false
        }

        for (int i = 0; i < usernameInputField.length(); i++) { // Loop over the username

            if (!Character.isDigit(usernameInputField.charAt(i)) && usernameInputField.length() > 20) { // If the username has no digits or the length is bigger than 20

                usernameField.setError("Username must contain digits and length must not be bigger than 20 characters");

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
                break;
            }

            if (Character.isDigit(usernameInputField.charAt(i)) && usernameInputField.length() != 20) {
                isValid = true;
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
                isValid = false;

            } else {
                isValid = true;
                usernameField.setError(null);
            }

        }

        return true;
    }

    private void validateEmailAddress() { // Routine that validates the e-mail address.

        String emailAddressInputField = emailAddressField.getText().toString().trim(); // Get the input for the emailAddress


        if (emailAddressInputField.isEmpty()) {
            AlertDialog.Builder emailError = new AlertDialog.Builder(RegisterActivity.this).setTitle("E-mail Error").setMessage("Re-Enter E-mail")
                    .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (dialog != null) {
                                dialog.dismiss();
                            }
                        }
                    });

            emailError.show();
            emailError.setCancelable(true);

            emailAddressField.setError("E-mail Field cannot be left empty");
            isEmpty = true;
        }

        if (emailAddressInputField.length() > 25) { // If the e-mail length is bigger than 25 characters
            emailAddressField.setError("E-mail can't have less than 0 characters or more than 25"); // Display error

            isValid = false; // Not valid
            return; // Return.
        }

        if (!regexPatterns.matcher(emailAddressInputField).find()) { // If there is no regex characters matched including the @ symbol that is needed

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
            return;
        }
    }

    private boolean validatePassword() { // Routine to validate the password
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
            return false;
        }

        for (int i = 0; i < passwordEntryField.length(); i++) { // Loop over the password entry

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

        return true;
    }

    private boolean validateTermsAndConditions() {

        if (!termsAndConditions.isChecked()) { // If the terms and conditions box is not checked
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

            boxError.show(); // Show the error
            termsAndConditions.setError("Must be ticked");
        }

        if (termsAndConditions.isChecked() && isValid) { // If the terms and conditions box is checked and the validation is all valid

            sendNotification(); // CALL METHOD TO SEND NOTIFICATION
            writeToDatabase(); // Write registration data to database
            writeToFirestore();
            transitionToLogin(); // Take user to login page

        } else {
            termsAndConditions.setError(null); // Otherwise set no error

            return false; // Otherwise return false
        }

        return true; // Fallback onto previous statement and return true
    }


    private void sendNotification() { // Routine to send notification after registration

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID) // Create the notification builder by passing the context to display it in and the channel id
                .setSmallIcon(R.drawable.ic_message_black_24dp) // Give the notification an icon
                .setContentTitle("Registration Status") // Set the content title of it
                .setContentText("You have registered Success!") // Give the message to be displayed
                .setPriority(NotificationCompat.PRIORITY_HIGH) // Set the priority of the notification
                .setColor(Color.BLACK) // Give the notification a colour
                .setAutoCancel(true); // Can auto cancel it

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(RegisterActivity.this); // Create the compat notification
        notificationManager.notify(0, builder.build()); // Build the notification
    }

    private void writeToDatabase() { // Writes to database

        // Get the user inputs
        String emailEntry = emailAddressField.getText().toString();
        String passwordEntry = passwordField.getText().toString();

        authentication.createUserWithEmailAndPassword(emailEntry, passwordEntry).addOnCompleteListener(new OnCompleteListener<AuthResult>() { // Create user account with e-mail and password
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) { // If the register task is successful

                    Toast.makeText(RegisterActivity.this, "Data written to DB", Toast.LENGTH_LONG).show();

                } else {

                    Toast.makeText(RegisterActivity.this, "Could not write to DB", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void writeToFirestore() {

        String usernameEntry = usernameField.getText().toString();
        String emailEntry = emailAddressField.getText().toString();
        String passwordEntry = passwordField.getText().toString();

        HashMap<String, Object> user_data = new HashMap<>(); // HashMap for the user data

        // Add the field entries into the HashMap
        user_data.put("username", usernameEntry);
        user_data.put("email_address", emailEntry);
        user_data.put("password", passwordEntry);

        db.collection("user_data").add(user_data).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(RegisterActivity.this, "Added data to firestore", Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("error : ", e.toString());
            }
        });
    }

    private void transitionToLogin() { // Take the user to the login page after registration
        String errorMessage = "Error";
        try {

            // Take user to login
            Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(loginIntent); // Start the login activity

        } catch (ActivityNotFoundException act) {

            Log.d(errorMessage, act.toString());
        }
    }
}