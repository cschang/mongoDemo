package com.line.mongoDemo.handle;

import com.line.mongoDemo.entity.LineUser;
import com.line.mongoDemo.repository.service.LineUserService;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.FileMessageContent;
import com.linecorp.bot.model.event.message.ImageMessageContent;
import com.linecorp.bot.model.event.message.MessageContent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@LineMessageHandler
public class CallBackHandle {

    @Autowired
    private LineUserService service;

    @EventMapping
    public Message handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
        log.info("event: " + event);

        if ( event.getMessage() instanceof  TextMessageContent ){
            handleMongoOperate(event.getSource().getUserId(),event.getMessage());
            String originalMessageText =((TextMessageContent)event.getMessage()) .getText();
            return new TextMessage(originalMessageText);
        } else {
            return new TextMessage("receive your data");
        }

    }
    private void handleMongoOperate(String userId, TextMessageContent message) {
        service.findByUserId(userId).ifPresentOrElse(lineUser->{
            log.info("exist: "+ lineUser);
            lineUser.addConetnts(message);
            service.addLineUser(lineUser);
        }, ()->{
            log.info("no exist " );
            service.addLineUser(new LineUser(userId,message));
        });
    }
}
