package com.example.yo7a.healthwatcher;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RespirationResult extends AppCompatActivity {

    private String user, Date;
    int RR;
    DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    Date today = Calendar.getInstance().getTime();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_respiration_result);

        Date = df.format(today);
        TextView RRR = this.findViewById(R.id.RRR);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            RR = bundle.getInt("bpm");
            user = bundle.getString("Usr");
            RRR.setText(String.valueOf(RR));
        }


    }

    @Override
    public void onBackPressed() {

        Intent i = new Intent(RespirationResult.this, Primary.class);
        i.putExtra("Usr", user);
        startActivity(i);
        finish();
        super.onBackPressed();

    }
}
