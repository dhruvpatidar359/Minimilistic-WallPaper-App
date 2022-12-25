package com.example.wallpaperapp.ui.latest;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
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
import com.example.wallpaperapp.R;
import com.example.wallpaperapp.RetrofitClient;
import com.example.wallpaperapp.db.DBHelper;
import com.example.wallpaperapp.models.Response;
import com.example.wallpaperapp.models.imageModel;
import com.example.wallpaperapp.ui.Wallpaper_Set;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;

public class LatestFragment extends Fragment implements ImageAdapter.RecyclerViewItemClickListeners {


    private List<imageModel> imageList;
    RecyclerView recyclerView;


    @SuppressLint("UseRequireInsteadOfGet")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_latest, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycle_latest);


getThumbnail();


        return view;


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
                        Log.d("this", imageList.get(0).getWebformatURL());
                        if (imageList != null && imageList.size() > 0) {


                            ImageAdapter imageAdapter = new ImageAdapter(requireContext(), imageList, LatestFragment.this, recyclerView, 2);
                          recyclerView.setHasFixedSize(true);
                            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
                            recyclerView.setAdapter(imageAdapter);

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

    }

    @Override
    public void onRecyclerViewItemClick(List<imageModel> items, int position) {


        Intent intent = new Intent(getContext(), Wallpaper_Set.class);
        String preview_URL = items.get(position).getWebformatURL();
        String imageName = items.get(position).getTags();
        String id = items.get(position).getId();
        intent.putExtra("URI", items.get(position).getLargeImageURL());
        intent.putExtra("preview_URL", preview_URL);
        intent.putExtra("imageName", imageName);
        intent.putExtra("id", id);

        DBHelper dbHelper = new DBHelper(getContext());
        if (!dbHelper.checkInDB(id)) {
            Picasso.get().load(preview_URL).into(picassoImageTarget(getContext(), "preview_Images", imageName, id));
        }

        startActivity(intent);


    }

    public Target picassoImageTarget(Context context, final String imageDir, final String imageName, String id) {

        ContextWrapper cw = new ContextWrapper(context);

        final File directory = cw.getDir(imageDir, Context.MODE_PRIVATE);
        final Target target =

                new Target() {

                    @Override
                    public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                final File myImageFile = new File(directory, id); // Create image file

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
                                        dbHelper.insertFuction("/data/data/com.example.wallpaperapp/app_preview_Images", imageName, id);
                                        Log.d("This", "Preview ho gaya database me");
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

