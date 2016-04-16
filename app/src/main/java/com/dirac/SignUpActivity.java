package com.dirac;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    String textName, textEmail, textPassword, textLocation;
    EditText editName, editEmail, editPassword, editLocation;
    Button btnSignUp;

    Firebase fireDIRAC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editName = (EditText) findViewById(R.id.edit_signup_name);
        editEmail = (EditText) findViewById(R.id.edit_signup_email);
        editPassword = (EditText) findViewById(R.id.edit_signup_password);
        editLocation = (EditText) findViewById(R.id.edit_signup_location);

        btnSignUp = (Button) findViewById(R.id.btn_signup);

        fireDIRAC = new Firebase(getResources().getString(R.string.firebase_url));

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textName = editName.getText().toString();
                textEmail = editEmail.getText().toString();
                textPassword = editPassword.getText().toString();
                textLocation = editLocation.getText().toString();

                fireDIRAC.createUser(textEmail, textPassword, new Firebase.ValueResultHandler<Map<String, Object>>() {
                    @Override
                    public void onSuccess(Map<String, Object> result) {
                        Toast.makeText(SignUpActivity.this, "Successfully created user account with UID: " + result.get("uid"), Toast.LENGTH_SHORT)
                                .show();
                    }

                    @Override
                    public void onError(FirebaseError firebaseError) {
                        Toast.makeText(SignUpActivity.this, "Some unknown error occurred!", Toast.LENGTH_SHORT)
                                .show();
                    }
                });
            }
        });
    }
}
