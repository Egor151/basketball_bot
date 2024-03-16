package org.example.basketball.bot;

import lombok.Getter;
import org.example.basketball.service.BotConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class WeatherBot extends TelegramLongPollingBot {
    private static final Logger logger = Logger.getLogger(WeatherBot.class.getName());
    @Autowired
    @Getter
    private  BotConfigService botConfigService;
    protected WeatherBotFacade weatherBotFacade;

    public WeatherBot() {
        super("6779438998:AAEcXESU77Ji2-lFqvmk92CqszIHGA4iHxo");
    }

    @Override
    public String getBotUsername() {
        return botConfigService.getBotUsername();
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            weatherBotFacade.handleUpdate(update);
        } catch (IOException | InterruptedException e) {
            logger.log(Level.SEVERE, "Ошибка при обработке обновления", e);
        }
    }
}