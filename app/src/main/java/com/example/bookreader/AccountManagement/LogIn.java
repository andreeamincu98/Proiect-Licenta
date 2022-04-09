package com.example.bookreader.AccountManagement;

import com.example.bookreader.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class LogIn extends AppCompatActivity {

    private Button signUpButton;
    private Button signInButton;
    private TextView forgotPassword;
    private CheckBox rememberMe;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        rememberMe=findViewById(R.id.remember_me);
        mAuth=FirebaseAuth.getInstance();
        signUpButton= findViewById(R.id.sign_up);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegister();
            }
        });

        signInButton= findViewById(R.id.sign_in);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();

            }
        });

        forgotPassword = findViewById(R.id.forgot_password_link);
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openForgotPassword();
            }


        });


    }

    public void openRegister(){
        Intent intent=new Intent(this, com.example.bookreader.AccountManagement.Register.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }


    public void loginUser(){
        EditText email,password;
        email=(EditText)findViewById(R.id.login_email_input);
        password =(EditText)findViewById(R.id.login_password_input);
        String em, pass;
        em=email.getText().toString().trim();
        pass=password.getText().toString().trim();

        if(!em.equals("") && !pass.equals("")){
            mAuth.signInWithEmailAndPassword(em,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
                        if(user.isEmailVerified()){

//                            FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
//                                    .setValue(pass);
                            Toast.makeText(LogIn.this,"User logged in successfully",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(LogIn.this, com.example.bookreader.AppPages.Home.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            startActivity(intent);
                        }
                        if(!user.isEmailVerified()){
                            user.sendEmailVerification();
                            Toast.makeText(LogIn.this,"Check your email to verify your account!",Toast.LENGTH_LONG).show();
                        }
                    }
                    else{
                        Toast.makeText(LogIn.this,"Account doesn't exist or credentials do not match",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
        if(em.equals("") || pass.equals("")){
            Toast.makeText(LogIn.this,"You must fill all credentials",Toast.LENGTH_LONG).show();
        }
    }



    private void openForgotPassword() {
        Intent intent = new Intent(this, ForgotPassword.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }

}
