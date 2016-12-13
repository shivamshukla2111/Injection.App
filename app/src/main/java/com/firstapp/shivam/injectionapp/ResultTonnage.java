package com.firstapp.shivam.injectionapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by SHIVAM on 28-10-2016.
 */
public class ResultTonnage extends AppCompatActivity {

    int clampingPartF;
    int clampingMoldF;
    int machineTonnageF;
    TextView cp;
    TextView cm;
    TextView mt;
    int i=0;
    int[] tonnage={0,75,100,140,160,220,250,300,350,450,550,650,850,1300,1800,2500};
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultt);
        Intent j=getIntent();
        Bundle extras=j.getExtras();
         clampingPartF=extras.getInt("clampingPart");
         clampingMoldF=extras.getInt("clampingMold");
         machineTonnageF=extras.getInt("machinetonnage");
        cp=(TextView)findViewById(R.id.textView16);
        cm=(TextView)findViewById(R.id.textView18);
        mt=(TextView)findViewById(R.id.textView20);
        setvalue();
    }

    private void setvalue() {
    cp.setText(String.valueOf(clampingPartF));
    cm.setText(String.valueOf(clampingMoldF));
        standardtonnage();
    }

    private void standardtonnage() {
        for(i=0;i<tonnage.length;i++)
        {
        if (tonnage[i]>=machineTonnageF) {
            break;
        }
    }
        mt.setText(String.valueOf(tonnage[i]));
}}
