package com.firstapp.shivam.injectionapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SHIVAM on 28-10-2016.
 */
public class Injection extends AppCompatActivity implements  AdapterView.OnItemSelectedListener {
    int screwpositionv;
    EditText maxpressure;
    int counter2=0;
    int counter3=0;
    EditText maxspeed;
    TextView injpressure;
    EditText injspeed;
    TextView screwposition;
    EditText holdpressure;
    TextView holdspeed;
    EditText holdtime;
    int maxpressurev;
    int maxspeedv;
    int injpressurev;
    int injspeedv;
    int counter = 0;
    int counter1=0;
    int holdingpressurev;
    int holdingspeedv;
    Button reportactivity;
    Button holdintimec;
    String resintypev;
    int holdweightp;
    int holdweightc;
    int totrunnerweightv;
    int totpartweightv;
    double mcfvalue;
    int innerdiameterv;
    EditText holdweigth;
    int injspeedincrease;
    int a=2;
    private int machinetonnage;
    String partname;
    private int noofcavities;
    private String usernamev;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_injection);
        final Bundle extras = getIntent().getExtras();
        usernamev=extras.getString(Login.USER_NAME);
        screwpositionv = extras.getInt("totalshotsize");
        resintypev=extras.getString("resintype");
        totrunnerweightv=extras.getInt("runnerweigth");
        mcfvalue=extras.getDouble("mcfv");
        innerdiameterv=extras.getInt("innerdiameter");
        totpartweightv=extras.getInt("partweight");
        machinetonnage=extras.getInt(MainActivity.MACHINE_TONNAGE);
        partname = extras.getString(MeteringLength.PART_NAME);
        noofcavities=extras.getInt(MeteringLength.NO_CAVITIES);
        maxpressure = (EditText) findViewById(R.id.editText5);
        maxpressure.requestFocus();
        maxspeed = (EditText) findViewById(R.id.editText12);
        injpressure = (TextView) findViewById(R.id.textView25);
        injspeed = (EditText) findViewById(R.id.editText18);
        screwposition = (TextView) findViewById(R.id.textView42);
        holdpressure = (EditText) findViewById(R.id.editText13);
        holdspeed = (TextView) findViewById(R.id.textView45);
        holdtime = (EditText) findViewById(R.id.editText7);
        reportactivity=(Button)findViewById(R.id.button6);
        holdintimec=(Button)findViewById(R.id.button7);
        holdweigth=(EditText)findViewById(R.id.editText17);
        injspeed.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    Toast.makeText(getApplication(),"AFTER CHANGE PRESS FLASH FROM DROP DOWN",Toast.LENGTH_SHORT).show();
                }
            }
        });
        holdintimec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter1=counter1+1;
                if(counter1==1) {
                    if(holdweigth.getText().toString().isEmpty())
                    {
                        Toast.makeText(getApplicationContext(),"WEIGTH CANNOT BE BLANK",Toast.LENGTH_SHORT).show();
                    }
                    else{
                    holdweightc = Integer.parseInt(holdweigth.getText().toString());
                    holdtime.setText("2");
                    holdweigth.setText(" ");
                    Toast.makeText(getApplicationContext(),"ENTER WEIGHT",Toast.LENGTH_SHORT).show();
                }
                }
                if(counter1!=1)
                {  holdweightp=holdweightc;
                    if(holdweigth.getText().toString().isEmpty())
                    {
                        Toast.makeText(getApplicationContext(),"WEIGTH CANNOT BE BLANK",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        holdweightc=Integer.parseInt(holdweigth.getText().toString().trim());
                    if(holdweightc==holdweightp)
                    {   holdtime.setText(String.valueOf(a-1));
                        Toast.makeText(getApplicationContext(),"SAME CONSECUTIVE WEIGHTS",Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), "CLICK REPORT BUTTON FOR REPORT", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {a=a+1;
                        holdtime.setText(String.valueOf(a));
                        holdweigth.setText(" ");
                        Toast.makeText(getApplicationContext(),"ENTER WEIGHT",Toast.LENGTH_SHORT).show();
                    }

                }}
            }
        });
        reportactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent r=new Intent(Injection.this,Report.class);
                Bundle extras=new Bundle();
                extras.putString(Login.USER_NAME,usernamev);
                extras.putInt("injpressure",injpressurev);
                extras.putInt("injspeed",injspeedv-injspeedincrease);
                extras.putInt("screwposition",screwpositionv);
                holdingpressurev=(int)(.5*injpressurev);
                extras.putInt("holdingpressure",holdingpressurev);
                holdingspeedv=(int)(.50*injspeedv);
                extras.putInt("holdingspeed",holdingspeedv);
                extras.putInt("holdingtime",Integer.parseInt(holdtime.getText().toString()));
                extras.putString("resintype",resintypev);
                extras.putInt("runnerweigth",totrunnerweightv);
                extras.putInt("partweight",totpartweightv);
                extras.putDouble("mcfv",mcfvalue);
                extras.putInt("innerdiameter",innerdiameterv);
                extras.putInt(MeteringLength.NO_CAVITIES,noofcavities);
                extras.putInt(MainActivity.MACHINE_TONNAGE,machinetonnage);
                extras.putString(MeteringLength.PART_NAME,partname);
                r.putExtras(extras);
                startActivity(r);
            }
        });
        setvalues();
        maxspeed.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (maxspeed.getText().toString().isEmpty()) {
                    maxspeedv = 0;
                } else {
                    maxspeedv = Integer.parseInt(maxspeed.getText().toString());
                }
                injspeedv = (int) (maxspeedv * .10);
                injspeed.setText(String.valueOf(injspeedv));
            }

            @Override
            public void afterTextChanged(Editable s) {
                counter3=counter3+1;
                if(counter3==1)
                {
                    Toast.makeText(getApplicationContext(),"CHOOSE OPTIONS FROM DROP DOWN",Toast.LENGTH_SHORT).show();
                }
            }
        });
        maxpressure.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (maxpressure.getText().toString().isEmpty()) {
                    maxpressurev = 0;
                } else {
                    maxpressurev = Integer.parseInt(maxpressure.getText().toString());
                }
                injpressurev = (int) (maxpressurev * .50);
                injpressure.setText(String.valueOf(injpressurev));
            }

            @Override
            public void afterTextChanged(Editable s) {
                 counter2=counter2+1;
                if(counter2==1)
                {
                    Toast.makeText(getApplicationContext(),"NOW ENTER MAX INJECTION SPEED",Toast.LENGTH_SHORT).show();
                }
            }
        });
        NDSpinner spinner = (NDSpinner)findViewById(R.id.spinner1);
        spinner.setOnItemSelectedListener(this);
        List<String> categories = new ArrayList<String>();
        categories.add("CHOOSE OPTION");
        categories.add("SHOT MOULDING");
        categories.add("FULLY FILLED");
        categories.add("FLASH");

// Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        switch (item) {
            case "SHOT MOULDING":
                changevalues();
                break;
            case "FULLY FILLED":
                changevalues();
                break;
            case "FLASH":
                finalvalues();
                break;
            default:
                Toast.makeText(getApplicationContext(),"ENTER MAX INJECTION PRESSURE",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void setvalues() {

        screwposition.setText(String.valueOf(screwpositionv));
    }

    private void finalvalues() {
        holdpressure.setText(String.valueOf(.50 * injpressurev));
        injspeedv=Integer.parseInt(injspeed.getText().toString());
        holdspeed.setText(String.valueOf(.50 *injspeedv));
        Toast.makeText(getApplicationContext(),"ENTER WEIGHT FOR HOLDING TIME",Toast.LENGTH_SHORT).show();
    }

    private void changevalues() {
         injspeedincrease = (int) (.10 * maxspeedv);
        int injpressureincrease = (int) (.10 * maxpressurev);
        if (injspeedv >= (.90 * maxspeedv)) {
            injspeedv = maxspeedv;
            injpressurev = maxpressurev;
            injspeed.setText(String.valueOf(injspeedv));
            injpressure.setText(String.valueOf(maxpressurev));
            Toast.makeText(getApplicationContext(), "MAX PRESSURE REACHED", Toast.LENGTH_SHORT).show();
        }
        if (injspeedv == maxspeedv) {
            injspeedv = maxspeedv;
            injpressurev = maxpressurev;
            injspeed.setText(String.valueOf(maxspeedv));
            injpressure.setText(String.valueOf(maxpressurev));
        }
        if (injspeedv > (.30 * maxspeedv) && injspeedv < (.90 * maxspeedv)) {
                injspeedv = injspeedv + injspeedincrease;
                injspeed.setText(String.valueOf(injspeedv));
            if(injpressurev<=(.90*maxpressurev)) {
                injpressurev = injpressurev + injpressureincrease;
                injpressure.setText(String.valueOf(injpressurev));
                Toast.makeText(getApplicationContext(), "PRESSURE HAS CHANGED", Toast.LENGTH_SHORT).show();
            }
        }
        if (injspeedv <= (.30 * maxspeedv)) {
            injspeedv = injspeedv + injspeedincrease;
            injspeed.setText(String.valueOf(injspeedv));

        }
    }

}
