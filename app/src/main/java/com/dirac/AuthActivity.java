package com.dirac;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AuthActivity extends AppCompatActivity {

    Button btnAuthSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        btnAuthSignIn = (Button) findViewById(R.id.btn_auth_signin);

        btnAuthSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AuthActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

    public void signUp(View v) {
        Intent i = new Intent(AuthActivity.this, MainActivity.class);
        startActivity(i);
    }
}
