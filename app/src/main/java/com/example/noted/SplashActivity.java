package com.example.noted;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                startActivity(
                        new Intent(SplashActivity.this, LoginActivity.class));
            }
        };

        Timer timer = new Timer();
        timer.schedule(timerTask,3000);

    }
}
