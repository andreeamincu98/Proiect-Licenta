package com.example.bookreader.AppPages;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.bookreader.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Library extends AppCompatActivity {
    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.Theme_Dark);
        }else{
            setTheme(R.style.Theme_Light);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.library);
        BottomNavigationView navigationView = findViewById(R.id.library_bottom_navigation);
        navigationView.setOnItemSelectedListener(item -> {
            Intent intent;
            switch (item.getItemId()){

                case R.id.nav_home:
                    intent=new Intent(Library.this, Home.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    break;
                case R.id.nav_genres:
                    intent=new Intent(Library.this, Genres.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    break;
                case R.id.nav_library:
                    break;
                case R.id.nav_profile:
                    intent=new Intent(Library.this, Profile.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    break;
                case R.id.nav_settings:
                    intent=new Intent(Library.this, Settings.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    break;

            }
            return true;
        });
    }
}
