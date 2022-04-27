package com.example.bookreader.AppPages;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.WindowManager;
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
    public static final String SHARED_PREFS=null;
    public String selectedCategory;
    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.Theme_Dark);
        }else{
            setTheme(R.style.Theme_Light);
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.genres);
        genreView = findViewById(R.id.genres_grid);

        loadGenres();
        CategoryAdapter adapter=new CategoryAdapter(genreList);
        genreView.setAdapter(adapter);
        SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);

        SharedPreferences.Editor editor=sharedPreferences.edit();

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





        genreView.setOnItemClickListener((parent, view, position, id) -> {
            switch(position) {
                case 0:
                    editor.putString(SHARED_PREFS,"fantasy");
                    editor.apply();
                    Intent intent=new Intent(Genres.this, GenreList.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    break;

                case 1:
                    editor.putString(SHARED_PREFS,"lifestyle");
                    editor.apply();
                    intent=new Intent(Genres.this, GenreList.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    break;
                case 2:
                    editor.putString(SHARED_PREFS,"fashion");
                    editor.apply();
                    intent=new Intent(Genres.this, GenreList.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    break;
                case 3:
                    editor.putString(SHARED_PREFS,"romance");
                    editor.apply();
                    intent=new Intent(Genres.this, GenreList.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    break;
                case 4:
                    editor.putString(SHARED_PREFS,"mystery");
                    editor.apply();
                    intent=new Intent(Genres.this, GenreList.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    break;
                case 5:
                    editor.putString(SHARED_PREFS,"historic");
                    editor.apply();
                    intent=new Intent(Genres.this, GenreList.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    break;
                case 6:
                    editor.putString(SHARED_PREFS,"sci-fi");
                    editor.apply();
                    intent=new Intent(Genres.this, GenreList.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    break;
                case 7:
                    editor.putString(SHARED_PREFS,"classic");
                    editor.apply();
                    intent=new Intent(Genres.this, GenreList.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    break;
                case 8:
                    editor.putString(SHARED_PREFS,"modern");
                    editor.apply();
                    intent=new Intent(Genres.this, GenreList.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    break;
                case 9:
                    editor.putString(SHARED_PREFS,"horror");
                    editor.apply();
                    intent=new Intent(Genres.this, GenreList.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    break;
                case 10:
                    editor.putString(SHARED_PREFS,"young adult");
                    editor.apply();
                    intent=new Intent(Genres.this, GenreList.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    break;
                case 11:
                    editor.putString(SHARED_PREFS,"thriller");
                    editor.apply();
                    intent=new Intent(Genres.this, GenreList.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    break;
                case 12:
                    editor.putString(SHARED_PREFS,"manga");
                    editor.apply();
                    intent=new Intent(Genres.this, GenreList.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    break;

            }

        });

    }

    private void loadGenres(){
        genreList.clear();
        genreList.add(new CategoryModel("0","Fantasy"));
        genreList.add(new CategoryModel("1","Lifestyle"));
        genreList.add(new CategoryModel("2","Fashion"));
        genreList.add(new CategoryModel("3","Romance"));
        genreList.add(new CategoryModel("4","Mystery"));
        genreList.add(new CategoryModel("5","Historic"));
        genreList.add(new CategoryModel("6","Sci-Fi"));
        genreList.add(new CategoryModel("7","Classic"));
        genreList.add(new CategoryModel("8","Modern"));
        genreList.add(new CategoryModel("9","Horror"));
        genreList.add(new CategoryModel("10","Young Adult"));
        genreList.add(new CategoryModel("11","Thriller"));
        genreList.add(new CategoryModel("12","Manga"));
    }





}
