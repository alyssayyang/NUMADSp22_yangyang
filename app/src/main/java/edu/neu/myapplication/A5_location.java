package edu.neu.myapplication;

import android.Manifest;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.Priority;
import com.google.android.gms.tasks.OnSuccessListener;


public class A5_location extends AppCompatActivity {

    private static final int PERMISSION_FINE_LOCATION = 99;
    private static final int DEFAULT_UPDATE_INTERVAL = 30;
    private static final int FAST_UPDATE_INTERVAL = 15;
    static double distanceInMetres = 0.0;
    static Location lastLocation = null;

    TextView tv_lati, tv_lonti, tv_total_dis, tv_accuracy;
    Button bt_resetDis;
    LocationRequest locationRequest;
    Switch sw_changePrecision;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a5_location);

        tv_lati = findViewById(R.id.tv_latitude);
        tv_lonti = findViewById(R.id.tv_longitude);
        tv_total_dis = findViewById(R.id.tv_totalDistance);
        tv_accuracy = findViewById(R.id.tv_accuracy);
        bt_resetDis = findViewById(R.id.bt_resetDistance);
        sw_changePrecision = findViewById(R.id.sw_changePrecision);

        getLocation();

        locationRequest = LocationRequest.create();
        //how often does the default location check occur
        locationRequest.setInterval(1000 * DEFAULT_UPDATE_INTERVAL);
        //how often does location check occur when set to the most frequent update?
        locationRequest.setFastestInterval(1000 * FAST_UPDATE_INTERVAL);
        locationRequest.setPriority(Priority.PRIORITY_BALANCED_POWER_ACCURACY);

        sw_changePrecision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sw_changePrecision.isChecked()){
                    locationRequest.setPriority(Priority.PRIORITY_HIGH_ACCURACY);
                    tv_accuracy.setText("Using GPS sensors");
                }
                else{
                    locationRequest.setPriority(Priority.PRIORITY_BALANCED_POWER_ACCURACY);
                    tv_accuracy.setText("Using Towers + WIFI") ;
                }
            }
        });

        bt_resetDis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                distanceInMetres = 0.0;
                lastLocation = null;
                tv_total_dis.setText("Distance(mile): " + String.valueOf(0));

            }
        });
    }

    private void getLocation(){


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_FINE_LOCATION);
        }

            String serviceString = Context.LOCATION_SERVICE;
            LocationManager locationManager = (LocationManager) getSystemService(serviceString);
            String provider = LocationManager.GPS_PROVIDER;

            //getLocationInfo will be called from locationListener
            locationManager.requestLocationUpdates(provider, 100, 1, locationListener);
    }

    private void getLocationInfo(Location location){

        if(location != null){
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            tv_lati.setText("Latitude: "+Double.toString(latitude));
            tv_lonti.setText("Longitude: "+Double.toString(longitude));

        }else{
            tv_lati.setText("N/A");
            tv_lonti.setText("N/A");

        }
    }

    private final LocationListener locationListener = new LocationListener() {


        @Override
        public void onLocationChanged(@NonNull Location location) {
            getLocationInfo(location);
            displayDistance(location);
        }
    };

    private void displayDistance(Location location){

            if (lastLocation == null) {
                lastLocation = location;
            }
            distanceInMetres = distanceInMetres + location.distanceTo(lastLocation);
            lastLocation = location;
            tv_total_dis.setText("Distance(mile): " + String.valueOf(distanceInMetres * 0.0006213712));
        }

}