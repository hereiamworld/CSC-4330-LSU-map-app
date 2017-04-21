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
/*
/*
/*
/*
/****************************************************************************************/

public class MapRes {

    public static LatLng startup=new LatLng(30.4076239, -91.1796833);
    public static String name="Patrick F. Taylor";
    private static LatLng[] latLngBuildings=new LatLng[] {new LatLng(30.414498,-91.178913), new LatLng(30.414474,-91.180403),new LatLng(30.412872, -91.177045),
            new LatLng(30.413885,-91.179590),new LatLng(30.413203,-91.178970),
            new LatLng(30.413811,-91.180548),new LatLng(30.413567,-91.180698),new LatLng(30.412951,-91.180477),
            new LatLng(30.412706,-91.180716),new LatLng(30.412387,-91.180700),new LatLng(30.412167,-91.179966),
            new LatLng(30.412421,-91.179537),new LatLng(30.413967,-91.179347),new LatLng(30.415021,-91.178910),
            new LatLng(30.415267,-91.180103),new LatLng(30.414915,-91.180819),new LatLng(30.413345,-91.181749),
            new LatLng(30.412023,-91.183811),new LatLng(30.411860,-91.179275),new LatLng(30.411423,-91.179947),
            new LatLng(30.407685,-91.172925),new LatLng(30.411799,-91.171104),new LatLng(30.407623,-91.179683)};
    private static String[] namesBuildings=new String[] {"War Memorial Tower","Middleton Library","LSU Student Union","Department of History",
            "Coates Hall","Oscar K. Allen Hall","Arthur T. Prescott Hall","Dodson Auditorium","John J. Audubon Hall",
            "Martin D. Woodin Hall","Thomas W Atkinson Hall","James W Nicholson Hall","Robert L. Himes Hall","Thomas Boyd Hall",
            "Murphy J. Foster Hall","George Peabody Hall","Samuel L. Lockett Hall","Tiger Stadium","Howe-Russel Geoscience Complex",
            "Engineering Shops Bldg","DMAE Digital Media Center","Design Bldg", "Patrick F. Taylor"};

    private static LatLng[] latLngFood = new LatLng[] {};
    private static String[] namesFood = new String[] {};

    private static LatLng[] latLngParking = new LatLng[] {};
    private static String[] namesParking = new String[] {};

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
            Marker m =  mMap.addMarker(new MarkerOptions().position(latLngBuildings[i]).title(namesBuildings[i]));
            buildingMarkerList.add(m);
        }
    }

    public static void displayFoodMarkers(GoogleMap mMap)
    {
        for(int i=0;i<latLngFood.length;i++)
        {
            Marker m = mMap.addMarker(new MarkerOptions().position(latLngFood[i]).title(namesFood[i])
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
            foodMarkerList.add(m);
        }
    }

    public static void displayParkingMarkers(GoogleMap mMap)
    {
        for(int i=0;i<latLngParking.length;i++)
        {
            Marker m = mMap.addMarker(new MarkerOptions().position(latLngParking[i]).title(namesParking[i])
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
            parkingMarkerList.add(m);
        }
    }
}