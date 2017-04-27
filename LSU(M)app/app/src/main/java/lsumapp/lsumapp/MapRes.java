package lsumapp.lsumapp;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

/****************************************************************************************/
/*
/* FILE NAME: MapsRes.java
/*
/* DESCRIPTION: Information and Locaation Resource file
/*
/*   DATE     	       BY                  DESCRIPTION
/* ======== 	================	=======================
/*  4/21/17 	  Kyle Eastin		  created the file
/*  4/25/17       Kyle Eastin         added food locations
/*  4/25/17       Kyle Eastin         added parking location examples
/*
/*
/****************************************************************************************/

public class MapRes {

    //startup location
    public static LatLng startup=new LatLng(30.4076239, -91.1796833);
    public static String name="Patrick F. Taylor";

    //building names and coordinates
    public static LatLng[] latLngBuildings=new LatLng[] {new LatLng(30.414498,-91.178913),new LatLng(30.414474,-91.180403),new LatLng(30.412872, -91.177045),
            new LatLng(30.413885,-91.179590),new LatLng(30.413203,-91.178970),
            new LatLng(30.413811,-91.180548),new LatLng(30.413567,-91.180698),new LatLng(30.412951,-91.180477),
            new LatLng(30.412706,-91.180716),new LatLng(30.412387,-91.180700),new LatLng(30.412167,-91.179966),
            new LatLng(30.412421,-91.179537),new LatLng(30.413967,-91.179347),new LatLng(30.415021,-91.178910),
            new LatLng(30.415267,-91.180103),new LatLng(30.414915,-91.180819),new LatLng(30.413345,-91.181749),
            new LatLng(30.412023,-91.183811),new LatLng(30.411860,-91.179275),new LatLng(30.411423,-91.179947),
            new LatLng(30.407434,-91.172411),new LatLng(30.411717,-91.180835),new LatLng(30.407623,-91.179683)};
    public static String[] namesBuildings=new String[] {"War Memorial Tower","Middleton Library","LSU Student Union","Department of History",
            "Coates Hall","Oscar K. Allen Hall","Arthur T. Prescott Hall","Dodson Auditorium","John J. Audubon Hall",
            "Martin D. Woodin Hall","Thomas W Atkinson Hall","James W Nicholson Hall","Robert L. Himes Hall","Thomas Boyd Hall",
            "Murphy J. Foster Hall","George Peabody Hall","Samuel L. Lockett Hall","Tiger Stadium","Howe-Russel Geoscience Complex",
            "Engineering Shops Bldg","DMAE Digital Media Center","Design Bldg", "Patrick F. Taylor"};

    //food names and coordinates
    private static LatLng[] latLngFood = new LatLng[] {new LatLng(30.4126572,-91.1771705),new LatLng(30.410366,-91.174250),new LatLng(30.414569,-91.180105),
            new LatLng(30.415296,-91.179815),new LatLng(30.417133,-91.181793),new LatLng(30.418000,-91.179152),
            new LatLng(30.418029,-91.176389),new LatLng(30.417314,-91.176268),new LatLng(30.417397,-91.177246),
            new LatLng(30.412737,-91.1754807),new LatLng(30.417377,-91.176831)};
    private static String[] namesFood = new String[] {"McDonald's","459 Commons","CC's Coffee House","Subway","The 5","Louie's Cafe",
            "Raising Cane's Chicken Fingers","The Chimes","Insomnia Cookies","Starbuck's","Highland Coffees"};

    //parking names and coordinates
    private static LatLng[] latLngParking = new LatLng[] {new LatLng(30.405065,-91.177035), new LatLng(30.408204,-91.175234)};
    private static String[] namesParking = new String[] {"Nicholson Extension East Lot","AG Coliseum Lot"};

    //lists of pins dropped
    private static List<Marker> buildingMarkerList = new ArrayList<Marker>();
    private static List<Marker> foodMarkerList = new ArrayList<Marker>();
    private static List<Marker> parkingMarkerList = new ArrayList<Marker>();

    /**
     * Displays a pin for each building name-building coordinate pair
     * @param mMap - the Map
     * @return void
     */
    public static void displayBuildingMarkers(GoogleMap mMap)
    {
        for(int i=0;i<latLngBuildings.length;i++)
        {
            Marker m = mMap.addMarker(new MarkerOptions().position(latLngBuildings[i]).title(namesBuildings[i]));
            buildingMarkerList.add(m);
        }
    }

    public static void removeBuildingMarkers(GoogleMap mMap)
    {
        for(int i=0;i<buildingMarkerList.size();i++) {
            buildingMarkerList.get(i).remove();
        }
        for(int i=0;i<buildingMarkerList.size();i++) {
            buildingMarkerList.remove(i);
        }
    }

    /**
     * Displays a pin for each food name-food coordinate pair
     * @param mMap - the Map
     * @return void
     */
    public static void displayFoodMarkers(GoogleMap mMap)
    {
        for(int i=0;i<latLngFood.length;i++)
        {
            Marker m = mMap.addMarker(new MarkerOptions().position(latLngFood[i]).title(namesFood[i])
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
            foodMarkerList.add(m);
        }
    }

    public static void removeFoodMarkers(GoogleMap mMap)
    {
        for(int i=0;i<foodMarkerList.size();i++) {
            foodMarkerList.get(i).remove();
        }
        for(int i=0;i<foodMarkerList.size();i++) {
            foodMarkerList.remove(i);
        }

    }

    /**
     * Displays a pin for each building name-building coordinate pair
     * @param mMap - the Map
     * @return void
     */
    public static void displayParkingMarkers(GoogleMap mMap)
    {
        for(int i=0;i<latLngParking.length;i++)
        {
            Marker m = mMap.addMarker(new MarkerOptions().position(latLngParking[i]).title(namesParking[i])
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)).snippet("Mostly Full"));
            parkingMarkerList.add(m);
        }
    }

    public static void removeParkingMarkers(GoogleMap mMap)
    {
        for(int i=0;i<parkingMarkerList.size();i++) {
            parkingMarkerList.get(i).remove();
        }
        for(int i=0;i<parkingMarkerList.size();i++) {
            parkingMarkerList.remove(i);
        }

    }
}
