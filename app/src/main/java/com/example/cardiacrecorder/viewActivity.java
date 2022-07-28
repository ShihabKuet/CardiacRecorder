package com.example.cardiacrecorder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class viewActivity extends AppCompatActivity {

    TextView dateview, timeview, sysview, diaview, pulseview, extview, posview, verview, comview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        dateview = findViewById(R.id.dateview);
        timeview = findViewById(R.id.timeview);
        sysview = findViewById(R.id.sysview);
        diaview = findViewById(R.id.diaview);
        pulseview = findViewById(R.id.pulseview);
        extview = findViewById(R.id.extview);
        posview = findViewById(R.id.posview);
        verview = findViewById(R.id.verview);
        comview = findViewById(R.id.comview);

        db_class dbClass = new db_class();
        bp_record_pass bp_edit = (bp_record_pass)getIntent().getSerializableExtra("VIEW");
            sysview.setText(bp_edit.getSys());
            diaview.setText(bp_edit.getDia());
            pulseview.setText(bp_edit.getPul());
            extview.setText(bp_edit.getExtrimity());
            posview.setText(bp_edit.getPosition());
            verview.setText(bp_edit.getVerdict());
            comview.setText(bp_edit.getComment());
            dateview.setText(bp_edit.getDate());
            timeview.setText(bp_edit.getTime());




    }
}