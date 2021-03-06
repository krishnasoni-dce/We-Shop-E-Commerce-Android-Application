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
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

// Author: Sabin Constantin Lungu
// Purpose of Activity: Shows the products in stock for the tech activity along with the colour to choose from and quantities.
// Date of Last Modified: 4/2/2020
// Any Bugs?: Currently none. Unit tested recently. 11/11 Tests completed

public class TechActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private TextView firstProductText;
    private Thread firstActivityThread;
    private ImageView firstProductImg;
    private TextView productCost;
    private TextView firstProductColour;
    private Button firstAddToBasketButton;

    private TextView secondProductText;
    private ImageView secondProductImg;
    private TextView secondProductCost;
    private TextView secondProductColour;
    private Button secondAddToBasketButton;


    private ImageView cartIcon; // Cart Icon should be red once a product is added
    private Spinner firstProductColourOptions;
    private Spinner firstProductQuantityOptions;

    private Spinner secondProductColourOptions;
    private Spinner secondProductQuantityOptions;

    private ArrayList<Colours> listOfColours;
    private ArrayList<Quantities> listOfQuantities;

    private CustomArrayAdapter quantitiesCustomAdapter;
    private ColourArrayAdapter colourArrayAdapter;

    private ArrayList<Colours> secondListOfColours;
    private ArrayList<Quantities> secondListOfQuantities;

    private Button nextPageBtn;

    // VARIABLES FOR THE COSTS
    private int quantity_zero_cost = 0;
    private int quantity_one_cost = 500;

    private int quantity_two_cost = 3 * quantity_one_cost;
    private int quantity_three_cost = 4 * quantity_one_cost;
    private int quantity_four_cost = 5 * quantity_one_cost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tech);

        // INITIALISE COMPONENTS FOR FIRST PRODUCT
        this.firstActivityThread = new Thread();
        this.firstProductText = findViewById(R.id.firstProductText);
        this.firstProductImg = findViewById(R.id.firstProductImg);
        this.productCost = findViewById(R.id.firstProductCost);
        this.firstProductColour = findViewById(R.id.firstProductColorText); // Text View for Product Cost £: (1)

        this.firstProductColourOptions = findViewById(R.id.firstColourSpinner); // Spinner 1. -> COLOURS
        this.firstProductQuantityOptions = findViewById(R.id.firstQuantitySpinner); // Spinner 2 -> QUANTITIES
        this.firstAddToBasketButton = findViewById(R.id.firstAddToBasketBtn); // Button: -> ADD TO BASKET BUTTON 1

        this.secondProductColourOptions = findViewById(R.id.secondColourSpinner);
        this.secondProductQuantityOptions = findViewById(R.id.secondQuantitySpinner);

        this.listOfColours = new ArrayList<>();
        this.listOfQuantities = new ArrayList<>();
        // Create 2nd array list
        this.secondListOfColours = new ArrayList<>();
        this.secondListOfQuantities = new ArrayList<>();

        addToColoursList();
        addToQuantitiesList();

        this.colourArrayAdapter = new ColourArrayAdapter(TechActivity.this, listOfColours);
        colourArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        firstProductColourOptions.setAdapter(colourArrayAdapter);

        this.quantitiesCustomAdapter = new CustomArrayAdapter(TechActivity.this, listOfQuantities);
        quantitiesCustomAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        firstProductQuantityOptions.setAdapter(quantitiesCustomAdapter);

        // Add action listener for first product colour and first product quantity
        firstProductColourOptions.setOnItemSelectedListener(this);
        firstProductQuantityOptions.setOnItemSelectedListener(this);

        secondProductColourOptions.setOnItemSelectedListener(this);
        secondProductQuantityOptions.setOnItemSelectedListener(this);


        // Initialise components for SECOND PRODUCT
        this.secondProductText = findViewById(R.id.secondProductTxt);
        this.secondProductImg = findViewById(R.id.appleWatchImg);
        this.secondProductCost = findViewById(R.id.secondProductCost);

        this.secondProductColour = findViewById(R.id.secondProductColourTxt);
        this.secondAddToBasketButton = findViewById(R.id.secondAddToBasketBtn);


        // Create adapters for the 2nd product
        this.quantitiesCustomAdapter = new CustomArrayAdapter(TechActivity.this, secondListOfQuantities);
        this.colourArrayAdapter = new ColourArrayAdapter(TechActivity.this, secondListOfColours);

        quantitiesCustomAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        colourArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        secondProductQuantityOptions.setAdapter(quantitiesCustomAdapter);
        secondProductColourOptions.setAdapter(colourArrayAdapter);

        this.nextPageBtn = findViewById(R.id.nextPageBtn);


        firstAddToBasketButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String colourErrorTitleOne = " First Colour Menu Error";
                String colourErrorBodyMsg = "You must select a colour before adding the product to cart";

                String quantityErrorTitleOne = "First Quantity Menu Error";
                String quantityErrorBodyMsg = "You must select a quantity before adding the product to cart";

                if (v.getId() == R.id.firstAddToBasketBtn) { // If the first add to basket button is clicked

                    if (firstProductColourOptions.getSelectedItemPosition() == 0) { //

                        AlertDialog.Builder colourErrorOne = new AlertDialog.Builder(TechActivity.this)
                                .setTitle(colourErrorTitleOne)
                                .setMessage(colourErrorBodyMsg).setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                    @Override

                                    public void onClick(DialogInterface dialog, int which) {

                                        if (dialog != null) {

                                            dialog.dismiss();
                                        }
                                    }
                                });

                        colourErrorOne.show();
                        colourErrorOne.setCancelable(true);
                    }

                    if (firstProductQuantityOptions.getSelectedItemPosition() == 0) {

                        AlertDialog.Builder quantityErrorOne = new AlertDialog.Builder(TechActivity.this)

                                .setTitle(quantityErrorTitleOne).setMessage(quantityErrorBodyMsg)

                                .setNegativeButton("OK", new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if (dialog != null) {
                                            dialog.dismiss();
                                        }
                                    }
                                });

                        quantityErrorOne.show();
                        quantityErrorOne.setCancelable(true);
                    }
                }
            }
        });

        secondAddToBasketButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View secondButton) {

                String colourErrorTitleTwo = "Second Colour Menu Error";
                String colourErrorTwoBodyMsg = "You must select a colour before adding the product to cart";

                String quantityTitleErrorTwo = "Second Quantity Menu Error";
                String quantityErrorTwoBodyMsg = "You must select a quantity before adding the product to cart";

                String[] splitTitle = quantityErrorTwoBodyMsg.split("\n");
                String[] splitBodyMsg = quantityErrorTwoBodyMsg.split("\n");


                if (secondButton.getId() == R.id.secondAddToBasketBtn) {

                    if (secondProductColourOptions.getSelectedItemPosition() == 0) {

                        AlertDialog.Builder secondProductColourError = new AlertDialog.Builder(TechActivity.this)
                                .setTitle(colourErrorTitleTwo)

                                .setMessage(colourErrorTwoBodyMsg)

                                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                    @Override

                                    public void onClick(DialogInterface dialog, int which) {
                                        if (dialog != null) {
                                            dialog.dismiss();
                                        }
                                    }
                                });

                        secondProductColourError.show();
                        secondProductColourError.setCancelable(true); // User can click outside the Window to cancel the dialogue
                    }

                    if (secondProductQuantityOptions.getSelectedItemPosition() == 0) {

                        AlertDialog.Builder secondProductQuantityError = new AlertDialog.Builder(TechActivity.this)
                                .setTitle(quantityTitleErrorTwo)

                                .setMessage(quantityErrorTwoBodyMsg)
                                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        if (dialog != null) {
                                            dialog.dismiss();
                                        }
                                    }
                                });

                        secondProductQuantityError.show();
                        secondProductQuantityError.setCancelable(true);
                    }
                }
            }
        });
    }

    private boolean addToColoursList() {
        boolean addedColours = false;
        String msgAdded = "Colours Added";
        Colours[] coloursArray = {new Colours(0, "Choose Colour Please"), new Colours(1, "Space Gray"), new Colours(2, "Silver"), new Colours(3, "Gold")};

        for (Colours colours : coloursArray) { // For each colour in the array
            listOfColours.add(colours); // Add it to the array list
            
            secondListOfColours.add(colours);
            addedColours = true;
        }

        if (addedColours) {
            Toast.makeText(TechActivity.this, msgAdded, Toast.LENGTH_SHORT).show();
        } else {

            Toast.makeText(TechActivity.this, "Could not add", Toast.LENGTH_SHORT).show();
        }

        return true;
    }

    private boolean addToQuantitiesList() { // Routine to add the quantities to the array list
        boolean addedQuantities = false;
        String msgQtyAdded = "Quantities added";

        Quantities[] firstProductQuantities = {
                new Quantities(0), new Quantities(1), new Quantities(2)
                , new Quantities(3), new Quantities(4), new Quantities(5)};

        Quantities[] secondProductQuantities = {new Quantities(0), new Quantities(1), new Quantities(2),
                new Quantities(3), new Quantities(4), new Quantities(5)};

        for (Quantities quantities : firstProductQuantities) {
            listOfQuantities.add(quantities);
            addedQuantities = true;
        }

        for (Quantities secondQuantities : secondProductQuantities) {
            secondListOfQuantities.add(secondQuantities);
            addedQuantities = true;
        }

        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        boolean valueAppended = false;
        int[] indexes = {0, 1, 2, 3, 4}; // Array of indexes

        String appended_text = "Product Cost : £";

        if (parent.getItemAtPosition(position).equals(listOfQuantities.get(indexes[0]))) {
            productCost.setText(null);
            productCost.append(appended_text + quantity_zero_cost);

            valueAppended = true;
        } else if (parent.getItemAtPosition(position).equals(listOfQuantities.get(indexes[1]))) {
            productCost.setText(null);
            productCost.append(appended_text + quantity_one_cost);


            valueAppended = true;
        } else if (parent.getItemAtPosition(position).equals(listOfQuantities.get(indexes[2]))) {
            productCost.setText(null);
            productCost.append(appended_text + quantity_two_cost);

            valueAppended = true;
        } else if (parent.getItemAtPosition(position).equals(listOfQuantities.get(indexes[3]))) {
            productCost.setText(null);
            productCost.append(appended_text + quantity_three_cost);
            valueAppended = true;

        } else if (parent.getItemAtPosition(position).equals(listOfQuantities.get(indexes[4]))) {
            productCost.setText(null);
            productCost.append(appended_text + quantity_four_cost);
            valueAppended = true;

        }

        if (parent.getItemAtPosition(position).equals(secondListOfQuantities.get(indexes[0]))) {
            secondProductCost.setText(null);
            secondProductCost.append(appended_text + quantity_zero_cost);
            valueAppended = true;
        } else if (parent.getItemAtPosition(position).equals(secondListOfQuantities.get(indexes[1]))) {
            secondProductCost.setText(null);
            secondProductCost.append(appended_text + quantity_one_cost);
            valueAppended = true;
        } else if (parent.getItemAtPosition(position).equals(secondListOfQuantities.get(indexes[2]))) {
            secondProductCost.setText(null);
            secondProductCost.append(appended_text + quantity_two_cost);


            valueAppended = true;
        } else if (parent.getItemAtPosition(position).equals(secondListOfQuantities.get(indexes[3]))) {
            secondProductCost.setText(null);
            secondProductCost.append(appended_text + quantity_three_cost);
        } else if (parent.getItemAtPosition(position).equals(secondListOfQuantities.get(indexes[4]))) {
            secondProductCost.setText(null);
            secondProductCost.append(appended_text + quantity_four_cost);
        } else {

            valueAppended = false;
            return;
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) { // Routine that gets an item selected from the menu
        String error_message = "Error Cause : ";

        try {

            switch (item.getItemId()) {

                case R.id.sportsAndOutdoorsCategory: // When the sports and activity category is chosen
                    Intent sportsAndOutdoors = new Intent(TechActivity.this, SportsAndOutdoorsActivity.class); // Create intent for sports and outdoors
                    Thread.sleep(1); // Sleep for 1ms
                    startActivity(sportsAndOutdoors); // Start the activity

                    return true; // Returns true

                case R.id.techCategory:
                    Intent techCategory = new Intent(TechActivity.this, TechActivity.class);
                    Thread.sleep(1);
                    startActivity(techCategory);

                    return true;

                case R.id.clothingCategory:
                    Intent clothingCategory = new Intent(TechActivity.this, ClothingCategory.class);
                    Thread.sleep(1);
                    startActivity(clothingCategory);

                    return true;


                case R.id.diyCategory:
                    Intent diyCategory = new Intent(TechActivity.this, DIYActivity.class);
                    Thread.sleep(1);
                    startActivity(diyCategory);

                    return true;

                default:
                    return super.onOptionsItemSelected(item); // Return basae item

            }

        } catch (ActivityNotFoundException act) {

            Log.d(error_message, act.toString());


        } catch (InterruptedException excp) {

            Log.d(error_message, excp.toString());
        }

        return true;
    }

    class Quantities {
        private int quantity;

        public Quantities(int quantity) {
            this.quantity = quantity;
        }

        public int getQuantity() {
            return this.quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public String toString() {
            return " " + this.quantity;
        }
    }

    @Override
    public void onBackPressed() {
        try {
            startActivity(new Intent(TechActivity.this, MainActivity.class));

        } catch (ActivityNotFoundException exc) {

            Log.d("Error : ", exc.toString());
        }
    }

    public void onDestroy() { // On destroy method
        super.onDestroy();
    }

    public void onResume() { // On resume method
        super.onResume();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public boolean onCreateOptionsMenu(Menu menu) {

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

                startActivity(new Intent(TechActivity.this, BasketActivity.class));
            }
        });


        return true;
    }

    // Anonymous inner classes that will be used later on
    class Colours { // Anonymous inner class o
        private int index;
        private String colour;

        public Colours(int index, String colour) {
            this.index = index;
            this.colour = colour;
        }

        public int getIndex() {
            return this.index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public String getColour() {
            return this.colour;
        }

        public void setColour(String colour) {
            this.colour = colour;
        }

        @Override
        public String toString() {
            return " " + colour;
        }
    }
}