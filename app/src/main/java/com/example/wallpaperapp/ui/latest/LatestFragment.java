package com.example.wallpaperapp.ui.latest;


import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wallpaperapp.Adapter.ImageAdapter;
import com.example.wallpaperapp.RetrofitClient;
import com.example.wallpaperapp.databinding.FragmentLatestBinding;
import com.example.wallpaperapp.db.DBHelper;
import com.example.wallpaperapp.models.Response;
import com.example.wallpaperapp.models.imageModel;
import com.example.wallpaperapp.ui.Wallpaper_Set;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class LatestFragment extends Fragment implements ImageAdapter.RecyclerViewItemClickListeners {

    private FragmentLatestBinding binding;
    private List<imageModel> imageList;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentLatestBinding.inflate(inflater, container, false);


getThumbnail();

        return binding.getRoot();
    }

    private void getThumbnail() {
        Call<Response> responseCall = RetrofitClient.getInstance().getMyApi().getImages(1, 200);
        responseCall.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.isSuccessful()) {
                    Response myResponse = response.body();
                    if (myResponse != null) {
                        imageList = myResponse.getHits();
                        Log.d("this",imageList.get(0).getWebformatURL());
                        if (imageList != null && imageList.size() > 0) {


                            ImageAdapter imageAdapter = new ImageAdapter(getContext(), imageList, LatestFragment.this::onRecyclerViewItemClick, binding.recycleLatest, 2);
                            binding.recycleLatest.setHasFixedSize(true);
                            binding.recycleLatest.setLayoutManager(new GridLayoutManager(getContext(), 2));
                            binding.recycleLatest.setAdapter(imageAdapter);

                        }
                    }

                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
            }
        });
    }




        @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onRecyclerViewItemClick(List<imageModel> items, int position) {



        Intent intent = new Intent(getContext(), Wallpaper_Set.class);
String preview_URL = items.get(position).getWebformatURL();
String imageName = items.get(position).getTags();
        intent.putExtra("URI",items.get(position).getLargeImageURL());
        intent.putExtra("preview_URL",preview_URL);
        intent.putExtra("imageName",imageName);
        Picasso.get().load(preview_URL).into(picassoImageTarget(getContext(), "preview_Images",imageName));

        startActivity(intent);



    }
    public Target picassoImageTarget(Context context, final String imageDir, final String imageName) {

        ContextWrapper cw = new ContextWrapper(context);

        final File directory = cw.getDir(imageDir, Context.MODE_PRIVATE);
        final Target target  =

                new Target() {

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

                                        DBHelper dbHelper = new DBHelper(getContext());
                                        dbHelper.insertPreview("/data/data/com.example.wallpaperapp/app_preview_Images",imageName);
                                        Log.d("This","Preview ho gaya database me");
                                        dbHelper.close();



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
                        if (placeHolderDrawable != null) {
                        }
                    }
                };

        return target;



    }


}

