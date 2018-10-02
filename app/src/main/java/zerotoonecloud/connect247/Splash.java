package com.example.shubhangi.connect247;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash extends AppCompatActivity {
    private static int SPLASH_TIME_OUT=3000;
    SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                prefs= PreferenceManager.getDefaultSharedPreferences(Splash.this);
                if(!(prefs.contains("sessionToken")&&prefs.contains("userId"))) {
                    Intent i = new Intent(Splash.this, LoginActivity.class);
                    startActivity(i);
                }
                else{
                    //Start home screen if prefs contains user_name or email when user is already logged in
                }
                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}

