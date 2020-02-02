package com.example.weshopapplication;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.nex3z.notificationbadge.NotificationBadge;

import java.util.ArrayList;

public class TechActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private TextView firstProductText;
    private ImageView firstProductImg;
    private TextView productCost;
    private TextView firstProductColour;
    private Button firstAddToBasketButton;

    private NotificationBadge badge;
    private ImageView cartIcon;
    private Spinner firstProductColourOptions;
    private Spinner firstProductQuantityOptions;

    private ArrayList<Colours> listOfColours;
    private ArrayList<Quantities> listOfQuantities;

    private CustomArrayAdapter quantitiesCustomAdapter;
    private ColourArrayAdapter colourArrayAdapter;

    private int quantity_zero_cost = 0;
    private int quantity_one_cost = 500;
    private int quantity_two_cost = 3 * quantity_one_cost;
    private int quantity_three_cost = 4 * quantity_one_cost;
    private int quantity_four_cost = 5 * quantity_one_cost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tech);

        this.firstProductText = findViewById(R.id.firstProductText);
        this.firstProductImg = findViewById(R.id.firstProductImg);
        this.productCost = findViewById(R.id.firstProductCost);
        this.firstProductColour = findViewById(R.id.firstProductColorText); // Text View for Product Cost £: (1)

        this.firstProductColourOptions = findViewById(R.id.firstColourSpinner); // Spinner 1. -> COLOURS
        this.firstProductQuantityOptions = findViewById(R.id.firstQuantitySpinner); // Spinner 2 -> QUANTITIES
        this.firstAddToBasketButton = findViewById(R.id.firstAddToBasketBtn); // Button: -> ADD TO BASKET BUTTON 1

        this.listOfColours = new ArrayList<>();
        this.listOfQuantities = new ArrayList<>();

        addToColoursList();
        addToQuantitiesList();

        this.colourArrayAdapter = new ColourArrayAdapter(TechActivity.this, listOfColours);
        colourArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        firstProductColourOptions.setAdapter(colourArrayAdapter);

        this.quantitiesCustomAdapter = new CustomArrayAdapter(TechActivity.this, listOfQuantities);
        quantitiesCustomAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        firstProductQuantityOptions.setAdapter(quantitiesCustomAdapter);

        firstProductColourOptions.setOnItemSelectedListener(this);
        firstProductQuantityOptions.setOnItemSelectedListener(this);

        firstAddToBasketButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (v.getId() == R.id.firstAddToBasketBtn) {

                    if (firstProductColourOptions.getSelectedItemPosition() == 0) { //

                        AlertDialog.Builder colourError = new AlertDialog.Builder(TechActivity.this)
                                .setTitle("Colour Menu Error")
                                .setMessage("You must select a colour before adding the product to cart").setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if (dialog != null) {
                                            dialog.dismiss();
                                        }
                                    }
                                });

                        colourError.show();
                        colourError.setCancelable(true);
                    }
                }
            }
        });
    }

    private boolean addToColoursList() {
        boolean addedColours = false;
        Colours[] coloursArray = {new Colours(0, "Choose Colour Please"), new Colours(1, "Space Gray"), new Colours(2, "Silver"), new Colours(3, "Gold")};

        for (Colours colours : coloursArray) {
            listOfColours.add(colours);
            addedColours = true;
        }

        return true;
    }

    private boolean addToQuantitiesList() { // Routine to add the quantities to the array list
        boolean addedQuantities = false;

        listOfQuantities.add(new Quantities(0));
        listOfQuantities.add(new Quantities(1));
        listOfQuantities.add(new Quantities(2));
        listOfQuantities.add(new Quantities(3));
        listOfQuantities.add(new Quantities(4));
        listOfQuantities.add(new Quantities(5));

        addedQuantities = true;

        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        boolean valueAppended = false;

        String appended_text = "Product Cost : £";

        if (parent.getItemAtPosition(position).equals(listOfQuantities.get(0))) {
            productCost.setText(null);
            productCost.append(appended_text + quantity_zero_cost);

        } else if (parent.getItemAtPosition(position).equals(listOfQuantities.get(1))) {

            productCost.setText(null);
            productCost.append(appended_text + quantity_one_cost);
        } else if (parent.getItemAtPosition(position).equals(listOfQuantities.get(2))) {
            productCost.setText(null);
            productCost.append(appended_text + quantity_two_cost);
        } else if (parent.getItemAtPosition(position).equals(listOfQuantities.get(3))) {
            productCost.setText(null);
            productCost.append(appended_text + quantity_three_cost);

        } else if (parent.getItemAtPosition(position).equals(listOfQuantities.get(4))) {
            productCost.setText(null);
            productCost.append(appended_text + quantity_four_cost);
        } else {
            return;
        }
    }

    class Colours { // Anonymous inner class o
        private int index;
        private String colour;

        public Colours(int index, String colour) {
            this.index = index;
            this.colour = colour;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public String getColour() {
            return colour;
        }

        public void setColour(String colour) {
            this.colour = colour;
        }

        @Override
        public String toString() {
            return " " + colour;
        }
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
            Log.d("Error : ", exc.getMessage());
        }
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void onResume() {
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

        badge = view.findViewById(R.id.badge);
        cartIcon = view.findViewById(R.id.cart_icon);

        cartIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(TechActivity.this, BasketActivity.class));
            }
        });


        return true;
    }
}
