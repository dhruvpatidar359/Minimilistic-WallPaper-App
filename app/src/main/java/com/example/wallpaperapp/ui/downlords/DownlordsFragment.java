package com.example.wallpaperapp.ui.downlords;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.wallpaperapp.databinding.FragmentDownlordBinding;


public class DownlordsFragment extends Fragment {

    private FragmentDownlordBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDownlordBinding.inflate(inflater, container, false);



        return binding.getRoot();


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}