package com.example.weshopapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

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

    private ArrayList<String> listOfColours;
    private ArrayList<String> listOfQuantities;

    private ArrayAdapter<String> firstCAdapter;
    private ArrayAdapter<String> firstQAdapter;

    private int quantity_one_cost = 500;
    private int quantity_two_cost = 2 * quantity_one_cost;
    private int quantity_three_cost = 3 * quantity_one_cost;
    private int quantity_four_cost = 4 * quantity_one_cost;
    private int quantity_five_cost = 5 * quantity_one_cost;

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

        listOfColours.add(0, "Choose Colour");
        listOfColours.add(1, "Space Gray");
        listOfColours.add(2, "Silver");
        listOfColours.add(3, "Gold");

        listOfQuantities.add(0, "Choose Quantity");
        listOfQuantities.add(1, "1");
        listOfQuantities.add(2, "2");
        listOfQuantities.add(3, "3");
        listOfQuantities.add(4, "4");
        listOfQuantities.add(5, "5");

        this.firstCAdapter = new ArrayAdapter<String>(TechActivity.this, android.R.layout.simple_spinner_item, listOfColours);
        firstCAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        firstProductColourOptions.setAdapter(firstCAdapter);

        this.firstQAdapter = new ArrayAdapter<String>(TechActivity.this, android.R.layout.simple_spinner_item, listOfQuantities);
        firstQAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        firstProductQuantityOptions.setAdapter(firstQAdapter);

        firstProductQuantityOptions.setOnItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void onResume() {
        super.onResume();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        boolean valueAppended = false;

        if (parent.getItemAtPosition(position).equals(listOfQuantities.get(1))) {

            productCost.append(" " + quantity_one_cost);
            valueAppended = true;
        }

        if (parent.getItemAtPosition(position).equals(listOfQuantities.get(2))) {
            productCost.append("");
            productCost.append(" " + quantity_two_cost);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public boolean onCreateOptionsMenu(Menu menu) {
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
