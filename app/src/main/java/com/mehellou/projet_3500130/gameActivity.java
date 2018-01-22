package com.mehellou.projet_3500130;

//import android.app.FragmentTransaction;
//import android.app.FragmentTransaction;
import android.os.*;
import android.view.*;
import android.support.v4.app.FragmentTransaction;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by lotfi on 20/01/18.
 */

public class gameActivity extends AppCompatActivity {
    private String level = "";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.game_activity);


        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();// getFragmentManager().beginTransaction();
        streatView st = new streatView();
        ft.add(R.id.containerf1, st,"streeatv");
        mapView mv = new mapView();
        ft.add(R.id.containerf2,mv,"mapv");
        ft.commit();
        /*FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
       ft.add(R.id.container, st);
       ft.replace(R.id.container, st.getParentFragment());*/

        System.out.println("Demmarage !!!!");
    }

    public void setLevel(String l){
        level = l;
        initMap();
    }

    public void initMap(){
        switch (level){
            case "novice" : {
                initMapNovice();
            };break;
            case "medium" : {
                initMapMedium();
            };break;
            case "expert" : {
                initMapExpert();
            };break;
        }
    }

    public void initMapNovice(){

    }

    public void initMapMedium(){

    }

    public void initMapExpert(){

    }
}
