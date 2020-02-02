package com.example.weshopapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class ColourArrayAdapter extends ArrayAdapter<TechActivity.Colours> {
    private Context context;
    private ArrayList<TechActivity.Colours> listOfColours = null;

    public ColourArrayAdapter(Context context, ArrayList<TechActivity.Colours> listOfColours) {
        super(context, 0, listOfColours);
        this.context = context;
        this.listOfColours = listOfColours;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View listOfItems = convertView;

        if (listOfItems == null) {
            listOfItems = LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);

            TechActivity.Colours colours = listOfColours.get(position);
        }

        return listOfItems;
    }
}
