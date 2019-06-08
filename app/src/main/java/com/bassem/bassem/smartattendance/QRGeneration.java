package com.bassem.bassem.smartattendance;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class QRGeneration extends ShowallData {
    private Button button1;
    private ImageView imageView;
    private Calendar calendar= Calendar.getInstance();
    private String Date= DateFormat.getDateInstance().format(calendar.getTime());
    Spinner spinner;
    private String subject,code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_generation);


        imageView =findViewById(R.id.image);
        button1=findViewById(R.id.GenerB);
        spinner=findViewById(R.id.spin);



        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subject=spinner.getSelectedItem().toString();

                switch (subject)
                {
                    case "Security":
                        code = "securityAttend001";
                        break;

                    case "Neural":
                        code = "neuralAttend010";
                        break;


                    case "Theory":

                        code = "theoryAttend011";
                        break;
                }
                SimpleDateFormat ss=new SimpleDateFormat("dd-MM-yyyy");
                java.util.Date date=new Date();
                final String currentDate=ss.format(date);
                String text=code+currentDate;
                try{
                    MultiFormatWriter multiFormatWriter=new MultiFormatWriter();
                    BitMatrix bitMatrix=multiFormatWriter.encode(text, BarcodeFormat.QR_CODE,500,500);
                    BarcodeEncoder barcodeEncoder=new BarcodeEncoder();
                    Bitmap bitmap=barcodeEncoder.createBitmap(bitMatrix);
                    imageView.setImageBitmap(bitmap);



                }
                catch (WriterException e)
                {

                    e.printStackTrace();

                }
            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null){
            if(result.getContents()==null){
                Toast.makeText(this, "You cancelled the scanning", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(this, result.getContents(),Toast.LENGTH_LONG).show();
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }


    }

}

