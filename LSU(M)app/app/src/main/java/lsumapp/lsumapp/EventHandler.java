package lsumapp.lsumapp;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Calendar;

/****************************************************************************************/
/*
/* FILE NAME: EventHandler.java
/*
/* DESCRIPTION: Information and Location Resource file
/*
/*   DATE     	       BY                  DESCRIPTION
/* ======== 	================	=======================
/*  4/23/17 	Alexandre Knijn		  created the file
/*  4/25/17     Alexandre Knijn       built event array added event pins
/*
/*
/*
/****************************************************************************************/

class Event{
    long startTime;
    long endTime;
    double latLocation;
    double lngLocation;
    String eventName;

    Event(long newStartTime, long newEndTime,double newLatLocation,double newLngLocation, String newEventName)
    {
        startTime = newStartTime;
        endTime = newEndTime;
        latLocation = newLatLocation;
        lngLocation = newLngLocation;
        eventName = newEventName;
    }
}

public class EventHandler {
    static long[] startTimeArray = new long[] {1493190000000L};
    static long[] endTimeArray = new long[]   {1493200800000L};
    static String[] eventNameArray = new String[]{"Pancake Breakfast-https://www.facebook.com/LSUadmissions/posts/421535464525532"};
    static double[] eventLatArray = new double[]{30.412844};
    static double[] eventLngArray = new double[]{-91.177847};
    static Event[] eventArray = new Event[startTimeArray.length];
    static Marker[] eventMarkerArray = new Marker[startTimeArray.length];

    public static void setup(){
        for(int i=0;i<startTimeArray.length;i++)
        {
            eventArray[i] = new Event(startTimeArray[i],endTimeArray[i],eventLatArray[i],eventLngArray[i],eventNameArray[i]);
        }
    }


    public static void checkEvents(long currentTime, GoogleMap mMap)
    {
        for(int i=0;i<eventArray.length;i++)
        {
            if(currentTime>eventArray[i].startTime && currentTime <eventArray[i].endTime && eventMarkerArray[i] == null) {
                LatLng eventLatLng = new LatLng(eventArray[i].latLocation,eventArray[i].lngLocation);
                Marker m = mMap.addMarker(new MarkerOptions().position(eventLatLng).title(eventArray[i].eventName)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)).snippet("test"));
                eventMarkerArray[i] = m;

            }
            else if(currentTime>eventArray[i].endTime && eventMarkerArray[i] != null)
            {
                eventMarkerArray[i].remove();
                eventMarkerArray[i] = null;
            }
        }
    }
}

