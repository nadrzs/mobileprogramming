package com.example.lockinapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class ContactUs extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        TextView facebook = (TextView) findViewById(R.id.facebookLink);
        facebook.setMovementMethod(LinkMovementMethod.getInstance());

        TextView instagram = (TextView) findViewById(R.id.instagramLink);
        instagram.setMovementMethod(LinkMovementMethod.getInstance());

        TextView twitter = (TextView) findViewById(R.id.twitterLink);
        twitter.setMovementMethod(LinkMovementMethod.getInstance());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapView);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        LatLng office = new LatLng(-6.201990, 106.781806);
        map.addMarker(new MarkerOptions().position(office).title("Office"));
        map.moveCamera(CameraUpdateFactory.newLatLng(office));
    }
}