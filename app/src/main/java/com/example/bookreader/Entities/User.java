package com.example.bookreader.Entities;

public class User {
    private String username,email;

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public User() {

    }


    public String getEmail() { return email; }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



}
