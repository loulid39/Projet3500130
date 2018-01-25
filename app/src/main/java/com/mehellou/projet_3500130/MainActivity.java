package com.mehellou.projet_3500130;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

         /*hide title & full screen mode*/
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        setContentView(R.layout.activity_main);

        Button nov = findViewById(R.id.novice);
        nov.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intt = new Intent(getApplicationContext() , gameActivity.class);
                //intt.putExtra("cle","valeur");
                intt.putExtra("LEVEL", "novince");
                startActivity(intt);
            }
        });

        Button med = findViewById(R.id.medium);
        med.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intt = new Intent(getApplicationContext(), gameActivity.class);
                intt.putExtra("LEVEL","medium");
                startActivity(intt);
            }
        });

        Button exp = findViewById(R.id.expert);
        exp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intt = new Intent(getApplicationContext() , gameActivity.class);
                intt.putExtra("LEVEL","expert");
                startActivity(intt);
            }
        });



        Button scor = findViewById(R.id.stat);
        scor.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), scoreView.class);
                startActivity(intent);
            }
        });
    }
}
