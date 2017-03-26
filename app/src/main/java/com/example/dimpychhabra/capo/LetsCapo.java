package com.example.dimpychhabra.capo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class LetsCapo extends BaseActivity {

    private static FragmentManager fragmentManager;

    public Ride currentRideLetsCapo = new Ride();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lets_capo);


        fragmentManager = getSupportFragmentManager();

        // If savedinstnacestate is null then replace login fragment
        if (savedInstanceState == null) {

            SharedPreferences spref = getSharedPreferences(BaseActivity.MyPref, Context.MODE_PRIVATE);
            currentRideLetsCapo.putDriver_id(spref.getString(BaseActivity.Phone, null));
            fragmentManager .beginTransaction()
                    .replace(R.id.frameContainer, new letsCapo_Frag1(), BaseActivity.letsCapo_Frag1)
                    .commit();
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        SharedPreferences spref = getSharedPreferences(BaseActivity.MyPref, Context.MODE_PRIVATE);
        String a = spref.getString(BaseActivity.Name, null); // getting String
        String b = spref.getString(BaseActivity.Phone, null);
        String c = spref.getString(BaseActivity.IS_LOGIN, "false");
        String dp = spref.getString(BaseActivity.displaypic, "null");
        int id1 = getResources().getIdentifier( dp , "drawable", getPackageName());

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.lets_capo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent i = new Intent(LetsCapo.this, MainActivity.class);
            startActivity(i);
            finish();

        } else if (id == R.id.nav_profile) {
            Intent i = new Intent(LetsCapo.this, MyProfile.class);
            startActivity(i);
            finish();

        } else if (id == R.id.nav_get_capo) {
            Intent i = new Intent(LetsCapo.this, GetCapo.class);
            startActivity(i);
            finish();

        } else if (id == R.id.nav_lets_capo) {

        }else if (id == R.id.nav_track){
            Intent i = new Intent(LetsCapo.this, Track.class);
            startActivity(i);
            finish();

        } else if (id == R.id.nav_share) {
            String shareBody = "This is an invite by your dear friend to come and join this community where people care and are making a change!" +
                    "Install Capo today from ... ";
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Greeting from Capo");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(sharingIntent, getResources().getString(R.string.share_using)));

        } else if (id == R.id.nav_logout) {
            SharedPreferences preferences = getSharedPreferences(MyPref, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(Name, "null" );
            editor.putString(IS_LOGIN, "false");
            editor.putString(Phone, "null" );
            editor.putString(College, "null" );
            editor.putString(Email, "null" );
            editor.putString(displaypic, "null" );

            editor.commit();

            Intent i = new Intent(LetsCapo.this,LoginBaseActivity.class);
            startActivity(i);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;


    }



}