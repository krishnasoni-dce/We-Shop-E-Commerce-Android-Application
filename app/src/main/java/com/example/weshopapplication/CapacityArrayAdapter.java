package com.example.weshopapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class CapacityArrayAdapter extends ArrayAdapter<Capacity> {
    private Context context;
    private ArrayList<Capacity> listCapacities = null; // Empty array list

    public CapacityArrayAdapter(Context context, ArrayList<Capacity> listOfCapacities) {
        super(context, 0, listOfCapacities);
        this.context = context;
        this.listCapacities = listOfCapacities;
    }

    @NonNull
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View listView = convertView;

        if (listCapacities != null) {
            listView = LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);

            Capacity capacities = listCapacities.get(position);
        }

        return listView; // Return list view
    }
}
