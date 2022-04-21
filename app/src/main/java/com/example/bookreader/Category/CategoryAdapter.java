package com.example.bookreader.Category;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bookreader.R;

import java.util.List;

public class CategoryAdapter extends BaseAdapter {

    private List<CategoryModel> genre_list;

    public CategoryAdapter(List<CategoryModel> genre_list) {
        this.genre_list = genre_list;
    }

    @Override
    public int getCount() {
        return genre_list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View myView;
        if(convertView == null){
            myView= LayoutInflater.from(parent.getContext()).inflate(R.layout.genre_item_layout,parent,false);
        }
        else{
            myView=convertView;
        }
        TextView genreName=myView.findViewById(R.id.genre_name);
        genreName.setText(genre_list.get(position).getName());

        return myView;
    }
}
