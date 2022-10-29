package com.example.wallpaperapp.ui.favourite;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wallpaperapp.databinding.FragmentFavouriteFragmentBinding;


public class favourite_fragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentFavouriteFragmentBinding binding = FragmentFavouriteFragmentBinding.inflate(inflater, container, false);





        return binding.getRoot();
    }}