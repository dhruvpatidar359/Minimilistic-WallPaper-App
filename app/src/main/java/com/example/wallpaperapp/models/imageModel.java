package com.example.wallpaperapp.models;

public class imageModel {


    String image ;
    int id ;
    String image_name;
    String downloadableImage;

    public String getDownloadableImage() {
        return downloadableImage;
    }

    public void setDownloadableImage(String downloadableImage) {
        this.downloadableImage = downloadableImage;
    }



    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }




    public imageModel(String image, int id , String image_name, String downlordableImage) {
        this.image = image;
        this.id = id;
        this.image_name = image_name;
        this.downloadableImage = downlordableImage;
    }

    public imageModel() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
