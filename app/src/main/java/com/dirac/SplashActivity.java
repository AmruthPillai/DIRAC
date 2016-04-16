package com.dirac;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.firebase.client.Firebase;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Firebase.setAndroidContext(this);

        int splashTimeOut = 2000;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, AuthActivity.class);
                startActivity(i);

                finish();
            }
        }, splashTimeOut);
    }
}
