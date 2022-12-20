package com.example.wallpaperapp.models;

public class imageModel {


    String tags;
    String webformatURL;
    String largeImageURL;
    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {

        this.id = id;
    }


    public String getLargeImageURL() {
        return largeImageURL;
    }

    public void setLargeImageURL(String largeImageURL) {
        this.largeImageURL = largeImageURL;
    }


    public String getWebformatURL() {
        return webformatURL;
    }

    public void setWebformatURL(String webformatURL) {
        this.webformatURL = webformatURL;
    }


    public String getTags() {
        return tags.split(",")[0];
    }

    public void setTags(String tags) {
        this.tags = tags;
    }


    public imageModel() {
    }

}
