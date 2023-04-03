package com.example.drafts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class admincontrol extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admincontrol);

        Button addpro;
        addpro = findViewById(R.id.addproducts);

        addpro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addpr = new Intent(admincontrol.this , productadd.class);
                startActivity(addpr);
            }
        });

    }
}