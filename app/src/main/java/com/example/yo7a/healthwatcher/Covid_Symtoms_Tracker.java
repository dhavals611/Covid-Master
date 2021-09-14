package com.example.yo7a.healthwatcher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Covid_Symtoms_Tracker extends AppCompatActivity {
    Covid_Symtoms covid_symtoms;
    EditText Heart_rate;
    EditText RR_rate;
    EditText fever;
    EditText cough;
    int heart_rate,rr_rate,Fever;
    String Cough;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_symtoms_tracker);
        Heart_rate = findViewById(R.id.edtu);
        RR_rate = findViewById(R.id.edtn);
        fever = findViewById(R.id.edtp);
        cough = findViewById(R.id.edtc);
        covid_symtoms = new Covid_Symtoms();
        Heart_rate.setText(String.valueOf(0));
        RR_rate.setText(String.valueOf(0));
        fever.setText(String.valueOf(0));
        cough.setText("None");
        DatabaseReference databaseReference;
        Button Track = findViewById(R.id.track);
        databaseReference = FirebaseDatabase.getInstance().getReference("/dhavals611");

        Track.setOnClickListener(view -> {

            covid_symtoms.setCough(String.valueOf(cough.getText()));
            covid_symtoms.setHeart_rate(Integer.parseInt(String.valueOf(Heart_rate.getText())));
            covid_symtoms.setRR_Rate(Integer.parseInt(String.valueOf(RR_rate.getText())));
            covid_symtoms.setFever(Integer.parseInt(String.valueOf(fever.getText())));
            databaseReference.setValue(covid_symtoms);
        });
    }
}