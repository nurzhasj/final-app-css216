package com.hfad.finalapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieApi {
    @GET("v3/08258b09-2f82-4c9f-9b13-89c85b28753a")
    Call<List<Movie>> getMovies();
}
