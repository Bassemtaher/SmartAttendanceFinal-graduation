package com.bassem.bassem.smartattendance;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bassem.bassem.smartattendance.Model.GetDataInterface;
import com.bassem.bassem.smartattendance.Model.Student;
import com.google.firebase.database.DataSnapshot;

public class ServiceLogin extends AppCompatActivity implements GetDataInterface {
    private TextView name,pass;
    private Button login;
    private ProgressDialog loading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_login);
        login=findViewById(R.id.serlogin);
        name=findViewById(R.id.serName);
        pass=findViewById(R.id.serPass);
        loading=new ProgressDialog(this);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(checkIfAllMandatoryFieldsEntered()) {

                    HelperMethods.getData(ServiceLogin.this, "Student", "please wait ", "loading");



                }
            }
        });

    }
    private boolean checkIfAllMandatoryFieldsEntered()
    {
        name.setError(null);
        pass.setError(null);

        String userName= name.getText().toString();
        String password=pass.getText().toString();
        boolean cancel=false;
        View focusView=null;

        if (TextUtils.isEmpty(userName)){
            name.setError("name is mandatory");
            focusView=name;
            cancel=true;

        }



        if (TextUtils.isEmpty(password))
        {
            pass.setError("password is mandatory");
            focusView=pass;
            cancel=true;

        }
        if(cancel)
        {
            focusView.requestFocus();
        }

        return !cancel;



    }




    @Override
    public void updateUI(DataSnapshot data) {
        Log.i("data","update ui : "+data.toString());


        boolean isExist=false;
        for (DataSnapshot currentChild:data.getChildren()){
            Student currentStudent= currentChild.getValue(Student.class);
            String x= currentStudent.getEmail().toString();
            String y=currentStudent.getPassword().toString();
            String z=currentStudent.getType();
            final String type="s";



            if (x.equalsIgnoreCase(name.getText().toString())
                    && y.equals(pass.getText().toString())
                    && z.equalsIgnoreCase(type.toString()))
            {

                isExist= true;
                break;


            }
        }


        if (isExist){
            Intent i= new Intent(ServiceLogin.this,studentService.class);
            startActivity(i);


        }

        if(isExist==false) {

            Toast.makeText(this, "wrong user name or password", Toast.LENGTH_SHORT).show();
        }
    }

}