package edu.neu.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;

import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class A6_weather extends AppCompatActivity {

    EditText etCity, etCountyCode;
    TextView tv_weatherDetails;
    Button bt_checkweather;
    private final String url = "https://api.openweathermap.org/data/2.5/weather";
    private final String appid = "e53301e27efa0b66d05045d91b2742d3";
    DecimalFormat df = new DecimalFormat("#.##");

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a6_weather);
        etCity = findViewById(R.id.etCity);
        etCountyCode = findViewById(R.id.etCountyCode);
        tv_weatherDetails = findViewById(R.id.tv_weatherDetails);
        bt_checkweather = findViewById(R.id.bt_getWeather);

        bt_checkweather.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                sendRequestWithHttpURLConnection();
            }
        });
    }

    private void sendRequestWithHttpURLConnection() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                String tempUrl = "";
                try {
                    String city = etCity.getText().toString().trim();
                    String country = etCountyCode.getText().toString().trim();
                    if (city.equals("")) {
                        tv_weatherDetails.setText("City field can not be empty!");
                    } else {
                        if (!country.equals("")) {
                            tempUrl = url + "?q=" + city + "," + country + "&appid=" + appid;
                        } else {
                            tempUrl = url + "?q=" + city + "&appid=" + appid;
                        }
                    }
                    URL url = new URL(tempUrl);

                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    if (connection.getResponseCode() == 200) {
                        InputStream in = connection.getInputStream();
                        reader = new BufferedReader(new InputStreamReader(in));
                        StringBuilder response = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            response.append(line);
                        }
                        pareJSON1(response.toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

    private void pareJSON1(String jsonData){
        String output = "";
        try {
            JSONObject jsonResponse = new JSONObject(jsonData);
            JSONArray jsonArray = jsonResponse.getJSONArray("weather");
            JSONObject jsonObjectWeather = jsonArray.getJSONObject(0);
            String description = jsonObjectWeather.getString("description");
            JSONObject jsonObjectMain = jsonResponse.getJSONObject("main");
            double temp = jsonObjectMain.getDouble("temp") - 273.15;
            double feelsLike = jsonObjectMain.getDouble("feels_like") - 273.15;
            float pressure = jsonObjectMain.getInt("pressure");
            int humidity = jsonObjectMain.getInt("humidity");
            JSONObject jsonObjectWind = jsonResponse.getJSONObject("wind");
            String wind = jsonObjectWind.getString("speed");
            JSONObject jsonObjectClouds = jsonResponse.getJSONObject("clouds");
            String clouds = jsonObjectClouds.getString("all");
            JSONObject jsonObjectSys = jsonResponse.getJSONObject("sys");
            String countryName = jsonObjectSys.getString("country");
            String cityName = jsonResponse.getString("name");

            output += "Current weather of " + cityName + " (" + countryName + ")"
                    + " Temp: " + df.format(temp) + " °C"
                    + "\n Feels Like: " + df.format(feelsLike) + " °C"
                    + "\n Humidity: " + humidity + "%"
                    + "\n Description: " + description
                    + "\n Wind Speed: " + wind + "m/s (meters per second)"
                    + "\n Cloudiness: " + clouds + "%"
                    + "\n Pressure: " + pressure + " hPa";
            tv_weatherDetails.setText(output);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}


