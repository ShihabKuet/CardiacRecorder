package com.example.cardiacrecorder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class showHistory extends AppCompatActivity {

    /**this activity shows record
     * in a recyclerview
     * the records are fetched from firebase
     **/

    RecyclerView recyclerView;
    DatabaseReference database;
    recviewAdapter recviewadapter;
    ArrayList<bp_record_pass> list;
    db_class dbc;
    String key = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_history);

        recyclerView = findViewById(R.id.recview);
        database = FirebaseDatabase.getInstance().getReference("bp_record_pass");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recviewadapter = new recviewAdapter(this);
        recyclerView.setAdapter(recviewadapter);
        dbc = new db_class();

        loadData();

    }

    private  void loadData(){
        dbc.get().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<bp_record_pass> bprs = new ArrayList<>();
                for(DataSnapshot data : snapshot.getChildren()){
                    bp_record_pass bpr = data.getValue(bp_record_pass.class);
                    bpr.setKey(data.getKey());
                    bprs.add(bpr);
                    key = data.getKey();
                }
                recviewadapter.setItems(bprs);
                recviewadapter.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }




}