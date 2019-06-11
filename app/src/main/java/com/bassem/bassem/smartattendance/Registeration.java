package com.bassem.bassem.smartattendance;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bassem.bassem.smartattendance.Model.AddInterface;

import com.firebase.geofire.core.GeoHashQuery;
import com.github.kmenager.materialanimatedswitch.Utils;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;


public class Registeration extends Activity implements AddInterface {





    EditText name,email,password,phone;
    Button register;



    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);
        email = findViewById(R.id.etEmail);
        name = findViewById(R.id.etName);
        password = findViewById(R.id.etPassword);
        phone = findViewById(R.id.et1phone);
        register = findViewById(R.id.btnRegister);






        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
                    for (NetworkInterface nif : all) {
                        if (!nif.getName().equalsIgnoreCase("wlan0")) continue;

                        byte[] macBytes = nif.getHardwareAddress();
                        if (macBytes == null) {
                        }

                        StringBuilder res1 = new StringBuilder();
                        for (byte b : macBytes) {
                            // res1.append(Integer.toHexString(b & 0xFF) + ":");
                            res1.append(String.format("%02X:",b));
                            HelperMethods.currentUser.setMacAddress(res1.toString());
                        }

                        if (res1.length() > 0) {
                            res1.deleteCharAt(res1.length() - 1);
                        }
                        res1.toString();
                    }
                } catch (Exception ex) {
                    //handle exception
                }

                if (checkIfAllMandatoryFieldsEntered()) {
                    String a=name.getText().toString();
                    String b=phone.getText().toString();
                    String c=password.getText().toString();
                    String d=email.getText().toString();
                    HelperMethods.currentUser.setName(a);
                    HelperMethods.currentUser.setPhone(b);
                    HelperMethods.currentUser.setPassword(c);
                    HelperMethods.currentUser.setEmail(d);
                    String id = HelperMethods.currentUser.getEmail().replace(".","");;
                    HelperMethods.pushInFireBase("Student", HelperMethods.currentUser, Registeration.this, "loading", "plz wait", id);
                    SharedPreferences.Editor editor = getSharedPreferences("Mark", MODE_PRIVATE).edit();
                    editor.putString("email", HelperMethods.currentUser.getEmail());
                    editor.putString("name",HelperMethods.currentUser.getName());
                    editor.apply();








                }


            }
        });
    }






    private boolean checkIfAllMandatoryFieldsEntered()
    {
        email.setError(null);
        phone.setError(null);
        name.setError(null);
        phone.setError(null);

        String userName= email.getText().toString();
        String number= phone.getText().toString();
        String pass=password.getText().toString();
        String nam=name.getText().toString();
        boolean cancel=false;
        View focusView=null;

        if (TextUtils.isEmpty(userName)){
            email.setError("Email Is Mandatory");
            focusView=email;
            cancel=true;

        }

        if(TextUtils.isEmpty(number))
        {
            phone.setError("Phone Number Is Mandatory");
            focusView=phone;
            cancel=true;
        }

        if (TextUtils.isEmpty(pass))
        {
            password.setError("Password Is Mandatory");
            focusView=password;
            cancel=true;

        }
        if (TextUtils.isEmpty(nam))
        {
            name.setError("Name Is Mandatory");
            focusView=name;
            cancel=true;

        }
        if(cancel)
        {
            focusView.requestFocus();
        }

        return !cancel;



    }

    @Override
    public void updateUI(DatabaseError databaseError)
    {
        if(databaseError==null){

            Toast.makeText(this, "registered", Toast.LENGTH_SHORT).show();
            Intent i=new Intent(Registeration.this,scanActivity.class);
            startActivity(i);
            finish();
        }
        else
        {
            Toast.makeText(this, "error in saving", Toast.LENGTH_SHORT).show();

        }

    }
}

