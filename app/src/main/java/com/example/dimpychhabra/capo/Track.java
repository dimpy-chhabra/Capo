package com.example.dimpychhabra.capo;

import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Track extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        com.google.android.gms.location.LocationListener, OnMapReadyCallback {


    private static final String TAG = "Location Services***";
    protected GoogleApiClient mGoogleApiClient;
    protected Location mLastLocation;
    protected TextView mLatitudeText;
    protected TextView mLongitudeText;
    protected LocationRequest mLocationRequest;

    double longi = 0.35;
    double lati = 0.345;
    GoogleMap m_map;
    boolean mapReady = false;

    MarkerOptions kirkland;
    MarkerOptions everett;


    static final CameraPosition SEATTLE = CameraPosition.builder()
            .target(new LatLng(47.6204, -122.2491))
            .zoom(10)
            .bearing(0)
            .tilt(45)
            .build();

    @Override
    public Resources getResources() {
        return super.getResources();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);

        kirkland = new MarkerOptions()
                .position(new LatLng(47.7301986, -122.1768858))
                .title("Kirkland")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.capopic));
        everett = new MarkerOptions()
                .position(new LatLng(47.7301986, -122.1768858))
                .title("Kirkland")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.capopic));

        mLatitudeText = (TextView) findViewById(R.id.tv1);
        mLongitudeText = (TextView) findViewById(R.id.tv2);
        buildGoogleApiClient();

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap map) {
        mapReady = true;
        m_map = map;

        m_map.addMarker(kirkland);
        m_map.addMarker(everett);

        flyTo(SEATTLE);

        //LatLng newYork = new LatLng(40.7425, -73.9857);
        //CameraPosition target = CameraPosition.builder().target(newYork).zoom(14).build();
        //m_map.moveCamera(CameraUpdateFactory.newCameraPosition(target));
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "Connection Suspended : " + i);
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected())
            mGoogleApiClient.disconnect();
    }

    @Override
    public void onConnected(Bundle connectionHint) {
        mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(1000);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);    //ignorable
        Toast.makeText(this, "In On Connected ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLocationChanged(Location location) {
        longi = location.getLatitude();
        mLatitudeText.setText(String.valueOf(location.getLatitude()));
        lati = location.getLongitude();
        mLongitudeText.setText(String.valueOf(location.getLongitude()));
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.i(TAG, "Connection Failed : " + connectionResult.getErrorCode());
    }

    public void onDisconnected() {
        Log.i(TAG, "DisConnected  ");
    }

    private void flyTo(CameraPosition target) {
        m_map.moveCamera(CameraUpdateFactory.newCameraPosition(target));
    }

}