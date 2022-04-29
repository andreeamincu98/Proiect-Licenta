package com.example.bookreader.AppPages;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import es.voghdev.pdfviewpager.library.remote.DownloadFile;

import com.example.bookreader.BookList.BookListAdapter;
import com.example.bookreader.Entities.Books;
import com.example.bookreader.R;
import com.example.bookreader.helper.ViewPDF;

import java.util.List;

public class Document extends AppCompatActivity {
    String title,url,genre;
    BookListAdapter adapter;
    List<Books> list;
    ProgressBar loader;
    ViewPager viewPager;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.document);
        viewPager=findViewById(R.id.document_book);
        linearLayout=findViewById(R.id.pdfViewer);
        loader=findViewById(R.id.loader);
        Bundle bundle=getIntent().getExtras();
        title=bundle.getString("title");
        url=bundle.getString("url");
        genre=bundle.getString("genre");
        new ViewPDF(url,linearLayout,loader,Document.this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ViewPDF.stopPDF();
    }
}
