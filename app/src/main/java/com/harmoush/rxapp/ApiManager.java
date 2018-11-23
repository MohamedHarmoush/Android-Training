package com.harmoush.rxapp;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {

    private static ApiManager apiManager;
    private static IWeatherApi service;
    private static final String APP_ID ="f38f5ef06ee3c3219af54d437c4f2437";
    private ApiManager() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(IWeatherApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        service = retrofit.create(IWeatherApi.class);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request.Builder builder = chain.request().newBuilder().
                        header("Authorization",Credentials.basic("aUsername","aPassword"));
                return chain.proceed(builder.build());
            }
        }).build();
        Retrofit retrofit1 = new Retrofit.Builder().baseUrl(IWeatherApi.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public synchronized static ApiManager getInstance() {
        if (apiManager == null) {
            apiManager = new ApiManager();
        }
        return apiManager;
    }
    public void getCurrentWeather(String cityName, final OnWeatherDataFetched onWeatherDataFetched){

        service.getTodayWeather(cityName,APP_ID,"metric").enqueue(new Callback<WeatherModel>() {
            @Override
            public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response) {
                onWeatherDataFetched.onWeatherDataFetchedSuccess(response.body());
            }

            @Override
            public void onFailure(Call<WeatherModel> call, Throwable t) {
                onWeatherDataFetched.onWeatherDataFetchedError(t.getMessage());
            }
        });
    }
}
