package com.example.weshopapplication;

import android.view.View;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertNotNull;

// Purpose of Test: To test if the MainActivity loads
// Author of Test: Sabin Constantin Lungu


@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Rule
    public ActivityTestRule<RegisterActivity> registerRule = new ActivityTestRule<>(RegisterActivity.class);

    private MainActivity mainActivity = null;
    
    private RegisterActivity registerActivity = null;


    @Before
    public void setUp() throws Exception {
        mainActivity = activityRule.getActivity(); // Get the activity
        registerActivity = registerRule.getActivity();
    }

    @Test
    public void testMainActivityLauncher() { // Routine that tests if the main activity launches.
        View view = mainActivity.findViewById(R.id.welcomeTxt);


        assertNotNull(view);
    }

    @Test
    public void rawUsernameInput() {

    }

    @Test
    public void testRegisterActivityLauncher() {
        View registerView = registerActivity.findViewById(R.id.registerTxt);
        assertNotNull(registerView);
    }

    @After
    public void tearDown() throws Exception {
        mainActivity = null;
        registerActivity = null;
    }
}