package com.example.weshopapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class SizeArrayAdapter extends ArrayAdapter<Size> {
    private Context context;
    private ArrayList<Size> listOfSizes = null;

    public SizeArrayAdapter(Context context, ArrayList<Size> listOfSizes) {
        super(context, 0, listOfSizes);
        this.context = context;
        this.listOfSizes = listOfSizes;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View listOfItems = convertView;

        if (listOfItems == null) {
            listOfItems = LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);

            Size sizes = listOfSizes.get(position);
        }

        return listOfItems;
    }

}
