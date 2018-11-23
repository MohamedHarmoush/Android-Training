package com.harmoush.rxapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.et_city_name)
    EditText mCityNameEditText;
    @BindView(R.id.btn_search)
    Button mSearchBtn;
    @BindView(R.id.tv_city_name)
    TextView mCityNameTextView;
    @BindView(R.id.tv_weather)
    TextView mWeatherDescriptionTextView;
    @BindView(R.id.tv_pressure)
    TextView mPressureTextView;
    @BindView(R.id.tv_humidity)
    TextView mHumidityTextView;
    @BindView(R.id.tv_temp)
    TextView mTempTextView;
    @BindView(R.id.tv_min_temp)
    TextView mMinTempTextView;
    @BindView(R.id.tv_max_temp)
    TextView mMaxTempTextView;
    private String cityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initUi();
        mSearchBtn.setOnClickListener(view -> ApiManager.getInstance().getCurrentWeather(mCityNameEditText.getText().toString(), new OnWeatherDataFetched() {
            @Override
            public void onWeatherDataFetchedSuccess(WeatherModel weatherData) {
                updateUi(weatherData);
            }

            @Override
            public void onWeatherDataFetchedError(String message) {
                printMessage(message);
            }
        }));
    }

    private void initUi() {

    }

    private void printMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void updateUi(WeatherModel weatherData) {
        cityName = mCityNameEditText.getText().toString();
        mCityNameTextView.setText(cityName);
        mWeatherDescriptionTextView.setText(weatherData.getWeather().get(0).getDescription());
        mMaxTempTextView.setText(String.format("MAX: %.1f °",weatherData.getConditions().getTemp_max()));
        mMinTempTextView.setText(String.format("Min: %.1f °",weatherData.getConditions().getTemp_min()));
        mTempTextView.setText(String.format("%.1f °",weatherData.getConditions().getTemp()));
        mHumidityTextView.setText(String.format("Humidity: %d %%",weatherData.getConditions().getHumidity()));
        mPressureTextView.setText(String.format("Pressure: %d hPa",weatherData.getConditions().getPressure()));
    }
}
