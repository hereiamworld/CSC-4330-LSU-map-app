package lsumapp.lsumapp;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Handler;
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
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
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
/*
/*
/*
/****************************************************************************************/


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    Geocoder geocoder;
    Marker marker;

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
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
<<<<<<< HEAD
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



=======
        mMap.setMinZoomPreference(15.0f);
        mMap.setMaxZoomPreference(20.0f);
>>>>>>> origin/master
        // Add a marker at LSU Memorial Tower and move the camera
        LatLng startup = new LatLng(30.414498,-91.178913);

<<<<<<< HEAD
        //MapRes.displayBuildingMarkers(mMap);

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
=======
        LatLngBounds LSU = new LatLngBounds(new LatLng(30.403478,-91.188744),new LatLng(30.420567,-91.167911));

        mMap.setLatLngBoundsForCameraTarget(LSU);
        //mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(LSU,0));
        //mMap.addMarker(new MarkerOptions().position(MapRes.startup).title(MapRes.name));
        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(MapRes.startup,15));
>>>>>>> origin/master

            @Override
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


        MapRes.displayBuildingMarkers(mMap);
        MapRes.displayFoodMarkers(mMap);

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

    }
}
