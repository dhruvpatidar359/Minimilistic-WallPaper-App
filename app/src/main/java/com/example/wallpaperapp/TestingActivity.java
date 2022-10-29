package com.example.wallpaperapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class TestingActivity extends AppCompatActivity {
Button button;
ImageView img ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);

        button = findViewById(R.id.button);
        img = findViewById(R.id.imageView3);



button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        DownlordImage("chutiya","https://media.istockphoto.com/photos/beautiful-emeraldcolored-glacial-rivers-of-iceland-taken-from-a-picture-id1202227531?s=612x612");



    }
});



    }

    void DownlordImage(String fileName , String imageUrl ){
        try{
            DownloadManager downloadManager = null;
            downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);

            Uri downloaduri = Uri.parse(imageUrl);

            DownloadManager.Request request = new DownloadManager.Request(downloaduri);

            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI)
                    .setAllowedOverRoaming(false)
                    .setTitle(fileName)
                    .setMimeType("image/jpeg")
                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                    .setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES,File.separator+fileName+".jpg");

            downloadManager.enqueue(request);
            Toast.makeText(this, "Sala chal gaya", Toast.LENGTH_SHORT).show();


        }

        catch (Exception e){
            Toast.makeText(this, "fail", Toast.LENGTH_SHORT).show();
        }
    }




}