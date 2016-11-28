package com.app.mytime2sallon;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener, GoogleMap.OnMarkerDragListener {

    private GoogleMap mMap;
    boolean markerClicked;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        textView = (TextView)findViewById(R.id.textView);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.setOnMapClickListener(this);
        mMap.setOnMapLongClickListener(this);
        mMap.setOnMarkerDragListener(this);
    }


    @Override
    public void onMapClick(LatLng latLng) {
        textView.setText(latLng.toString());

        mMap.addMarker(new MarkerOptions()
                .position(latLng)
                .draggable(true));

        markerClicked = false;
    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        textView.setText("New marker added@" + latLng.toString());

        mMap.addMarker(new MarkerOptions()
                .position(latLng)
                .draggable(true));

        markerClicked = false;
    }

    @Override
    public void onMarkerDrag(Marker marker) {
        textView.setText("Marker " + marker.getId() + " Drag@" + marker.getPosition());
    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        textView.setText("Marker " + marker.getId() + " DragEnd");
    }

    @Override
    public void onMarkerDragStart(Marker marker) {
        textView.setText("Marker " + marker.getId() + " DragStart");

    }
}
