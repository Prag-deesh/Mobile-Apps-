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

import com.example.drafts.models.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    Button signUp;
    EditText name,email,password,phone;
    TextView signIn;

    FirebaseAuth fAuth;
    FirebaseDatabase fDatabase;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        signUp = findViewById(R.id.tosignup);
        name = findViewById(R.id.inputname);
        email = findViewById(R.id.inputemail);
        password = findViewById(R.id.inputpassword);
        phone = findViewById(R.id.inputphone);
        signIn = findViewById(R.id.toSignin);
        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);

        fAuth = FirebaseAuth.getInstance();
        fDatabase = FirebaseDatabase.getInstance();

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this , LoginActivity.class));
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                CreateUser();
                progressBar.setVisibility(View.VISIBLE);

            }
        });
    }

    private void CreateUser() {

        String userName = name.getText().toString();
        String userEmail = email.getText().toString();
        String userPassword = password.getText().toString();
        String userPhone = phone.getText().toString();

        if(TextUtils.isEmpty(userName)) {
            Toast.makeText(this, "Name is empty ", Toast.LENGTH_SHORT).show();
            return;
        }

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

        if(TextUtils.isEmpty(userPhone)) {
            Toast.makeText(this, "Phone is empty ", Toast.LENGTH_SHORT).show();
            return;
        }

        //create user
        fAuth.createUserWithEmailAndPassword(userEmail,userPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            UserModel userModel = new UserModel(userName,userEmail,userPassword,userPhone);
                            String id = task.getResult().getUser().getUid();
                            fDatabase.getReference().child("Users").child(id).setValue(userModel);

                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(RegisterActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(RegisterActivity.this, "Error : "+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}