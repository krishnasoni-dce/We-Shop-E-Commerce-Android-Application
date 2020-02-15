package com.example.weshopapplication;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

// Author of Application: Sabin Constantin Lungu
// Purpose of Class: To allow user's to fill out a contact us form if they are having issues with the app
// Date of last modification: 15/02/2020
// Any Errors? No

public class ContactUsActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        View submitComplaint = findViewById(R.id.submitComplaintBtn);
        submitComplaint.setOnClickListener(this);

        View checkComplaint = findViewById(R.id.checkComplaintsBtn);
        checkComplaint.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        try {

            switch (v.getId()) {
                case R.id.submitComplaintBtn:
                    Intent submitComplaintIntent = new Intent(ContactUsActivity.this, SubmitComplaint.class);
                    startActivity(submitComplaintIntent);
                    break;

                case R.id.checkComplaintsBtn:
                    Intent checkComplaintsIntent = new Intent(ContactUsActivity.this, CheckComplaints.class);

                    startActivity(checkComplaintsIntent);
                    break;
            }

        } catch (ActivityNotFoundException exc) {

            Log.d("Error ", exc.toString());
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
