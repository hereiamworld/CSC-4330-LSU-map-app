package lsumapp.lsumapp;

import android.graphics.Bitmap;

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
/*
/*
/*
/****************************************************************************************/

public class MapRes {

    public static LatLng startup=new LatLng(30.4076239, -91.1796833);
    public static String name="Patrick F. Taylor";
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

    private static LatLng[] latLngFood = new LatLng[] {new LatLng(30.4126572,-91.1771705),new LatLng(30.410366,-91.174250),new LatLng(30.414569,-91.180105),
            new LatLng(30.415296,-91.179815),new LatLng(30.417133,-91.181793),new LatLng(30.418000,-91.179152),
            new LatLng(30.418029,-91.176389),new LatLng(30.417314,-91.176268),new LatLng(30.417397,-91.177246),
            new LatLng(30.412737,-91.1754807),new LatLng(30.417377,-91.176831)};
    private static String[] namesFood = new String[] {"McDonald's","459 Commons","CC's Coffee House","Subway","The 5","Louie's Cafe",
            "Raising Cane's Chicken Fingers","The Chimes","Insomnia Cookies","Starbuck's","Highland Coffees"};

    private static LatLng[] latLngParking = new LatLng[] {new LatLng(30.405065,-91.177035), new LatLng(30.408204,-91.175234)};
    private static String[] namesParking = new String[] {"Nicholson Extension East Lot","AG Coliseum Lot"};

    private static List<Marker> buildingMarkerList = new ArrayList<Marker>();
    private static List<Marker> foodMarkerList = new ArrayList<Marker>();
    private static List<Marker> parkingMarkerList = new ArrayList<Marker>();




    public static void set(String newName)
    {
        int i;
        int x=-1;
        for(i = 0;i<namesBuildings.length;i++)
        {
            if(newName.equals(namesBuildings[i]))
                x = i;
        }
        if(x!=-1)
        {
            name=namesBuildings[x];
            startup=latLngBuildings[x];
        }
    }

    public static void displayBuildingMarkers(GoogleMap mMap)
    {
        for(int i=0;i<latLngBuildings.length;i++)
        {
            Marker m = mMap.addMarker(new MarkerOptions().position(latLngBuildings[i]).title(namesBuildings[i]).icon(BitmapDescriptorFactory.fromResource(R.drawable.building)).snippet("Click for more information"));
            buildingMarkerList.add(m);
        }
    }

    public static void displayFoodMarkers(GoogleMap mMap)
    {
        for(int i=0;i<latLngFood.length;i++)
        {
            Marker m = mMap.addMarker(new MarkerOptions().position(latLngFood[i]).title(namesFood[i]).icon(BitmapDescriptorFactory.fromResource(R.drawable.food)));
            foodMarkerList.add(m);
        }
    }

    public static void displayParkingMarkers(GoogleMap mMap)
    {
        for(int i=0;i<latLngParking.length;i++)
        {
            Marker m = mMap.addMarker(new MarkerOptions().position(latLngParking[i]).title(namesParking[i]).icon(BitmapDescriptorFactory.fromResource(R.drawable.parking)));
            parkingMarkerList.add(m);
        }
    }
}
