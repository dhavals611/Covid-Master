package com.example.yo7a.healthwatcher;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Primary extends AppCompatActivity {

    private String user;
    private int p;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primary);


        ImageButton HeartRate = this.findViewById(R.id.HR);

        ImageButton RRate = this.findViewById(R.id.RR);

        Button track = this.findViewById(R.id.track);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            user = extras.getString("Usr");
            //The key argument here must match that used in the other activity
        }


        //Every Test Button sends the username + the test number, to go to the wanted test after the instructions activity
        HeartRate.setOnClickListener(v -> {
            databaseReference = FirebaseDatabase.getInstance().getReference("/");
            databaseReference.setValue("dhavals611");
            p = 1;
            Intent i = new Intent(v.getContext(), StartVitalSigns.class);
            i.putExtra("Usr", user);
            i.putExtra("Page", p);
            startActivity(i);
            finish();
        });

        RRate.setOnClickListener(v -> {
            p = 3;
            Intent i = new Intent(v.getContext(), StartVitalSigns.class);
            i.putExtra("Usr", user);
            i.putExtra("Page", p);
            startActivity(i);
            finish();
        });

        track.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(),Covid_Symtoms_Tracker.class);
            startActivity(intent);
        });

    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, (arg0, arg1) -> {

                    Primary.super.onBackPressed();
                    finish();
                    System.exit(0);
                }).create().show();
    }


}

