package com.mehellou.projet_3500130;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.SupportStreetViewPanoramaFragment;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by lotfi on 20/01/18.
 */

public class streetView extends Fragment implements OnStreetViewPanoramaReadyCallback{
    static final LatLng HAMBURG = new LatLng(40.780269, -73.961473);
    private StreetViewPanorama panorama;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        super.onCreateView(inflater,container,savedInstanceState);
        final View rootview = inflater.inflate(R.layout.streetview, container, false);

        SupportStreetViewPanoramaFragment sfrag = (SupportStreetViewPanoramaFragment)getChildFragmentManager().findFragmentById(R.id.str);
        if(sfrag != null) {
            sfrag.getStreetViewPanoramaAsync(this);
        }

        return rootview;

    }

    @Override
    public void onCreate(Bundle b){
        super.onCreate(b);
        setRetainInstance(true);
    }

    @Override
    public void onStart(){
        super.onStart();
        initMap();
    }

    public void initMap(){

    }

    @Override
    public void onStreetViewPanoramaReady(StreetViewPanorama streetViewPanorama) {
        panorama = streetViewPanorama;
        StreetViewPanoramaCamera.Builder builder = new StreetViewPanoramaCamera.Builder( panorama.getPanoramaCamera() );
        builder.tilt( 0.0f );
        builder.zoom( 0.0f );
        builder.bearing( 0.0f );
        panorama.animateTo( builder.build(), 0 );
    }

    public void changePosition(LatLng latLng){
        panorama.setPosition(latLng, 300);
        panorama.setStreetNamesEnabled(true);
    }
}