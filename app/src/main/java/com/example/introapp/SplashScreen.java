package com.example.introapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.introapp.databinding.ActivityMainBinding;
import com.example.introapp.databinding.ActivitySplashScreenBinding;

public class SplashScreen extends AppCompatActivity {
    private ActivitySplashScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Animation animation = AnimationUtils.loadAnimation(SplashScreen.this, R.anim.intro_animation);
        binding.logo.startAnimation(animation);


        Thread splashThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000); // 3 שניות
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // חזרה ל-UI Thread כדי לבצע מעבר מסכים
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(SplashScreen.this, LogInActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        finish();
                    }
                });
            }
        });

        splashThread.start();

    }
}