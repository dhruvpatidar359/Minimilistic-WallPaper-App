package com.example.wallpaperapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wallpaperapp.databinding.ActivityMainBinding;
import com.example.wallpaperapp.ui.categories.CategoriesFragment;
import com.example.wallpaperapp.ui.downlords.DownlordsFragment;
import com.example.wallpaperapp.ui.favourite.favourite_fragment;
import com.example.wallpaperapp.ui.latest.LatestFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        // setting up the adapter
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        // add the fragments
        viewPagerAdapter.add(new LatestFragment(), "Page 1");
        viewPagerAdapter.add(new CategoriesFragment(), "Page 2");
        viewPagerAdapter.add(new favourite_fragment(), "Page 3");
        viewPagerAdapter.add(new DownlordsFragment(), "Page 3");

        // Set the adapter
        binding.viewPager.setAdapter(viewPagerAdapter);
        binding.dotsIndicator.setViewPager(binding.viewPager);

    }


}