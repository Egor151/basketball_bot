package org.example.basketball.bot;

import org.example.basketball.config.BotConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.List;

@Component
public class BotInit {
    private static final Logger logger = LoggerFactory.getLogger(BotInit.class);
    private final WeatherBot weatherBot;

    @Autowired
    public BotInit(WeatherBot weatherBot) {
        this.weatherBot = weatherBot;
    }

    //после того, как приложение полностью запущено
    @EventListener({ApplicationReadyEvent.class})
    public void init() throws TelegramApiException {
        try {
            //weatherBot.getBotConfigService().update();
            List<BotConfig> configs = weatherBot.getBotConfigService().getAllBotConfigs();

            if (!configs.isEmpty()) {
                TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
                telegramBotsApi.registerBot(weatherBot);
            } else {
                logger.error("BotConfig is empty. No bot registered.");
            }
        } catch (TelegramApiException e) {
            logger.error("Failed to register bot.", e);
        }
    }
}
