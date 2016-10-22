package com.example.android.pnpcab;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    String car="SEDAN",source="";
double lat,lon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        lat=getIntent().getDoubleExtra("lat",0.0);
        lon=getIntent().getDoubleExtra("long",0.0);
        source=getIntent().getExtras().getString("source");


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(lat, lon))
                .title("Current Location")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        LatLng chandigarh = new LatLng(lat, lon);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(chandigarh,13));



    }
    public void ride(View v)
    {
        Intent i=new Intent(MapsActivity.this,Ride.class);
        i.putExtra("car",car);
        i.putExtra("source",source);
        startActivity(i);

    }
    public void ridenow(View v)
    {

        Intent i=new Intent(MapsActivity.this,RideNow.class);
        i.putExtra("car",car);
        i.putExtra("source",source);
        startActivity(i);
    }
    public void b1(View v)
    {
       car="MICRO";
    }
    public void b2(View v)
    {
        car="MINI";
    }
    public void b3(View v)
    {
       car="SEDAN";
    }
    public void b4(View v)
    {
        car="PRIME";
    }
    public void b5(View v)
    {
        car="SHARE";
    }

}
