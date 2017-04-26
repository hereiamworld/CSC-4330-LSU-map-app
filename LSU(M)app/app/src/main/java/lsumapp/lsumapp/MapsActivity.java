package lsumapp.lsumapp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.widget.PopupMenu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

/****************************************************************************************/
/*
/* FILE NAME: MapsActivity.java
/*
/* DESCRIPTION: main program file
/*
/*   DATE     	       BY                 DESCRIPTION
/* ======== 	================	=======================
/*  4/15/17 	 Kyle Eastin		created the file
/*  4/19/17	     Alexandre Knijn	added pin creation on mouse long click
/*  4/25/17      Kyle Eastin        Zoom and Boundary restrictions
/*  4/25/17      Aaron Mouledous    clickEvents and currentLocation
/*  4/25/17      Alexandre Knijn    locationPermissions and event handeler
/*  4/25/17      Kyle Eastin        added onMapSearch function
/*
/*
/****************************************************************************************/


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Geocoder geocoder;
    Marker marker;


    /**
     * This function initializes the map
     * @param savedInstanceState - mapping
     * @return void
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        geocoder = new Geocoder(this, Locale.getDefault());

        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        EventHandler.setup();
        final Handler handler = new Handler();

        /**
         * This function is called every 10 seconds by a handler so that events can be checked for
         *
         * @return void
         * @param point:
         * @throws null
         */
        Runnable checkEvent = new Runnable() {
            @Override
            public void run() {

                EventHandler.checkEvents(Calendar.getInstance().getTimeInMillis(),mMap);

                handler.postDelayed(this, 1000);
            }
        };

        handler.postDelayed(checkEvent, 1000);

        //set a restriction on zooming the map
        mMap.setMinZoomPreference(15.0f);
        mMap.setMaxZoomPreference(20.0f);

        //set a restriction on
        LatLngBounds LSU = new LatLngBounds(new LatLng(30.403478,-91.188744),new LatLng(30.420567,-91.167911));
        mMap.setLatLngBoundsForCameraTarget(LSU);

        //center the camera on the startup point
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(MapRes.startup,16));

        //Listener for clicking on a pin's title
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            /**
             * This function is called when a marker title is clicked
             *
             * @return void
             * @param point: marker of title that is clicked
             * @throws null
             */
            public void onInfoWindowClick(Marker arg0){
                String[] separateMarkerTitle = arg0.getTitle().split("-",2);
                if(separateMarkerTitle.length>1)
                {
                    Uri uri = Uri.parse(separateMarkerTitle[1]);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
            }
        });

        //display pins on the Map
        MapRes.displayBuildingMarkers(mMap);
        MapRes.displayFoodMarkers(mMap);
        MapRes.displayParkingMarkers(mMap);

        /**
         * This function drops a custom pin down when a longClick occurs
         * @return marker
         * @param clickedLatLng - location where user clicked
         * @param marker - pin that drops where user clicked
         * @throws null
         */
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            /**
             * Executed when a long click is done on the map.
             *
             * @return void
             * @param point: latitude and longitude of clicked point
             * @throws null
             */
            @Override
            public void onMapLongClick(LatLng point) {
                //save current location
                LatLng clickedLatLng = point;

                List<Address> addresses = new ArrayList<>();
                try {
                    addresses = geocoder.getFromLocation(point.latitude, point.longitude,1);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                android.location.Address address = addresses.get(0);

                if (address != null) {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < address.getMaxAddressLineIndex(); i++){
                        sb.append(address.getAddressLine(i) + "\n");
                    }
                    Toast.makeText(MapsActivity.this, sb.toString(), Toast.LENGTH_LONG).show();
                }

                //remove previously placed Marker
                if (marker != null) {
                    marker.remove();
                }

                //place marker where user just clicked
                marker = mMap.addMarker(new MarkerOptions().position(point).title("Marker")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));

            }
        });


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED )
        {
            requestPermissions(new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.INTERNET
            }, 10);
            return;
        }
        else
        {
            mMap.setMyLocationEnabled(true);
        }
    }

    /**
     * This function is called when the program asks for permission
     *
     * @return void
     * @param requestCode: code of the request
     * @param permissions: all the permisions being asked for
     * @param grantResults: grants results
     * @throws null
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 10) {
            if (permissions.length == 1 &&
                    permissions[0] == Manifest.permission.ACCESS_FINE_LOCATION &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mMap.setMyLocationEnabled(true);
            } else {
                // Permission was denied. Display an error message.
            }
        }
    }

    /**
     * This function is called when the program asks for permission
     *
     * @return void
     * @param currentView: currentView of the scene
     * @throws null
     */
    public void showPopup(View currentView) {
        PopupMenu popup = new PopupMenu(this, currentView);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.popmenu, popup.getMenu());
        popup.show();
    }

    /**
     * This function is called when the user clicks the search button
     * @param view - area of the screen
     * @return void
     */
    public void onMapSearch(View view)
    {
        EditText locationSearch = (EditText)findViewById(R.id.editText);
        String location = locationSearch.getText().toString();

        for(int i=0;i<MapRes.namesBuildings.length;i++)
        {
            if(location.compareTo(MapRes.namesBuildings[i])==0)
            {
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(MapRes.latLngBuildings[i],15));
                break;
            }
        }
        locationSearch = null;
    }

}
