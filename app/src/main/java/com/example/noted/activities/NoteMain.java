package com.example.noted.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.noted.database.DBHelper;
import com.example.noted.databinding.ActivityNoteMainBinding;
import com.example.noted.model.Note;
import com.example.noted.model.User;

import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NoteMain extends AppCompatActivity {

    ActivityNoteMainBinding binding;
    EditText editTextTitle;
    EditText editTextContent;
    ImageView imgViewSpeechToText;
    DBHelper db;

    int noteid;
    String notetitle;
    String notecontent;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityNoteMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        setContentView(view);

        editTextTitle = binding.editTxtTitle;
        editTextContent = binding.editTxtContent;
        imgViewSpeechToText = binding.imgViewSpeechToText;



        db = new DBHelper(this);

//        onBackPressed(db.addNote(new Note(noteid, notetitle, notecontent, username)));

        imgViewSpeechToText.setOnClickListener(view1 -> {
//            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
//            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
//            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
//            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Start speaking...");
//
//            try {
//                startActivityForResult(intent, 10);
//            } catch (ActivityNotFoundException ex) {
//                Toast.makeText(NoteMain.this, "Your device Does Not Support Speech Input", Toast.LENGTH_SHORT).show();
//                ex.printStackTrace();
//            }
            Intent getintent = getIntent();
            username = getintent.getStringExtra("username");

            notetitle = editTextTitle.getText().toString();
            notecontent = editTextContent.getText().toString();
            noteid = 1;
            db.addNote(new Note(noteid, notetitle, notecontent, username));

        });
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 10) {
//            if (requestCode == RESULT_OK && data != null) {
//                editTextContent.setText(data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS).get(0));
//            }
//        }
//    }

//    public void addNote(int noteid, String notetitle, String notecontent, String username) {
//        noteid = 0;
//        notetitle = editTextTitle.getText().toString();
//        notecontent = editTextContent.getText().toString();
//        db = new DBHelper(this);
//        db.addNote(new Note(noteid, notetitle, notecontent, username));
//    }
}
