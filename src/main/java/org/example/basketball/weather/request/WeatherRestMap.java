package org.example.basketball.weather.request;

import org.example.basketball.service.BotConfigService;
import org.example.basketball.weather.answer.WeatherNow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class WeatherRestMap {
    private static final Logger logger = LoggerFactory.getLogger(WeatherRestMap.class);
    protected RestTemplate restTemplate;

    private final BotConfigService botConfigService;
    @Autowired
    public WeatherRestMap(BotConfigService botConfigService) {
        this.botConfigService = botConfigService;
    }

    //получение текущей погоды
    public WeatherNow getNowWeather(String city){
        try {
            return restTemplate.getForObject(botConfigService.getNowApiTemp()
                            .replace("{city}",city),
                    WeatherNow.class);
        }catch (Exception e){
            logger.error("An exception occurred while checking the city existence: ", e);
            return null;
        }
    }

    //проверка существования города
    public boolean isCity(String city) throws IOException {

        URL weatherApiUrl = new URL(botConfigService.getNowApiTemp().replace("{city}",city));

        HttpURLConnection weatherApiConnection = (HttpURLConnection)weatherApiUrl.openConnection();
        weatherApiConnection.setRequestMethod("GET");
        weatherApiConnection.connect();
        return weatherApiConnection.getResponseCode() == HttpURLConnection.HTTP_OK;
    }
}