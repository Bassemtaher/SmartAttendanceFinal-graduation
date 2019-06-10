package com.bassem.bassem.smartattendance;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bassem.bassem.smartattendance.Model.GetDataInterface;
import com.bassem.bassem.smartattendance.Model.Subject;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

public class ShowallData extends AppCompatActivity implements GetDataInterface {
GridView gridView;
    TextView bassem;
    Button show;
    String r;
    EditText textday,textmonth,textyear;
    Spinner spinner;
    String subject,subNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjects);
        gridView=findViewById(R.id.gridview);
        show=findViewById(R.id.Showing);
        textday=findViewById(R.id.Day);
        textmonth=findViewById(R.id.Month);
        textyear=findViewById(R.id.Year);
        spinner=findViewById(R.id.spinAllData);

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

                String d,m,y;
                d=textday.getText().toString();
                m=textmonth.getText().toString();
                y=textyear.getText().toString();
                String dates=d+"-"+m+"-"+y;


                HelperMethods.getData(ShowallData.this,"Subject",subNo,"date",dates,"emails","please wait","Loading");

            }
        });




    }

    @Override
    public void updateUI(DataSnapshot data) {


        Log.i("data", "update ui : " + data.toString());
HelperMethods.subjectArrayList.clear();
        ArrayList<String> subjectEmails=new ArrayList<>();
        for (DataSnapshot currentChild : data.getChildren()) {
            Subject currentSubject = currentChild.getValue(Subject.class);
            subjectEmails.add(currentSubject.getEmail());

            HelperMethods.subjectArrayList.add(currentSubject);



        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, subjectEmails);
        gridView.setAdapter(adapter);


    }
}