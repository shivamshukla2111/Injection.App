package com.firstapp.shivam.injectionapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.round;

/**
 * Created by SHIVAM on 29-10-2016.
 */
public class Report extends AppCompatActivity {
    TextView injpressure5p;
    Button emailscreenshot;
    TextView injpressure4p;
    TextView injpressure3p;
    TextView injpressure2p;
    TextView injpressure1p;
    TextView injspeed5p;
    TextView injspeed4p;
    TextView injspeed3p;
    TextView injspeed2p;
    TextView injspeed1p;
    TextView screwposition4p;
    TextView screwposition3p;
    TextView screwposition2p;
    TextView screwposition1p;
    TextView holdingpressure3p;
    TextView holdingpressure2p;
    TextView holdingpressure1p;
    TextView holdingspeed3p;
    TextView holdingspeed2p;
    TextView holdingspeed1p;
    TextView holdingtime3p;
    TextView holdingtime2p;
    TextView holdingtime1p;
    TextView casetype;
    EditText transferpo;
    EditText optspeed;
    EditText percent;
    double percentv;
    double transferpov;
    double optspeedv;
    int injpressurev;
    int injspeedv;
    int screwpositionv;
    int holdingpressurev;
    int holdingspeedv;
    int holdingtimev;
    String resintypev;
    int totrunnerweightv;
    double mcfvalue;
    int innerdiameterv;
    double screwposition4pv;
    double screwposition3pv;
    double screwposition2pv;
    double screwposition1pv;
    double r31;
    int totpartweightv;
    String body;
    private int machinetonnage;
    private String partname;
    private int noofcavities;
    String body1;
    String mailbody;
    private String usernamev;
    private String subject;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        Bundle extras = getIntent().getExtras();
        usernamev=extras.getString(Login.USER_NAME);
        injpressurev = extras.getInt("injpressure");
        injspeedv = extras.getInt("injspeed");
        screwpositionv = extras.getInt("screwposition");
        holdingpressurev = extras.getInt("holdingpressure");
        holdingspeedv = extras.getInt("holdingspeed");
        holdingtimev = extras.getInt("holdingtime");
        resintypev = extras.getString("resintype");
        totrunnerweightv = extras.getInt("runnerweigth");
        mcfvalue = extras.getDouble("mcfv");
        innerdiameterv = extras.getInt("innerdiameter");
        totpartweightv = extras.getInt("partweight");
        machinetonnage=extras.getInt(MainActivity.MACHINE_TONNAGE);
        partname=extras.getString(MeteringLength.PART_NAME);
        noofcavities=extras.getInt(MeteringLength.NO_CAVITIES);
        casetype = (TextView) findViewById(R.id.textView47);
        injpressure5p = (TextView) findViewById(R.id.textView75);
        injpressure4p = (TextView) findViewById(R.id.textView66);
        injpressure3p = (TextView) findViewById(R.id.textView56);
        injpressure2p = (TextView) findViewById(R.id.textView57);
        injpressure1p = (TextView) findViewById(R.id.textView58);
        transferpo= (EditText) findViewById(R.id.transferposition);
        transferpo.requestFocus();
        optspeed= (EditText) findViewById(R.id.optspeed);
        percent= (EditText) findViewById(R.id.percent);
        injspeed5p = (TextView) findViewById(R.id.textView76);
        injspeed4p = (TextView) findViewById(R.id.textView77);
        injspeed3p = (TextView) findViewById(R.id.textView59);
        injspeed2p = (TextView) findViewById(R.id.textView60);
        injspeed1p = (TextView) findViewById(R.id.textView61);
        screwposition4p = (TextView) findViewById(R.id.textView78);
        screwposition3p = (TextView) findViewById(R.id.textView62);
        screwposition2p = (TextView) findViewById(R.id.textView63);
        screwposition1p = (TextView) findViewById(R.id.textView64);
        holdingpressure3p = (TextView) findViewById(R.id.textView67);
        holdingpressure2p = (TextView) findViewById(R.id.textView68);
        holdingpressure1p = (TextView) findViewById(R.id.textView69);
        holdingspeed3p = (TextView) findViewById(R.id.textView70);
        holdingspeed2p = (TextView) findViewById(R.id.textView71);
        holdingspeed1p = (TextView) findViewById(R.id.textView72);
        holdingtime3p = (TextView) findViewById(R.id.textView73);
        holdingtime2p = (TextView) findViewById(R.id.textView74);
        holdingtime1p = (TextView) findViewById(R.id.textView65);
        emailscreenshot = (Button) findViewById(R.id.button8);
        emailscreenshot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendEmail();

            }
        });
        if (resintypev != "GPPS" && totrunnerweightv > 0) {
            compwithrunner();
        }
        if (resintypev == "GPPS" && totrunnerweightv == 0) {

            gppswithoutrunner();
        }
        if (resintypev != "GPPS" && totrunnerweightv == 0) {
            partswithoutrunner();
        }
        transferpo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (resintypev != "GPPS" && totrunnerweightv > 0) {
                    screwposition1();
                }
                if (resintypev == "GPPS" && totrunnerweightv == 0) {
                    screwposition2();
                }
                if (resintypev != "GPPS" && totrunnerweightv == 0) {
                    screwposition3();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        optspeed.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (resintypev != "GPPS" && totrunnerweightv > 0) {
                    screwposition1();
                }
                if (resintypev == "GPPS" && totrunnerweightv == 0) {
                    screwposition2();
                }
                if (resintypev != "GPPS" && totrunnerweightv == 0) {
                    screwposition3();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        percent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (resintypev != "GPPS" && totrunnerweightv > 0) {
                    screwposition1();
                }
                if (resintypev == "GPPS" && totrunnerweightv == 0) {
                    screwposition2();
                }
                if (resintypev != "GPPS" && totrunnerweightv == 0) {
                    screwposition3();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    private void compwithrunner() {
        casetype.setText("REPORT 1");
        injpressure5p.setText(String.valueOf(round(.49 * injpressurev)));
        injpressure4p.setText(String.valueOf(round(.49 * injpressurev)));
        injpressure3p.setText(String.valueOf(injpressurev));
        injpressure2p.setText(String.valueOf(round(.4 * injpressurev)));
        injpressure1p.setText(String.valueOf(round(.7 * injpressurev)));
        injspeed5p.setText(String.valueOf(round(.75 * injspeedv)));
        injspeed4p.setText(String.valueOf(round((round(.75 * injspeedv) + injspeedv) * .5)));
        injspeed3p.setText(String.valueOf(injspeedv));
        injspeed2p.setText(String.valueOf(round(.4 * injspeedv)));
        injspeed1p.setText(String.valueOf(round(.7 * injspeedv)));
        screwposition1();
        holdingpressure3p.setText(String.valueOf(round(0.75 * holdingpressurev)));
        holdingpressure2p.setText(String.valueOf(holdingpressurev));
        holdingpressure1p.setText(String.valueOf(round(0.7 * holdingpressurev)));
        holdingspeed3p.setText(String.valueOf(round(0.75 * holdingspeedv)));
        holdingspeed2p.setText(String.valueOf(holdingspeedv));
        holdingspeed1p.setText(String.valueOf(round(0.7 * holdingspeedv)));
        holdingtime3p.setText(String.valueOf(.5));
        holdingtime2p.setText(String.valueOf(holdingtimev));
        holdingtime1p.setText(String.valueOf(.5));
        body="PART NAME: "+partname+"\n"+"CAVITIES: "+String.valueOf(noofcavities)+"\n"+"PART WEIGHT: "+String.valueOf(totpartweightv)+"\n"+
                "RUNNER WEIGHT: "+String.valueOf(totrunnerweightv)+"\n"+"SHOT WEIGHT: "+String.valueOf(totrunnerweightv+totpartweightv)
                +"\n"+"RESIN USED: "+String.valueOf(resintypev)+"\n"+"MACHINE TONNAGE: "+String.valueOf(machinetonnage)+"\n"
                +"RUNNER WEIGHT: "+String.valueOf(totrunnerweightv)+"\n"+"\n"+
                "INJ PRESSURE: "+String.valueOf(round(.49 * injpressurev))+"  "+String.valueOf(round(.49 * injpressurev))+"  "+String.valueOf(round(injpressurev))+"  "+String.valueOf(round(.4 * injpressurev))+"  "+String.valueOf(round(.7 * injpressurev))+"\n"+
                "INJ SPEED: "+String.valueOf(round(.75 * injspeedv))+"  "+String.valueOf(round((round(.75 * injspeedv) + injspeedv) * .5))+"  "+String.valueOf(round(injspeedv))+"  "+String.valueOf(round(.4 * injspeedv))+"  "+String.valueOf(round(.7 * injspeedv))+"\n"+
                "HOLD PRESSURE:"+" "+String.valueOf(round(.75 * holdingpressurev))+"   "+String.valueOf(holdingpressurev)+"   "+String.valueOf(round(0.7*holdingpressurev))+"\n"+
                "HOLD SPEED:"+" "+String.valueOf(round(.75 * holdingspeedv))+"   "+String.valueOf(holdingspeedv)+"   "+String.valueOf(round(0.7*holdingspeedv))+"\n"+
                "HOLD TIME:"+" "+String.valueOf(.5)+"   "+String.valueOf(holdingtimev)+"   "+String.valueOf(.5)+"\n";


    }


    private void gppswithoutrunner() {
        casetype.setText("REPORT 2");
        injpressure5p.setText(" ");
        injpressure4p.setText(" ");
        injpressure3p.setText(String.valueOf(round(.75 * injpressurev)));
        injpressure2p.setText(String.valueOf(round(.8 * injpressurev)));
        injpressure1p.setText(String.valueOf(injpressurev));
        injspeed5p.setText(" ");
        injspeed4p.setText(" ");
        injspeed3p.setText(String.valueOf(round(.75 * injspeedv)));
        injspeed2p.setText(String.valueOf(round(.8 * injspeedv)));
        injspeed1p.setText(String.valueOf(injspeedv));
        screwposition2();
        holdingpressure3p.setText(String.valueOf(round(0.75 * holdingpressurev)));
        holdingpressure2p.setText(String.valueOf(holdingpressurev));
        holdingpressure1p.setText(String.valueOf(round(0.7 * holdingpressurev)));
        holdingspeed3p.setText(String.valueOf(round(0.75 * holdingspeedv)));
        holdingspeed2p.setText(String.valueOf(holdingspeedv));
        holdingspeed1p.setText(String.valueOf(round(0.7 * holdingspeedv)));
        holdingtime3p.setText(String.valueOf(.5));
        holdingtime2p.setText(String.valueOf(holdingtimev));
        holdingtime1p.setText(String.valueOf(.5));
        body="PART NAME: "+partname+"\n"+"CAVITIES: "+String.valueOf(noofcavities)+"\n"+"PART WEIGHT: "+String.valueOf(totpartweightv)+"\n"+
                "RUNNER WEIGHT: "+String.valueOf(totrunnerweightv)+"\n"+"SHOT WEIGHT: "+String.valueOf(totrunnerweightv+totpartweightv)
                +"\n"+"RESIN USED: "+String.valueOf(resintypev)+"\n"+"MACHINE TONNAGE: "+String.valueOf(machinetonnage)+"\n"
                +"RUNNER WEIGHT: "+String.valueOf(totrunnerweightv)+"\n"+"\n"+
                "INJ PRESSURE:"+" "+String.valueOf(round(.75*injpressurev))+"  "+String.valueOf(round(.8 * injpressurev))+"  "+String.valueOf(round(injpressurev))+"\n"+
                "INJ SPEED:"+" "+String.valueOf(round(.75*injspeedv))+"  "+String.valueOf(round(.8 * injspeedv))+"  "+String.valueOf(round(injspeedv))+"\n"+
                "HOLD PRESSURE:"+" "+String.valueOf(round(.75 * holdingpressurev))+"   "+String.valueOf(holdingpressurev)+"   "+String.valueOf(round(0.7*holdingpressurev))+"\n"+
                "HOLD SPEED:"+" "+String.valueOf(round(.75 * holdingspeedv))+"   "+String.valueOf(holdingspeedv)+"   "+String.valueOf(round(0.7*holdingspeedv))+"\n"+
                "HOLD TIME:"+" "+String.valueOf(.5)+"   "+String.valueOf(holdingtimev)+"   "+String.valueOf(.5)+"\n";

    }

    private void partswithoutrunner() {
        casetype.setText("REPORT 3");
        injpressure5p.setText(" ");
        injpressure4p.setText(" ");
        injpressure3p.setText(String.valueOf(round(.75 * injpressurev)));
        injpressure2p.setText(String.valueOf(injpressurev));
        injpressure1p.setText(String.valueOf(round(.7 * injpressurev)));
        injspeed5p.setText(" ");
        injspeed4p.setText(" ");
        injspeed3p.setText(String.valueOf(round(.75 * injspeedv)));
        injspeed2p.setText(String.valueOf(injspeedv));
        injspeed1p.setText(String.valueOf(round(.7 * injspeedv)));
        screwposition3();
        holdingpressure3p.setText(String.valueOf(round(0.75 * holdingpressurev)));
        holdingpressure2p.setText(String.valueOf(holdingpressurev));
        holdingpressure1p.setText(String.valueOf(round(0.7 * holdingpressurev)));
        holdingspeed3p.setText(String.valueOf(round(0.75 * holdingspeedv)));
        holdingspeed2p.setText(String.valueOf(holdingspeedv));
        holdingspeed1p.setText(String.valueOf(round(0.75 * holdingspeedv)));
        holdingtime3p.setText(String.valueOf(.5));
        holdingtime2p.setText(String.valueOf(holdingtimev));
        holdingtime1p.setText(String.valueOf(.5));
        body="PART NAME: "+partname+"\n"+"CAVITIES: "+String.valueOf(noofcavities)+"\n"+"PART WEIGHT: "+String.valueOf(totpartweightv)+"\n"+
                "RUNNER WEIGHT: "+String.valueOf(totrunnerweightv)+"\n"+"SHOT WEIGHT: "+String.valueOf(totrunnerweightv+totpartweightv)
                +"\n"+"RESIN USED: "+String.valueOf(resintypev)+"\n"+"MACHINE TONNAGE: "+String.valueOf(machinetonnage)+"\n"
                +"RUNNER WEIGHT: "+String.valueOf(totrunnerweightv)+"\n"+"\n"+
                "INJ PRESSURE:"+" "+String.valueOf(round(.75*injpressurev))+"  "+String.valueOf(round(injpressurev))+"  "+String.valueOf(round(.7*injpressurev))+"\n"+
                "INJ SPEED:"+" "+String.valueOf(round(.75*injspeedv))+"  "+String.valueOf(round(injspeedv))+"  "+String.valueOf(round(.7*injspeedv))+"\n"+
                "HOLD PRESSURE:"+" "+String.valueOf(round(.75 * holdingpressurev))+"   "+String.valueOf(holdingpressurev)+"   "+String.valueOf(round(0.7*holdingpressurev))+"\n"+
                "HOLD SPEED:"+" "+String.valueOf(round(.75 * holdingspeedv))+"   "+String.valueOf(holdingspeedv)+"   "+String.valueOf(round(0.75*holdingspeedv))+"\n"+
                "HOLD TIME:"+" "+String.valueOf(.5)+"   "+String.valueOf(holdingtimev)+"   "+String.valueOf(.5)+"\n";

    }

    private void screwposition1() {
        if(percent.getText().toString().isEmpty())
        {
            percentv=0;
        }else{
        percentv=(Double.parseDouble(percent.getText().toString().trim()))/100;
        }

        if(transferpo.getText().toString().isEmpty()){
            transferpov=0;
        }else
        {
        transferpov=Double.parseDouble(transferpo.getText().toString().trim())/100;}
        if(optspeed.getText().toString().isEmpty()) {
            optspeedv = 0;
        }
        else{
        optspeedv=Double.parseDouble(optspeed.getText().toString().trim())/100;}
        r31 = (1 * 3.14 * (innerdiameterv * .5) * (innerdiameterv * .5) * mcfvalue) / 1000;
        screwposition1pv = screwpositionv - (totrunnerweightv / r31);
        screwposition2pv = screwposition1pv - ((totpartweightv * percentv) / (3.14 * innerdiameterv * innerdiameterv * mcfvalue * .00025));
        screwposition3pv = screwposition2pv - ((totpartweightv * 4000 * optspeedv) / (3.14 * innerdiameterv * innerdiameterv * mcfvalue));
        screwposition4pv = screwposition3pv - ((totpartweightv * (transferpov-(optspeedv+percentv))) / ((3.14 * innerdiameterv * innerdiameterv * mcfvalue)/4000));
        screwposition4p.setText(String.valueOf(round(screwposition4pv)));
        screwposition3p.setText(String.valueOf(round(screwposition3pv)));
        screwposition2p.setText(String.valueOf(round(screwposition2pv)));
        screwposition1p.setText(String.valueOf(round(screwposition1pv)));
        body1="SCREW POS:"+" "+String.valueOf(round(screwposition4pv))+"   "+String.valueOf(round(screwposition3pv))+"   "+String.valueOf(round(screwposition2pv))+"   "+String.valueOf(round(screwposition1pv));
    }

    private void screwposition2() {
        if(transferpo.getText().toString().isEmpty()){
            transferpov=0;
        }else
        {
            transferpov=Double.parseDouble(transferpo.getText().toString().trim())/100;}
        if(optspeed.getText().toString().isEmpty()) {
            optspeedv = 0;
        }
        else{
            optspeedv=Double.parseDouble(optspeed.getText().toString().trim())/100;}
        percent.setVisibility(View.GONE);
        screwposition4p.setText(" ");
        screwposition3p.setText(" ");
        screwposition1pv = screwpositionv - ((totpartweightv * optspeedv) / ((3.14 * innerdiameterv * innerdiameterv * mcfvalue) / 4000));
        screwposition2pv = screwpositionv - ((totpartweightv * transferpov) / ((3.14 * innerdiameterv * innerdiameterv * mcfvalue) / 4000));
        screwposition2p.setText(String.valueOf(round(screwposition2pv)));
        screwposition1p.setText(String.valueOf(round(screwposition1pv)));
        body1="SCREW POS:"+" "+String.valueOf(round(screwposition2pv))+"   "+String.valueOf(round(screwposition1pv));
    }

    private void screwposition3() {

        if(percent.getText().toString().isEmpty())
        {
            percentv=0;
        }else{
            percentv=Double.parseDouble(percent.getText().toString().trim())/100;}
        if(transferpo.getText().toString().isEmpty()){
            transferpov=0;
        }else
        {
            transferpov=Double.parseDouble(transferpo.getText().toString().trim())/100;}
        if(optspeed.getText().toString().isEmpty()) {
            optspeedv = 0;
        }
        else{
        optspeedv=Double.parseDouble(optspeed.getText().toString().trim())/100;}
        screwposition4p.setText(" ");
        screwposition1pv = screwpositionv - (((totpartweightv + totrunnerweightv) * percentv) / ((3.14 * innerdiameterv * .5 * innerdiameterv * .5 * mcfvalue) / 1000));
        screwposition2pv = screwposition1pv - (((totpartweightv + totrunnerweightv) * optspeedv) / ((3.14 * innerdiameterv * .5 * innerdiameterv * .5 * mcfvalue) / 1000));
        screwposition3pv = screwposition2pv - (((totpartweightv + totrunnerweightv) * (transferpov-(percentv+optspeedv))) / ((3.14 * innerdiameterv * .5 * innerdiameterv * .5 * mcfvalue) / 1000));
        screwposition3p.setText(String.valueOf(round(screwposition3pv)));
        screwposition2p.setText(String.valueOf(round(screwposition2pv)));
        screwposition1p.setText(String.valueOf(round(screwposition1pv)));
        body1="SCREW POS:"+" "+String.valueOf(round(screwposition3pv))+"   "+String.valueOf(round(screwposition2pv))+"   "+String.valueOf(round(screwposition1pv));
    }
    protected void sendEmail() {
        subject="IN00198"+"("+usernamev+")"+","+partname;
        Log.i("Send email", "");
        String[] TO = {"gritesh15@gmail.com"};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.setPackage("com.google.android.gm");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        mailbody=body+body1;
        emailIntent.putExtra(Intent.EXTRA_TEXT,mailbody);
        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));

        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(Report.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
    }



