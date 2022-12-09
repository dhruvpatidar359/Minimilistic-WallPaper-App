package com.example.wallpaperapp.models;

public class Item {

    int image;

    public Item(int image, String title) {
        this.image = image;
        this.title = title;
    }

    private String title;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

}
