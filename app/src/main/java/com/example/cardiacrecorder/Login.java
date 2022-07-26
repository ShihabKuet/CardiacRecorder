package com.example.cardiacrecorder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

public class Login extends AppCompatActivity {

    FirebaseAuth mAuth;

    TextView crtnew;
    EditText inputemail, inputpass;
    ProgressBar progressBar;
    Button log;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        inputemail = findViewById(R.id.inputemail);
        inputpass = findViewById(R.id.inputpass);
        progressBar = findViewById(R.id.progressbar);
        log = findViewById(R.id.loginbtn);
        crtnew = findViewById(R.id.createAcc);

        crtnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, Registration.class));
            }
        });

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PerforLogin();
            }
        });
    }

    private void PerforLogin() {
        String email = inputemail.getText().toString();
        String password = inputpass.getText().toString();

        if (!email.matches(emailPattern)) {
            inputemail.setError("Enter correct email");
            inputemail.requestFocus();
            return;
        }
        if (password.isEmpty() || password.length() < 6) {
            inputpass.setError("Enter proper password");
            inputpass.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(Login.this, "Login Complete", Toast.LENGTH_SHORT).show();
                    sendUserToNextActivity();

                } else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(Login.this, "Failed to login" , Toast.LENGTH_SHORT).show();

                }
            }
        });


    }

    private void sendUserToNextActivity() {
        Intent intent = new Intent(Login.this, home_2.class);
        startActivity(intent);
    }

}