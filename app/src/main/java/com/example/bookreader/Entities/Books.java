package com.example.bookreader.Entities;

public class Books {
    private String genre, url, title,cover;

    public Books(String genre, String url, String title,String cover) {
        this.genre = genre;
        this.url = url;
        this.title = title;
        this.cover=cover;
    }

    public String getGenre() {
        return genre;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
}
