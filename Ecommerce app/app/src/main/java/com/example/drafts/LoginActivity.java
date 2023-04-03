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

public class LoginActivity extends AppCompatActivity {

    Button signIn;
    EditText email,password;
    TextView signUp,admin;

    FirebaseAuth fAuth;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        admin = findViewById(R.id.adminsignin);

        signIn = findViewById(R.id.userlogin);
        email = findViewById(R.id.checkemail);
        password = findViewById(R.id.checkpassword);
        signUp = findViewById(R.id.toSignUp);
        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this , RegisterActivity.class));
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginUser();
                progressBar.setVisibility(View.VISIBLE);
            }
        });

        //fro admin
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ad = new Intent(LoginActivity.this , adminlogin.class);
                startActivity(ad);
            }
        });
    }

    private void LoginUser() {

        String userEmail = email.getText().toString();
        String userPassword = password.getText().toString();


        if(TextUtils.isEmpty(userEmail)) {
            Toast.makeText(this, "Email is empty ", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(userPassword)) {
            Toast.makeText(this, "Password is empty ", Toast.LENGTH_SHORT).show();
            return;
        }
        if(userPassword.length() < 6) {
            Toast.makeText(this, "Password must be greater than 6 characters", Toast.LENGTH_SHORT).show();
            return;
        }

        //login user
        fAuth.signInWithEmailAndPassword(userEmail,userPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(LoginActivity.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this , MainActivity.class));
                        }
                        else{
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(LoginActivity.this, "Error : "+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });



    }
}