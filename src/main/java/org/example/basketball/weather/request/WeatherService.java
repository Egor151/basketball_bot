package org.example.basketball.weather.request;

import org.example.basketball.weather.answer.WeatherNow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class WeatherService {
    private final WeatherRestMap weatherRestMap;
    @Autowired
    public WeatherService(WeatherRestMap weatherRestMap) {
        this.weatherRestMap = weatherRestMap;
    }

    public boolean isCity(String city) throws IOException {
        return weatherRestMap.isCity(city);
    }

    public WeatherNow getCurrentWeather(String city){
        return weatherRestMap.getNowWeather(city);
    }
}
