package com.example.intelligentalert;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginButton = findViewById(R.id.login_btn);
        TextView registerLink = findViewById(R.id.register_link);

        Intent registerIntent = new Intent(this, Register.class);

        loginButton.setOnClickListener(v -> Log.d("Login Clicked", "Login Button was clicked"));
        registerLink.setOnClickListener(v -> startActivity(registerIntent));
    }
}