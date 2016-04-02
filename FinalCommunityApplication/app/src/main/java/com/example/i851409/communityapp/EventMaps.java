package com.example.i851409.communityapp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Locale;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class EventMaps extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    private GoogleMap mMap;
    static String address;
    static private Geocoder mGeocoder;
    static double lan;
    static double lng;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_map);

        mGeocoder = new Geocoder(getApplicationContext(), Locale.getDefault());

        Intent y = getIntent();

        address = y.getExtras().getString("h");


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        convertAddress();
    }


    public void convertAddress() {
        if (address != null && !address.isEmpty()) {
            try {
                List<Address> addressList = mGeocoder.getFromLocationName(address, 1);
                if (addressList != null && addressList.size() > 0) {
                    lan = addressList.get(0).getLatitude();
                    lng = addressList.get(0).getLongitude();

                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        mMap = googleMap;

        LatLng event = new LatLng(lan, lng);

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(event, 15));

        mMap.addMarker(new MarkerOptions().position(event).title("Event Destn:--> click direction Below").icon(BitmapDescriptorFactory.fromResource(R.drawable.b)));
    }
    @Override
    public void onLocationChanged(Location location)
    { }
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras)
    { }

    @Override
    public void onProviderEnabled(String provider)
    { }

    @Override
    public void onProviderDisabled(String provider)
    { }
}
