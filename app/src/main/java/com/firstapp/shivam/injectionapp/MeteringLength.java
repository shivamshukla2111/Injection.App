package com.firstapp.shivam.injectionapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Math.round;

/**
 * Created by SHIVAM on 25-10-2016.
 */
public class MeteringLength extends AppCompatActivity  {
    public static final String PART_NAME ="PART_NAME" ;
    public static final String NO_CAVITIES ="NO_CAVITIES" ;
    EditText partname;
    EditText totpartweigth;
    EditText totrunnerweigth;
    EditText cylinderinnerdiameter;
    EditText cushionlength;
    TextView mcfactor;
    TextView Noofcavities;
    TextView totweigth;
    TextView totshotsize;
    double mcfvalue;
    int noofcavities;
    Button b3;
    Button b4;
    Intent k;
    int totalweigthv;
    int totpartweigthv;
    int totrunnerweigthv;
    int innerdiamterv;
    int shotsizev;
    int cushionlengthv;
    double finalshotsizev;
    String resintypev;
    private int machintonnage;
    private String partnamev;
    private String usernamev;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras=getIntent().getExtras();
        usernamev=extras.getString(Login.USER_NAME);
        mcfvalue=extras.getDouble("meltcorrectionfactor");
        noofcavities=extras.getInt("noofcavities");
        resintypev=extras.getString("resintype");
        machintonnage=extras.getInt(MainActivity.MACHINE_TONNAGE);
        setContentView(R.layout.activity_meter1);
        partname=(EditText)findViewById(R.id.editText);
        partname.requestFocus();
        totpartweigth=(EditText)findViewById(R.id.editText8);
        totrunnerweigth=(EditText)findViewById(R.id.editText9);
        cylinderinnerdiameter=(EditText)findViewById(R.id.editText10);
        cushionlength=(EditText)findViewById(R.id.editText11);
        mcfactor=(TextView) findViewById(R.id.textView12);
        Noofcavities=(TextView)findViewById(R.id.textView10);
        totweigth=(TextView)findViewById(R.id.textView23);
        totshotsize=(TextView)findViewById(R.id.textView26);
        b3=(Button)findViewById(R.id.button3);
        b4=(Button)findViewById(R.id.button5);
        totrunnerweigth.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    Toast.makeText(getApplicationContext(),"ENTER DIAMETER AND PRESS SHOT SIZE",Toast.LENGTH_SHORT).show();
                }
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                k=new Intent(MeteringLength.this,Injection.class);
                Bundle extras=new Bundle();
                extras.putString(Login.USER_NAME,usernamev);
                extras.putInt("totalshotsize",shotsizev);
                extras.putString("resintype",resintypev);
                extras.putInt("runnerweigth",totrunnerweigthv);
                extras.putInt(NO_CAVITIES,noofcavities);
                extras.putDouble("mcfv",mcfvalue);
                extras.putInt("innerdiameter",innerdiamterv);
                extras.putInt("partweight",totpartweigthv);
                extras.putInt(MainActivity.MACHINE_TONNAGE,machintonnage);
                partnamev=partname.getText().toString();
                extras.putString(PART_NAME,partnamev);
                k.putExtras(extras);
                startActivity(k);
            }
        });
        setvalues();
        b3.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                 totalweigthv=totalweigth();
                 totweigth.setText(String.valueOf(totalweigthv));
                 shotsizev=(int)(round(calculateshotsize()));
                 totshotsize.setText(String.valueOf(shotsizev));
                Toast.makeText(getApplicationContext(),"PRESS INJECTION BUTTON NOW",Toast.LENGTH_SHORT).show();
            }

            private double calculateshotsize() {
                if (cylinderinnerdiameter.getText().toString().isEmpty()) {
                } else {
                    innerdiamterv = Integer.parseInt(cylinderinnerdiameter.getText().toString());
                }
                if (cushionlength.getText().toString().isEmpty()) {
                } else {
                    cushionlengthv = Integer.parseInt(cushionlength.getText().toString());
                }
                 finalshotsizev=((4*totalweigthv*1000)/(3.14*innerdiamterv*innerdiamterv*mcfvalue))+cushionlengthv;
                if(finalshotsizev>0)
                    return  finalshotsizev;
                else
                    return 0;
            }
        });
    }

    private int totalweigth() {
        if (totpartweigth.getText().toString().isEmpty()) {
        }
        else
        {totpartweigthv=Integer.parseInt(totpartweigth.getText().toString());
        }
        if (totrunnerweigth.getText().toString().isEmpty()) {
        }
        else
        {totrunnerweigthv=Integer.parseInt(totrunnerweigth.getText().toString());
        }
        int totweigthl=totpartweigthv+totrunnerweigthv;
        if(totweigthl>0)
            return totweigthl;
        else
            return 0;
    }
    private void setvalues() {
        mcfactor.setText(String.valueOf(mcfvalue));
        Noofcavities.setText(String.valueOf(noofcavities));
    }

}