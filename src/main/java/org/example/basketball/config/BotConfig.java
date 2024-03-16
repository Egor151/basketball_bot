package org.example.basketball.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.math.BigInteger;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@Document(collection = "bot_config")
public class BotConfig {
    @Id
    private BigInteger id;

    private String name = "basketball_bot⛹️";

    private String accessToken = "6779438998:AAEcXESU77Ji2-lFqvmk92CqszIHGA4iHxo";

    private String nowWeatherApiTemp = "https://api.openweathermap.org/data/2.5/weather?q={city}&appid=9ae3deab5a88fb0c8e21d0f94013fa85&units=metric&lang=ru";

    private String telegramCallbackAnswerTemp = "https://api.telegram.org/bot6779438998:AAEcXESU77Ji2-lFqvmk92CqszIHGA4iHxo/answerCallbackQuery?callback_query_id={id}";

    private List<Command> commands;

    public static BotConfig getConfig(){
        return new BotConfig();
    }

}

