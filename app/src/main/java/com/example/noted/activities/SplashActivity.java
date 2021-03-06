package com.example.noted.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.noted.R;
import com.example.noted.database.DBHelper;
import com.example.noted.model.Note;

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

        //DBHelper db = new DBHelper(this);
        //db.addNote(new Note(0, "title", "content", "asdf"));

    }
}
