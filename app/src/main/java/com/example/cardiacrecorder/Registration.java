package com.example.cardiacrecorder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;


public class Registration extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    EditText inputname, inputemail, inputpass, inputpass2;
    TextView hvaccnt;
    ProgressBar progressBar;
    Button reg;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_registration);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        hvaccnt = findViewById(R.id.haveacc);
        inputname = findViewById(R.id.inputname);
        inputemail = findViewById(R.id.inputemail);
        inputpass = findViewById(R.id.inputpass);
        inputpass2 = findViewById(R.id.inputpass2);
        progressBar = findViewById(R.id.progressbar);
        reg = findViewById(R.id.regbtn);


        /*
         * use last logged in account
         * */
        if (mUser != null) {
            sendUserToNextActivity();
            finish();
        }


        hvaccnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Registration.this, Login.class));
            }
        });


        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PerforAuth();
            }
        });

    }

    /*
     * PerforAuth() method
     * used for authentication and send value to firebase
     * */

    private void PerforAuth() {
        String fullname = inputname.getText().toString();
        String email = inputemail.getText().toString();
        String password = inputpass.getText().toString();
        String confirmpassword = inputpass2.getText().toString();

        if (!email.matches(emailPattern)) {
            inputemail.setError("Enter correct email");
            inputemail.requestFocus();
            return;
        }
        if (fullname.isEmpty()) {
            inputname.setError("Enter proper password");
            inputname.requestFocus();
            return;
        }
        if (password.isEmpty() || password.length() < 6) {
            inputpass.setError("Enter proper password");
            inputpass.requestFocus();
            return;
        }
        if (!password.equals(confirmpassword)) {
            inputpass2.setError("Both password doesn't match");
            inputpass2.requestFocus();
            return;
        }


        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(Registration.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                    sendUserToNextActivity();
                } else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(Registration.this, "Registration Failed "+task.getException(), Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
    private void sendUserToNextActivity() {
        Intent intent = new Intent(Registration.this, home_2.class);
        startActivity(intent);
    }


}




