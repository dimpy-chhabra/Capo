package com.example.dimpychhabra.capo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;


public class Main_found_Frag extends Fragment {

    private static View view;
    private static Button button;
    private static EditText to, from, fromTime;
    private static FragmentManager fragmentManager;

    public Main_found_Frag() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_main_found_, container, false);

        final ArrayList<Ride> ridesArrayList = new ArrayList<>();
        ridesArrayList.add(new Ride("lj","kj"));
        ridesArrayList.add(new Ride("PitamPura" ,"Kashmere Gate"));
        ridesArrayList.add(new Ride("Rohini" ,"Kashmere Gate"));
        ridesArrayList.add(new Ride("NSP" ,"Kashmere Gate"));

        RideAdapter rideAdapter = new RideAdapter(getActivity(), ridesArrayList);
        ListView listView = (ListView)view.findViewById(R.id.list);
        listView.setAdapter(rideAdapter);

        return view;
    }

}