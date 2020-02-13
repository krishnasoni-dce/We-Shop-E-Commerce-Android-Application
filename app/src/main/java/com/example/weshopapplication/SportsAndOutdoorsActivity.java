package com.example.weshopapplication;

import android.os.Bundle;
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

public class SportsAndOutdoorsActivity extends AppCompatActivity {
    // Set-up variables
    private int current_product_id = 1;
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
    private Spinner secondSportsOutdoorSpinnerTxt;

    private TextView secondSportsOutdoorQuantityLbl;
    private Spinner secondSportsOutdoorQuantityMenu;

    private TextView secondSportsOutdoorSizeLbl;
    private Spinner secondSportsOutdoorSizeMenu;
    private Button secondSportsAddToBasketBtn;

    private Button nextPageBtn;

    private ArrayList<TechActivity.Colours> listOfColoursOne;
    private ArrayList<TechActivity.Quantities> listOfQuantitiesOne;
    private ArrayList<Size> listOfSizes;

    private double[] productOneCosts = {0.00, 90.00, 180.00, 360.00, 720.00, 1440.00};
    private double[] productTwoCosts = {0.00, 50.00, 100.00, 150.00, 200.00, 250.00};

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



    }
}
