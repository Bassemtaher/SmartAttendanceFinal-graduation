package com.bassem.bassem.smartattendance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DrForm extends AppCompatActivity {
    Button gener,showing,count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dr_form);
        gener=findViewById(R.id.GenerateQr);
        showing=findViewById(R.id.showstudents);
        count=findViewById(R.id.Counter);
        gener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generate();
            }
        });
        showing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attend();
            }
        });
        count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counts();
            }
        });
    }

    public void generate()
    {
        Intent intent=new Intent(DrForm.this,QRGeneration.class);
        startActivity(intent);
        finish();
    }
    public void attend()
    {
        Intent intent=new Intent(DrForm.this,ShowallData.class);
        startActivity(intent);
        finish();
    }
    public void counts()
    {
        Intent intent=new Intent(DrForm.this,Counting.class);
        startActivity(intent);
        finish();
    }
}
