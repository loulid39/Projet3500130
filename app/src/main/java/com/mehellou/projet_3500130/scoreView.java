package com.mehellou.projet_3500130;

import static com.mehellou.projet_3500130.ConstantStat.FIRST_COLUMN;
import static com.mehellou.projet_3500130.ConstantStat.SECOND_COLUMN;
import static com.mehellou.projet_3500130.ConstantStat.THIRD_COLUMN;

import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

/**
 * Created by lotfi on 21/01/18.
 */

public class scoreView extends AppCompatActivity {
    private ArrayList<HashMap<String,String>> list;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scoreview);


        final ListView listView = (ListView)findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                setSortedElements("score");
            }
        });

        list = new ArrayList<HashMap<String,String>>();
        File file = new File(getApplicationContext().getFilesDir(),"mydir");
        if(!file.exists()){
            file.mkdir();
        }

        try{
            File gpxfile = new File(file, "scoreData.txt");
            FileReader rd = new FileReader(gpxfile);
            BufferedReader bf = new BufferedReader(rd);
            String k;
            while ((k = bf.readLine()) != null) {
                String[] q = k.split("@");
                HashMap<String,String> l1 = new HashMap<String, String>();
                l1.put(FIRST_COLUMN,q[0]);
                l1.put(SECOND_COLUMN,q[1]);
                l1.put(THIRD_COLUMN,q[2]);
                list.add(l1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        ListViewAdapter adapter = new ListViewAdapter(this, list);
        listView.setAdapter(adapter);
    }

    public void setSortedElements(String s){
        switch (s){
            case "score" : {

            } break;
            case "date" : {

            } break;
            case "level":{

            }
        }
    }
}
