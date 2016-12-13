package com.firstapp.shivam.injectionapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SHIVAM on 25-10-2016.
 */
public class Zone extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    int[] maxTemp={250,250,250,240,260,250,200,260,300,390,270,250,300,400,300,220};
    int[] minTemp={200,200,200,180,200,230,150,200,280,260,250,230,280,370,250,190};
    double[] mcf={0.96,0.96,0.96,0.75,0.75,0.73,1.10,1.10,1.09,1.36,1.13,0.99,1.00,1.34,1.06,1.15};
    TextView zone1;
    int diffrence;
    TextView zone2;
    TextView zone3;
    TextView zone4;
    TextView zone5;
    Intent k;
    int zone2v;
    int zone3v;
    int zone4v;
    int positionglobal;
    int noofcavitiesv;
    Button meterl;
    String resintypev;
    private int machinetonnage=0;
    private String usernamev;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras=getIntent().getExtras();
        usernamev=extras.getString(Login.USER_NAME);
        noofcavitiesv=extras.getInt("noofcavities");
        machinetonnage=extras.getInt(MainActivity.MACHINE_TONNAGE);
        setContentView(R.layout.activity_zone);
        zone1=(TextView)findViewById(R.id.textView34);
        zone2=(TextView)findViewById(R.id.textView35);
        zone3=(TextView)findViewById(R.id.textView36);
        zone4=(TextView)findViewById(R.id.textView37);
        zone5=(TextView)findViewById(R.id.textView38);
        meterl=(Button)findViewById(R.id.button4);
        Spinner spinner = (Spinner) findViewById(R.id.spinner3);
        spinner.setOnItemSelectedListener(this);
        List<String> categories = new ArrayList<String>();
        categories.add("GPPS");
        categories.add("HIPS");
        categories.add("ABS");
        categories.add("LDPE");
        categories.add("HDPE\n");
        categories.add("PP\n");
        categories.add("PVC\n");
        categories.add("PMMA\n");
        categories.add("PC\n");
        categories.add("PET\n");
        categories.add("PBT\n");
        categories.add("PA6\n");
        categories.add("PA66\n");
        categories.add("PPS\n");
        categories.add("PPO\n");
        categories.add("POM\n");
        Toast.makeText(getApplicationContext(),"CHOOSE RESIN FROM DROP DOWN",Toast.LENGTH_SHORT).show();


// Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        meterl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 k=new Intent(Zone.this,MeteringLength.class);
                 Bundle extras=new Bundle();
                 extras.putString(Login.USER_NAME,usernamev);
                 extras.putDouble("meltcorrectionfactor",mcf[positionglobal]);
                 extras.putInt("noofcavities",noofcavitiesv);
                 extras.putString("resintype",resintypev);
                 extras.putInt(MainActivity.MACHINE_TONNAGE,machinetonnage);
                 k.putExtras(extras);
                 startActivity(k);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
           resintypev=item;
        // Showing selected spinner item
        zone1.setText(String.valueOf(maxTemp[position]));
        zone5.setText(String.valueOf(minTemp[position]));
        positionglobal=position;
        diffrence=(maxTemp[position]-minTemp[position])/4;
        zone2v=maxTemp[position]-diffrence;
        zone3v=zone2v-diffrence;
        zone4v=zone3v-diffrence;
        zone2.setText(String.valueOf(zone2v));
        zone3.setText(String.valueOf(zone3v));
        zone4.setText(String.valueOf(zone4v));
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    }


