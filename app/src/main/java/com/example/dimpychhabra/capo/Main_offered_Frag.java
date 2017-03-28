package com.example.dimpychhabra.capo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
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
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;


public class Main_offered_Frag extends Fragment {


    View view;
    Button button;
    EditText to, from, fromTime;
    FragmentManager fragmentManager;

    public Main_offered_Frag() {
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
        view = inflater.inflate(R.layout.fragment_main_offered_, container, false);
        //here we will fetch data from database via volley --> make an object --> set adapter and work accordingly
        final ArrayList<Ride> ridesArrayList = new ArrayList<>();
        ridesArrayList.add(new Ride("Rajiv Chownk", "IGDTU", " 3 seats ", " 10:00 am ", "12:00 pm ", " 120", "ride001"));
        ridesArrayList.add(new Ride("Pitampura", "DTU", " 2 seats ", " 09:00 am ", "10:00 am ", " 120", "ride002"));
        ridesArrayList.add(new Ride("Rohini", "NSIT", " 3 seats ", " 07:00 am ", "08:00 am ", " 120", "ride003"));
        ridesArrayList.add(new Ride("NSP", "IIITD", " 1 seat ", " 10:00 am ", "11:00 am ", " 120", "ride004"));

        RideAdapter rideAdapter = new RideAdapter(getActivity(), ridesArrayList);
        ListView listView = (ListView) view.findViewById(R.id.list);
        listView.setAdapter(rideAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Ride ride = ridesArrayList.get(position);
                Log.e("onItem Click ", " " + ride.getR_id());
                /*
                YourNewFragment ldf = new YourNewFragment ();
                Bundle args = new Bundle();
                args.putString("YourKey", "YourValue");
                ldf.setArguments(args);
                //Inflate the fragment
                getFragmentManager().beginTransaction().add(R.id.container, ldf).commit();
                */
                My_offered_rides_Frag frag = new My_offered_rides_Frag();
                Bundle args = new Bundle();
                args.putString("rideId", "" + ride.getR_id());

                frag.setArguments(args);
                getFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                        .add(R.id.ViewPagerTrack, frag, BaseActivity.offered_rides_Frag)
                        .commit();
            }
        });

        return view;
    }

}