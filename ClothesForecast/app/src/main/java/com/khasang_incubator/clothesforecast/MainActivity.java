package com.khasang_incubator.clothesforecast;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();

    }

    private void initUI() {
        tvResponse = (TextView) findViewById(R.id.tvResponse);
        etCityName = (EditText) findViewById(R.id.etCityName);
        ((Button) findViewById(R.id.btnFetch)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etCityName.getText().toString().trim().isEmpty()) {
                    new FetchForecastTask().execute(etCityName.getText().toString());
                } else {
                    Toast.makeText(MainActivity.this, "Enter City Name", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void onResponseReceived(String response) {
        tvResponse.setText(response);
    }

    public void Show_weather(View view) {
        Intent intent = new Intent(MainActivity.this, ShowWeather.class);
        startActivity(intent);
    }

    private class FetchForecastTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String cityName = params[0];
            String response = null;
            try {
                response = new String(getUrlBytes(RequestMaker.getForecastFor(cityName)));
                Logger.d(response);
            } catch (IOException e) {
                e.printStackTrace();
                response = "Something Wrong";
            }
            return response;
        }

        @Override
        protected void onPostExecute(String s) {
            onResponseReceived(s);
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
