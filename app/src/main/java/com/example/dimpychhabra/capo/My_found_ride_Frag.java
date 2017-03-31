package com.example.dimpychhabra.capo;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class My_found_ride_Frag extends Fragment {

    public My_found_ride_Frag() {
        // Required empty public constructor
    }

    View view;
    ListView listVi;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_my_found_ride_, container, false);
        listVi = (ListView) view.findViewById(R.id.listViewFoundRid);

        String value = getArguments().getString("rideId");
        Log.e("!!!!!!!!!!!!!!!", "" + value);
        Toast.makeText(getActivity().getApplicationContext(), "  >>>>>> " + value, Toast.LENGTH_SHORT).show();
        final ArrayList<Ride> ridesArrayList = new ArrayList<>();
        ridesArrayList.add(new Ride("Rajiv Chownk", "IGDTU", " 3 seats ", " 10:00 am ", "12:00 pm ", " 120", "ride001"));
        ridesArrayList.add(new Ride("Rajiv Chownk", "IGDTU", " 3 seats ", " 10:00 am ", "12:00 pm ", " 120", "ride001"));
        ridesArrayList.add(new Ride("Rajiv Chownk", "IGDTU", " 3 seats ", " 10:00 am ", "12:00 pm ", " 120", "ride001"));

        RideAdapter rideAdapter = new RideAdapter(getActivity(), ridesArrayList);
        listVi.setAdapter(rideAdapter);

        listVi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Ride ride = ridesArrayList.get(position);
                Log.e("onItem Click ", " " + ride.getR_id());

                new AlertDialog.Builder(view.getContext())
                        .setTitle("Request a Ride")
                        .setMessage("Drop a proposal")
                        .setPositiveButton("Lets CAPO", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(getActivity().getApplicationContext(), "Requested! <3", Toast.LENGTH_SHORT).show();
                            }
                        })

                        .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(getActivity().getApplicationContext(), " Naaaaah!!", Toast.LENGTH_SHORT).show();
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