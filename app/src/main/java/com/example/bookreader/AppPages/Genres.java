package com.example.bookreader.AppPages;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
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
    public static String selectedCategory=null;
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
                case R.id.nav_search:
                    intent=new Intent(Genres.this, Search.class);
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
                    selectedCategory="fantasy";
                    Intent intent=new Intent(Genres.this, GenreList.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    break;

                case 1:
                    selectedCategory="lifestyle";
                    intent=new Intent(Genres.this, GenreList.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    break;
                case 2:
                    selectedCategory="fashion";
                    intent=new Intent(Genres.this, GenreList.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    break;
                case 3:
                    selectedCategory="romance";
                    intent=new Intent(Genres.this, GenreList.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    break;
                case 4:
                    selectedCategory="mystery";
                    intent=new Intent(Genres.this, GenreList.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    break;
                case 5:
                    selectedCategory="historic";
                    intent=new Intent(Genres.this, GenreList.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    break;
                case 6:
                    selectedCategory="sci-fi";
                    intent=new Intent(Genres.this, GenreList.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    break;
                case 7:
                    selectedCategory="classic";
                    intent=new Intent(Genres.this, GenreList.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    break;
                case 8:
                    selectedCategory="modern";
                    intent=new Intent(Genres.this, GenreList.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    break;
                case 9:
                    selectedCategory="horror";
                    intent=new Intent(Genres.this, GenreList.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    break;
                case 10:
                    selectedCategory="young adult";
                    intent=new Intent(Genres.this, GenreList.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    break;
                case 11:
                    selectedCategory="thriller";
                    intent=new Intent(Genres.this, GenreList.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    break;
                case 12:
                    selectedCategory="manga";
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
