package org.example.basketball.callback;

import org.example.basketball.service.BotConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class CallbackAnswer {

    private final BotConfigService botConfigService;
    @Autowired
    public CallbackAnswer(BotConfigService botConfigService) {
        this.botConfigService = botConfigService;
    }

    public void callbackAnswer(String callbackId) throws IOException, InterruptedException {
        HttpClient telegramApiClient = HttpClient.newHttpClient();
        HttpRequest telegramCallbackAnswerReq = HttpRequest.newBuilder(URI
                        .create(botConfigService
                                .getTelegramCallbackAnswerTemp()
                                .replace("{token}",botConfigService.getBotAccessToken())
                                .replace("{id}",callbackId)))
                .GET().build();

        telegramApiClient.send(telegramCallbackAnswerReq, HttpResponse.BodyHandlers
                .ofString());
    }
}
