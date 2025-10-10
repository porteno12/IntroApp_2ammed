package com.example.introapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnCreate;
    TextView tvSignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnCreate = findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(this);

        tvSignIn = findViewById(R.id.tvSignIn);
        tvSignIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==btnCreate){
            Toast.makeText(this, "create!", Toast.LENGTH_SHORT).show();
        }

        if(v==tvSignIn){
            //explicit intentet - אינטנט מפורש
            Intent intent = new Intent(RegisterActivity.this,
                    LogInActivity.class);
            startActivity(intent);
        }
    }
}