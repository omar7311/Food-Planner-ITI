package com.example.food_planner_iti;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        firebaseAuth=FirebaseAuth.getInstance();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(firebaseAuth.getCurrentUser()==null){
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    finish();
                }else {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                }

            }
        },1000);

    }
}