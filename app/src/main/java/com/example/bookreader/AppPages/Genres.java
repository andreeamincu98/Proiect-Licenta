package com.example.bookreader.AppPages;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bookreader.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Genres extends AppCompatActivity {
    @SuppressLint("NonConstantResourceId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.genres);
        BottomNavigationView navigationView = findViewById(R.id.genres_bottom_navigation);
        navigationView.setOnItemSelectedListener(item -> {
            Intent intent;
            switch (item.getItemId()){

                case R.id.nav_home:
                    intent=new Intent(Genres.this, Home.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    break;
                case R.id.nav_genres:
                    break;
                case R.id.nav_library:
                    intent=new Intent(Genres.this, Library.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    break;
                case R.id.nav_profile:
                    intent=new Intent(Genres.this, Profile.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    break;
                case R.id.nav_settings:
                    intent=new Intent(Genres.this, Settings.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    break;

            }
            return true;
        });
    }
}
