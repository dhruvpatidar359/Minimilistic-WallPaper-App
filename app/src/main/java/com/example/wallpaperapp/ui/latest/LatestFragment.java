package com.example.wallpaperapp.ui.latest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.wallpaperapp.Adapter.ResponsiveItemListAdapter;
import com.example.wallpaperapp.R;
import com.example.wallpaperapp.databinding.FragmentLatestBinding;
import com.example.wallpaperapp.models.Item;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class LatestFragment extends Fragment implements ResponsiveItemListAdapter.RecyclerViewItemClickListeners {

    private FragmentLatestBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentLatestBinding.inflate(inflater, container, false);


        List<Item> items = new ArrayList<>();
        items.add(new Item(R.drawable.img_rectangle2,"one"));
        items.add(new Item(R.drawable.img_rectangle2,"one"));
        items.add(new Item(R.drawable.img_rectangle2,"one"));
        items.add(new Item(R.drawable.img_rectangle2,"one"));
        items.add(new Item(R.drawable.img_rectangle2,"one"));
        items.add(new Item(R.drawable.img_rectangle2,"one"));
        items.add(new Item(R.drawable.img_rectangle2,"one"));
        items.add(new Item(R.drawable.img_rectangle2,"one"));
        items.add(new Item(R.drawable.img_rectangle2,"one"));


      RecyclerView  recyclerView = binding.recycleLatest;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.setAdapter(new ResponsiveItemListAdapter(container.getContext(), items,this, recyclerView, 2));


        return binding.getRoot();
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onRecyclerViewItemClick(List<Item> items, int position) {
        Toast.makeText(getContext(), items.get(position).getTitle(), Toast.LENGTH_LONG).show();

    }






}

