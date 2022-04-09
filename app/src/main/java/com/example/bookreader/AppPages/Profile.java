package com.example.bookreader.AppPages;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bookreader.AccountManagement.LogIn;
import com.example.bookreader.Entities.User;
import com.example.bookreader.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Profile extends AppCompatActivity {
    private BottomNavigationView navigationView;
    private Button logout;
    private FirebaseAuth mFirebaseAuth;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        TextView helloUser;
        helloUser=findViewById(R.id.profile_username);
        logout=findViewById(R.id.profile_log_out);
        mFirebaseAuth=FirebaseAuth.getInstance();

        FirebaseDatabase.getInstance().getReference().child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String email = user.getEmail();
                helloUser.setText(email);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFirebaseAuth.signOut();
                Toast.makeText(Profile.this,"User logged out successfully!",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(Profile.this, com.example.bookreader.AccountManagement.LogIn.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });


        navigationView=findViewById(R.id.home_bottom_navigation);
        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent=new Intent(Profile.this, com.example.bookreader.AppPages.Profile.class);
                switch (item.getItemId()){

                    case R.id.nav_home:
                        intent=new Intent(Profile.this, com.example.bookreader.AppPages.Home.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                        break;
                    case R.id.nav_genres:
                        intent=new Intent(Profile.this, com.example.bookreader.AppPages.Genres.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                        break;
                    case R.id.nav_library:
                        intent=new Intent(Profile.this, com.example.bookreader.AppPages.Library.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                        break;
                    case R.id.nav_profile:
                        break;
                    case R.id.nav_settings:
                        intent=new Intent(Profile.this, com.example.bookreader.AppPages.Settings.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                        break;

                }
                return true;
            }
        });

    }
}
