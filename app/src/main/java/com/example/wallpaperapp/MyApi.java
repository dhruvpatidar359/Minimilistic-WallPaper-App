package com.example.wallpaperapp;


import com.example.wallpaperapp.models.Response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MyApi {

    @GET("?key=26217315-a4d484065b202ba78392ccbfc")
    Call<Response> getImages(@Query("page") int page,
                             @Query("per_page") int per_page);
}