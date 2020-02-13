package com.example.weshopapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

// Author of Application & Class: Sabin Constantin Lungu
// Purpose of Class: Stores the code needed to implement the Sports and Outdoors Activity 1.
// Date of Last Modification: 13/02/2020
// Any Errors? Currently None

public class SportsAndOutdoorsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    // Set-up variables
    private int current_product_id = 1;
    private ImageView cartIcon;
    private TextView firstSportsOutdoorTxt;
    private ImageView firstSportsOutdoorImg;
    private TextView firstSportsOutdoorCostTxt;
    private TextView firstSportsOutdoorColourLbl;
    private Spinner firstSportsOutdoorsColourMenu;

    private TextView firstSportsOutdoorQuantityLbl;
    private Spinner firstSportsOutdoorQuantityMenu;

    private TextView firstSportsOutdoorsSizeLbl;
    private Spinner firstSportsOutdoorsSizeMenu;
    private Button firstSportsAddToBasketBtn;

    private TextView secondSportsOutdoorTxt;
    private ImageView secondSportsOutdoorImg;
    private TextView secondSportsOutdoorCostLbl;
    private TextView secondSportsOutdoorColourLbl;
    private Spinner secondSportsOutdoorsColourMenu;

    private TextView secondSportsOutdoorQuantityLbl;
    private Spinner secondSportsOutdoorQuantityMenu;

    private TextView secondSportsOutdoorSizeLbl;
    private Spinner secondSportsOutdoorSizeMenu;
    private Button secondSportsAddToBasketBtn;
    private Button nextPageBtn;

    private double[] productOneCosts = {0.00, 90.00, 180.00, 360.00, 720.00, 1440.00};
    private double[] productTwoCosts = {0.00, 50.00, 100.00, 150.00, 200.00, 250.00};

    private ArrayList<TechActivity.Colours> listOfColoursOne;
    private ArrayList<TechActivity.Quantities> listOfQuantitiesOne;
    private ArrayList<Size> listOfSizesOne;

    private ArrayList<TechActivity.Colours> listOfColoursTwo;
    private ArrayList<TechActivity.Quantities> listOfQuantitiesTwo;
    private ArrayList<Size> listOfSizesTwo;

    private ColourArrayAdapter coloursAdapter;
    private CustomArrayAdapter quantitiesAdapter;
    private SizeArrayAdapter sizeArrayAdapter;

    private boolean coloursAdded;
    private boolean quantitiesAdded;

    private boolean sizesAdded;
    private boolean addedToBasket;

    private HashMap<Integer, Products> listOfProductsToAddToBasket = new HashMap<>(); // A HashMap of products to add here.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports_and_outdoors);

        // INITIALISE COMPONENTS
        this.firstSportsOutdoorTxt = findViewById(R.id.firstSportsOutdoorTxt);
        this.firstSportsOutdoorImg = findViewById(R.id.firstSportsOutdoorImg);
        this.firstSportsOutdoorCostTxt = findViewById(R.id.firstSportsOutdoorCostLbl);
        this.firstSportsOutdoorColourLbl = findViewById(R.id.firstSportsColourLbl);

        this.firstSportsOutdoorsColourMenu = findViewById(R.id.firstSportsOutdoorColourMenu);
        this.firstSportsOutdoorQuantityLbl = findViewById(R.id.firstSportsOutdoorQuantityLbl);
        this.firstSportsOutdoorQuantityMenu = findViewById(R.id.firstSportsOutdoorQuantityMenu);

        this.firstSportsOutdoorsSizeLbl = findViewById(R.id.firstSportsOutdoorSizeLbl);
        this.firstSportsOutdoorsSizeMenu = findViewById(R.id.firstSportsOutdoorSizeMenu);
        this.firstSportsAddToBasketBtn = findViewById(R.id.firstAddToBasketBtn); // Button for the first product to add to the basket.


        this.secondSportsOutdoorTxt = findViewById(R.id.secondSportsOutdoorsProductTxt);
        this.secondSportsOutdoorImg = findViewById(R.id.secondSportsOutdoorsImg);
        this.secondSportsOutdoorCostLbl = findViewById(R.id.secondSportsOutdoorProductCostLbl);
        this.secondSportsOutdoorColourLbl = findViewById(R.id.secondSportsOutdoorsColourLbl);
        this.secondSportsOutdoorsColourMenu = findViewById(R.id.secondSportsOutdoorsColourMenu);

        this.secondSportsOutdoorQuantityLbl = findViewById(R.id.secondsSportsOutdoorQuantityLbl);
        this.secondSportsOutdoorQuantityMenu = findViewById(R.id.secondSportsOutdoorsQuantityMenu);
        this.secondSportsOutdoorSizeLbl = findViewById(R.id.secondSportsOutdoorsSizeLbl);
        this.secondSportsOutdoorSizeMenu = findViewById(R.id.secondSportsOutdoorsSizeMenu);

        this.secondSportsAddToBasketBtn = findViewById(R.id.secondAddToBasketBtn);
        this.nextPageBtn = findViewById(R.id.nextPageBtn); // Button for taking the user to the next page.

        this.listOfColoursOne = new ArrayList<>();
        this.listOfQuantitiesOne = new ArrayList<>();
        this.listOfSizesOne = new ArrayList<>();

        this.listOfColoursTwo = new ArrayList<>();
        this.listOfQuantitiesTwo = new ArrayList<>();
        this.listOfSizesTwo = new ArrayList<>();

        // Set up the colours adapter
        this.coloursAdapter = new ColourArrayAdapter(SportsAndOutdoorsActivity.this, listOfColoursOne);
        coloursAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        firstSportsOutdoorsColourMenu.setAdapter(coloursAdapter);
        firstSportsOutdoorsColourMenu.setOnItemSelectedListener(this);

        // Create array adapter for the quantities for product 1
        this.quantitiesAdapter = new CustomArrayAdapter(SportsAndOutdoorsActivity.this, listOfQuantitiesOne);
        quantitiesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        firstSportsOutdoorQuantityMenu.setAdapter(quantitiesAdapter);
        firstSportsOutdoorQuantityMenu.setOnItemSelectedListener(this);

        // Create array adapter for the sizes
        this.sizeArrayAdapter = new SizeArrayAdapter(SportsAndOutdoorsActivity.this, listOfSizesOne);
        sizeArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        firstSportsOutdoorsSizeMenu.setAdapter(sizeArrayAdapter);
        firstSportsOutdoorsSizeMenu.setOnItemSelectedListener(this);

        this.coloursAdapter = new ColourArrayAdapter(SportsAndOutdoorsActivity.this, listOfColoursTwo);
        coloursAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        secondSportsOutdoorsColourMenu.setAdapter(coloursAdapter);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { // Add the toolbar menu
        // Inflate the activities menu
        MenuInflater activityInflater = getMenuInflater(); // Get the activity inflater
        activityInflater.inflate(R.menu.homepagemenu, menu);

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.basket_action_button, menu);

        View view = menu.findItem(R.id.cart_menu).getActionView();

        cartIcon = view.findViewById(R.id.cart_icon);

        cartIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent basketIntent = new Intent(SportsAndOutdoorsActivity.this, BasketActivity.class);
                basketIntent.putExtra("map", listOfProductsToAddToBasket);
                startActivity(basketIntent);
            }
        });

        return true;
    }
}
