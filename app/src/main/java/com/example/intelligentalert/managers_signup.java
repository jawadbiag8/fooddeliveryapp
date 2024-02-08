package com.example.intelligentalert;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class managers_signup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managers_signup);
        Intent login = new Intent(this, managers_login.class);

        DBHandler dbHandler = new DBHandler(managers_signup.this);

        EditText name = findViewById(R.id.reg_name);
        EditText age = findViewById(R.id.reg_age);
        EditText cnic = findViewById(R.id.reg_cnic);
        EditText contact = findViewById(R.id.reg_contact);
        EditText address = findViewById(R.id.reg_address);

        EditText password = findViewById(R.id.reg_password);
        EditText confirmPassword = findViewById(R.id.reg_confirm_password);

        TextView loginLink = findViewById(R.id.login_link);
        Button registerBtn = findViewById(R.id.register_btn);

        registerBtn.setOnClickListener(v -> {
            ArrayList<String> users = dbHandler.readUsers();
            //  Empty Validations
            if (TextUtils.isEmpty(name.getText().toString())) {
                name.setError("Please provide a name.");
                return;
            }
            if (TextUtils.isEmpty(age.getText().toString())) {
                age.setError("Please provide your age.");
                return;
            }
            if (TextUtils.isEmpty(cnic.getText().toString())) {
                cnic.setError("CNIC can not be empty. Please enter a valid CNIC.");
                return;
            }
            if (TextUtils.isEmpty(contact.getText().toString())) {
                contact.setError("Please provide your contact number.");
                return;
            }
            if (TextUtils.isEmpty(address.getText().toString())) {
                address.setError("Please provide your address.");
                return;
            }
            if (TextUtils.isEmpty(password.getText().toString())) {
                password.setError("Please provide your password.");
                return;
            }
            if (TextUtils.isEmpty(confirmPassword.getText().toString())) {
                confirmPassword.setError("Please re-type the password you wish to set.");
                return;
            }
            // Compile regular expression
            final Pattern pattern = Pattern.compile("\\d\\d\\d\\d\\d-\\d\\d\\d\\d\\d\\d\\d-\\d", Pattern.CASE_INSENSITIVE);
            // Match regex against input
            final Matcher matcher = pattern.matcher(cnic.getText().toString());
            // Use results...
            if(!matcher.matches()) {
                cnic.setError("Invalid format entered. Make sure that the CNIC number is entered in the format 12345-1234567-1");
                return;
            };

            if (users.contains(cnic.getText().toString())) {
                cnic.setError("User with the specified CNIC already exists. Please use the login screen to log in with this CNIC number.");
                return;
            }

            if (!password.getText().toString().equals(confirmPassword.getText().toString())) {
                password.setError("Password and Confirm Password do not match");
                return;
            }

            dbHandler.addNewUser(name.getText().toString(), age.getText().toString(), cnic.getText().toString(), contact.getText().toString(), password.getText().toString(), password.getText().toString(), address.getText().toString(),"manager");
            Log.d("Login", "User added successfully");
            startActivity(login);

        });

        loginLink.setOnClickListener(v -> startActivity(login));
    }
}