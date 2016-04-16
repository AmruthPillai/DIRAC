package com.dirac;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

public class AuthActivity extends AppCompatActivity {

    String textEmail, textPassword;

    Button btnAuthSignIn;
    EditText editEmail, editPassword;

    Firebase fireDIRAC;
    Firebase.AuthStateListener authStateListener;
    Firebase.AuthResultHandler authResultHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        editEmail = (EditText) findViewById(R.id.edit_auth_email);
        editPassword = (EditText) findViewById(R.id.edit_auth_password);

        fireDIRAC = new Firebase(getResources().getString(R.string.firebase_url));

        authStateListener = new Firebase.AuthStateListener() {
            @Override
            public void onAuthStateChanged(AuthData authData) {
                if (authData != null) {
                    Intent i = new Intent(AuthActivity.this, MainActivity.class);
                    startActivity(i);
                }
            }
        };

        authResultHandler = new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                Intent i = new Intent(AuthActivity.this, MainActivity.class);
                startActivity(i);
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                Toast.makeText(AuthActivity.this, "Login Error", Toast.LENGTH_SHORT)
                        .show();
            }
        };

        btnAuthSignIn = (Button) findViewById(R.id.btn_auth_signin);

        btnAuthSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textEmail = editEmail.getText().toString();
                textPassword = editPassword.getText().toString();
                fireDIRAC.authWithPassword(textEmail, textPassword, authResultHandler);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        fireDIRAC.removeAuthStateListener(authStateListener);
    }

    public void signUp(View v) {
        Intent i = new Intent(AuthActivity.this, SignUpActivity.class);
        startActivity(i);
    }
}
