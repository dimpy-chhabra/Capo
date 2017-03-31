package com.example.dimpychhabra.capo;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FirstActivity extends AppCompatActivity {
    ImageButton ib1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        ib1=(ImageButton)findViewById(R.id.dummy_button);
        SharedPreferences spref = getSharedPreferences(BaseActivity.MyPref, Context.MODE_PRIVATE);
        final String a = spref.getString(BaseActivity.Name, null); // getting String
        final String b = spref.getString(BaseActivity.Phone, null);
        final String c = spref.getString(BaseActivity.IS_LOGIN, "false");


        ib1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


        Log.e("in FullScreen Activity ","name : "+a+" phone : "+b+" isloggedin? : "+c);
        if(c.equals("true")){
            Toast.makeText(FirstActivity.this, "Welcome Fellow User", Toast.LENGTH_SHORT).show();
            Log.e("in  Activity in if","name : "+a+" phone : "+b+" isloggedin? : "+c);
            Intent i  = new Intent(FirstActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }else {
            Toast.makeText(FirstActivity.this, "Start Saving money with Capo TODAY!", Toast.LENGTH_SHORT).show();
            Intent i  = new Intent(FirstActivity.this, LoginBaseActivity.class);
            startActivity(i);
            finish();
        }

            }
        });
    }
}


