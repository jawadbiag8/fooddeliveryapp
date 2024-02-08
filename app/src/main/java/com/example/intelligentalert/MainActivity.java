package com.example.intelligentalert;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        String value = intent.getStringExtra("username");
        String type = intent.getStringExtra("type");
        TextView textView = findViewById(R.id.textView);
        textView.setText("welcome "+type+"("+value+")");
        Button logoutButton = findViewById(R.id.logout);

        logoutButton.setOnClickListener(v -> finish());

    }
}