package com.example.bookreader.BookList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.bookreader.AppPages.Document;
import com.example.bookreader.Entities.Books;
import com.example.bookreader.R;

import java.util.ArrayList;
import java.util.List;
public class BookListAdapterGenres extends RecyclerView.Adapter<BookListAdapterSearch.BooksHolder>{

    List<Books> list;
    Activity activity;

    public BookListAdapterGenres(List<Books> list, Activity activity) {
        this.list = list;
        this.activity = activity;
    }

    @NonNull
    @Override
    public BookListAdapterSearch.BooksHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BookListAdapterSearch.BooksHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_books,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull BookListAdapterSearch.BooksHolder holder, int position) {
        Books books = list.get(position);

        holder.title.setText(books.getTitle());
        Glide.with(activity).load(books.getCover()).transition(DrawableTransitionOptions.withCrossFade()).into(holder.cover);
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(activity, Document.class);
            intent.putExtra("title", books.getTitle());
            intent.putExtra("url", books.getUrl());
            intent.putExtra("genre", books.getGenre());
            activity.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {

        return list.size();

    }



    public static class BooksHolder extends RecyclerView.ViewHolder{

        ImageView cover;
        TextView title;

        public BooksHolder(@NonNull View itemView) {
            super(itemView);

            cover=itemView.findViewById(R.id.book_cover);
            title=itemView.findViewById(R.id.book_title);


        }
    }
}
