package com.example.drafts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class adminlogin extends AppCompatActivity {

    Button adsignIn;
    EditText email, password;
    TextView userssign;

    FirebaseAuth fAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlogin);

        userssign = findViewById(R.id.usersign);

        adsignIn = findViewById(R.id.adlogin);
        email = findViewById(R.id.checkemail);
        password = findViewById(R.id.checkpassword);


        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);


        adsignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Loginad();
                progressBar.setVisibility(View.VISIBLE);
            }
        });

        //fr users
        userssign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent us = new Intent(adminlogin.this, LoginActivity.class);
                startActivity(us);
            }
        });
    }

    private void Loginad() {

        String adEmail = email.getText().toString();
        String adPassword = password.getText().toString();
        String ae = "admin";
        String ap = "adminpassword";

        if (TextUtils.isEmpty(adEmail)) {
            Toast.makeText(this, "Email is empty ", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(adPassword)) {
            Toast.makeText(this, "Password is empty ", Toast.LENGTH_SHORT).show();
            return;
        }
        if (adPassword.length() < 6) {
            Toast.makeText(this, "Password must be greater than 6 characters", Toast.LENGTH_SHORT).show();
            return;
        }

        if (adEmail.equals(ae)) {
            if (adPassword.equals(ap)) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(adminlogin.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(adminlogin.this, admincontrol.class));
            } else {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(adminlogin.this, "incorrect credintials", Toast.LENGTH_SHORT).show();
            }
        } else {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(adminlogin.this, "incorrect credintials", Toast.LENGTH_SHORT).show();
        }
    }

}