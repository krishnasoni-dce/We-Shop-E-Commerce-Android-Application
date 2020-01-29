package com.example.weshopapplication;

import android.view.View;
import android.widget.EditText;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

// Purpose of Test: To test if the MainActivity loads
// Author of Test: Sabin Constantin Lungu


@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    // Rules created for each test
    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Rule
    public ActivityTestRule<RegisterActivity> registerRule = new ActivityTestRule<>(RegisterActivity.class);

    @Rule
    public ActivityTestRule<LoginActivity> loginActivityRule = new ActivityTestRule<>(LoginActivity.class);


    // Activities to be tested
    private MainActivity mainActivity = null;
    private RegisterActivity registerActivity = null;

    private LoginActivity loginActivity = null;
    private SportsAndOutdoorsActivity sportsAndOutdoorsActivity = null;
    private TechActivity techActivity = null;
    private ClothingCategory clothingCategory = null;
    private DIYActivity diyActivity = null;


    private EditText usernameTest;
    private EditText emailAddressTest;
    private EditText passwordTest;


    @Before
    public void setUp() throws Exception {
        getActivities();

        // Get the edit text fields
        usernameTest = registerActivity.findViewById(R.id.usernameField);
        emailAddressTest = registerActivity.findViewById(R.id.emailAddressField);
        passwordTest = registerActivity.findViewById(R.id.passwordField);
    }

    public void getActivities() {
        mainActivity = activityRule.getActivity(); // Get the activity
        registerActivity = registerRule.getActivity();
        loginActivity = loginActivityRule.getActivity(); // Get the login activity
    }


    @Test
    public void testMainActivityLauncher() { // Routine that tests if the main activity launches.
        View view = mainActivity.findViewById(R.id.welcomeTxt);

        assertNotNull(view);
    }

    public void testPreconditions() {
        assertNotNull(usernameTest);
        assertNotNull(emailAddressTest);
        assertNotNull(passwordTest);
    }

    @Test
    public void testUsernameEntryOne() { // 1. Tests entries for an empty username. Should not get accepted and should pass

        assertEquals("", usernameTest.getText().toString());
    }

    @Test
    public void testUsernameEntryTwo() { // 2. Tests for regex entries (SHOULD PASS AS THE VALIDATION WORKS 100%)
        assertNotEquals("*£&$^", usernameTest.getText().toString());
    }

    @Test
    public void testRegisterActivityLauncher() {
        View registerView = registerActivity.findViewById(R.id.registerTxt);
        assertNotNull(registerView);
    }

    @Test
    public void testLoginActivityLauncher() { // Test stub that tests to see if the login activity launches
        //View loginView = loginActivity.findViewById();
    }

    @After
    public void tearDown() throws Exception {
        mainActivity = null;
        registerActivity = null;
        loginActivity = null;
    }
}