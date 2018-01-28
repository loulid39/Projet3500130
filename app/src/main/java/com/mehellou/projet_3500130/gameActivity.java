package com.mehellou.projet_3500130;

//import android.app.FragmentTransaction;
//import android.app.FragmentTransaction;
import android.os.*;
import android.support.v4.app.FragmentTransaction;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by lotfi on 20/01/18.
 */

public class gameActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        mapView mv = new mapView();
        ft.add(R.id.containerf2,mv,"mapv");
        streetView st = new streetView();
        ft.add(R.id.containerf1, st,"streeatv");
        ft.commit();
    }
}
