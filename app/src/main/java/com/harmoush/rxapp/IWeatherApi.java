package com.harmoush.rxapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IWeatherApi {
    public static final String BASE_URL = "http://api.openweathermap.org";

    @GET("data/2.5/weather")
    Call<WeatherModel> getTodayWeather(@Query("q") String cityName, @Query("appid") String appId,@Query("units") String units);
}
