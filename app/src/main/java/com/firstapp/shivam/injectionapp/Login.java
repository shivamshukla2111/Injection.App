package com.firstapp.shivam.injectionapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    public static final String USER_NAME = "username";
    EditText username;
    String usernamev;
    EditText password;
    String passwordv;
    Button loginid;
    Button reset;
    int counter=1;
    String[] usernamea={"IL000001","IN001947","IN001186","IN001930","IN001089","IN019803","IN001988","IN001956","IN002166","IN001343","IN001122","IN023092","Admin","Ritesh","Sandeep"};
    String[] passworda={"TLN01","STI02","KTE03","PGE04","DJM05","SPL06","KTL07","PTS08","PTP09","UPL10","PPL11","SKL12","2016","1001","1002"};
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.editText19);
        password = (EditText) findViewById(R.id.editText20);
        username.requestFocus();
        loginid = (Button) findViewById(R.id.button9);
        reset = (Button) findViewById(R.id.button10);
        loginid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "ENTER USERNAME", Toast.LENGTH_SHORT).show();
                }else{
                usernamev=username.getText().toString().trim();
                    if(password.getText().toString().isEmpty())
                    {
                        Toast.makeText(getApplicationContext(), "ENTER PASSWORD", Toast.LENGTH_SHORT).show();
                    }else
                    {
                        passwordv=password.getText().toString().trim();
                        for(int i=0;i<15;i++){
                            counter=0;
                            if(usernamea[i].equals(usernamev)){
                                counter=counter+1;
                                if(passworda[i].equals(passwordv)){
                                    Intent k=new Intent(Login.this,MainActivity.class);
                                    k.putExtra(USER_NAME,usernamev);
                                    startActivity(k);
                                    break;
                                }else{
                                    Toast.makeText(getApplicationContext(), "INCORRECT PASSWORD", Toast.LENGTH_SHORT).show();
                                    break;
                                }

                            }

                        }
                        if(counter==0)
                        {Toast.makeText(getApplicationContext(), "INCORRECT USERID", Toast.LENGTH_SHORT).show();}
                    }}



            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username.setText("");
                password.setText("");
                Toast.makeText(getApplicationContext(), "ENTER DETAILS AGAIN", Toast.LENGTH_SHORT).show();
            }
        });
    }}