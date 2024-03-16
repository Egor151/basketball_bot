package org.example.basketball.service;

import org.example.basketball.bot.BotInit;
import org.example.basketball.config.BotConfig;
import org.example.basketball.config.Command;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import org.slf4j.LoggerFactory;

@Service
public class BotConfigService {
    private static final Logger logger = LoggerFactory.getLogger(BotConfigService.class);
    private final BotConfigRepo botConfigRepo;
    @Autowired
    public BotConfigService(BotConfigRepo botConfigRepo) {
        this.botConfigRepo = botConfigRepo;
    }
    public List<BotConfig> getAllBotConfigs() {
        try {
            return botConfigRepo.findAll();
        } catch (Exception e) {
            logger.error("Error while fetching BotConfig data from the database.", e);
            return Collections.emptyList();
        }
    }
    public String getTelegramCallbackAnswerTemp(){
        return this.botConfigRepo.findAll().get(0).getTelegramCallbackAnswerTemp();
    }

    public String getNowApiTemp(){
        return this.botConfigRepo.findAll().get(0).getNowWeatherApiTemp();
    }

    public List<Command> getAllCommands(){
        return botConfigRepo.findAll().get(0).getCommands();
    }

    public String getBotUsername() {
        List<BotConfig> configs = botConfigRepo.findAll();
        if (!configs.isEmpty()) {
            return configs.get(0).getName();
        }
        return null;
    }

    public String getBotAccessToken() {
        List<BotConfig> configs = botConfigRepo.findAll();
        if (!configs.isEmpty()) {
            return configs.get(0).getAccessToken();
        }
        return null;
    }

    public void update() {
        botConfigRepo.save(BotConfig.getConfig());
    }
}