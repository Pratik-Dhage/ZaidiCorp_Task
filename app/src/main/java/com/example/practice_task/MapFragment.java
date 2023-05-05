package com.example.practice_task;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.practice_task.databinding.FragmentMapBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapFragment extends Fragment {

    FragmentMapBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_map, container, false);

        SupportMapFragment supportMapFragment = (SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.mapFragment);

        assert supportMapFragment != null;
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {

                // Create a Geocoder object to handle geocoding
                Geocoder geocoder = new Geocoder(getContext());

// Define the address string for Lodha Xperia Mall
                String addressString = "Lodha Xperia Mall, Kalyan Shil Road, Dombivli East, Maharashtra, India";

// Declare a LatLng variable to hold the coordinates
                LatLng lodhaMallLatLng = null;

                try {
                    // Use the Geocoder to get the address information
                    List<Address> addresses = geocoder.getFromLocationName(addressString, 1);

                    // If the Geocoder found a result, extract the latitude and longitude
                    if (addresses != null && addresses.size() > 0) {
                        Address address = addresses.get(0);
                        lodhaMallLatLng = new LatLng(address.getLatitude(), address.getLongitude());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

// If we were able to obtain the coordinates, add the marker to the map and animate the camera
                if (lodhaMallLatLng != null) {
                    // Create a MarkerOptions object and set its position and title
                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(lodhaMallLatLng);
                    markerOptions.title("Lodha Xperia Mall");

                    // Add the marker to the map and animate the camera to center on the marker
                    googleMap.addMarker(markerOptions);
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(lodhaMallLatLng, 15));
                } else {
                    // If we were not able to obtain the coordinates, log an error
                    Log.e("MapsActivity", "Unable to geocode address: " + addressString);
                }





               /* googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {

                        MarkerOptions markerOptions = new MarkerOptions();
                        markerOptions.position(latLng);
                        markerOptions.title("Here LatLong:"+latLng.latitude+","+latLng.longitude);
                        googleMap.clear();
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,20));
                        googleMap.addMarker(markerOptions);
                    }
                });*/
            }
        });


        return binding.getRoot();
    }
}