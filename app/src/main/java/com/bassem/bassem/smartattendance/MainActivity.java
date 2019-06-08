package com.bassem.bassem.smartattendance;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;





public class MainActivity extends AppCompatActivity {
    Button btnStud,btnDRs,btnServi;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        btnStud=findViewById(R.id.btnStudent);
        btnDRs=findViewById(R.id.btnDoctor);
        btnServi=findViewById(R.id.btnServ);







        btnStud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register();

            }
        });
        btnDRs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login();
            }
        });
        btnServi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scan();
            }
        });

    }


    public void Register(){

        Intent intent=new Intent(MainActivity.this,Registeration.class);
        startActivity(intent);
        finish();

    }
    public void Login(){

        Intent intent=new Intent(MainActivity.this,DrLogin  .class);
        startActivity(intent);
        finish();

    }
    public void scan()
    {
        Intent intent=new Intent(MainActivity.this,ServiceLogin.class);
        startActivity(intent);
        finish();


    }


}