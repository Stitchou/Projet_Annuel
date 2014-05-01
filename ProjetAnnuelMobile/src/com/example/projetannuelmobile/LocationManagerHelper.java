package com.example.projetannuelmobile;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;


public class LocationManagerHelper implements LocationListener {

    private static double latitude  ; // Contient la latitude actuelle
    private static double longitude ; // Contient la longitude actuelle
    

    @Override
    public void onLocationChanged(Location lc) {
        latitude  = lc.getLatitude() ; // Mise à jour de la latitude
        longitude = lc.getLongitude(); // Mise à jour de la longitude
    }

    @Override
    public void onProviderDisabled(String provider) { }

    @Override
    public void onProviderEnabled(String provider) { }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub
    }

    public static double getLatitude() { // Accesseur Latitude
        return latitude  ;
    }

    public static double getLongitude() { // Accesseur Longitude
        return longitude ;
    }

} ;
