package com.example.noted.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.room.Query;

import com.example.noted.model.Note;
import com.example.noted.model.User;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "Login.db";

    //user
    public static final String TABLE_USERS = "users";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String FIRST_NAME = "firstname";
    public static final String LAST_NAME = "lastname";

    //note
    public static final String TABLE_NOTES = "notes";
    public static final String NOTE_ID = "id";
    public static final String NOTE_TITLE = "title";
    public static final String NOTE_CONTENT = "content";
    public static final String NOTE_CREATOR = "username";

    public DBHelper(Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL("create Table users(USERNAME TEXT primary key, password TEXT, firstname TEXT, lastname TEXT)");

        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "(" + USERNAME + " TEXT PRIMARY KEY," + PASSWORD + " TEXT," + FIRST_NAME + " TEXT," + LAST_NAME + " TEXT" + ")";
        db.execSQL(CREATE_USERS_TABLE);

        String CREATE_NOTES_TABLE = "CREATE TABLE " + TABLE_NOTES + "(" + NOTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + NOTE_TITLE + " TEXT," + NOTE_CONTENT + " TEXT," + NOTE_CREATOR + " TEXT, FOREIGN KEY (" + NOTE_CREATOR + ") REFERENCES " + TABLE_USERS + " (" + USERNAME + "))";
        db.execSQL(CREATE_NOTES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists " + TABLE_USERS);
        db.execSQL("drop Table if exists " + TABLE_NOTES);
        onCreate(db);
    }

    public Boolean InsertData(String username, String password, String firstname, String lastname) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        //contentValues.put("email", email);
        contentValues.put(USERNAME, username);
        contentValues.put(PASSWORD, password);
        contentValues.put(FIRST_NAME, firstname);
        contentValues.put(LAST_NAME, lastname);

        long result = MyDB.insert(TABLE_USERS, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

//    public Boolean CheckEmail(String email) {
//        SQLiteDatabase MyDB = this.getWritableDatabase();
//        Cursor cursor = MyDB.rawQuery("select * from users where email = ?", new String[]{email});
//        if (cursor.getCount() > 0) { //if user exists
//            return true;
//        } else {
//            return false;
//        }
//    }

    public Boolean CheckUsername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from users where username = ?", new String[]{username});
        if (cursor.getCount() > 0) { //if user exists
            return true;
        } else {
            return false;
        }
    }

    public Boolean CheckUsernamePassword(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from users where username = ? and password = ?", new String[]{username, password});
        if (cursor.getCount() > 0) { //if user exists
            return true;
        } else {
            return false;
        }
    }

    public void addUser(User user) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USERNAME, user.getUsername());
        values.put(FIRST_NAME, user.getFirstname());
        values.put(LAST_NAME, user.getLastname());
        values.put(PASSWORD, user.getPassword());
    }

    public void addNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NOTE_TITLE, note.getNotetitle());
        values.put(NOTE_CONTENT, note.getNotecontent());
        values.put(NOTE_ID, note.getNoteid());
        values.put(USERNAME, note.getUsername());

        db.insert(TABLE_NOTES, null, values);
        db.close();
    }

    public List<Note> getAllNotes(String username) {
        List<Note> noteList = new ArrayList<Note>();
        // Select All Query
//        String selectQuery = "SELECT  * FROM " + TABLE_NOTES + " WHERE " + USERNAME + " = " + username;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from notes where username = ?", new String[]{username});

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Note note = new Note();
                note.setNoteid(Integer.parseInt(cursor.getString(0)));
                note.setNotetitle(cursor.getString(1));
                note.setNotecontent(cursor.getString(2));
                note.setUsername(cursor.getString(3));
                noteList.add(note);
            } while (cursor.moveToNext());
        }
        return noteList;
    }

}
