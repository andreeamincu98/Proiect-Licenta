package com.example.bookreader.AppPages;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;
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

public class Home extends AppCompatActivity {


    private BottomNavigationView navigationView;
    private ImageSlider mainslider;
    private LinearLayout firstLinearLayout,secondLinearLayout,thirdLinearLayout;
    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES || AppCompatDelegate.getDefaultNightMode()== AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY){
            setTheme(R.style.Theme_Dark);
        }else{
            setTheme(R.style.Theme_Light);
        }

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        firstLinearLayout=findViewById(R.id.first_linear_layout);
        secondLinearLayout=findViewById(R.id.second_linear_layout);
        thirdLinearLayout=findViewById(R.id.third_linear_layout);
        mainslider= (ImageSlider) findViewById(R.id.home_first_image_slider);


        final List<SlideModel> remoteimages=new ArrayList<>();

        FirebaseDatabase.getInstance().getReference().child("HomeImageSlideshow").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot data:dataSnapshot.getChildren()) {

                    remoteimages.add(new SlideModel(Objects.requireNonNull(data.child("cover").getValue()).toString(), Objects.requireNonNull(data.child("title").getValue()).toString(), ScaleTypes.FIT));
                }
                mainslider.setImageList(remoteimages, ScaleTypes.FIT);
                mainslider.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onItemSelected(int i) {
                        String title=remoteimages.get(i).getTitle().toString();
                        for(DataSnapshot data:dataSnapshot.getChildren()){
                            if(Objects.requireNonNull(data.child("title").getValue()).toString().equals(title)){
                                String cover = Objects.requireNonNull(data.child("cover").getValue()).toString();
                                String url = Objects.requireNonNull(data.child("url").getValue()).toString();
                                String genre = Objects.requireNonNull(data.child("genre").getValue()).toString();
                                Intent intent = new Intent(Home.this, Document.class);
                                intent.putExtra("title", title);
                                intent.putExtra("url", url);
                                intent.putExtra("genre", genre);
                                startActivity(intent);
                            }
                        }
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        FirebaseDatabase.getInstance().getReference().child("HomePopular").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot data:dataSnapshot.getChildren()) {
                            String title = Objects.requireNonNull(data.child("title").getValue()).toString();
                            String cover = Objects.requireNonNull(data.child("cover").getValue()).toString();
                            String url = Objects.requireNonNull(data.child("url").getValue()).toString();
                            String genre = Objects.requireNonNull(data.child("genre").getValue()).toString();
                            ImageView image =new ImageView(Home.this);
                            image.setLayoutParams(new ViewGroup.LayoutParams(350, ViewGroup.LayoutParams.MATCH_PARENT));
                            image.setScaleType(ImageView.ScaleType.CENTER_CROP);
                            image.setAdjustViewBounds(true);
                            image.setPadding(2,2,2,2);
                            image.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(Home.this, Document.class);
                                    intent.putExtra("title", title);
                                    intent.putExtra("url", url);
                                    intent.putExtra("genre", genre);
                                    startActivity(intent);
                                }
                            });
                    RequestOptions requestOptions=new RequestOptions();
                    Glide.with(Home.this)
                            .load(cover)
                            .apply(requestOptions)
                            .into(image);

                    firstLinearLayout.addView(image);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        FirebaseDatabase.getInstance().getReference().child("HomeRecent").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    String title = Objects.requireNonNull(data.getKey());
                    String cover = Objects.requireNonNull(data.child("cover").getValue()).toString();
                    String url = Objects.requireNonNull(data.child("url").getValue()).toString();
                    String genre = Objects.requireNonNull(data.child("genre").getValue()).toString();
                    ImageView image = new ImageView(Home.this);
                    image.setLayoutParams(new ViewGroup.LayoutParams(350, ViewGroup.LayoutParams.MATCH_PARENT));
                    image.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    image.setAdjustViewBounds(true);
                    image.setPadding(2,2,2,2);
                    image.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(Home.this, Document.class);
                            intent.putExtra("title", title);
                            intent.putExtra("url", url);
                            intent.putExtra("genre", genre);
                            startActivity(intent);
                        }
                    });
                    RequestOptions requestOptions = new RequestOptions();
                    Glide.with(Home.this)
                            .load(cover)
                            .apply(requestOptions)
                            .into(image);

                    secondLinearLayout.addView(image);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        FirebaseDatabase.getInstance().getReference().child("HomeDiscover").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot data:dataSnapshot.getChildren()) {
                    String title = Objects.requireNonNull(data.getKey());
                    String cover = Objects.requireNonNull(data.child("cover").getValue()).toString();
                    String url = Objects.requireNonNull(data.child("url").getValue()).toString();
                    String genre = Objects.requireNonNull(data.child("genre").getValue()).toString();
                    ImageView image =new ImageView(Home.this);
                    image.setLayoutParams(new ViewGroup.LayoutParams(350, ViewGroup.LayoutParams.MATCH_PARENT));
                    image.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    image.setAdjustViewBounds(true);
                    image.setPadding(2,2,2,2);
                    image.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(Home.this, Document.class);
                            intent.putExtra("title", title);
                            intent.putExtra("url", url);
                            intent.putExtra("genre", genre);
                            startActivity(intent);
                        }
                    });
                    RequestOptions requestOptions=new RequestOptions();
                    Glide.with(Home.this)
                            .load(cover)
                            .apply(requestOptions)
                            .into(image);

                    thirdLinearLayout.addView(image);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        navigationView=findViewById(R.id.home_bottom_navigation);
        navigationView.setOnItemSelectedListener(item -> {
            Intent intent;
            switch (item.getItemId()){

                case R.id.nav_home:
                    break;
                case R.id.nav_genres:
                    intent=new Intent(Home.this, Genres.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    break;
                case R.id.nav_search:
                    intent=new Intent(Home.this, Search.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    break;
                case R.id.nav_profile:
                    intent=new Intent(Home.this, Profile.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    break;
                case R.id.nav_settings:
                    intent=new Intent(Home.this, Settings.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    break;

            }
            return true;
        });


    }


}
