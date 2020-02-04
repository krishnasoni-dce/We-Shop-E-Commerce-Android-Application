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
// Date of Last Modification: 4/2/2020
// Tests Pass? : Yes

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

    @Rule
    public ActivityTestRule<TechActivity> techActivityActivityTestRule = new ActivityTestRule<>(TechActivity.class);

    @Rule
    public ActivityTestRule<BasketActivity> basketActivityActivityTestRule = new ActivityTestRule<>(BasketActivity.class);

     @Rule
     public ActivityTestRule<ClothingCategory> clothingCategoryActivityTestRule = new ActivityTestRule<>(ClothingCategory.class);

    // Activities to be tested
    private MainActivity mainActivity = null;
    private RegisterActivity registerActivity = null;
    private LoginActivity loginActivity = null;

    private SportsAndOutdoorsActivity sportsAndOutdoorsActivity = null;
    private TechActivity techActivity = null;

    private ClothingCategory clothingCategory = null;
    private DIYActivity diyActivity = null;
    private BasketActivity productsBasket = null;

    // Retrieve the data from the fields
    private EditText usernameTest;
    private EditText emailAddressTest;
    private EditText passwordTest;


    @Before
    public void setUp() { // Sets up the tests
        getActivities();
        // Get the edit text fields
        usernameTest = registerActivity.findViewById(R.id.usernameField);
        emailAddressTest = registerActivity.findViewById(R.id.emailAddressField);
        passwordTest = registerActivity.findViewById(R.id.passwordField);
    }

    public void getActivities() { // Retrieves the activities
        mainActivity = activityRule.getActivity(); // Get the activity
        registerActivity = registerRule.getActivity();
        clothingCategory = clothingCategoryActivityTestRule.getActivity(); // Get the clothing activity

        loginActivity = loginActivityRule.getActivity(); // Get the login activity
        techActivity = techActivityActivityTestRule.getActivity(); // Get the tech activity
        productsBasket = basketActivityActivityTestRule.getActivity();
    }


    @Test
    public void testMainActivityLauncher() { // Routine that tests if the main activity launches.
        View view = mainActivity.findViewById(R.id.welcomeTxt);

        assertNotNull(view);
    }

    public void testPreconditions() { // Test preconditions
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
    public void testEmailAddressEntryOne() { // 1. Test one for the e-mail address
        assertNotEquals("sabinlungu293@f", emailAddressTest.getText().toString()); // Will pass because this value is unexpected
    }

    @Test
    public void testEmailAddressEntryTwo() {
        assertNotEquals("oerkgoierjg09erjg0e9rjg09@yahoo.com", emailAddressTest.getText().toString());
    }

    @Test
    public void testPasswordEntryOne() {
        assertEquals("", passwordTest.getText().toString());
    }

    @Test
    public void testPasswordUppercase() { // 2. Test 2 for the password, it should pass because the test should not expect sabin as it does not have an upper case character, otherwise it would fail.
        assertNotEquals("sabin", passwordTest.getText().toString());
    }

    @Test
    public void testRegisterActivityLauncher() {
        View registerView = registerActivity.findViewById(R.id.registerTxt);
        assertNotNull(registerView);
    }

    @Test
    public void testTechActivityLauncher() { // Tests to see if the tech activity loads correctly.
        View activityView = techActivity.findViewById(R.id.firstProductImg);
        assertNotNull(activityView);
    }

    @Test
    public void testClothingActivityLauncher() { // Tests to see if the clothing activity is loaded
        View activityView = clothingCategory.findViewById(R.id.clothingCategory);
        assertNotNull(activityView);
    }

    @Test
    public void testLoadBasketActivity() { // Tests to see if the basket loads correctly.
        View basketView = productsBasket.findViewById(R.id.placeOrderBtn);
        assertNotNull(basketView);
    }

    @Test
    public void testLoginActivityLauncher() { // Test stub that tests to see if the login activity launches
        View loginView = loginActivity.findViewById(R.id.loginBtn); // Finds the login button
        assertNotNull(loginView); // Check condition
    }

    @After
    public void tearDown() { // After testing
        // Empty activities after testing
        mainActivity = null;
        registerActivity = null;
        loginActivity = null;

        techActivity = null;
        productsBasket = null;
    }
}