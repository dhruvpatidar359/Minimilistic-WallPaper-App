package com.example.wallpaperapp.ui.latest;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wallpaperapp.Adapter.ImageAdapter;
import com.example.wallpaperapp.databinding.FragmentLatestBinding;
import com.example.wallpaperapp.models.imageModel;
import com.example.wallpaperapp.ui.Wallpaper_Set;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LatestFragment extends Fragment implements ImageAdapter.RecyclerViewItemClickListeners {

    private FragmentLatestBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentLatestBinding.inflate(inflater, container, false);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

        List<imageModel> imgs = new ArrayList<>();

        RecyclerView recyclerView = binding.recycleLatest;

        ImageAdapter imageAdapter = new ImageAdapter(container.getContext(), imgs, this, recyclerView, 2);
        firebaseDatabase.getReference().child("images")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        imgs.clear();
                        for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                            imageModel models = snapshot1.getValue(imageModel.class);

                            imgs.add(models);
                        }
                        Log.d("models",(imgs.get(1).getDownloadableImage()));
                        imageAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });




        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(imageAdapter);


        return binding.getRoot();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onRecyclerViewItemClick(List<imageModel> items, int position) {


//        Glide.with(getContext())
//                .asBitmap()
//                .load(items.get(position).getDownloadableImage())
//                .into(new SimpleTarget<Bitmap>() {
//                    @Override
//                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
//                        try {
//                            WallpaperManager.getInstance(getContext()).setBitmap(resource);
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                });

        Intent intent = new Intent(getContext(), Wallpaper_Set.class);
        intent.putExtra("URI",items.get(position).getDownloadableImage());
        intent.putExtra("imageName",items.get(position).getImage_name());

        startActivity(intent);



    }


}

