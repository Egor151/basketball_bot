package org.example.basketball.additional;

import com.vdurmont.emoji.EmojiParser;
import org.example.basketball.service.BotConfigService;
import org.example.basketball.weather.answer.WeatherNow;
import org.example.basketball.weather.request.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class MessageGenerator {
    private final BotConfigService botConfigService;
    private final WeatherService weatherService;

    @Autowired
    public MessageGenerator(BotConfigService botConfigService, WeatherService weatherService) {
        this.botConfigService = botConfigService;
        this.weatherService = weatherService;
    }

    public String generateStartMessage(String name){
        return EmojiParser.parseToUnicode("Привет, " + name + " :wave: \nЧтобы узнать, как мной пользоваться - введите /help");
    }

    public String generateHelpMessage() {
        String message = ":sunny: Вот мои доступные команды :sunny:\n\n";
        return EmojiParser.parseToUnicode(
                botConfigService.getAllCommands()
                        .stream()
                        .map(command -> command.getName() + " - " + command.getDescription())
                        .collect(Collectors.joining("\n", message, ""))
        );
    }

    public String generateSuccessCancel(){
        return EmojiParser.parseToUnicode(":white_check_mark: Активная команда успешно отклонена");
    }

    public String generateSuccessSetCity(String city){
        return EmojiParser.parseToUnicode(":white_check_mark: Новый стандартный город - " + city);
    }

    public String generateErrorCity(){
        return EmojiParser.parseToUnicode(":x: Такого города не существует");
    }

    public String generateSuccessGetCity(String city){
        return EmojiParser.parseToUnicode(":cityscape: Стандартный город - " + city);
    }

    public String generateErrorGetCity(){
        return EmojiParser.parseToUnicode(":x: Стандартный город не назначен");
    }

    public String generateCurrentWeather(String city){
        WeatherNow weatherNow = weatherService.getCurrentWeather(city);
        return EmojiParser.parseToUnicode("Текущая погода\n\n" +
                "В городе " + city + " " + weatherNow.getWeather().get(0).getDescription() + "\n" +
                ":thermometer: Температура: " + weatherNow.getMain().getTemp() + "°C, ощущается как " + weatherNow.getMain().getFeelsLike() + "°C");
    }
}
