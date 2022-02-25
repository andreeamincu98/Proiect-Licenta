package com.example.bookreader.AccountManagement;


import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bookreader.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    private Button submitUpButton;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);
        mAuth=FirebaseAuth.getInstance();
        submitUpButton= findViewById(R.id.forgot_password_reset_password);
        submitUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogIn();
            }
        });

    }

    public void openLogIn(){
        EditText email ;
        email = (EditText)findViewById(R.id.forgot_password_email_input);
        String em;
        em=email.getText().toString().trim();

        if(em.equals("")) {
            email.setError("Email is required!");
            email.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(em).matches()){
            email.setError("Please provide a valid email!");
            email.requestFocus();
            return;
        }

        mAuth.sendPasswordResetEmail(em).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(ForgotPassword.this, "Check your email to reset your password!", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(ForgotPassword.this,LogIn.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                }
                if(!task.isSuccessful()){
                    Toast.makeText(ForgotPassword.this, "Try again!Something wrong happened!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
