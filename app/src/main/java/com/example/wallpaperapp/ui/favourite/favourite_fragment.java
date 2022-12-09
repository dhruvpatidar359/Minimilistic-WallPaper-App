package com.example.wallpaperapp.ui.favourite;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wallpaperapp.Adapter.DownloadsAdapter;
import com.example.wallpaperapp.R;
import com.example.wallpaperapp.databinding.FragmentFavouriteFragmentBinding;
import com.example.wallpaperapp.db.DBHelper;
import com.example.wallpaperapp.models.imageModel;
import com.example.wallpaperapp.ui.Wallpaper_Set;

import java.util.ArrayList;
import java.util.List;


public class favourite_fragment extends Fragment implements DownloadsAdapter.RecyclerViewItemClickListeners {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentFavouriteFragmentBinding binding = FragmentFavouriteFragmentBinding.inflate(inflater, container, false);



        SharedPreferences sharedPreferences =this.getActivity().getSharedPreferences("boolToFav", Context.MODE_PRIVATE);
        sharedPreferences.edit().putBoolean("isBool",true);

        if(sharedPreferences.getBoolean("isBool",true)){


        }



        DBHelper dbHelper = new DBHelper(getContext());
        ArrayList<imageModel> items = new ArrayList<>();
        items = dbHelper.getOrdersFav();

        DownloadsAdapter imageAdapter = new DownloadsAdapter(container.getContext(), items, this, binding.recycleViewFavourite, 2);



        binding.recycleViewFavourite.setLayoutManager(new GridLayoutManager(getContext(), 2));
        binding.recycleViewFavourite.setAdapter(imageAdapter);



Log.d("debug1","we are calling onVIew Create");


        return binding.getRoot();
    }




    @Override
    public void onResume() {
        super.onResume();
        RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.recycle_view_favourite);
        Log.d("debug1","this is resume");

        DBHelper dbHelper = new DBHelper(getContext());
        ArrayList<imageModel> items = new ArrayList<>();
        items = dbHelper.getOrdersFav();

        DownloadsAdapter imageAdapter = new DownloadsAdapter(getContext(), items, this, recyclerView, 2);



        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(imageAdapter);

    }

    @Override
    public void onRecyclerViewItemClick(List<imageModel> items, int position) {

        Intent intent = new Intent(getContext(), Wallpaper_Set.class);
        intent.putExtra("URI",items.get(position).getHeavyDownloadbaleImages());
        intent.putExtra("preview_URL",items.get(position).getDownloadableImage());
        intent.putExtra("imageName",items.get(position).getImage_name());

        startActivity(intent);
    }



}