package com.mehellou.projet_3500130;

import static com.mehellou.projet_3500130.ConstantStat.FIRST_COLUMN;
import static com.mehellou.projet_3500130.ConstantStat.SECOND_COLUMN;
import static com.mehellou.projet_3500130.ConstantStat.THIRD_COLUMN;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
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

    /*@Override
    public void onSaveInstanceState(Bundle inst){
        super.onSaveInstanceState(inst);
        System.out.println("###### sauvgarder");
    }*/
    @Override
    protected void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
        System.out.println("########################### SAVE?");
    }

    @Override
    public void onRestoreInstanceState(Bundle inst){
        System.out.println("###### restaure");
    }

    @Override
    public void onStop(){
        super.onStop();

        System.out.println("Alooooooooooooooooooooooooooooooooooooooooooooooo bon class");

    }

    @Override
    public void onDestroy(){
        super.onDestroy();

        System.out.println("Alooooooooooooooooooooooooooooooooooooooooooooooo bin class");

    }

    @Override
    public void onBackPressed(){

        File file = new File(getApplicationContext().getFilesDir(),"mydir");
        System.out.println("XXXXXXX "+getApplicationContext().getFilesDir().toString());
        if(!file.exists()){
            file.mkdir();
        }

        try{
            File gpxfile = new File(file, "scoreData.txt");

            /*FileWriter f = new FileWriter(gpxfile);
            Properties properties = new Properties();
            int i = 0;
            while (i <= list.size() - 1){
            for (HashMap.Entry<String, String> e :list.get(i).entrySet()){
                properties.put(e.getKey(),e.getValue());
            }
              properties.store(f, null);
                i++;
            }*/



            FileWriter writer = new FileWriter(gpxfile,true);
            HashMap<String,String> m = list.get(0);
            writer.append(m.get(FIRST_COLUMN)+"@"+m.get(SECOND_COLUMN)+"@"+m.get(THIRD_COLUMN)+"\n");
            m = list.get(list.size() - 1);
            writer.append(m.get(FIRST_COLUMN)+"@"+m.get(SECOND_COLUMN)+"@"+m.get(THIRD_COLUMN) + "\n");
            writer.flush();
            writer.close();

            FileReader rd = new FileReader(gpxfile);
            BufferedReader bf = new BufferedReader(rd);
            String k;
                while ((k = bf.readLine()) != null) {
                    //System.out.println("voilà ce que j'ai lu : " + k);
                    String[] q = k.split("@");
                    System.out.println(FIRST_COLUMN+":"+q[0]+" "+SECOND_COLUMN+":"+q[1]+" "+THIRD_COLUMN+":"+q[2]);
                }



        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("************** back button pressed");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if(savedInstanceState == null){
            System.out.println("###### sauvgarder Creat");
        } else {
            System.out.println("###### restaure Creat");
        }
        setContentView(R.layout.scoreview);

        ListView listView = (ListView)findViewById(R.id.listView);
        list = new ArrayList<HashMap<String,String>>();
        HashMap<String,String> l1 = new HashMap<String, String>();
        l1.put(FIRST_COLUMN,"1254800");
        l1.put(SECOND_COLUMN,"expert");
        l1.put(THIRD_COLUMN,"12/02/1965 14:22");
        list.add(l1);
        list.add(l1);
        HashMap<String,String> l2 = new HashMap<String, String>();
        l2.put(FIRST_COLUMN,"54875");
        l2.put(SECOND_COLUMN,"novice");
        l2.put(THIRD_COLUMN,"12/12/1 1:2");
        list.add(l2);
        list.add(l2);
        HashMap<String,String> l3 = new HashMap<String, String>();
        l3.put(FIRST_COLUMN,"9658");
        l3.put(SECOND_COLUMN,"medium");
        l3.put(THIRD_COLUMN,"1/2/1855 4:12");
        list.add(l3);

        ListViewAdapter adapter = new ListViewAdapter(this, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int pos = i+1;
                Toast.makeText(scoreView.this, Integer.toString(pos)+" Clicked", Toast.LENGTH_SHORT);
            }
        });
    }
}
