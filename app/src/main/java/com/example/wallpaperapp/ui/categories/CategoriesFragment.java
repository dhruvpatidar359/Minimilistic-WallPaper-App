package com.example.wallpaperapp.ui.categories;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.wallpaperapp.databinding.FragmentCategoriesBinding;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class CategoriesFragment extends Fragment{

    private FragmentCategoriesBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentCategoriesBinding.inflate(inflater, container, false);

        Picasso.get().load("https://media.istockphoto.com/photos/icon-concept-circulating-in-the-hands-of-young-women-for-environment-picture-id1360783154?s=612x612").into(binding.imageView);


        binding.downlordbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Picasso.get().load("https://media.istockphoto.com/photos/icon-concept-circulating-in-the-hands-of-young-women-for-environment-picture-id1360783154?s=612x612").into(picassoImageTarget(container.getContext(), "imageDir", "my_imag.jpeg"));

                File file = new File("/data/data/com.example.wallpaperapp/app_imageDir", "my_imag.jpeg");
                if (file.isFile()) {
                    Toast.makeText(getContext(), "ye waha pe file chutiye", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Downloading", Toast.LENGTH_SHORT).show();
                    // ...


                    try {
                        File f = new File("/data/data/com.example.wallpaperapp/app_imageDir", "my_imag.jpeg");
                        Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
                        binding.imageView.setImageBitmap(b);


                    } catch (FileNotFoundException e) {
                        Log.d("mc","chutiya");
                        e.printStackTrace();
                    }

                }
            }

        });





        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);


    }
    @Override
    public void onResume() {
        super.onResume();

        File f=new File("/data/data/com.example.wallpaperapp/app_imageDir", "my_imag.jpeg");
        Bitmap b = null;
        try {
            b = BitmapFactory.decodeStream(new FileInputStream(f));
            binding.imageView.setImageBitmap(b);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private Target picassoImageTarget(Context context, final String imageDir, final String imageName) {
        Log.d("picassoImageTarget", " picassoImageTarget");
        ContextWrapper cw = new ContextWrapper(context);
        final File directory = cw.getDir(imageDir, Context.MODE_PRIVATE); // path to /data/data/yourapp/app_imageDir
        return new Target() {
            @Override
            public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final File myImageFile = new File(directory, imageName); // Create image file
                        FileOutputStream fos = null;
                        try {
                            fos = new FileOutputStream(myImageFile);
                            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            try {
                                fos.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        Log.i("image", "image saved to >>>" + myImageFile.getAbsolutePath());

                    }
                }).start();
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                if (placeHolderDrawable != null) {}
            }
        };
    }





    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



}