package com.example.bookreader.AppPages;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.WindowManager;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookreader.BookList.BookListAdapterSearch;
import com.example.bookreader.Entities.Books;
import com.example.bookreader.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Search extends AppCompatActivity {
    private SearchView inputSearch;
    private RecyclerView searchResult;
    BookListAdapterSearch adapter;
    List<Books> list=new ArrayList<>();

    @SuppressLint({"NonConstantResourceId", "NotifyDataSetChanged"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES || AppCompatDelegate.getDefaultNightMode()== AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY){
            setTheme(R.style.Theme_Dark);
        }else{
            setTheme(R.style.Theme_Light);
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        BottomNavigationView navigationView = findViewById(R.id.library_bottom_navigation);
        inputSearch=findViewById(R.id.input_search);
        searchResult=findViewById(R.id.search_results);
        adapter=new BookListAdapterSearch(list,this);
        searchResult.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        FirebaseDatabase.getInstance().getReference().child("Books").addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot data:snapshot.getChildren()){
                    String title = Objects.requireNonNull(data.getKey());
                    String cover = Objects.requireNonNull(data.child("cover").getValue()).toString();
                    String url = Objects.requireNonNull(data.child("url").getValue()).toString();
                    String genre = Objects.requireNonNull(data.child("genre").getValue()).toString();
                    list.add(new Books(genre, url, title, cover));
                    adapter.notifyDataSetChanged();

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        inputSearch.setQueryHint("Search ...");
        inputSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        adapter=new BookListAdapterSearch(list,this);
        searchResult.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        navigationView.setOnItemSelectedListener(item -> {
            Intent intent;
            switch (item.getItemId()){

                case R.id.nav_home:
                    intent=new Intent(Search.this, Home.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    break;
                case R.id.nav_genres:
                    intent=new Intent(Search.this, Genres.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    break;
                case R.id.nav_search:
                    break;
                case R.id.nav_profile:
                    intent=new Intent(Search.this, Profile.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    break;
                case R.id.nav_settings:
                    intent=new Intent(Search.this, Settings.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    break;

            }
            return true;
        });


    }



}
