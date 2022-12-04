package com.example.wallpaperapp.models;

public class DatabaseModel {
    public DatabaseModel(String address, String image_name) {
        this.address = address;
        this.image_name = image_name;
    }

    String address;
    String image_name;

    public DatabaseModel(){}


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }
}
