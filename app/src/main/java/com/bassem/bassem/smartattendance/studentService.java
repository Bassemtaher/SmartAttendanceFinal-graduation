package com.bassem.bassem.smartattendance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class studentService extends AppCompatActivity {
Button sh,cr,crserv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_service);
        sh=findViewById(R.id.showst);
        cr=findViewById(R.id.creatingdrs);
        crserv=findViewById(R.id.creatingserv);

        sh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show();
            }
        });
        cr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                create();
            }
        });
        crserv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createserv();
            }
        });
    }
    public void show()
    {
        Intent intent=new Intent(studentService.this,ShowallData.class);
        startActivity(intent);
        finish();

    }
    public void create()
    {
        Intent intent=new Intent(studentService.this,CreateDrs.class);
        startActivity(intent);
        finish();


    }
    public void createserv()
    {
        Intent intent=new Intent(studentService.this,CreatingServ.class);
        startActivity(intent);
        finish();


    }
}
