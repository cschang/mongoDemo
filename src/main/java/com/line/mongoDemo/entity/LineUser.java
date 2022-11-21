package com.line.mongoDemo.entity;

import com.linecorp.bot.model.event.message.MessageContent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document("user")
@Data
@NoArgsConstructor
public class LineUser {
    @Id
    private String userId;
    private List<TextMessageContent> contents;
    public LineUser(String userId, TextMessageContent message) {
        this.userId = userId;
        this.contents = new ArrayList<>();
        this.contents.add(message);
    }

    public void addConetnts(TextMessageContent message) {
        this.contents.add(message);
    }
}