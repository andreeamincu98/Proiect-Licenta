package com.example.bookreader.AppPages;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

import com.example.bookreader.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Settings extends AppCompatActivity {

    private SwitchCompat switchCompat;

    @SuppressLint("NonConstantResourceId")
    protected void onCreate(Bundle savedInstanceState) {
        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.Theme_Dark);
        }else{
            setTheme(R.style.Theme_Light);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        switchCompat=findViewById(R.id.settings_switch_day_night_mode);
        SharedPreferences sharedPreferences=getSharedPreferences("save",MODE_PRIVATE);

        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
            switchCompat.setChecked(sharedPreferences.getBoolean("value",true));
        }else{
            switchCompat.setChecked(sharedPreferences.getBoolean("value",false));
        }


        switchCompat.setOnClickListener(v -> {
            if(switchCompat.isChecked()){
                SharedPreferences.Editor editor = getSharedPreferences("save",MODE_PRIVATE).edit();
                editor.putBoolean("value",true);
                editor.apply();
                switchCompat.setChecked(true);

                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                setTheme(R.style.Theme_Dark);
            }else {
                SharedPreferences.Editor editor = getSharedPreferences("save",MODE_PRIVATE).edit();
                editor.putBoolean("value",false);
                editor.apply();
                switchCompat.setChecked(false);
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                setTheme(R.style.Theme_Light);
            }
        });


        BottomNavigationView navigationView = findViewById(R.id.settings_bottom_navigation);
        navigationView.setOnItemSelectedListener(item -> {
            Intent intent;
            switch (item.getItemId()){

                case R.id.nav_home:
                    intent=new Intent(Settings.this, Home.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    break;
                case R.id.nav_genres:
                    intent=new Intent(Settings.this, Genres.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    break;
                case R.id.nav_library:
                    intent=new Intent(Settings.this, Library.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    break;
                case R.id.nav_profile:
                    intent=new Intent(Settings.this, Profile.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    break;
                case R.id.nav_settings:
                    break;

            }
            return true;
        });
    }
}
