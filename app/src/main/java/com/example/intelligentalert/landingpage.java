package com.example.intelligentalert;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class landingpage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landingpage);

        Intent registerIntent = new Intent(this, Register.class);
        Button cusignupbuton = findViewById(R.id.cuSignup);
        cusignupbuton.setOnClickListener(v -> startActivity(registerIntent));

        Intent loginLink = new Intent(this, Login.class);
        Button cuLogin = findViewById(R.id.cuLogin);
        cuLogin.setOnClickListener(v -> startActivity(loginLink));

        Intent maregisterIntent = new Intent(this, managers_signup.class);
        Button masignupbuton = findViewById(R.id.maSignup);
        masignupbuton.setOnClickListener(v -> startActivity(maregisterIntent));

        Intent maloginLink = new Intent(this, managers_login.class);
        Button maLogin = findViewById(R.id.maLogin);
        maLogin.setOnClickListener(v -> startActivity(maloginLink));


    }

}