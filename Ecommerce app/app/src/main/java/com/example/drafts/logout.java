package com.example.drafts;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class logout {

    protected void onCreate(Bundle savedInstanceState) {

        FirebaseAuth fAuth;

        fAuth = FirebaseAuth.getInstance();

        fAuth.signOut();

    }
}
