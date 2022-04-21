package com.example.bookreader.Category;

public class CategoryModel {
    private String name;

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    private String id;
    public CategoryModel(String id,String name){
        this.name=name;
        this.id=id;
    }
}
