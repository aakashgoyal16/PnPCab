package com.example.android.pnpcab;

import android.*;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

public class CurrentLocation extends AppCompatActivity {
EditText e1;
    double lat,lon;
    Button button;
    String source="";
    private LocationManager locationManager;
    private LocationListener locationListener;
    @TargetApi(Build.VERSION_CODES.M)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_location);
        button = (Button) findViewById(R.id.loc);

        e1 = (EditText) findViewById(R.id.e1);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                //   finish();
                // double lat,lon;
                lat=location.getLatitude();
                lon=location.getLongitude();
                e1.append("\n" + location.getLatitude() + "   " + location.getLongitude());

            }
            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

                Intent intent=new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        };
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
        {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            {
                requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,
                        android.Manifest.permission.ACCESS_COARSE_LOCATION,
                        android.Manifest.permission.INTERNET},10);
                return;

            }
        }
        else
        {
            configureButton();
        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode)
        {
            case 10:
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED)
                    configureButton();
                return;

        }
    }
    public void send(View v)
    {
        try {
            Toast.makeText(CurrentLocation.this,"reached 1",Toast.LENGTH_LONG).show();

            Geocoder geocoder=new Geocoder(CurrentLocation.this, Locale.getDefault());
            List<Address> addresses=geocoder.getFromLocation(lat,lon,1);
            Toast.makeText(CurrentLocation.this,"reached 2",Toast.LENGTH_LONG).show();

            StringBuilder sb=new StringBuilder();
            Address address=addresses.get(0);
            for (int i=0;i<address.getMaxAddressLineIndex();i++)
                sb.append(address.getAddressLine(i)).append("\n");
            sb.append(address.getLocality());
            sb.append(address.getPostalCode());
           // sb.append(address.getCountryName());
            source=sb.toString();
            Log.d("message", source);
            Toast.makeText(CurrentLocation.this,sb.toString(),Toast.LENGTH_LONG).show();
        }
        catch (Exception e)
        {
            Toast.makeText(CurrentLocation.this,"exception 1",Toast.LENGTH_LONG).show();

        }

        Intent i=new Intent(CurrentLocation.this,MapsActivity.class);
        i.putExtra("lat",lat);
        i.putExtra("long",lon);
        i.putExtra("source",source);
        startActivity(i);
        CurrentLocation.this.finish();
    }

    private void configureButton() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 0, locationListener);

            }
        });

    }


}
