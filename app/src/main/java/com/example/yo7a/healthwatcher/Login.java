package com.example.yo7a.healthwatcher;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.regex.Pattern;

public class Login extends AppCompatActivity {

    public ImageButton Log;
    public EditText ed1, ed2, ed3, ed4, ed5, ed6, ed7, ed8;
    private Toast mainToast;
    public Spinner GenderSpin;
    public String m1 = "Male";
    public String m2 = "Female";
    public String nameStr, weightStr, heightStr, ageStr, passStr, usrStr, usrStrlow, passConStr, emailStr;
    private int age, weight, height;
    UserDB Data = new UserDB(this);
    UserDB check = new UserDB(this);
    int c, y = 0;
    int check1 = 0;

    //Camera Permission
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

            }
        }
    }

    //Password Pattern
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Checking for camera
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
        }

        Log = findViewById(R.id.Login);
        ed3 = findViewById(R.id.edtn);
        ed5 = findViewById(R.id.edtu);
        ed6 = findViewById(R.id.edtp);
        ed7 = findViewById(R.id.edtpc);



        Log.setOnClickListener(v -> {

            check1 = 0;
            nameStr = ed3.getText().toString();
            usrStrlow = ed5.getText().toString();
            passStr = ed6.getText().toString();
            passConStr = ed7.getText().toString();
            usrStr = usrStrlow.toLowerCase();


            //Email Validation


            //Username Validation
            if (usrStr.isEmpty()) {
                ed5.setError("Field can't be empty");
                check1 = 1;
            } else if (usrStr.length() > 15) {
                ed5.setError("Username too long");
                check1 = 1;
            } else {
                ed5.setError(null);
                check1 = 0;
            }
            c = check.checkUser(usrStr); //will check if username exists will return 0 otherwise it will be 1
            if (c == y) {
                check1 = 1;
                mainToast = Toast.makeText(getApplicationContext(), "Username already exist", Toast.LENGTH_SHORT);
                mainToast.show();
            }

            //Password Validation
            if (passStr.isEmpty()) {
                check1 = 1;
                ed6.setError("Field can't be empty");
            } else if (!PASSWORD_PATTERN.matcher(passStr).matches()) {
                check1 = 1;
                ed6.setError("Password too weak: must contain\nUppercase characters (A-Z)\n" +
                        "Lowercase characters (a-z)\n" +
                        "Digits (0-9)\n" +
                        "Special characters (~!@#$%&*_:;'.?/)");
            } else {
                check1 = 0;
                ed6.setError(null);
            }
            if (!(passStr.equals(passConStr))) {
                check1 = 1;
                mainToast = Toast.makeText(getApplicationContext(), "Password don't match !", Toast.LENGTH_SHORT);
                mainToast.show();
            }
            //Checking other Inputs
             else if (check1 == 0) {

                check1 = 0;

                user per = new user();
                per.setUsername(usrStr);
                per.setname(nameStr);

                per.setPass(passStr);

                Data.addUser(per);
                Intent i = new Intent(v.getContext(), First.class);
                mainToast = Toast.makeText(getApplicationContext(), "Your account has been created", Toast.LENGTH_SHORT);
                mainToast.show();
                startActivity(i);
                finish();

            }

        });
    }
}

