package com.example.intelligentalert;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginButton = findViewById(R.id.login_btn);
        TextView registerLink = findViewById(R.id.register_link);

        EditText username = findViewById(R.id.username);

        Intent registerIntent = new Intent(this, Register.class);

        loginButton.setOnClickListener(v -> {
            if (TextUtils.isEmpty(username.getText().toString())) {
                username.setError("Username can not be empty. Please enter a valid CNIC.");
                return;
            }
            // Compile regular expression
            final Pattern pattern = Pattern.compile("\\d\\d\\d\\d\\d-\\d\\d\\d\\d\\d\\d\\d-\\d", Pattern.CASE_INSENSITIVE);
            // Match regex against input
            final Matcher matcher = pattern.matcher(username.getText().toString());
            // Use results...
            if(!matcher.matches()) {
                username.setError("Invalid format entered. Make sure that the CNIC number is entered in the format 12345-1234567-1");
                return;
            };
            Log.d("Login","Username worked!");
        });
        registerLink.setOnClickListener(v -> startActivity(registerIntent));
    }
}