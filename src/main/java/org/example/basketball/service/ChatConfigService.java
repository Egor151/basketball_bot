package org.example.basketball.service;

import org.example.basketball.condition.BotState;
import org.example.basketball.config.ChatConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatConfigService {
    private final ChatConfigRepo chatConfigRepo;
    @Autowired
    public ChatConfigService(ChatConfigRepo chatConfigRepo) {
        this.chatConfigRepo = chatConfigRepo;
    }

    public boolean isChatInit(Long chatId) {
        return chatConfigRepo.findAllByChatId(chatId) != null;
    }

    //создание нового чата
    public void initChat(Long chatId) {
        chatConfigRepo.save(new ChatConfig(chatId, BotState.DEFAULT));
    }

    public void deleteChat(Long chatId) {
        chatConfigRepo.deleteByChatId(chatId);
    }

    public void setBotState(Long chatId, BotState botState) {
        ChatConfig chatConfig = chatConfigRepo.findAllByChatId(chatId);
        chatConfig.setBotState(botState);
        chatConfigRepo.save(chatConfig);
    }

    public BotState getBotState(Long chatId) {
        return chatConfigRepo.findAllByChatId(chatId).getBotState();
    }

    public void setCity(Long chatId, String city) {
        ChatConfig chatConfig = chatConfigRepo.findAllByChatId(chatId);
        chatConfig.setCity(city);
        chatConfigRepo.save(chatConfig);
    }

    public String getCity(Long chatId) {
        return chatConfigRepo.findAllByChatId(chatId).getCity();
    }
}
