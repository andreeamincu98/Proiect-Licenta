package com.example.bookreader.AppPages;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.bookreader.MainActivity;
import com.example.bookreader.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Home extends AppCompatActivity {


    ImageSlider mainslider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        ImageView firstPicture,secondPicture,thirdPicture,forthPicture,fifthPicture,sixthPicture,seventhPicture;
        firstPicture=findViewById(R.id.first_pic);
        secondPicture=findViewById(R.id.second_pic);
        thirdPicture=findViewById(R.id.third_pic);
        forthPicture=findViewById(R.id.forth_pic);
        fifthPicture=findViewById(R.id.fifth_pic);
        sixthPicture=findViewById(R.id.sixth_pic);
        seventhPicture=findViewById(R.id.seventh_pic);
        ImageView firstPicture2,secondPicture2,thirdPicture2,forthPicture2,fifthPicture2,sixthPicture2,seventhPicture2;
        firstPicture2=findViewById(R.id.first_pic2);
        secondPicture2=findViewById(R.id.second_pic2);
        thirdPicture2=findViewById(R.id.third_pic2);
        forthPicture2=findViewById(R.id.forth_pic2);
        fifthPicture2=findViewById(R.id.fifth_pic2);
        sixthPicture2=findViewById(R.id.sixth_pic2);
        seventhPicture2=findViewById(R.id.seventh_pic2);
        ImageView firstPicture3,secondPicture3,thirdPicture3,forthPicture3,fifthPicture3,sixthPicture3,seventhPicture3;
        firstPicture3=findViewById(R.id.first_pic3);
        secondPicture3=findViewById(R.id.second_pic3);
        thirdPicture3=findViewById(R.id.third_pic3);
        forthPicture3=findViewById(R.id.forth_pic3);
        fifthPicture3=findViewById(R.id.fifth_pic3);
        sixthPicture3=findViewById(R.id.sixth_pic3);
        seventhPicture3=findViewById(R.id.seventh_pic3);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mainslider= (ImageSlider) findViewById(R.id.home_first_image_slider);
        final List<SlideModel> remoteimages=new ArrayList<>();

        FirebaseDatabase.getInstance().getReference().child("BookCovers").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot data:dataSnapshot.getChildren()) {
                    remoteimages.add(new SlideModel(data.child("url").getValue().toString(), data.child("title").getValue().toString(), ScaleTypes.FIT));
                 }
                mainslider.setImageList(remoteimages, ScaleTypes.FIT);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        FirebaseDatabase.getInstance().getReference().child("HomePopular").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<String> links= new ArrayList<>();
                for(DataSnapshot data:dataSnapshot.getChildren()) {
                    links.add(Objects.requireNonNull(data.child("url").getValue()).toString());
                }
                RequestOptions requestOptions=new RequestOptions();
                Glide.with(Home.this)
                        .load(links.get(0))
                        .apply(requestOptions)
                        .into(firstPicture);
                Glide.with(Home.this)
                        .load(links.get(1))
                        .apply(requestOptions)
                        .into(secondPicture);
                Glide.with(Home.this)
                        .load(links.get(2))
                        .apply(requestOptions)
                        .into(thirdPicture);
                Glide.with(Home.this)
                        .load(links.get(3))
                        .apply(requestOptions)
                        .into(forthPicture);
                Glide.with(Home.this)
                        .load(links.get(4))
                        .apply(requestOptions)
                        .into(fifthPicture);
                Glide.with(Home.this)
                        .load(links.get(5))
                        .apply(requestOptions)
                        .into(sixthPicture);
                Glide.with(Home.this)
                        .load(links.get(6))
                        .apply(requestOptions)
                        .into(seventhPicture);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        FirebaseDatabase.getInstance().getReference().child("HomeRecent").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<String> links= new ArrayList<>();
                for(DataSnapshot data:dataSnapshot.getChildren()) {
                    links.add(Objects.requireNonNull(data.child("url").getValue()).toString());
                }
                RequestOptions requestOptions=new RequestOptions();
                Glide.with(Home.this)
                        .load(links.get(0))
                        .apply(requestOptions)
                        .into(firstPicture2);
                Glide.with(Home.this)
                        .load(links.get(1))
                        .apply(requestOptions)
                        .into(secondPicture2);
                Glide.with(Home.this)
                        .load(links.get(2))
                        .apply(requestOptions)
                        .into(thirdPicture2);
                Glide.with(Home.this)
                        .load(links.get(3))
                        .apply(requestOptions)
                        .into(forthPicture2);
                Glide.with(Home.this)
                        .load(links.get(4))
                        .apply(requestOptions)
                        .into(fifthPicture2);
                Glide.with(Home.this)
                        .load(links.get(5))
                        .apply(requestOptions)
                        .into(sixthPicture2);
                Glide.with(Home.this)
                        .load(links.get(6))
                        .apply(requestOptions)
                        .into(seventhPicture2);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        FirebaseDatabase.getInstance().getReference().child("HomeDiscover").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<String> links= new ArrayList<>();
                for(DataSnapshot data:dataSnapshot.getChildren()) {
                    links.add(Objects.requireNonNull(data.child("url").getValue()).toString());
                }
                RequestOptions requestOptions=new RequestOptions();
                Glide.with(Home.this)
                        .load(links.get(0))
                        .apply(requestOptions)
                        .into(firstPicture3);
                Glide.with(Home.this)
                        .load(links.get(1))
                        .apply(requestOptions)
                        .into(secondPicture3);
                Glide.with(Home.this)
                        .load(links.get(2))
                        .apply(requestOptions)
                        .into(thirdPicture3);
                Glide.with(Home.this)
                        .load(links.get(3))
                        .apply(requestOptions)
                        .into(forthPicture3);
                Glide.with(Home.this)
                        .load(links.get(4))
                        .apply(requestOptions)
                        .into(fifthPicture3);
                Glide.with(Home.this)
                        .load(links.get(5))
                        .apply(requestOptions)
                        .into(sixthPicture3);
                Glide.with(Home.this)
                        .load(links.get(6))
                        .apply(requestOptions)
                        .into(seventhPicture3);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


}
