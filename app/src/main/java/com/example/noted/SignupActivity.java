package com.example.noted;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
                if (pass.equals(repass)) {
                    Boolean checkuser = DB.CheckUsername(user);
                    if (checkuser == false) {
                        Boolean insert = DB.InsertData(user, pass, fname, lname);
                        if (insert == true) {
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
            }
        });
    }
}