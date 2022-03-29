package com.example.noted.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.noted.R;
import com.example.noted.adapter.NoteAdapter;
import com.example.noted.database.DBHelper;
import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    Button logout;
    FloatingActionButton fabAdd;
    String username;
    ListView listView;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fabAdd = findViewById(R.id.fabAdd);
        logout = findViewById(R.id.btnLogout);
        listView = findViewById(R.id.listViewNotes);

        DB = new DBHelper(this);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            NoteAdapter adapter = new NoteAdapter(DB.getAllNotes("asdf"));
            listView.setAdapter(adapter);
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences settings = getSharedPreferences("PREFS_NAME", 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putBoolean("isChecked", false);
                editor.commit();

                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        fabAdd.setOnClickListener((View view) -> {
            Intent intent2 = new Intent(getApplicationContext(), NoteMain.class);
            intent2.putExtra("username", username);
            startActivity(intent2);
        });
    }
}