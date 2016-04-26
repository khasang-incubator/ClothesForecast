package com.khasang_incubator.clothesforecast;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.khasang_incubator.clothesforecast.database.Wardrobe;
import com.khasang_incubator.clothesforecast.helpers.Adviser;
import com.khasang_incubator.clothesforecast.helpers.Converter;
import com.khasang_incubator.clothesforecast.helpers.Logger;
import com.khasang_incubator.clothesforecast.helpers.RequestMaker;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private TextView tvResponse;
    private EditText etCityName;
    private Button btnFetchWeather;
    private Button btnFetchForecast;
    private ProgressBar pBar;

    private Adviser adviser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();

        adviser = new Adviser(new Wardrobe(this));
    }

    private void initUI() {
        tvResponse = (TextView) findViewById(R.id.tvResponse);
        etCityName = (EditText) findViewById(R.id.etCityName);
        btnFetchWeather = (Button) findViewById(R.id.btn_fetch_weather);
        btnFetchForecast = (Button) findViewById(R.id.btn_fetch_forecast);
        pBar = (ProgressBar) findViewById(R.id.progress_bar);
    }

    public void onButtonClick(View view) {
        switch (view.getId()) {
            case R.id.btn_fetch_weather:
                new FetchTask(RequestMaker.TYPE_WEATHER, getCityName()).execute();
                break;
            case R.id.btn_fetch_forecast:
                new FetchTask(RequestMaker.TYPE_FORECAST, getCityName()).execute();
                break;
        }
        pBar.setVisibility(View.VISIBLE);
        btnFetchWeather.setEnabled(false);
        btnFetchForecast.setEnabled(false);
    }

    @NonNull
    private String getCityName() {
        return etCityName.getText().toString();
    }

    private void onResponseReceived(int type, String response) {
        pBar.setVisibility(View.GONE);

        switch (type) {
            case RequestMaker.TYPE_WEATHER:
                tvResponse.setText(
                        String.format(
                                "%s\n%s",
                                Converter.convertWeatherResponseToString(response),
                                adviser.getCollection(10.0))
                );
                btnFetchWeather.setBackgroundColor(Color.CYAN);
                btnFetchForecast.setBackgroundColor(Color.TRANSPARENT);
                break;
            case RequestMaker.TYPE_FORECAST:
                tvResponse.setText(Converter.convertForecastResponseToString(response));
                btnFetchForecast.setBackgroundColor(Color.CYAN);
                btnFetchWeather.setBackgroundColor(Color.TRANSPARENT);
                break;
            default:
                tvResponse.setText(response);
                btnFetchForecast.setBackgroundColor(Color.TRANSPARENT);
                btnFetchWeather.setBackgroundColor(Color.TRANSPARENT);
                break;
        }

        btnFetchWeather.setEnabled(true);
        btnFetchForecast.setEnabled(true);
    }

    public void showWeather(View view) {
        Intent intent = new Intent(MainActivity.this, ShowWeather.class);
        startActivity(intent);
    }

    private class FetchTask extends AsyncTask<Void, Void, String> {
        private int requestType;
        private String request;

        public FetchTask(int requestType, String cityName) {
            this.requestType = requestType;
            this.request = RequestMaker.getRequestStringFor(requestType, cityName);
        }

        @Override
        protected String doInBackground(Void... params) {
            String response = null;
            try {
                response = new String(getUrlBytes(request));
                Logger.d(response);
            } catch (IOException e) {
                e.printStackTrace();
                requestType = RequestMaker.ERROR;
                response = "Something Wrong";
            }
            return response;
        }

        @Override
        protected void onPostExecute(String s) {
            onResponseReceived(requestType, s);
        }

        private byte[] getUrlBytes(String urlSpec) throws IOException {
            URL url = new URL(urlSpec);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            try {
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                InputStream in = connection.getInputStream();
                if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    return null;
                }

                int bytesRead = 0;
                byte[] buffer = new byte[1024];
                while ((bytesRead = in.read(buffer)) > 0) {
                    out.write(buffer, 0, bytesRead);
                }
                out.close();
                return out.toByteArray();
            } finally {
                connection.disconnect();
            }

        }
    }
}
