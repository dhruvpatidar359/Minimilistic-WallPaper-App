package com.example.wallpaperapp.ui.favourite;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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




        DBHelper dbHelper = new DBHelper(getContext());
        ArrayList<imageModel> items = new ArrayList<>();
        items = dbHelper.getOrdersFav();

        DownloadsAdapter imageAdapter = new DownloadsAdapter(container.getContext(), items, this, binding.recycleViewFavourite, 2);


        binding.recycleViewFavourite.setLayoutManager(new GridLayoutManager(getContext(), 2));
        binding.recycleViewFavourite.setAdapter(imageAdapter);


        return binding.getRoot();
    }


    @Override
    public void onResume() {
        super.onResume();
        RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.recycle_view_favourite);


        DBHelper dbHelper = new DBHelper(getContext());
        ArrayList<imageModel> items = new ArrayList<>();
        items = dbHelper.getOrdersFav();

        DownloadsAdapter imageAdapter = new DownloadsAdapter(getContext(), items, this, recyclerView, 2);


        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(imageAdapter);

    }

    @Override
    public void onRecyclerViewItemClick(List<imageModel> items, int position) {
        String id = items.get(position).getId();
        Intent intent = new Intent(getContext(), Wallpaper_Set.class);
        intent.putExtra("URI", items.get(position).getLargeImageURL());
        intent.putExtra("preview_URL", items.get(position).getWebformatURL());
        intent.putExtra("imageName", items.get(position).getTags());
        intent.putExtra("id", id);

        startActivity(intent);
    }


}