package com.line.mongoDemo.api;

import com.line.mongoDemo.entity.LineUser;
import com.line.mongoDemo.repository.service.LineUserService;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.event.message.MessageContent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.response.BotApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
@Slf4j
@RestController
@RequestMapping("line")
public class ResourceController {

    @Autowired
    private LineUserService service;

    @Value("${line.bot.channel-token}")
    private String token;

    private LineMessagingClient client ;

    @PostMapping
    public void pushDataToServer(@RequestBody Map<String, String> map){
        if (map == null || map.get("to") == null  || map.get("text") == null) {
            throw new RuntimeException("no data");
        }
        if (client == null) {
            client = LineMessagingClient
                    .builder(token)
                    .build();
        }
        PushMessage pushMessage =
                new PushMessage(map.get("to"), new TextMessage(map.get("text")));

        BotApiResponse botApiResponse;
        try {
            botApiResponse = client.pushMessage(pushMessage).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return;
        }
        log.info("botApiResponse: {}",botApiResponse);
    }

    @GetMapping("{userId}")
    public List<TextMessageContent> getMessageByUserId(@PathVariable("userId") String userId){
        log.info("userId: {}", userId);
        return service.findMessageByUserId(userId);
    }
    @DeleteMapping("{userId}")
    public String deleteByUserId(@PathVariable("userId") String userId){
        return service.deleteById(userId);
    }

}
