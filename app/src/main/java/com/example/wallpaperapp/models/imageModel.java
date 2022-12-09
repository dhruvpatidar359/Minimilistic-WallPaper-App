package com.example.wallpaperapp.models;

public class imageModel {




    String image_name;
    String downloadableImage;
    String heavyDownloadbaleImages;

    public String getHeavyDownloadbaleImages() {
        return heavyDownloadbaleImages;
    }

    public void setHeavyDownloadbaleImages(String heavyDownloadbaleImages) {
        this.heavyDownloadbaleImages = heavyDownloadbaleImages;
    }



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


    public imageModel( String image_name, String downlordableImage) {


        this.image_name = image_name;
        this.downloadableImage = downlordableImage;
    }

    public imageModel() {
    }

}
