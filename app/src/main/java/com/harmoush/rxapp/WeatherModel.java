package com.harmoush.rxapp;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class WeatherModel {

    @SerializedName("weather")
    private ArrayList<WeatherDescription> weather;
    @SerializedName("main")
    private WeatherConditions conditions;

    public class WeatherDescription {
        private String description;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

    public ArrayList<WeatherDescription> getWeather() {
        return weather;
    }

    public void setWeather(ArrayList<WeatherDescription> weather) {
        this.weather = weather;
    }

    public WeatherConditions getConditions() {
        return conditions;
    }

    public void setConditions(WeatherConditions conditions) {
        this.conditions = conditions;
    }

    public class WeatherConditions {
        private float temp;
        private int pressure;
        private int humidity;
        private float temp_max;
        private float temp_min;

        public float getTemp() {
            return temp;
        }

        public void setTemp(float temp) {
            this.temp = temp;
        }

        public int getPressure() {
            return pressure;
        }

        public void setPressure(int pressure) {
            this.pressure = pressure;
        }

        public int getHumidity() {
            return humidity;
        }

        public void setHumidity(int humidity) {
            this.humidity = humidity;
        }

        public float getTemp_max() {
            return temp_max;
        }

        public void setTemp_max(float temp_max) {
            this.temp_max = temp_max;
        }

        public float getTemp_min() {
            return temp_min;
        }

        public void setTemp_min(float temp_min) {
            this.temp_min = temp_min;
        }
    }
}
