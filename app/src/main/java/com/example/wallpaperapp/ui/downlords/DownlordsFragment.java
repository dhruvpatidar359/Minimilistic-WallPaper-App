package com.example.wallpaperapp.ui.downlords;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.wallpaperapp.Adapter.DownloadsAdapter;
import com.example.wallpaperapp.Adapter.ImageAdapter;
import com.example.wallpaperapp.databinding.FragmentDownlordBinding;
import com.example.wallpaperapp.db.DBHelper;
import com.example.wallpaperapp.models.imageModel;
import com.example.wallpaperapp.ui.Wallpaper_Set;

import java.util.ArrayList;
import java.util.List;


public class DownlordsFragment extends Fragment implements DownloadsAdapter.RecyclerViewItemClickListeners {

    private FragmentDownlordBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDownlordBinding.inflate(inflater, container, false);

        DBHelper dbHelper = new DBHelper(getContext());
        ArrayList<imageModel> model = dbHelper.getOrders();

        DownloadsAdapter imageAdapter = new DownloadsAdapter(container.getContext(), model, this, binding.recycleLatestDownload, 2);



        binding.recycleLatestDownload.setLayoutManager(new GridLayoutManager(getContext(), 2));
        binding.recycleLatestDownload.setAdapter(imageAdapter);
        return binding.getRoot();


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onRecyclerViewItemClick(List<imageModel> items, int position) {

        Intent intent = new Intent(getContext(), Wallpaper_Set.class);
        intent.putExtra("URI",items.get(position).getDownloadableImage());
        intent.putExtra("imageName",items.get(position).getImage_name());

        startActivity(intent);
    }
}