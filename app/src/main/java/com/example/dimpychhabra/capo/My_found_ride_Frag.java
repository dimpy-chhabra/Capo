package com.example.dimpychhabra.capo;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
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
import java.util.List;
import java.util.Map;


public class My_found_ride_Frag extends Fragment {

    public My_found_ride_Frag() {
        // Required empty public constructor
    }

    View view;
    ListView listVi;
    String value, res, res2;
    RequestQueue requestQueue;
    StringRequest stringRequest;
    private String DataParseUrl = "http://impycapo.esy.es/foundRidesList.php";
    private String DataParseUrl2 = "http://impycapo.esy.es/proposeRide.php";

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

        value = getArguments().getString("rideId");
        Log.e("!!!!!!!!!!!!!!!", "" + value);
        Toast.makeText(getActivity().getApplicationContext(), "  >>>>>> " + value, Toast.LENGTH_SHORT).show();

        volleyToFetchResponse();

        final ArrayList<Ride> ridesArrayList = extractRides(res);
        //ridesArrayList.add(new Ride("Rajiv Chownk", "IGDTU", " 3 seats ", " 10:00 am ", "12:00 pm ", " 120", "ride001"));
        //ridesArrayList.add(new Ride("Rajiv Chownk", "IGDTU", " 3 seats ", " 10:00 am ", "12:00 pm ", " 120", "ride001"));
        ridesArrayList.add(new Ride("Rajiv Chownk", "IGDTU", " 3 seats ", " 10:00 am ", "12:00 pm ", " 120", "ride001"));

        RequestCapoAdapter rideAdapter = new RequestCapoAdapter(getActivity(), ridesArrayList);
        listVi.setAdapter(rideAdapter);

        listVi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Ride ride = ridesArrayList.get(position);
                final String R_ID = ride.getR_id();
                final String PP_ID = ride.getPp_id();

                Log.e("onItem Click ", " " + ride.getR_id() + " " + ride.getPp_id());

                new AlertDialog.Builder(view.getContext())
                        .setTitle("Request a Ride")
                        .setMessage("Drop a proposal")
                        .setPositiveButton("Lets CAPO", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(getActivity().getApplicationContext(), "Requested! <3", Toast.LENGTH_SHORT).show();
                                proposeRide(R_ID, PP_ID);
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

    private void proposeRide(String r_id, String pp_id) {
        final String RIDE_ID = r_id;
        final String PP_ID = pp_id;

        stringRequest = new StringRequest(Request.Method.POST, DataParseUrl2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null && response.length() > 0 && response.equals("OK")) {
                    Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                    res2 = response;
                } else {
                    Toast.makeText(getContext(), "Something went wrong! Please contact the developers with a screenshot", Toast.LENGTH_LONG).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error != null && error.toString().length() > 0) {
                            Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();
                            Log.e(" in MY_Found_ride_Frag", " error in proposing request");
                        } else
                            Toast.makeText(getContext(), "Something went terribly wrong! ", Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                SharedPreferences spref = getActivity().getSharedPreferences(BaseActivity.MyPref, Context.MODE_PRIVATE);
                final String riderID = spref.getString(BaseActivity.Phone, null); // getting String
                params.put("rideID", RIDE_ID);
                params.put("riderID", riderID);
                params.put("ppid", PP_ID);
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
                String pp = currentRide.getString("pick_point");
                String pp_time = currentRide.getString("pick_point_time");
                String pp_price = currentRide.getString("pick_price");
                String pp_id = currentRide.getString("_pp_id");
                String r_id = currentRide.getString("r_id");
                SharedPreferences spref = getActivity().getSharedPreferences(BaseActivity.MyPref, Context.MODE_PRIVATE);
                String college = spref.getString(BaseActivity.College, null); // getting String

                Ride ride = new Ride(pp, pp_time, pp_price, pp_id, college, r_id);
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
                    Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                    res = response;
                } else {
                    Toast.makeText(getContext(), "Something went wrong! Please contact the developers with a screenshot", Toast.LENGTH_LONG).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error != null && error.toString().length() > 0) {
                            Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();
                            Log.e(" in MY_Found_ride_Frag", " error in parsing data");
                        } else
                            Toast.makeText(getContext(), "Something went terribly wrong! ", Toast.LENGTH_LONG).show();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                //SharedPreferences spref = getActivity().getSharedPreferences(BaseActivity.MyPref, Context.MODE_PRIVATE);
                //final String college = spref.getString(BaseActivity.College, null); // getting String
                params.put("rid", value);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(40000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        requestQueue.add(stringRequest);
    }



}