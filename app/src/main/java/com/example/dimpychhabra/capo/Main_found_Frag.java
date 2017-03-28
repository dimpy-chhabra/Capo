package com.example.dimpychhabra.capo;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
/*
*Project : CAPO, fully created by
* Dimpy Chhabra, IGDTUW, BTech, IT
* Second year (as of 2017)
* Expected Class of 2019
* Please do not circulate as your own
* Criticism is appreciated to work on memory leaks and bugs
* Contact Info : Find me on Linked in : linkedin.com/in/dimpy-chhabra
*
*/
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class Main_found_Frag extends Fragment {
    private static ListView listView;
    private static View view;
    private static TextView tv2;
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
        tv2 = (TextView) view.findViewById(R.id.tv2);
        fragmentManager = getActivity().getSupportFragmentManager();
        //     initViews();
        //     setListeners();
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                        .replace(R.id.frameContainerTrack, new Main_offered_Frag(),
                                BaseActivity.offered_rides_Frag).commit();
            }
        });

        listView = (ListView) view.findViewById(R.id.list);

        final ArrayList<Ride> ridesArrayList = new ArrayList<>(); //Max Price
        ridesArrayList.add(new Ride("Rajiv Chownk", "IGDTU", " 3 seats ", " 10:00 am ", "12:00 pm ", " 120", "0001"));
        ridesArrayList.add(new Ride("Pitampura", "DTU", " 2 seats ", " 09:00 am ", "10:00 am ", " 120", "0002"));
        ridesArrayList.add(new Ride("Rohini", "NSIT", " 3 seats ", " 07:00 am ", "08:00 am ", " 120", "0003"));
        ridesArrayList.add(new Ride("NSP", "IIITD", " 1 seat ", " 10:00 am ", "11:00 am ", " 120", "0004"));

        RideAdapter rideAdapter = new RideAdapter(getActivity(), ridesArrayList);
        ListView listView = (ListView)view.findViewById(R.id.list);
        listView.setAdapter(rideAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Ride ride = ridesArrayList.get(position);
                //menu pop up... click on one to send a request! and then print toast as a new row is created in the propsal table
                Toast.makeText(getActivity().getApplicationContext(), "yo " + ride.getR_id(), Toast.LENGTH_SHORT).show();

                My_found_ride_Frag frag = new My_found_ride_Frag();
                Bundle args = new Bundle();
                args.putString("rideId", "" + ride.getR_id());
                frag.setArguments(args);

                fragmentManager
                        .beginTransaction()
                        .replace(R.id.frameContainerTrack, frag,
                                BaseActivity.my_found_ride_Frag).commit();

            }
        });
        /*
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Ride ride = ridesArrayList.get(position);
                Log.e("onItem Click ", " " + ride.getR_id());

        My_offered_rides_Frag frag = new My_offered_rides_Frag();
        Bundle args = new Bundle();
        args.putString("rideId", "" + ride.getR_id());


        frag.setArguments(args);

        fragmentManager
                .beginTransaction()
                .replace(R.id.frameContainerTrack, frag,
                        BaseActivity.offered_rides_Frag).commit();
    }
});
         */


        return view;
    }


}