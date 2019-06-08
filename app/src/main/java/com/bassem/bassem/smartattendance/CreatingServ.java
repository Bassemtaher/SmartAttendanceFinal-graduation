package com.bassem.bassem.smartattendance;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bassem.bassem.smartattendance.Model.AddInterface;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreatingServ extends AppCompatActivity implements AddInterface{

    EditText name,email,password,phone;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creating_serv);
        email = findViewById(R.id.Emailserv);
        name = findViewById(R.id.Nameserv);
        password = findViewById(R.id.Passwordserv);
        phone = findViewById(R.id.phoneserv);
        register = findViewById(R.id.Registerserv);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkIfAllMandatoryFieldsEntered()) {
                    String a=name.getText().toString();
                    String b=phone.getText().toString();
                    String c=password.getText().toString();
                    String d=email.getText().toString();
                    String x="s";
                    HelperMethods.currentUser.setName(a);
                    HelperMethods.currentUser.setPhone(b);
                    HelperMethods.currentUser.setPassword(c);
                    HelperMethods.currentUser.setEmail(d);
                    HelperMethods.currentUser.setType(x);
                    String id = HelperMethods.currentUser.getEmail().replace(".","");;
                    HelperMethods.pushInFireBase("Student", HelperMethods.currentUser, CreatingServ.this, "loading", "plz wait", id);









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
            Intent i=new Intent(CreatingServ.this,studentService.class);
            startActivity(i);
        }
        else
        {
            Toast.makeText(this, "error in saving", Toast.LENGTH_SHORT).show();

        }

    }
}
