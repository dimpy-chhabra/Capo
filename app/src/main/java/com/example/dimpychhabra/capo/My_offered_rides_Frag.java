package com.example.dimpychhabra.capo;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
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
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class My_offered_rides_Frag extends Fragment {
    ListView listViewOffRid;
    TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8;
    RequestQueue requestQueue;
    StringRequest stringRequest;
    String res, res2;
    String riderId;
    private String DataParseUrl = "http://impycapo.esy.es/proposalsList.php";
    private String DataParseUrl2 = "http://impycapo.esy.es/updateStatus.php";

    public My_offered_rides_Frag() {
        // Required empty public constructor
    }

    private View view;
    String value;

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


        value = getArguments().getString("rideId");
        tv2.setText(value);
        //new CustomToast().Show_Toast(getActivity(), view,"So Ride Id is : "+value+" and container id is"+container.toString());
        //fetch required data via volley and fetch requests too! there on simply display required data and
        //thence create clickable ride proposals!

        volleyToFetchResponse();

        //final ArrayList<Ride> ridesArrayList = new ArrayList<>(); //Max Pric
        final ArrayList<Ride> ridesArrayList = extractRides(res);
        //ridesArrayList.add(new Ride("Rajiv Chownk", "IGDTU", " 3 seats ", " 10:00 am ", "12:00 pm ", " 120", "ride001"));
        //ridesArrayList.add(new Ride("Rajiv Chownk", "IGDTU", " 3 seats ", " 10:00 am ", "12:00 pm ", " 120", "ride001"));
        //ridesArrayList.add(new Ride("Rajiv Chownk", "IGDTU", " 3 seats ", " 10:00 am ", "12:00 pm ", " 120", "ride001"));

        RideAdapter rideAdapter = new RideAdapter(getActivity(), ridesArrayList);
        listViewOffRid.setAdapter(rideAdapter);

        listViewOffRid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Ride ride = ridesArrayList.get(position);
                final String proId = ride.getPp_id();
                riderId = ride.getRider_id();
                Log.e("onItem Click ", " " + ride.getPp_id());
                Log.e("onItem Click ", " " + ride.getRider_id());

                new AlertDialog.Builder(view.getContext())
                        .setTitle("Accept Rider")
                        .setMessage("Accept/ Decline or ignore this proposal!")
                        .setPositiveButton("Lets CAPO", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                changestatus(proId, riderId);
                                sendSms();
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

    private void sendSms() {

        SmsManager smsm = SmsManager.getDefault();
        smsm.sendTextMessage(riderId, null, "Your Proposal has been accepted! Congrats! -Regards Capo( Happy fuel Saving)", null, null);
    }

    private void changestatus(final String proId, final String riderId) {

        stringRequest = new StringRequest(Request.Method.POST, DataParseUrl2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null && response.length() > 0) {
                    Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                    res2 = response;
                } else {
                    Toast.makeText(getContext(), "Sorry!", Toast.LENGTH_LONG).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error != null && error.toString().length() > 0) {
                            Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();
                            Log.e(" in my_ofrd_rides_Frag", " error in proposal accep");
                        } else
                            Toast.makeText(getContext(), "Something went terribly wrong! ", Toast.LENGTH_LONG).show();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                //SharedPreferences spref = getActivity().getSharedPreferences(BaseActivity.MyPref, Context.MODE_PRIVATE);
                //final String college = spref.getString(BaseActivity.College, null); // getting String
                params.put("ride_id", value);
                params.put("pro_id", proId);
                params.put("rider_id", riderId);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(40000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        requestQueue.add(stringRequest);


    }

    private ArrayList<Ride> extractRides(String res) {
        ArrayList<Ride> ridesAL = new ArrayList<>();
        try {
            JSONArray baseArray = new JSONArray(res);
            for (int i = 0; i < baseArray.length(); i++) {
                JSONObject currentRide = baseArray.getJSONObject(i);
                String r_id = currentRide.getString("r_id");
                String rider_id = currentRide.getString("rider_id");
                String _pp_id = currentRide.getString("_pp_id");
                SharedPreferences spref = getActivity().getSharedPreferences(BaseActivity.MyPref, Context.MODE_PRIVATE);
                String college = spref.getString(BaseActivity.College, null); // getting String

                Ride ride = new Ride(r_id, rider_id, _pp_id, college);
                ridesAL.add(ride);
            }


        } catch (JSONException e) {
            Log.e("in ExtractRides : ", "JSON TRY CATCH ERR!");
        }
        return ridesAL;
    }

    private void volleyToFetchResponse() {
        stringRequest = new StringRequest(Request.Method.POST, DataParseUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null && response.length() > 0) {
                    //Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                    Log.e("in my offered rides ", " " + response);
                    res = response;
                } else {
                    Toast.makeText(getContext(), "No proposals to ride with you!!! Sorry", Toast.LENGTH_LONG).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error != null && error.toString().length() > 0) {
                            Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();
                            Log.e(" in my_ofrd_rides_Frag", " error in parsing data");
                        } else
                            Toast.makeText(getContext(), "Something went terribly wrong! ", Toast.LENGTH_LONG).show();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                //SharedPreferences spref = getActivity().getSharedPreferences(BaseActivity.MyPref, Context.MODE_PRIVATE);
                //final String college = spref.getString(BaseActivity.College, null); // getting String
                params.put("ride_id", value);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(40000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        requestQueue.add(stringRequest);
    }

}