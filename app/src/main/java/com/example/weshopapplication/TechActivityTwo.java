package com.example.weshopapplication;

import android.app.ProgressDialog;
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
    private Button fourthAddToBasketButton;

    // An array list of colours, quantities and capacity
    private ArrayList<TechActivity.Colours> listOfColours;
    private ArrayList<TechActivity.Quantities> listOfQuantities;
    private ArrayList<Capacity> listOfCapacities;

    private ArrayList<TechActivity.Colours> secondListOfColours;
    private ArrayList<TechActivity.Quantities> secondListOfQuantities;

    private double quantity_zero_cost = 0.0;
    private double quantity_one_cost = 249.99;

    // Formulae to calculate price & Capacity
    // COST FOR THE FIRST PRODUCT
    private double quantity_two_cost = 2 * quantity_one_cost; // Quantity 2 is 3 times the price of 1 quantity.
    private double quantity_three_cost = 3 * quantity_one_cost;
    private double quantity_four_cost = 4 * quantity_one_cost;
    private double quantity_five_cost = 5 * quantity_one_cost;

    private double product_four_zero_cost = 0.00;
    private double product_four_one_cost = 750.00;
    private double product_four_two_cost = 2 * product_four_one_cost;
    private double product_four_three_cost = 3 * product_four_one_cost;
    private double product_four_four_cost = 4 * product_four_one_cost;

    // Formulae for the cost of different capacities
    private double sixtyFourGBCost = product_four_one_cost * 2;
    private double oneTwoEightGbCost = product_four_two_cost * 3;
    private double twoFiveSixGbCost = product_four_three_cost * 2;
    private double fiveTwelveGbCost = quantity_three_cost * 4;

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
        this.fourthAddToBasketButton = findViewById(R.id.fourthAddToBasketButton);

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
        this.quantitiesAdapter = new CustomArrayAdapter(TechActivityTwo.this, secondListOfQuantities);
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


        this.fourthAddToBasketButton.setOnClickListener(new View.OnClickListener() { // Add action listener to the fourth button
            @Override

            public void onClick(View view) {
                if (view.getId() == R.id.fourthAddToBasketButton) {


                    if (fourthProductColourSpinner.getSelectedItemPosition() == 0 || fourthProductMemoryDropDown.getSelectedItemPosition() == 0 || fourthProductQuantityDropDown.getSelectedItemPosition() == 0) {
                        AlertDialog.Builder error = new AlertDialog.Builder(TechActivityTwo.this).setTitle("Error")

                                .setMessage("You must select a colour, capacity and quantity in order to add to basket")


                                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if (dialog != null) {
                                            dialog.dismiss();
                                        }
                                    }
                                });

                        error.show();
                        error.setCancelable(true);
                    } else {
                        addProductFourToBasket();
                    }
                }
            }
        });
    }

    private void addProductThreeToBasket() { // Adds the third product to basket
        // SECTION 1
        // Create the progress dialogue
        final ProgressDialog dialog = new ProgressDialog(TechActivityTwo.this);
        dialog.setTitle("Adding to Basket..");
        dialog.setMessage("Please Wait");

        dialog.setCancelable(false);

        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        new Thread(new Runnable() { // Create a new thread

            @Override
            public void run() {
                try {
                    Thread.sleep(1900);
                } catch (InterruptedException exc) {
                    Log.d("Error : ", exc.toString());
                }

                dialog.dismiss();
            }
        }).start();

        dialog.show(); // Show the progress bar
    }

    private void addProductFourToBasket() { // Adds the fourth product to the basket

        final ProgressDialog dialog = new ProgressDialog(TechActivityTwo.this);
        dialog.setTitle("Adding to Basket..");
        dialog.setMessage("Please Wait");

        dialog.setCancelable(false);

        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        new Thread(new Runnable() { // Create a new thread

            @Override
            public void run() {
                try {
                    Thread.sleep(1900);
                } catch (InterruptedException exc) {
                    Log.d("Error : ", exc.toString());
                }

                dialog.dismiss();
            }
        }).start();

        dialog.show(); // Show the progress bar
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

        TechActivity.Quantities[] secondProductQuantities = {new TechActivity.Quantities(0), new TechActivity.Quantities(1), new TechActivity.Quantities(2),
                new TechActivity.Quantities(3), new TechActivity.Quantities(4), new TechActivity.Quantities(5)};


        for (TechActivity.Quantities qty : quantitiesArray) {
            listOfQuantities.add(qty);
            addedQuantities = true;
        }

        for (TechActivity.Quantities qty2 : secondProductQuantities) {
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
        boolean valueAppended = false;
        int[] quantityIndexes = {0, 1, 2, 3, 4, 5};
        int[] capacityIndexes = {0, 1, 2, 3}; // Array of capacity indexes

        String text_to_append = "Product Cost £: ";

        if (parent.getItemAtPosition(position).equals(listOfQuantities.get(quantityIndexes[0]))) {
            thirdProductCostTxt.setText(null);
            thirdProductCostTxt.append(text_to_append + quantity_zero_cost);

            valueAppended = true;
        }

        // If the quantity at index 1 is chosen
        else if (parent.getItemAtPosition(position).equals(listOfQuantities.get(quantityIndexes[1]))) {
            thirdProductCostTxt.setText(null);
            thirdProductCostTxt.append(text_to_append + quantity_one_cost);

            valueAppended = true; // Value is appended
        } else if (parent.getItemAtPosition(position).equals(listOfQuantities.get(quantityIndexes[2]))) {
            thirdProductCostTxt.setText(null);
            thirdProductCostTxt.append(text_to_append + quantity_two_cost);

            valueAppended = true;
        } else if (parent.getItemAtPosition(position).equals(listOfQuantities.get(quantityIndexes[3]))) {
            thirdProductCostTxt.setText(null);
            thirdProductCostTxt.append(text_to_append + quantity_three_cost);

            valueAppended = true;
        } else if (parent.getItemAtPosition(position).equals(listOfQuantities.get(quantityIndexes[4]))) {
            thirdProductCostTxt.setText(null);
            thirdProductCostTxt.append(text_to_append + quantity_four_cost);

            valueAppended = true;
        } else if (parent.getItemAtPosition(position).equals(listOfQuantities.get(quantityIndexes[5]))) {
            thirdProductCostTxt.setText(null);
            thirdProductCostTxt.append(text_to_append + quantity_five_cost);

            valueAppended = true;
        }

        if (parent.getItemAtPosition(position).equals(secondListOfQuantities.get(quantityIndexes[0]))) {
            fourthProductCost.setText(null);
            fourthProductCost.append(text_to_append + quantity_zero_cost);
        } else if (parent.getItemAtPosition(position).equals(secondListOfQuantities.get(quantityIndexes[1]))) {
            fourthProductCost.setText(null);
            fourthProductCost.append(text_to_append + product_four_one_cost);

            valueAppended = true;
        } else if (parent.getItemAtPosition(position).equals(secondListOfQuantities.get(quantityIndexes[2]))) {
            fourthProductCost.setText(null);
            fourthProductCost.append(text_to_append + product_four_two_cost);

            valueAppended = true;
        } else if (parent.getItemAtPosition(position).equals(secondListOfQuantities.get(quantityIndexes[3]))) {
            fourthProductCost.setText(null);
            fourthProductCost.append(text_to_append + quantity_three_cost);

            valueAppended = true;
        } else if (parent.getItemAtPosition(position).equals(secondListOfQuantities.get(quantityIndexes[4]))) {
            fourthProductCost.setText(null);
            fourthProductCost.append(text_to_append + quantity_four_cost);

            valueAppended = true;
        } else if (parent.getItemAtPosition(position).equals(secondListOfQuantities.get(quantityIndexes[5]))) {
            fourthProductCost.setText(null);
            fourthProductCost.append(text_to_append + quantity_five_cost);
        } else if (parent.getItemAtPosition(position).equals(listOfCapacities.get(capacityIndexes[0]))) {
            fourthProductCost.setText(null);
            fourthProductCost.append(text_to_append + quantity_zero_cost);
        } else if (parent.getItemAtPosition(position).equals(listOfCapacities.get(capacityIndexes[1]))) {
            fourthProductCost.setText(null);
            fourthProductCost.append(text_to_append + sixtyFourGBCost);

            valueAppended = true;
        } else if (parent.getItemAtPosition(position).equals(listOfCapacities.get(capacityIndexes[2]))) {
            fourthProductCost.setText(null);
            fourthProductCost.append(text_to_append + oneTwoEightGbCost);

            valueAppended = true;
        } else if (parent.getItemAtPosition(position).equals(listOfCapacities.get(capacityIndexes[3]))) {
            fourthProductCost.setText(null);
            fourthProductCost.append(text_to_append + twoFiveSixGbCost);

            valueAppended = true;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { // Creates the menu bar
        MenuInflater activityInflater = getMenuInflater();
        activityInflater.inflate(R.menu.homepagemenu, menu);

        MenuInflater basketButtonInflater = getMenuInflater();
        basketButtonInflater.inflate(R.menu.basket_action_button, menu);

        View cartView = menu.findItem(R.id.cart_menu).getActionView(); // Get the action view for the cart

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