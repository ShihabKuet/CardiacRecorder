package com.example.cardiacrecorder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class addRecord extends AppCompatActivity {

    TextView  time_text,date_text;
    Spinner ex_spin, pos_spin;
    String[] extrimity ={"Right Arm","Left Arm","Right Calf","Left Calf","Right Thigh","Left Thigh"};
    String[] position ={"Seated","Lying","Standing"};

    TextView timetxt, datetxt, sys, dia, pultxt ,cmntxt ;
   // Spinner get_pos , get_extr;
    Button addbtn;
    String verdict;
    int systol = 0;
    int diastole=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        time_text=findViewById(R.id.timetxt);
        date_text=findViewById(R.id.datetxt);

        //Current Time and Date add
        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("hh:mm a");
        String time = simpleDateFormat.format(calendar.getTime());
        time_text.setText(time);
        SimpleDateFormat simpleDateFormat2=new SimpleDateFormat("dd-MMM-yy");
        String date = simpleDateFormat2.format(calendar.getTime());
        date_text.setText(date);



    /*
    ex_spin variable
    extrimity value showing dropdown list
     */
        ex_spin = findViewById(R.id.extrimity_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(addRecord.this,R.layout.selected_dropdown_item,extrimity);
        adapter.setDropDownViewResource(R.layout.dropped_item);
        ex_spin.setAdapter(adapter);
        ex_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String value=adapterView.getItemAtPosition(i).toString();
                //Toast.makeText(addRecord.this,value, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    /*
    pos_spin variable
    position value showing dropdown list
     */

        pos_spin = findViewById(R.id.position_spinner);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(addRecord.this,R.layout.selected_dropdown_item,position);
        adapter1.setDropDownViewResource(R.layout.dropped_item);
        pos_spin.setAdapter(adapter1);
        pos_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String value=adapterView.getItemAtPosition(i).toString();
                //Toast.makeText(addRecord.this,value, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        datetxt = findViewById(R.id.datetxt);
        timetxt = findViewById(R.id.timetxt);
        sys =findViewById(R.id.sys);
        dia =findViewById(R.id.dia);
        pultxt =findViewById(R.id.pul);
        cmntxt = findViewById(R.id.cmmnt);
        //get_extr =(Spinner) findViewById(R.id.extrimity_spinner);
       // get_pos = (Spinner) findViewById(R.id.position_spinner);
        addbtn = findViewById(R.id.addbtn);



       addbtn.setOnClickListener(v->
       {

           systol = Integer.valueOf(sys.getText().toString().trim());
           diastole = Integer.valueOf(dia.getText().toString().trim());
           String puls = pultxt.getText().toString().trim();
           String ext = ex_spin.getSelectedItem().toString();
           String pos = pos_spin.getSelectedItem().toString();
           String systolic = sys.getText().toString().trim();
           String diastolic = dia.getText().toString().trim();
           String comment = cmntxt.getText().toString().trim();


           if(systol>=90 && systol<=140 && diastole>=60 && diastole<=90)
           {
               verdict = "Normal";
           }
           else if(systol<90 && diastole<60){
               verdict = "Hypotension";
           }

           db_class dbClass = new db_class();
           bp_record_pass bpRecordPass = new bp_record_pass(systolic,diastolic,puls,ext,pos,date_text.getText().toString(),time_text.getText().toString(),comment,verdict);
            dbClass.add(bpRecordPass).addOnSuccessListener(suc->
                    {
                       Toast.makeText(this,"Insertion successful",Toast.LENGTH_SHORT).show();

                    }).addOnFailureListener(er->
            {
                Toast.makeText(this,""+er.getMessage(),Toast.LENGTH_SHORT).show();

            });
       });


    }
}