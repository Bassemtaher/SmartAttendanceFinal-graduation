package com.bassem.bassem.smartattendance;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bassem.bassem.smartattendance.Model.AddInterface;
import com.bassem.bassem.smartattendance.Model.GetDataInterface;
import com.bassem.bassem.smartattendance.Model.Student;
import com.bassem.bassem.smartattendance.Model.Subject;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class showStudent extends AppCompatActivity implements GetDataInterface {
TextView bassem;
Button show;
String r;
EditText textday,textmonth,textyear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_student);
        bassem=findViewById(R.id.showDate);
        show=findViewById(R.id.Show);
        textday=findViewById(R.id.textDay);
        textmonth=findViewById(R.id.textMonth);
        textyear=findViewById(R.id.textYear);




        SharedPreferences currentSharedPreferences = getSharedPreferences("Mark", MODE_PRIVATE);
        String email = currentSharedPreferences.getString("email", "");
        String name= currentSharedPreferences.getString("name","");
        HelperMethods.currentSubject.setEmail(email);
        final String id = HelperMethods.currentSubject.getEmail().replace(".","");

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String d,m,y;
                d=textday.getText().toString();
                m=textmonth.getText().toString();
                y=textyear.getText().toString();

                    String dates=d+"-"+m+"-"+y;


                        SharedPreferences currentSharedPreferences = getSharedPreferences("Mark", MODE_PRIVATE);
                        String email = currentSharedPreferences.getString("email", "");
                        String name = currentSharedPreferences.getString("name", "");
                        HelperMethods.currentSubject.setEmail(name);

                        HelperMethods.getData(showStudent.this, "Subject", "0", "date", dates, "emails", "plz wait", "Loading");


                        Toast.makeText(showStudent.this, dates, Toast.LENGTH_SHORT).show();


            }
        });

    }




    @Override
    public void updateUI(DataSnapshot data) {
        Log.i("data","update ui : "+data.toString());


        boolean isExist=false;
        for (DataSnapshot currentChild:data.getChildren()){
            Subject currentSubject= currentChild.getValue(Subject.class);
            String x= currentSubject.getEmail().toString();

            SharedPreferences currentSharedPreferences = getSharedPreferences("Mark", MODE_PRIVATE);
            String email = currentSharedPreferences.getString("email", "");
            String name = currentSharedPreferences.getString("name", "");



            if (x.equalsIgnoreCase(name))
            {

                isExist= true;
                break;


            }
        }


        if (isExist==true){
            Toast.makeText(this, "you are signed as attendant", Toast.LENGTH_SHORT).show();


        }

        if(isExist==false) {

            Toast.makeText(this, "you did not attend this lecture", Toast.LENGTH_SHORT).show();
        }

    }
}
