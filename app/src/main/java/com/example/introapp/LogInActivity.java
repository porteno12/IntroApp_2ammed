package com.example.introapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.introapp.databinding.ActivityLogInBinding;
import com.google.android.material.snackbar.Snackbar;

public class LogInActivity extends AppCompatActivity {
    ActivityLogInBinding binding;
    //todo - add sharedpreference (remember me checkbpx near the text-forgot)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLogInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LogInActivity.this, "hello user\nyour password is:" +
                        Utils.ADMIN_PASSWORD + "!!!", Toast.LENGTH_LONG).show();
            }
        });
        binding.tvGoToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogInActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        binding.btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.etLoginEmail.getText().toString();
                String password = binding.etLoginPassword.getText().toString();
                if (email.equals(Utils.ADMIN_EMAIL) && password.equals(Utils.ADMIN_PASSWORD))
                    startActivity(new Intent(LogInActivity.this, MainActivity.class));
                else if (email.trim().length() == 0 || password.trim().length() == 0)
                    Snackbar.make(v, "please enter all required data!", Snackbar.LENGTH_LONG).show();
                else
                    Snackbar.make(v,
                            "incorrect data!",
                            Snackbar.LENGTH_LONG).setAction("reset",
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    binding.etLoginEmail.setText("");
                                    binding.etLoginPassword.setText("");
                                }
                            }).show();

            }
        });
    }
}