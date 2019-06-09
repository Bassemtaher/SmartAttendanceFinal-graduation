package com.bassem.bassem.smartattendance;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bassem.bassem.smartattendance.Model.AddInterface;
import com.google.firebase.database.DatabaseError;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class scanActivity extends AppCompatActivity implements AddInterface {


    Button scan,show;
    ImageView imageView;
    Spinner spinner;
    String subject,code,subNo;
    private Calendar calendar= Calendar.getInstance();
    private String Date= DateFormat.getDateInstance().format(calendar.getTime());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        scan = findViewById(R.id.scB);
        show = findViewById(R.id.shB);
        spinner=findViewById(R.id.spinSubject);


        imageView = findViewById(R.id.imsc);



        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subject=spinner.getSelectedItem().toString();

                switch (subject)
                {
                    case "Security":
                        code = "securityAttend001";
                        subNo="0";
                        break;

                    case "Neural":
                        code = "neuralAttend010";
                        subNo="1";
                        break;



                    case "Theory":

                        code = "theoryAttend011";
                        subNo="2";
                        break;
                }


                IntentIntegrator integrator = new IntentIntegrator(scanActivity.this);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setCameraId(0);
                integrator.setOrientationLocked(false);
                integrator.setPrompt("scanning");
                integrator.setBeepEnabled(true);
                integrator.setBarcodeImageEnabled(true);
                integrator.initiateScan();



            }
        });
show.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        showstudent();
    }
});
    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null){
            if(result.getContents()==null){
                Toast.makeText(this, "You cancelled the scanning", Toast.LENGTH_LONG).show();
            }else {
                SimpleDateFormat ss = new SimpleDateFormat("dd-MM-yyyy");
                java.util.Date date = new Date();
                final String currentDate = ss.format(date);
                String text = code+currentDate;


                SharedPreferences currentSharedPreferences = getSharedPreferences("Mark", MODE_PRIVATE);
                String email = currentSharedPreferences.getString("email", "");
                String name = currentSharedPreferences.getString("name", "");
                HelperMethods.currentSubject.setEmail(name);
                final String id = HelperMethods.currentSubject.getEmail().replace(".", "");
                Log.i("dataa","on data change"+(text));

if(result.getContents().equals(text)) {
    HelperMethods.pushInFireBase("Subject",subNo, "date", "5-5-2019", "emails", HelperMethods.currentSubject, scanActivity.this, "plz wait", "loading", "0", "date", currentDate, "emails", id);
}

            }


        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }


    }


    public void showstudent()
    {
        Intent intent=new Intent(scanActivity.this,showStudent.class);
        startActivity(intent);
        finish();


    }

    @Override
    public void updateUI(DatabaseError databaseError) {

    }
}
