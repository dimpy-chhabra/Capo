package com.example.dimpychhabra.capo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Dimpy Chhabra on 3/26/2017.
 */

public class RideAdapter extends ArrayAdapter<Ride>{

    public RideAdapter(Context context, ArrayList<Ride> ridesArrayList){
        super(context, 0, ridesArrayList);

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        //getting the view into a variable listItemView
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext())
                    .inflate(R.layout.ride_item, parent, false);
        }
        //This is to ensure the reusablity of a view

        Ride currentRide = getItem(position);

        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.textViewDirections);
        defaultTextView.setText(currentRide.getStart_loc("34344") + currentRide.getDesti_loc("34344"));

        TextView frenchTextView = (TextView) listItemView.findViewById(R.id.pricetag);
        frenchTextView.setText(currentRide.getNo_seats("34344"));

        TextClock tc = (TextClock)listItemView.findViewById(R.id.textClock);
        tc.setFormat24Hour(currentRide.getStart_time("878787"));


        return listItemView;
    }
}
