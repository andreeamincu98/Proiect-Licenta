package com.example.bookreader.AccountManagement;

import com.example.bookreader.R;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bookreader.Entities.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class Register extends AppCompatActivity {

    private FirebaseAuth fAuth;
    private Button submitUpButton;
    private TextView forgotPassword;
    DatabaseReference reff;
    FirebaseAuth mAuth;
    User user;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        reff=FirebaseDatabase.getInstance().getReference().child("User");
        mAuth=FirebaseAuth.getInstance();
        submitUpButton = findViewById(R.id.submit_data);
        submitUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();

            }
        });

        forgotPassword = findViewById(R.id.forgot_password_link2);
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openForgotPassword();
            }


        });
    }

    private void openForgotPassword() {
        Intent intent = new Intent(this, ForgotPassword.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }


    private void registerUser() {
        EditText username, email, password, confirmPassword;
        username = (EditText) findViewById(R.id.register_username_input);
        email = (EditText) findViewById(R.id.register_email_input);
        password = (EditText) findViewById(R.id.register_password_input);
        confirmPassword = (EditText) findViewById(R.id.register_repeat_password_input);
        String uname, em, pass, cpass;
        uname = username.getText().toString().trim();
        em = email.getText().toString().trim();
        pass = password.getText().toString().trim();
        cpass = confirmPassword.getText().toString().trim();
        if (!uname.equals("") && !em.equals("") && !pass.equals("") && !cpass.equals("")) {
            if (pass.equals(cpass)) {
                mAuth.createUserWithEmailAndPassword(em, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            User user = new User(uname,em,pass);
                            FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful())
                                    {
                                        Toast.makeText(Register.this, "User registered successfully", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(Register.this, LogIn.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                        startActivity(intent);
                                    }
                                }
                            });
                        }
                    }
                });

            }
            if(!pass.equals(cpass))
                Toast.makeText(Register.this, "The passwords do not match", Toast.LENGTH_LONG).show();
        }
        if(uname.equals("") || em.equals("") || pass.equals("") || cpass.equals(""))
            Toast.makeText(Register.this,"You must fill all credentials",Toast.LENGTH_LONG).show();
    }
}
