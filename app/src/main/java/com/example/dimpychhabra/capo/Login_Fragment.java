package com.example.dimpychhabra.capo;

/**
 * Created by Dimpy Chhabra on 3/20/2017.
 */

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import android.content.Context;
import android.content.Intent;
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
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by Dimpy Chhabra on 3/20/2017.
 */

public class Login_Fragment extends Fragment implements OnClickListener {

    private static View view;
    private static EditText emailid, password;
    private static Button loginButton;
    private static TextView forgotPassword, signUp;
    private static CheckBox show_hide_password;
    private static LinearLayout loginLayout;
    private static Animation shakeAnimation;
    private static FragmentManager fragmentManager;
    RequestQueue requestQueue;
    StringRequest stringRequest;
    String final_response;

    private static String DataParseUrl = "http://impycapo.esy.es/login.php";

    public Login_Fragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.login_layout, container, false);
        initViews();
        setListeners();
        return view;
    }

    // Initiate Views
    private void initViews() {
        fragmentManager = getActivity().getSupportFragmentManager();

        emailid = (EditText) view.findViewById(R.id.login_emailid);
        password = (EditText) view.findViewById(R.id.login_password);
        loginButton = (Button) view.findViewById(R.id.loginBtn);
        forgotPassword = (TextView) view.findViewById(R.id.forgot_password);
        signUp = (TextView) view.findViewById(R.id.createAccount);
        show_hide_password = (CheckBox) view
                .findViewById(R.id.show_hide_password);
        loginLayout = (LinearLayout) view.findViewById(R.id.login_layout);

        // Load ShakeAnimation
        shakeAnimation = AnimationUtils.loadAnimation(getActivity(),
                R.anim.shake);

        // Setting text selector over textviews
        XmlResourceParser xrp = getResources().getXml(R.drawable.text_selector);
        try {
            ColorStateList csl = ColorStateList.createFromXml(getResources(),
                    xrp);

            forgotPassword.setTextColor(csl);
            show_hide_password.setTextColor(csl);
            signUp.setTextColor(csl);
        } catch (Exception e) {
        }
    }

    // Set Listeners
    private void setListeners() {
        loginButton.setOnClickListener(this);
        forgotPassword.setOnClickListener(this);
        signUp.setOnClickListener(this);

        // Set check listener over checkbox for showing and hiding password
        show_hide_password
                .setOnCheckedChangeListener(new OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton button,
                                                 boolean isChecked) {

                        // If it is checkec then show password else hide
                        // password
                        if (isChecked) {

                            show_hide_password.setText(R.string.hide_pwd);// change
                            // checkbox
                            // text

                            password.setInputType(InputType.TYPE_CLASS_TEXT);
                            password.setTransformationMethod(HideReturnsTransformationMethod
                                    .getInstance());// show password
                        } else {
                            show_hide_password.setText(R.string.show_pwd);// change
                            // checkbox
                            // text

                            password.setInputType(InputType.TYPE_CLASS_TEXT
                                    | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                            password.setTransformationMethod(PasswordTransformationMethod
                                    .getInstance());// hide password

                        }

                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginBtn:
                checkValidation();
                break;

            case R.id.forgot_password:

                // Replace forgot password fragment with animation
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                        .replace(R.id.frameContainer,
                                new ForgotPassword_Fragment(),
                                BaseActivity.ForgotPassword_Fragment).commit();
                break;
            case R.id.createAccount:

                // Replace signup frgament with animation
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                        .replace(R.id.frameContainer, new SignUp_Fragment(),
                                BaseActivity.SignUp_Fragment).commit();
                break;
        }

    }


    // Check Validation before login
    private void checkValidation() {
        // Get email id and password
        String getEmailId = emailid.getText().toString().trim();
        String getPassword = password.getText().toString().trim();

        // Check patter for email id
        //Pattern p = Pattern.compile(BaseActivity.regEx);
        //Matcher m = p.matcher(getEmailId);

        // Check for both field is empty or not
        if (getEmailId.equals("") || getEmailId.length() == 0
                || getPassword.equals("") || getPassword.length() == 0) {
            loginLayout.startAnimation(shakeAnimation);
            new CustomToast().Show_Toast(getActivity(), view,
                    "Enter both credentials.");

        }
        // Check if email id is valid or not
        //else if (!m.find())
        //new CustomToast().Show_Toast(getActivity(), view,
        // "Your Email Id is Invalid.");
        // Else do login and do your stuff

        else {

            checkAuthenticity(getEmailId, getPassword);


        }
    }

    private void checkAuthenticity(String getEmailId, String getPassword) {
        final String mobile = getEmailId;
        final String pword = getPassword;

        stringRequest = new StringRequest(Request.Method.POST, DataParseUrl, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                final_response = response;
                Toast.makeText(getActivity().getApplicationContext(), "  " + response, Toast.LENGTH_SHORT).show();
                Log.e("response: ", "  " + response);
                if (!response.equals("NO_PROF")) {
                    verified();

                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "wrong username or password", Toast.LENGTH_LONG).show();
                    Log.e("in login frag", "" + response);
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
                params.put("password", pword);

                return params;
            }

        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(40000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        requestQueue.add(stringRequest);
    }

    private void verified() {
        Toast.makeText(getActivity(), "Successfully Loged IN!", Toast.LENGTH_SHORT).show();
        SharedPreferences preferences = this.getActivity().getSharedPreferences(BaseActivity.MyPref, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        String[] tokens = final_response.split(">");
        //Toast.makeText(getActivity().getApplicationContext(), " 0Name :"+tokens[0]+ " 1Phone :"+ tokens[1]+ " 2College :"+ tokens[2], Toast.LENGTH_SHORT).show();
        Log.e("!!!!!!", " Name :" + tokens[0] + " Phone :" + tokens[1] + " College :" + tokens[2]);
        editor.putString(BaseActivity.Name, tokens[0]);
        editor.putString(BaseActivity.IS_LOGIN, "true");
        editor.putString(BaseActivity.Phone, tokens[1]); //1
        editor.putString(BaseActivity.displaypic, tokens[4]);  //4
        editor.putString(BaseActivity.College, tokens[2]); //2
        editor.putString(BaseActivity.Email, tokens[3]);  //3
        editor.putString(BaseActivity.Extras, "Female, 19 years old, does not have license :O ");
        editor.commit();
        Toast.makeText(getActivity(), "Shared Pref added!", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(getActivity(), MainActivity.class);
        startActivity(i);


    }

}