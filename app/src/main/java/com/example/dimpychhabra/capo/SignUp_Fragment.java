package com.example.dimpychhabra.capo;

/**
 * Created by Dimpy Chhabra on 3/20/2017.
 */

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class SignUp_Fragment extends Fragment implements OnClickListener {
    private static View view;
    private static EditText fullName, emailId, mobileNumber, location,
            password, confirmPassword;
    private static TextView login;
    private static Button signUpButton;
    private static CheckBox terms_conditions;
    RequestQueue requestQueue;
    StringRequest stringRequest;

    private static String DataParseUrl = "http://impycapo.esy.es/register.php";

    public SignUp_Fragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.signup_layout, container, false);
        initViews();
        setListeners();
        return view;
    }

    // Initialize all views
    private void initViews() {
        fullName = (EditText) view.findViewById(R.id.fullName);
        emailId = (EditText) view.findViewById(R.id.userEmailId);
        mobileNumber = (EditText) view.findViewById(R.id.mobileNumber);
        location = (EditText) view.findViewById(R.id.location);
        password = (EditText) view.findViewById(R.id.password);
        confirmPassword = (EditText) view.findViewById(R.id.confirmPassword);
        signUpButton = (Button) view.findViewById(R.id.signUpBtn);
        login = (TextView) view.findViewById(R.id.already_user);
        terms_conditions = (CheckBox) view.findViewById(R.id.terms_conditions);

        // Setting text selector over textviews
        XmlResourceParser xrp = getResources().getXml(R.drawable.text_selector);
        try {
            ColorStateList csl = ColorStateList.createFromXml(getResources(),
                    xrp);

            login.setTextColor(csl);
            terms_conditions.setTextColor(csl);
        } catch (Exception e) {
        }
    }

    // Set Listeners
    private void setListeners() {
        signUpButton.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signUpBtn:

                // Call checkValidation method
                checkValidation();
                break;

            case R.id.already_user:

                // Replace login fragment
                new LoginBaseActivity().replaceLoginFragment();
                break;
        }

    }

    // Check Validation Method
    private void checkValidation() {

        // Get all edittext texts
        String getFullName = fullName.getText().toString();
        String getEmailId = emailId.getText().toString();
        String getMobileNumber = mobileNumber.getText().toString();
        String getLocation = location.getText().toString();
        String getPassword = password.getText().toString();
        String getConfirmPassword = confirmPassword.getText().toString();

        // Pattern match for email id
        //Pattern p = Pattern.compile(BaseActivity.regEx);
        //Matcher m = p.matcher(getEmailId);

        // Check if all strings are null or not


        if (getFullName.equals("") || getEmailId.equals("") || getMobileNumber.equals("") || getLocation.equals("") || getPassword.equals("") || getConfirmPassword.equals(""))
            new CustomToast().Show_Toast(getActivity(), view, "All fields are required.");

            // Check if email id valid or not
            //else if (!m.find())
            //new CustomToast().Show_Toast(getActivity(), view,
            // "Your Email Id is Invalid.");

            // Check if both password should be equal
        else if (!getConfirmPassword.equals(getPassword))
            new CustomToast().Show_Toast(getActivity(), view,
                    "Both password doesn't match.");

            // Make sure user should check Terms and Conditions checkbox
        else if (!terms_conditions.isChecked())
            new CustomToast().Show_Toast(getActivity(), view,
                    "Please select Terms and Conditions.");

            // Else do signup or do your stuff
        else {
            Toast.makeText(getActivity(), "Do SignUp. >>" + getFullName + " " + getEmailId + " " + getMobileNumber + " " + getLocation + " " + getPassword + " " + getConfirmPassword + " << ", Toast.LENGTH_SHORT).show();

            registerUser(getFullName, getEmailId, getMobileNumber, getLocation, getPassword);
        }
    }

    public void registerUser(String name, final String email, String mob, String college, String passwrd) {
        final String Name = name;
        final String Email = email;
        final String Mob = mob;
        final String College = college;
        final String Pword = passwrd;
        stringRequest = new StringRequest(Request.Method.POST, DataParseUrl, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                if (response != null && response.length() > 0)
                    Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getContext(), "Something went terribly wrong! ", Toast.LENGTH_LONG).show();
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
                params.put("name", Name);
                params.put("mob_no", Mob);
                params.put("college", College);
                params.put("email", Email);
                params.put("dob", "dummy");
                params.put("sex", "dummy");
                params.put("pword", Pword);
                params.put("dp", "dummy");
                params.put("fb_link", "dummy");

                return params;
            }

        };
        //stringRequest.setRetryPolicy(new DefaultRetryPolicy(40000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        requestQueue.add(stringRequest);
    }
}