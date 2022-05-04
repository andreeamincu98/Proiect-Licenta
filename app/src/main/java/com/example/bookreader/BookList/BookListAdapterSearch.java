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

public class BookListAdapterSearch extends RecyclerView.Adapter<BookListAdapterSearch.BooksHolder> implements Filterable {

    List<Books> list;
    List<Books> filteredList=new ArrayList<>();
    Activity activity;

    public BookListAdapterSearch(List<Books> list, Activity activity) {
        this.list = list;
        this.activity = activity;
        this.filteredList.addAll(list);
    }

    @NonNull
    @Override
    public BooksHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BooksHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_books,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull BooksHolder holder, int position) {
            Books books = filteredList.get(position);

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

        return filteredList.size();

    }

    @Override
    public Filter getFilter() {
        return bookFilter;
    }

    private final Filter bookFilter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Books> filteredBooksList=new ArrayList<>();
            if(constraint == null || constraint.length()==0){
                filteredList.clear();
                filteredList.addAll(list);
                filteredBooksList.addAll(list);
            }
            else{
                String filterPattern=constraint.toString().toLowerCase().trim();
                for(Books books: list){
                    if(books.getTitle().toLowerCase().contains(filterPattern)){
                        filteredBooksList.add(books);
                    }
                }

            }
            FilterResults results=new FilterResults();
            results.values=filteredBooksList;
            results.count=filteredBooksList.size();
            return results;
        }

        @SuppressLint("NotifyDataSetChanged")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredList.clear();
            filteredList.addAll((ArrayList) results.values);
            notifyDataSetChanged();
        }
    };




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
