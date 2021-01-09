package com.example.binusezyfoody_natashiavirnilia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Restaurant extends AppCompatActivity {
    SupportMapFragment supportMapFragment;
    GoogleMap map;
    FusedLocationProviderClient person;
    FloatingActionButton fb;
    private String resto;
    double personLang = 0, personLong = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        fb = findViewById(R.id.fb);
        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.googleMap);
        person = LocationServices.getFusedLocationProviderClient(this);

        if (ActivityCompat.checkSelfPermission(getBaseContext(), Manifest.permission.ACCESS_FINE_LOCATION) == getPackageManager().PERMISSION_GRANTED) {
            Task<Location> task = person.getLastLocation();
            getPersonLocation(task);
        }else{
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 7);
        }

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                intent.putExtra("type", "restaurant");
                intent.putExtra("restaurant", resto);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 7){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                onRestart();
            }
        }
    }

    private void getPersonLocation(Task<Location> task){
            task.addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if(location != null){
                        personLang = location.getLatitude();
                        personLong = location.getLongitude();
                        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                            @Override
                            public void onMapReady(GoogleMap googleMap) {
                                map = googleMap;
                                map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(personLang, personLong), 30));
                                map.addMarker(new MarkerOptions().position(new LatLng(personLang, personLong)).title("Im here"));
                                map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                                    @Override
                                    public void onMapClick(LatLng latLng) {
                                        map.clear();
                                        fb.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_check_24));
                                        fb.setVisibility(View.VISIBLE);
                                        map.addMarker(new MarkerOptions().position(latLng).title("destination"));
                                        getNearby(getBaseContext(), Double.toString(latLng.latitude), Double.toString(latLng.longitude));
                                        map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(personLang, personLong), 15));
                                    }
                                });

                                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                                    @Override
                                    public boolean onMarkerClick(Marker marker) {
                                        resto = marker.getTitle();
                                        return false;
                                    }
                                });
                            }
                        });
                    }
                }
            });
        }

        private void getNearby(Context context, String lat, String longs){
            String URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?"
                    +"location=" + lat + "," + longs + "&radius=3000"
                    + "&type=restaurant" + "&sensor=true" + "&key=AIzaSyCNLyMOH3YhQZWaQmQPf-3faTxYd4GLvC4";
            RequestQueue requestQueue = Volley.newRequestQueue(context);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        String result = response.getString("results");
                        JSONArray array = new JSONArray(result);
                        for(int i=0; i< array.length(); i++){
                            JSONObject maps = array.getJSONObject(i);
                            String name = maps.getString("name");
                            JSONObject geometry = new JSONObject(maps.getString("geometry"));
                            JSONObject latLng = new JSONObject(geometry.getString("location"));
                            String lat = latLng.getString("lat");
                            String lng = latLng.getString("lng");
                            Marker marker = map.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(lat), Double.parseDouble(lng))).title(name));
                            marker.setTag(i);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });

            requestQueue.add(jsonObjectRequest);
        }
}