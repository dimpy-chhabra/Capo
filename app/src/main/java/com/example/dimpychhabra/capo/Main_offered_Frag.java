package com.example.dimpychhabra.capo;

import android.content.Context;
import android.content.SharedPreferences;
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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Main_offered_Frag extends Fragment {


    View view;
    Button button;
    EditText to, from, fromTime;
    FragmentManager fragmentManager;
    TextView tv11, tv22;
    RequestQueue requestQueue;
    StringRequest stringRequest;
    String final_response = "dummy";

    private static String DataParseUrl = "http://impycapo.esy.es/offeredRidesList.php";

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
        fragmentManager = getActivity().getSupportFragmentManager();
        tv11 = (TextView) view.findViewById(R.id.tv11);

        tv11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.right_out, R.anim.left_enter)
                        .replace(R.id.frameContainerTrack, new Main_found_Frag(),
                                BaseActivity.found_rides_Frag).commit();
            }
        });

        //here we will fetch data from database via volley --> make an object --> set adapter and work accordingly
        final ArrayList<Ride> ridesArrayList = new ArrayList<>();

        String res = fetchData();
        String[] tokens = res.split(">");
        //Toast.makeText(getActivity().getApplicationContext(), " 0Name :"+tokens[0]+ " 1Phone :"+ tokens[1]+ " 2College :"+ tokens[2], Toast.LENGTH_SHORT).show();
        Log.e("!!!!!!", " THING1 :" + tokens[0] + " THING2 :" + tokens[1] + " THING3 :" +
                tokens[2] + " THING4 :" + tokens[3] + " THING5 :>" + tokens[4] + "< THING6 :" + tokens[5] + " THING7 :" + tokens[6]);

        //In order to break the rows: iterate till : <br> is reached
        // and then send in 7 tokens into the constructor via a loop!


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

                fragmentManager
                        .beginTransaction()
                        .replace(R.id.frameContainerTrack, frag,
                                BaseActivity.offered_rides_Frag).commit();
            }
        });

        return view;
    }

    private String fetchData() {
        SharedPreferences spref = getActivity().getSharedPreferences(BaseActivity.MyPref, Context.MODE_PRIVATE);
        final String mobile = spref.getString(BaseActivity.Phone, null);

        stringRequest = new StringRequest(Request.Method.POST, DataParseUrl, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                final_response = response;
                Toast.makeText(getActivity().getApplicationContext(), "  " + response, Toast.LENGTH_SHORT).show();
                Log.e("response: ", "  " + response);
                if (!response.equals("NO_RIDES_OFFERED")) {
                    Log.e(" In onResponse : ", "Values obtained");

                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "No Rides Offered by you!", Toast.LENGTH_LONG).show();
                    Log.e("in login frag", "  " + response);
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error != null && error.toString().length() > 0)
                            Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(getContext(), "Something went terribly wrong! ", Toast.LENGTH_LONG).show();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("mob_no", mobile);
                return params;
            }

        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(40000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        requestQueue.add(stringRequest);

        return final_response;

    }

}