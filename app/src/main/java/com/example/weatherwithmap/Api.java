package com.example.weatherwithmap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    @GET("weather")
    Call<Get> Pogodka(@Query("q") String q, @Query("units") String units, @Query("appid") String appid);
}
