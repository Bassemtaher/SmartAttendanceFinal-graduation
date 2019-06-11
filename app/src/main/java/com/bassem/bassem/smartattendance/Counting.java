package com.bassem.bassem.smartattendance;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bassem.bassem.smartattendance.Model.GetDataInterface;
import com.bassem.bassem.smartattendance.Model.Subject;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.GenericArrayType;

public class Counting extends AppCompatActivity   {
    Button show;
    String r;
    EditText textday, textmonth, textyear;
    TextView textView;
    private static DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    Spinner spinner;
    String subject,subNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counting);
        show = findViewById(R.id.Showno);
        textday = findViewById(R.id.Day1);
        textmonth = findViewById(R.id.Month1);
        textyear = findViewById(R.id.Year1);
        textView=findViewById(R.id.theno);
        spinner=findViewById(R.id.spinCounting);




        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                subject=spinner.getSelectedItem().toString();

                switch (subject)
                {
                    case "Security":
                        subNo="0";
                        break;

                    case "Neural":
                        subNo="1";
                        break;



                    case "Theory":

                        subNo="2";
                        break;
                }
                String d, m, y;
                d = textday.getText().toString();
                if(Integer.parseInt(d)<10){d="0"+d;}
                m = textmonth.getText().toString();
                if(Integer.parseInt(m)<10) {m="0"+m;}
                y = textyear.getText().toString();
                String dates = d + "-" + m + "-" + y;
                mDatabase = FirebaseDatabase.getInstance().getReference();
                mDatabase.child("Subject").child(subNo).child("date").child(dates).child("emails").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        long count=dataSnapshot.getChildrenCount();
                        textView.setText(Long.toString(count));

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });







                Toast.makeText(Counting.this, dates, Toast.LENGTH_SHORT).show();


            }
        });

    }



}
