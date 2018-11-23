package com.harmoush.rxapp;

public interface OnWeatherDataFetched {
    void onWeatherDataFetchedSuccess(WeatherModel weatherData);
    void onWeatherDataFetchedError(String message);
}
