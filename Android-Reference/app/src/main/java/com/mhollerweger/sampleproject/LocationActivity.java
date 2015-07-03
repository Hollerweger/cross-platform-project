package com.mhollerweger.sampleproject;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by MHollerweger on 24.04.15.
 */
public class LocationActivity {

    private Activity activity;
    private LocationListener locationListener;
    private LocationManager locationManager;

    public LocationActivity (Activity activity){
        this.activity = activity;
        init();
    }

    // Define a listener that responds to location updates
    private void init() {
        locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                // Called when a new location is found by the network location provider.
                //makeUseOfNewLocation(location);
                //TextView altitude = (TextView) activity.findViewById(R.id.altitude);
                TextView latitude = (TextView) activity.findViewById(R.id.latitude);
                TextView longitude = (TextView) activity.findViewById(R.id.longitude);
                //altitude.setText(String.valueOf(location.getAltitude()));
                latitude.setText(String.valueOf(location.getLatitude()));
                longitude.setText(String.valueOf(location.getLongitude()));
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };

        // Acquire a reference to the system Location Manager
        locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
    }

// Register the listener with the Location Manager to receive location updates
    public void requestLocationUpdate(){
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
    }
    }
