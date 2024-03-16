package org.example.basketball.config;

import lombok.*;
import org.example.basketball.condition.BotState;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.math.BigInteger;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "chats_config")
public class ChatConfig {
    @Id
    private BigInteger id;

    @NonNull
    @Field(targetType = FieldType.STRING)
    private BotState botState = BotState.DEFAULT;

    private String city;

    @NonNull
    private Long chatId;

    public ChatConfig(@NonNull Long chatId, @NonNull BotState botState) {
        this.botState = botState;
        this.chatId = chatId;
    }

    public @NonNull BotState getBotState() {
        return botState;
    }

    public void setBotState(@NonNull BotState botState) {
        this.botState = botState;
    }

}