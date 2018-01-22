package com.mehellou.projet_3500130;

//import android.app.Fragment;
import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by lotfi on 20/01/18.
 */

public class mapView extends Fragment implements OnMapReadyCallback{
    static final LatLng HAMBURG = new LatLng(53.558, 9.927);
    private GoogleMap map;

    @Override
    public View onCreateView(LayoutInflater inf, ViewGroup vg , Bundle s){
        super.onCreateView(inf,vg, s);
        return inf.inflate(R.layout.mapview,vg,false);
    }

    @Override
    public void onCreate(Bundle b){
        super.onCreate(b);
    }

    public void onStart(){
        super.onStart();
        initMap();
    }

    public void initMap(){

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        if(mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.addMarker(new MarkerOptions().position(HAMBURG));
        map.moveCamera(CameraUpdateFactory.newLatLng(HAMBURG));
    }
}
