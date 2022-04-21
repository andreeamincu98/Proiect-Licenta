package com.example.bookreader.AppPages;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.bookreader.Category.CategoryAdapter;
import com.example.bookreader.Category.CategoryModel;
import com.example.bookreader.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class Genres extends AppCompatActivity {

    private GridView genreView;
    private List<CategoryModel> genreList=new ArrayList<>();

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.Theme_Dark);
        }else{
            setTheme(R.style.Theme_Light);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.genres);
        genreView = findViewById(R.id.genres_grid);

        loadGenres();
        CategoryAdapter adapter=new CategoryAdapter(genreList);
        genreView.setAdapter(adapter);

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

    private void loadGenres(){
        genreList.clear();
        genreList.add(new CategoryModel("1","Fantasy"));
        genreList.add(new CategoryModel("2","Lifestyle"));
        genreList.add(new CategoryModel("3","Fashion"));
        genreList.add(new CategoryModel("4","Romance"));
        genreList.add(new CategoryModel("5","Mystery"));
        genreList.add(new CategoryModel("6","Historic"));
        genreList.add(new CategoryModel("7","Sci-Fi"));
        genreList.add(new CategoryModel("8","Classic"));
        genreList.add(new CategoryModel("9","Modern"));
        genreList.add(new CategoryModel("10","Horror"));
        genreList.add(new CategoryModel("11","Young Adult"));
        genreList.add(new CategoryModel("12","Thriller"));
    }

}
