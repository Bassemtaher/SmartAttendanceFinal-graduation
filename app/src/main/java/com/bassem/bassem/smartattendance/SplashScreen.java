package com.bassem.bassem.smartattendance;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;

import com.bassem.bassem.smartattendance.Model.Student;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class SplashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder().
                setDefaultFontPath("fonts/Arkhip_font.ttf").setFontAttrId(R.attr.fontPath).build());
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash_screen);
        Thread myThread=new Thread(){
            @Override
            public void run() {
                try {
                    sleep(3000);
                    SharedPreferences currentSharedPreferences = getSharedPreferences("Mark", MODE_PRIVATE);
                    String email = currentSharedPreferences.getString("email", "");
                    if (email.equals("")) {
                        Intent mainIntent = new Intent(SplashScreen.this, MainActivity.class);
                        startActivity(mainIntent);
                        finish();

                    } else {
                        Intent intent = new Intent(SplashScreen.this, scanActivity.class);
                        HelperMethods.currentUser = new Student();
                        HelperMethods.currentUser.setEmail(email);
                        startActivity(intent);
                        finish();

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();


    }
}
