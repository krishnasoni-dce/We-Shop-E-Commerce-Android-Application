package com.example.weshopapplication;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class TechActivityTwo extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    // Components for the Third Product
    private TextView thirdProductTextView;
    private ImageView thirdProductImage;
    private TextView thirdProductCostTxt;
    private TextView thirdProductColourLbl;

    private ImageView cartIcon;
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

    private TextView fourthProductQuantity;
    private Spinner fourthProductQuantityDropDown;

    // An array list of colours, quantities and capacity
    private ArrayList<TechActivity.Colours> listOfColours;
    private ArrayList<TechActivity.Quantities> listOfQuantities;
    private ArrayList<Capacity> listOfCapacities;

    private ArrayList<TechActivity.Colours> secondListOfColours;
    private ArrayList<TechActivity.Quantities> secondListOfQuantities;

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
        this.fourthProductQuantity = findViewById(R.id.fourthProductQuantityLbl);

        this.fourthProductQuantityDropDown = findViewById(R.id.fourthProductQuantityDropDown);

        // Put array list on the heap
        this.listOfColours = new ArrayList<>();
        this.listOfQuantities = new ArrayList<>();
        this.listOfCapacities = new ArrayList<>();

        this.secondListOfColours = new ArrayList<>();
        this.secondListOfQuantities = new ArrayList<>();

        addToColoursList(); // Method to add to the colours array list
        addToQuantitiesList();
        addToCapacityList();

        // SET UP THE THIRD PRODUCT QUANTITIES DROP DOWN MENU TO SHOW
        this.quantitiesAdapter = new CustomArrayAdapter(TechActivityTwo.this, listOfQuantities);
        quantitiesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        thirdQuantityDropDown.setAdapter(quantitiesAdapter);
        thirdQuantityDropDown.setOnItemSelectedListener(TechActivityTwo.this);

        // SET UP THE THIRD PRODUCT COLOUR SPINNER DROP DOWN MENU TO SHOW
        this.colourArrayAdapter = new ColourArrayAdapter(TechActivityTwo.this, listOfColours);
        colourArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        thirdProductDropDown.setAdapter(colourArrayAdapter);
        thirdProductDropDown.setOnItemSelectedListener(TechActivityTwo.this);

        // SET UP THE FOURTH PRODUCT COLOUR DROP DOWN MENU TO SHOW
        this.colourArrayAdapter = new ColourArrayAdapter(TechActivityTwo.this, secondListOfColours);
        colourArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        fourthProductColourSpinner.setAdapter(colourArrayAdapter);
        fourthProductColourSpinner.setOnItemSelectedListener(TechActivityTwo.this);

        // SET UP MEMORY CAPACITY FOR THE FOURTH PRODUCT
        this.capacityArrayAdapter = new CapacityArrayAdapter(TechActivityTwo.this, listOfCapacities);
        capacityArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        fourthProductMemoryDropDown.setAdapter(capacityArrayAdapter);
        fourthProductMemoryDropDown.setOnItemSelectedListener(TechActivityTwo.this);

        // SET UP QUANTITY FOR FOURTH PRODUCT
        this.quantitiesAdapter = new CustomArrayAdapter(TechActivityTwo.this, listOfQuantities);
        quantitiesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fourthProductQuantityDropDown.setAdapter(quantitiesAdapter);
        fourthProductQuantityDropDown.setOnItemSelectedListener(TechActivityTwo.this);


        this.thirdAddToBasketButton.setOnClickListener(new View.OnClickListener() { // Add Listener for third button
            @Override

            public void onClick(View v) {

                if (v.getId() == R.id.thirdAddToBasketBtn) {


                    if (thirdProductDropDown.getSelectedItemPosition() == 0) {


                        AlertDialog.Builder colourError = new AlertDialog.Builder(TechActivityTwo.this).setTitle("Colour Error")
                                .setMessage("You must select a colour before adding to the basket.")
                                .setNegativeButton("Ok", new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if (dialog != null) {

                                            dialog.dismiss();
                                        }
                                    }
                                });

                        colourError.show(); // Show the error
                        colourError.setCancelable(true); // Set cancelable to true
                    } else {
                        addProductThreeToBasket();
                    }

                    if (thirdQuantityDropDown.getSelectedItemPosition() == 0) { // If no value is chosen for the quantity

                        AlertDialog.Builder quantityError = new AlertDialog.Builder(TechActivityTwo.this).setTitle("Quantity Error") // Create an alert dialogue

                                .setMessage("You must select a quantity before adding to the basket")

                                .setNegativeButton("Ok", new DialogInterface.OnClickListener() {

                                    @Override

                                    public void onClick(DialogInterface dialog, int which) {
                                        if (dialog != null) { // If there is no dialogue

                                            dialog.dismiss(); // Delete it
                                        }
                                    }
                                });

                        quantityError.show(); // Show the quantity error.
                        quantityError.setCancelable(true);
                    } else {
                        addProductThreeToBasket();
                    }
                }
            }
        });
    }

    private void addProductThreeToBasket() {
        
    }

    private void addToColoursList() { // Routine that adds the colours to the array list

        TechActivity.Colours[] firstColoursArray = {new TechActivity.Colours(0, "Please Choose Colour"), new TechActivity.Colours(1, "White"), new TechActivity.Colours(2, "Black")};
        TechActivity.Colours[] secondColoursArray = {new TechActivity.Colours(0, "Please Choose Colour"), new TechActivity.Colours(1, "Salmon Pink"), new TechActivity.Colours(2, "Lime Green"),
                new TechActivity.Colours(3, "Ruby Red"), new TechActivity.Colours(4, "Midnight Black")};

        for (TechActivity.Colours colours : firstColoursArray) {
            listOfColours.add(colours);
            addedColours = true;
        }

        for (TechActivity.Colours secondColours : secondColoursArray) {
            secondListOfColours.add(secondColours);
            addedColours = true;
        }


    }

    private void addToQuantitiesList() { // Routine that adds the quantities to the array list
        String quantitiesAddedMsg = "Quantities Added Success";

        TechActivity.Quantities[] quantitiesArray = {new TechActivity.Quantities(0), new TechActivity.Quantities(1), new TechActivity.Quantities(2),
                new TechActivity.Quantities(3), new TechActivity.Quantities(4), new TechActivity.Quantities(5)};

        for (TechActivity.Quantities qty : quantitiesArray) {
            listOfQuantities.add(qty);
            addedQuantities = true;
        }

        for (TechActivity.Quantities qty2 : quantitiesArray) {
            secondListOfQuantities.add(qty2);
            addedQuantities = true;
        }
    }

    private void addToCapacityList() { // Routine that adds the capacity to the array list
        String capacityAddedMsg = "Capacity Added";

        Capacity[] capacities = {new Capacity(0, 64), new Capacity(1, 128), new Capacity(2, 256), new Capacity(3, 512)};

        for (Capacity capacity : capacities) {
            listOfCapacities.add(capacity);
            addedCapacities = true;
        }
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) { // Routine that determines which item has been selected

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater activityInflater = getMenuInflater();
        activityInflater.inflate(R.menu.homepagemenu, menu);

        MenuInflater basketButtonInflater = getMenuInflater();
        basketButtonInflater.inflate(R.menu.basket_action_button, menu);

        View cartView = menu.findItem(R.id.cart_menu).getActionView();

        cartIcon = cartView.findViewById(R.id.cart_icon);

        cartIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    Intent basketIntent = new Intent(TechActivityTwo.this, BasketActivity.class);
                    startActivity(basketIntent); // Start the basket intent

                } catch (ActivityNotFoundException exc) {
                    Log.d("Error : ", exc.toString());
                }
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        try {

            switch (item.getItemId()) {

                case R.id.sportsAndOutdoorsCategory:
                    Intent sportsAndOutdoorsActivity = new Intent(TechActivityTwo.this, SportsAndOutdoorsActivity.class);
                    startActivity(sportsAndOutdoorsActivity);

                    return true;

                case R.id.techCategory:
                    Intent techCategory = new Intent(TechActivityTwo.this, TechActivity.class);
                    startActivity(techCategory);

                    return true;

                case R.id.clothingCategory:
                    Intent clothingCategory = new Intent(TechActivityTwo.this, ClothingCategory.class);
                    startActivity(clothingCategory);

                    return true;

                case R.id.diyCategory:

                    Intent diyCategory = new Intent(TechActivityTwo.this, DIYActivity.class);
                    startActivity(diyCategory);

                    return true;

                default:
                    return super.onOptionsItemSelected(item);

            }


        } catch (ActivityNotFoundException exc) {

            Log.d("Error : ", exc.toString());
        }

        return true;
    }
}
