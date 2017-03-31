package lsumapp.lsumapp;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Kyle Eastin on 3/30/2017.
 */

public class MapRes
{
    public static LatLng startup=new LatLng(30.414498,-91.178913);
    public static String name="War Memorial Tower";
    private static LatLng[] latLng=new LatLng[] {new LatLng(30.414474,-91.180403),new LatLng(30.412872, -91.177045),
            new LatLng(30.413885,-91.179590),new LatLng(30.413203,-91.178970),
            new LatLng(30.413811,-91.180548),new LatLng(30.413567,-91.180698),new LatLng(30.412951,-91.180477),
            new LatLng(30.412706,-91.180716),new LatLng(30.412387,-91.180700),new LatLng(30.412167,-91.179966),
            new LatLng(30.412421,-91.179537),new LatLng(30.413967,-91.179347),new LatLng(30.415021,-91.178910),
            new LatLng(30.415267,-91.180103),new LatLng(30.414915,-91.180819),new LatLng(30.413345,-91.181749),
            new LatLng(30.412023,-91.183811),new LatLng(30.411860,-91.179275),new LatLng(30.411423,-91.179947),
            new LatLng(30.411803,-91.180441),new LatLng(30.411799,-91.171104)};
    private static String[] names=new String[] {"Middleton Library","LSU Student Union","Department of History",
            "Coates Hall","Oscar K. Allen Hall","Arthur T. Prescott Hall","Dodson Auditorium","John J. Audubon Hall",
            "Martin D. Woodin Hall","Thomas W Atkinson Hall","James W Nicholson Hall","Robert L. Himes Hall","Thomas Boyd Hall",
            "Murphy J. Foster Hall","George Peabody Hall","Samuel L. Lockett Hall","Tiger Stadium","Howe-Russel Geoscience Complex",
            "Engineering Shops Bldg","Arts Bldg","Design Bldg"};
    public static void setup(String newName)
    {
        int i;
        int x=-1;
        for(i = 0;i<names.length;i++)
        {
            if(newName.equals(names[i]))
                x = i;
        }
        if(x!=-1)
        {
            name=names[x];
            startup=latLng[x];
        }
    }
}
