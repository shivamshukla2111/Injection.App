package com.firstapp.shivam.injectionapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static java.lang.Math.round;

public class MainActivity extends AppCompatActivity {
    public static final String MACHINE_TONNAGE ="MACHINE_TONNAGE" ;
    EditText moldLength;
    EditText moldWidth;
    EditText moldHeight;
    EditText squareLength;
    EditText squareWidth;
    EditText circularThicknes;
    EditText userThicknes;
    EditText NoOfCavities;
    int PressureMold=0;
    double squarelen=0;
    double squarewid=0;
    double circularArea=0;
    double circularDiameter=0;
    double squareArea=0;
    double thicknessEntered=0;
    Button TONNAGE;
    Button ZONE;
    int cavities=0;
    int clamMoldWidth=0;
    double totalArea=0;
    int clampingPart=0;
    int clampingMold=0;
    int machinetonnage=0;
    int i;
    int[] thicknesCheck={1,260,370,450,510,560,610,685,710,830,880,1000,1100,1400,1600,1810};
    int[] clampingForceMold={0,75,100,140,160,220,250,300,350,450,550,650,850,1300,1800,2500};
    private String usernamev;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usernamev=getIntent().getStringExtra(Login.USER_NAME);
        moldLength=(EditText)findViewById(R.id.editText1);
        moldLength.requestFocus();
        moldWidth=(EditText)findViewById(R.id.editText2);
        moldHeight=(EditText)findViewById(R.id.editText3);
        squareLength=(EditText)findViewById(R.id.editText4);
        squareWidth=(EditText)findViewById(R.id.editText14);
        circularThicknes=(EditText)findViewById(R.id.editText15);
        userThicknes=(EditText)findViewById(R.id.editText6);
        NoOfCavities=(EditText)findViewById(R.id.editText16);
        TONNAGE=(Button)findViewById(R.id.button);
        ZONE=(Button)findViewById(R.id.button2);
              ZONE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Zone.class);
                Bundle extras=new Bundle();
                if(NoOfCavities.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"ENTER NO OF CAVITIES",Toast.LENGTH_SHORT).show();

                }
                else{
                extras.putInt("noofcavities",Integer.parseInt(NoOfCavities.getText().toString()));
                    extras.putInt(MACHINE_TONNAGE,machinetonnage);
                    extras.putString(Login.USER_NAME,usernamev);
                i.putExtras(extras);
                startActivity(i);}
            }
        });
        TONNAGE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                squareArea = squareOfArea();
                circularArea = circularOfArea();
                PressureMold = checkThicknes();
                if (NoOfCavities.getText().toString().isEmpty()) {
                    cavities = 0;
                } else {
                    cavities = Integer.parseInt(NoOfCavities.getText().toString());
                }
                totalArea = (squareArea + circularArea) * cavities;
                clampingPart = (int) (round((totalArea * PressureMold) / (100000 * 0.8)));
                clampingMold = getClampingMold();
                if (clampingPart > clampingMold) {
                    machinetonnage = clampingPart;
                } else
                    machinetonnage = clampingMold;
                if (moldWidth.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "MOLD WIDHT IS REQUIRED FOR TONNAGE ", Toast.LENGTH_SHORT).show();
                } else {
                    Intent j = new Intent(MainActivity.this, ResultTonnage.class);
                    Bundle extras = new Bundle();
                    extras.putInt("clampingPart", clampingPart);
                    extras.putInt("clampingMold", clampingMold);
                    extras.putInt("machinetonnage", machinetonnage);
                    j.putExtras(extras);
                    startActivity(j);
                }
            }
            private int getClampingMold() {
                if(moldWidth.getText().toString().isEmpty())
                {
                 Toast.makeText(getApplicationContext(),"ENTER MOLD WIDTH",Toast.LENGTH_LONG).show();
                }

                else
                {clamMoldWidth=Integer.parseInt(moldWidth.getText().toString());}
                for(i=0;i<15;i++)
                {
                    if(clamMoldWidth<thicknesCheck[i])
                    {
                        break;
                    }
                }
                return clampingForceMold[i];
            }

        });
    }


    private int checkThicknes() {

        if(userThicknes.getText().toString().isEmpty()) {
        thicknessEntered=0;
        }
        else
        {
        thicknessEntered=Double.parseDouble(userThicknes.getText().toString());}
    if(thicknessEntered<2)
        return 400;
    if(thicknessEntered>=2 && thicknessEntered<=3)
        return 350;
    if(thicknessEntered>3)
        return 300;
        else
        return 0;
    }

    private double squareOfArea() {
        if(squareLength.getText().toString().isEmpty())
        {
         squarelen=0;
        }
        else
        {squarelen=Integer.parseInt(squareLength.getText().toString());}
        if(squareWidth.getText().toString().isEmpty())
        {
            squarewid=0;
        }
        else
        {squarewid=Integer.parseInt(squareWidth.getText().toString());}
         squareArea=squarelen*squarewid;
        if(squareArea>0)
            return squareArea;
            else
        return 0;
    }

    private double circularOfArea() {
        if(circularThicknes.getText().toString().isEmpty())
        {
         circularDiameter=0;
        }
        else
        {
        circularDiameter=Integer.parseInt(circularThicknes.getText().toString());
        }
        circularArea=((circularDiameter/2)*(circularDiameter/2)*3.14);
        if(circularArea>0)
            return circularArea;
        else
            return 0;
    }
}
