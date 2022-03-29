package com.example.noted.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.noted.database.DBHelper;
import com.example.noted.R;
import com.example.noted.model.User;

public class SignupActivity extends AppCompatActivity {

    //EditText email;
    EditText username, firstname, lastname, password, repassword;
    Button signup;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        username = findViewById(R.id.inputUsername);
        firstname = findViewById(R.id.inputFirstName);
        lastname = findViewById(R.id.inputLastName);
        //email = findViewById(R.id.inputEmail);
        password = findViewById(R.id.inputPassword);
        repassword = findViewById(R.id.inputPasswordConfirm);
        signup = findViewById(R.id.btnSignup);

        DB = new DBHelper(this);

        signup.setOnClickListener(view -> {
            String user = username.getText().toString();
            String pass = password.getText().toString();
            String repass = repassword.getText().toString();
            String fname = firstname.getText().toString();
            String lname = lastname.getText().toString();

            if (user.equals("") || pass.equals("") || repass.equals("") || fname.equals("")) {
                Toast.makeText(SignupActivity.this, "Input cannot be empty. Please input username or password correctly.", Toast.LENGTH_SHORT).show();
            } else {
                if (fname.matches(".*\\d.*") || lname.matches(".*\\d.*")) {
                    Toast.makeText(SignupActivity.this, "Name contains number! Please input alphabetical values.", Toast.LENGTH_SHORT).show();
                } else {
                    if (pass.length() > 7) {
                        if (pass.equals(repass)) {
                            Boolean checkuser = DB.CheckUsername(user);
                            if (checkuser == false) {
                                Boolean insert = DB.InsertData(user, pass, fname, lname);
                                if (insert == true) {
                                    DB.addUser(new User(user, pass, fname,lname));
                                    Toast.makeText(SignupActivity.this, "Successfully registered.", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(SignupActivity.this, "Registration failed.", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(SignupActivity.this, "User exists! Please sign in.", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(SignupActivity.this, "Password does not match! Please try again.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(SignupActivity.this, "Password has to be 8 characters minimum.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}