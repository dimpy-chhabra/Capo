package com.example.dimpychhabra.capo;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
/*
*Project : CAPO, fully created by
* Dimpy Chhabra, IGDTUW, BTech, IT
* Second year (as of 2017)
* Expected Class of 2019
* Please do not circulate as your own
* Criticism is appreciated to work on memory leaks and bugs
* Contact Info : Find me on Linked in : linkedin.com/in/dimpy-chhabra
*/
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class My_offered_rides_Frag extends Fragment {
    ListView listViewOffRid;
    TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8;

    public My_offered_rides_Frag() {
        // Required empty public constructor
    }

    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my_offered_rides_, container, false);
        listViewOffRid = (ListView) view.findViewById(R.id.listViewOffRid);
        tv2 = (TextView) view.findViewById(R.id.r_id_tv2);


        String value = getArguments().getString("rideId");
        tv2.setText(value);
        //new CustomToast().Show_Toast(getActivity(), view,"So Ride Id is : "+value+" and container id is"+container.toString());
        //fetch required data via volley and fetch requests too! there on simply display required data and
        //thence create clickable ride proposals!

        final ArrayList<Ride> ridesArrayList = new ArrayList<>();
        ridesArrayList.add(new Ride("Rajiv Chownk", "IGDTU", " 3 seats ", " 10:00 am ", "12:00 pm ", " 120", "ride001"));
        ridesArrayList.add(new Ride("Rajiv Chownk", "IGDTU", " 3 seats ", " 10:00 am ", "12:00 pm ", " 120", "ride001"));
        ridesArrayList.add(new Ride("Rajiv Chownk", "IGDTU", " 3 seats ", " 10:00 am ", "12:00 pm ", " 120", "ride001"));

        RideAdapter rideAdapter = new RideAdapter(getActivity(), ridesArrayList);
        listViewOffRid.setAdapter(rideAdapter);

        listViewOffRid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Ride ride = ridesArrayList.get(position);
                Log.e("onItem Click ", " " + ride.getR_id());

                new AlertDialog.Builder(view.getContext())
                        .setTitle("Accept Rider")
                        .setMessage("Accept/ Decline or ignore this proposal!")
                        .setPositiveButton("Lets CAPO", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(getActivity().getApplicationContext(), "WE CAPO-ED! <3", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("NOPE!", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(getActivity().getApplicationContext(), " DECLINED", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(getActivity().getApplicationContext(), " ignored!!", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

            }
        });


        Toast.makeText(getActivity().getApplicationContext(), "YO! ", Toast.LENGTH_SHORT).show();

        return view;
    }
}