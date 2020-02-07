package com.example.weshopapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class TechActivityTwo extends AppCompatActivity {
    // Components for the Third Product
    private TextView thirdProductTextView;
    private ImageView thirdProductImage;
    private TextView thirdProductCostTxt;
    private TextView thirdProductColourLbl;
    private Spinner thirdProductDropDown;

    private TextView thirdQuantityLabel;
    private Spinner thirdQuantityDropDown;
    private Button thirdAddToBasketButton;

    // Components for the Fourth Product to sell
    private TextView fourthProductTextView;
    private ImageView fourthProductImage;
    private TextView fourthProductCost;
    private TextView fourthProductColourLbl;
    private Spinner fourthProductColourSpinner;

    private TextView fourthProductMemoryLbl;
    private Spinner fourthProductMemoryDropDown;
    private TextView fourthProductCapacity;
    private Spinner fourthProductCapacityDropDown;

    // An array list of colours, quantities and capacity
    private ArrayList<TechActivity.Colours> listOfColours;
    private ArrayList<TechActivity.Quantities> listOfQuantities;
    private ArrayList<Capacity> listOfCapacities;

    private int quantity_zero_cost = 0;
    private int quantity_one_cost = 750;

    // Formulae to calculate price & Capacity
    private int quantity_two_cost = 3 * quantity_one_cost; // Quantity 2 is 3 times the price of 1 quantity.
    private int quantity_three_cost = 4 * quantity_one_cost;
    private int quantity_four_cost = 5 * quantity_one_cost;

    // Formulae for the cost of different capacities
    private int sixtyFourGBCost = quantity_one_cost * 2;
    private int oneTwoEightGbCost = quantity_two_cost * 3;
    private int twoFiveSixGbCost = quantity_four_cost * 2;
    private int fiveTwelveGbCost = quantity_three_cost * quantity_four_cost;

    // Hashmap to store the products that will be added to the basket
    private HashMap<Integer, Products> listOfProductsToAdd = new HashMap<>();

    // Array adapters to aid the addition of colours, capacity and colours to the array list
    private CustomArrayAdapter quantitiesAdapter;
    private ColourArrayAdapter colourArrayAdapter;
    private CapacityArrayAdapter capacityArrayAdapter;

    // Boolean variables that holds either true or false
    private boolean addedColours = false;
    private boolean addedQuantities = false;
    private boolean addedCapacities = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) { // On create method
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tech_two);

        // Initialise components for the THIRD PRODUCT

        this.thirdProductTextView = findViewById(R.id.thirdProductTextView);
        this.thirdProductImage = findViewById(R.id.thirdProductImg);
        this.thirdProductCostTxt = findViewById(R.id.thirdProductCostTxt);
        this.thirdProductColourLbl = findViewById(R.id.thirdColourLbl);
        this.thirdProductDropDown = findViewById(R.id.thirdColourDropDownMenu);

        this.thirdQuantityLabel = findViewById(R.id.thirdQuantityLbl);
        this.thirdQuantityDropDown = findViewById(R.id.thirdQuantityDropDownMenu);
        this.thirdAddToBasketButton = findViewById(R.id.thirdAddToBasketBtn);

        // INITIALISE COMPONENTS FOR THE FOURTH PRODUCT

        this.fourthProductTextView = findViewById(R.id.fourthProductTitleLbl);
        this.fourthProductImage = findViewById(R.id.fourthProductImg);
        this.fourthProductCost = findViewById(R.id.fourthProductImgCost);
        this.fourthProductColourLbl = findViewById(R.id.fourthProductColourLabel);
        this.fourthProductColourSpinner = findViewById(R.id.fourthProductDropDownMenu);
        this.fourthProductMemoryLbl = findViewById(R.id.fourthProductMemoryLabel);
        this.fourthProductMemoryDropDown = findViewById(R.id.fourthProductMemoryDropDownMenu);
        this.fourthProductCapacity = findViewById(R.id.fourthProductCapacityLbl);

        this.fourthProductCapacityDropDown = findViewById(R.id.fourthProductCapacityDropDown);

        // Put array list on the heap
        this.listOfColours = new ArrayList<>();
        this.listOfQuantities = new ArrayList<>();
        this.listOfCapacities = new ArrayList<>();

        addToColoursList();
        addToQuantitiesList();
        addToCapacityList();

        this.quantitiesAdapter = new CustomArrayAdapter(TechActivityTwo.this, listOfQuantities);
        quantitiesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Add the adapter
        thirdQuantityDropDown.setAdapter(quantitiesAdapter);
    }

    private void addToColoursList() { // Routine that adds the colours to the array list
        String coloursMsgAdded = "Colours Added Success";
    }

    private void addToQuantitiesList() { // Routine that adds the quantities to the array list

    }

    private void addToCapacityList() {

    }

    @Override
    protected void onDestroy() { // End the activity
        super.onDestroy();
    }

    @Override
    public void onBackPressed() { // Click back button
        super.onBackPressed();
    }

    @Override
    protected void onResume() { // Resumes the activity
        super.onResume();
    }

    @Override
    protected void onRestart() { // When restarted.
        super.onRestart();
    }
}
