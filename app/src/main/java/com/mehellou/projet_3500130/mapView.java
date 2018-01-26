       package com.mehellou.projet_3500130;


        import static com.mehellou.projet_3500130.ConstantStat.FIRST_COLUMN;
        import static com.mehellou.projet_3500130.ConstantStat.SECOND_COLUMN;
        import static com.mehellou.projet_3500130.ConstantStat.THIRD_COLUMN;
//import android.app.Fragment;
        import com.google.android.gms.maps.CameraUpdate;
        import com.google.android.gms.maps.model.Marker;

        import android.app.AlertDialog;
        import android.content.Context;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.location.Location;
        import android.support.v4.app.Fragment;

        import android.os.Bundle;
        import android.support.v4.app.FragmentTransaction;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;

        import com.google.android.gms.maps.CameraUpdateFactory;
        import com.google.android.gms.maps.GoogleMap;
        import com.google.android.gms.maps.OnMapReadyCallback;
        import com.google.android.gms.maps.SupportMapFragment;
        import com.google.android.gms.maps.model.LatLng;
        import com.google.android.gms.maps.model.MarkerOptions;
        import com.google.android.gms.maps.model.Polyline;
        import com.google.android.gms.maps.model.PolylineOptions;

        import java.io.File;
        import java.io.FileWriter;
        import java.text.DateFormat;
        import java.text.DecimalFormat;
        import java.text.SimpleDateFormat;
        import java.util.ArrayList;
        import java.util.Date;
        import java.util.HashMap;

/**
 * Created by lotfi on 20/01/18.
 */

public class mapView extends Fragment implements OnMapReadyCallback{
    static final LatLng POS = new LatLng(53.558, 9.927);
    static final LatLng POS1 = new LatLng(40.780269, -73.961473);
    static final LatLng POS2 = new LatLng(48.858620, 2.293985);
    //static final LatLng POS3 = new LatLng(40.412837, -3.700073);
    static final LatLng POS3 = new LatLng(48.8485461,2.3427929);
    static final LatLng POS4 = new LatLng(48.8481521,2.3425766);

    private GoogleMap map;
    LatLng currentP = POS;
    Date date;
    int nbEssay = 4;
    streatView fg = null;

    int score = 0;
    String dat;
    String level = "";

    private ArrayList positions;
    private CSVHandler csv;



    @Override
    public View onCreateView(LayoutInflater inf, ViewGroup vg , Bundle s){
        super.onCreateView(inf,vg, s);
        level = getActivity().getIntent().getStringExtra("LEVEL");
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        dat = dateFormat.format(date);

        csv = new CSVHandler(getContext());
        if (nbEssay == 4){
            initPositions();
            //lvlHandler();
            float[] pos= (float[])positions.get(nbEssay-1);
            currentP = new LatLng(pos[0],pos[1]);
            Log.wtf("mapVIew",currentP.toString());
            for (int i = nbEssay ; i > 0; i--){
                float[] pos2= (float[])positions.get(i-1);
                Log.wtf("mapView",pos2[0] + " " + pos2[1]);
            }
        }


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
        map.moveCamera(CameraUpdateFactory.newLatLng(currentP));
        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Location la = new Location("A");
                la.setAltitude(currentP.latitude);
                la.setLongitude(currentP.longitude);
                Location lb = new Location("B");
                lb.setLongitude(latLng.longitude);
                lb.setAltitude(latLng.latitude);
                MarkerOptions m =  new MarkerOptions().position(latLng);
                MarkerOptions m2 = new MarkerOptions().position(currentP);
                map.addMarker(m);
                map.addMarker(m2);

                // map.moveCamera((CameraUpdateFactory.newLatLng(latLng)));
                map.moveCamera(CameraUpdateFactory.newLatLng(currentP));
                CameraUpdate zoom=CameraUpdateFactory.zoomIn();
                map.animateCamera(zoom,100,null);



                float dis = la.distanceTo(lb);

                /*dessine chemin entre deux points*/
                PolylineOptions pathOptions = new PolylineOptions()
                        .add(latLng)
                        .add(currentP);
                Polyline polyline = map.addPolyline(pathOptions);

                /* appel affichage de la distance + gère si fin de partie*/
                handlePlayerTurn(dis);
            }
        });
        fg = (streatView)getActivity().getSupportFragmentManager().findFragmentByTag("streeatv");
        fg.changePosition(currentP);
    }

    public void processScore(float lb){
        score += getScore(lb);
        nouvelEssay();

        lvlHandler();
        fg.changePosition(currentP);
        initState();
    }

    public void initState(){
        LatLng l = new LatLng(40.585106,25.2025836);
        map.moveCamera(CameraUpdateFactory.newLatLng(l));
        CameraUpdate zoom=CameraUpdateFactory.zoomOut();

        map.animateCamera(zoom,10,null);
    }

    /*
    * TODO: rajouter une catégorie expert
     */
    public void initPositions(){
        switch (level){
            case "medium" : positions = csv.getRandom(CSVHandler.fileName.WORLD,nbEssay);break;
            case "novince" : positions = csv.getRandom(CSVHandler.fileName.CAPITAL,nbEssay);break;
            case "expert" : positions = csv.getRandom(CSVHandler.fileName.WORLD,nbEssay);break;
            default: Log.wtf("mapView","ERROR, this level doesn't exist");
        }
    }


    public void nouvelEssay(){
        switch (level){
            case "medium" : mediumLevel();break;
            case "novince" : novinceLevel();break;
            case "expert" : expertLevel();break;
        }
    }

    public void lvlHandler(){
        if (nbEssay > 0){
            float[] pos= (float[])positions.get(nbEssay-1);
            currentP = new LatLng(pos[0],pos[1]);
            nbEssay--;
        }
        else {
            persist();

            AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
            //alertDialog.setTitle("Alert");
            alertDialog.setMessage("Hey, you have " + score + " points \\o/");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                             /* retour à l'accueil*/
                            getActivity().onBackPressed();
                        }
                    });
            alertDialog.setCancelable(false);
            alertDialog.show();
        }

    }



    public void novinceLevel(){
        if(nbEssay > 0){
            if(nbEssay == 2) currentP = POS1;
            if(nbEssay == 1) currentP = POS2;
            if(nbEssay == 3) currentP = POS3;
            if(nbEssay == 4) currentP = POS4;
            nbEssay--;

        } else {
            persist();

            AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
            //alertDialog.setTitle("Alert");
            alertDialog.setMessage("Hey, you have " + score + " points \\o/");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                             /* retour à l'accueil*/
                            getActivity().onBackPressed();
                        }
                    });
            alertDialog.setCancelable(false);
            alertDialog.show();
        }
    }

    /*A faire [3]*/
    public void mediumLevel(){
        novinceLevel();
        /*remplacer l'appel de la fct : novinceLevel() par par le traitement de choisir la bonne position selon niveau */
    }

    /*A faire [4]*/
    public void expertLevel(){
        novinceLevel();
        /*remplacer l'appel de la fct : novinceLevel() par par le traitement de choisir la bonne position selon niveau */
    }

    public void persist(){
        File file = new File(getActivity().getApplicationContext().getFilesDir(),"mydir");

        if(!file.exists()){
            file.mkdir();
        }

        try{
            File gpxfile = new File(file, "scoreData.txt");
            FileWriter writer = new FileWriter(gpxfile,true);

            writer.append(score+"@"+level+"@"+dat+"\n");
            writer.flush();
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /* récupère la distance parcourue, l'affiche et gère le score puis lance niveau suivant*/
    public float handlePlayerTurn(float dist){
        DecimalFormat df = new DecimalFormat("#.##");
        //final float dis = dist*0.001f;
        final float dis = dist/1000;

        AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
        //alertDialog.setTitle("Alert");
        alertDialog.setMessage("You are " + df.format(dis) + "km away");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        processScore(dis);
                        map.clear();
                    }
                });
        alertDialog.setCancelable(false);
        alertDialog.show();
        return dist;
    }

    /*A faire: sile temps ajouter un affichage temporisé affichant le score ajouté à l'écran
    * on click (exemple: +100! +200!*/
    /*dist en km*/
    public int getScore(float dist){
          /*Moins de 300m, AMAZING!*/
        if (dist < 0.3)
            return 4200;
        if (dist < 20)
            return 2000;
        if (dist <300)
            return 1000;
        if (dist < 800)
            return 500;
        return (dist < 1200 ) ? 300 : 100 ;
    }
}
