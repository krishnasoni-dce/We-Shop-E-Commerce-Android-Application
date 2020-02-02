package com.example.weshopapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import java.util.ArrayList;


public class CustomArrayAdapter extends ArrayAdapter<TechActivity.Quantities> {

    private Context context;
    private ArrayList<TechActivity.Quantities> quantitiesList = null; // Array list of quantities

    public CustomArrayAdapter(Context context, ArrayList<TechActivity.Quantities> quantitiesList) {
        super(context, 0, quantitiesList);

        this.context = context;
        this.quantitiesList = quantitiesList;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View listItems = convertView;

        if (listItems == null) {
            listItems = LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);

            TechActivity.Quantities quantities = quantitiesList.get(position);
        }

        return listItems;

    }
}
