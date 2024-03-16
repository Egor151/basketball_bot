package org.example.basketball.weather.answer;


import lombok.Getter;
import lombok.NoArgsConstructor;

import lombok.Setter;
import org.example.basketball.weather.answer.Main;
import org.example.basketball.weather.answer.Weather;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class WeatherNow {
    private List<Weather> weather;
    private Main main;

}

