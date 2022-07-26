package com.example.cardiacrecorder;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class db_class {
    private DatabaseReference databaseReference;
    public  db_class()
    {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(bp_record_pass.class.getSimpleName());
    }

    public Task<Void> add(bp_record_pass bpr)
    {
        //if (bpr == null) //throw exception
        return  databaseReference.push().setValue(bpr);
    }

}
