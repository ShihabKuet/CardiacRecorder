package com.example.cardiacrecorder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class home_2 extends AppCompatActivity {
    ImageView addRecord,showHistory;
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);



        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        addRecord=findViewById(R.id.addRecord);
        showHistory=findViewById(R.id.showHistory);


        /* making image clickable to jump to record adding */
        addRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(home_2.this, addRecord.class);
                startActivity(intent);
            }
        });

        /* making image clickable to jump to show bp recorded */
        showHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(home_2.this, showHistory.class);
                startActivity(intent);

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sidemenu,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       switch (item.getItemId()){
           case R.id.logout:
               mAuth.signOut();
               signOutUser();
               return true;

           default:
               return super.onOptionsItemSelected(item);
       }

    }

    private void signOutUser() {
        Intent intent = new Intent(home_2.this, Login.class);
        startActivity(intent);
        finish();
    }

}