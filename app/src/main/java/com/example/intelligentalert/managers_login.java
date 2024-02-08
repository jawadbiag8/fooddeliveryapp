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

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class managers_login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managers_login);

        Button loginButton = findViewById(R.id.login_btn);
        TextView registerLink = findViewById(R.id.register_link);

        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);

        Intent registerIntent = new Intent(this, managers_signup.class);
        Intent homeIntent = new Intent(this, MainActivity.class);
        DBHandler dbHandler = new DBHandler(managers_login.this);

        loginButton.setOnClickListener(v -> {
            if (TextUtils.isEmpty(username.getText().toString())) {
                username.setError("Username can not be empty. Please enter a valid CNIC.");
                return;
            }
            if (TextUtils.isEmpty(password.getText().toString())) {
                username.setError("Password can not be empty. Please enter a valid password.");
                return;
            }
            // Compile regular expression
            final Pattern pattern = Pattern.compile("\\d\\d\\d\\d\\d-\\d\\d\\d\\d\\d\\d\\d-\\d", Pattern.CASE_INSENSITIVE);
            // Match regex against input
            final Matcher matcher = pattern.matcher(username.getText().toString());
            // Use results...
            if (!matcher.matches()) {
                username.setError("Invalid format entered. Make sure that the CNIC number is entered in the format 12345-1234567-1");
                return;
            }
            ;
            String users = dbHandler.readUsers(username.getText().toString());
            String data[] = users.split(",");
            if (data[5].equals(password.getText().toString())&&data[3].equals(username.getText().toString())&& !data[7].equals("customer")) {
                homeIntent.putExtra("username", data[1]);
                homeIntent.putExtra("type", data[7]);
                startActivity(homeIntent);
            }
            Log.d("Login", "Username worked!");
        });
        registerLink.setOnClickListener(v -> startActivity(registerIntent));
    }
}