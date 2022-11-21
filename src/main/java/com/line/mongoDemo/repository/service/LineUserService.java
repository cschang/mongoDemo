package com.line.mongoDemo.repository.service;

import com.line.mongoDemo.entity.LineUser;
import com.linecorp.bot.model.event.message.MessageContent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface LineUserService {
    public LineUser addLineUser(LineUser lineUser);

    Optional<LineUser> findByUserId(String userId);
    List<TextMessageContent> findMessageByUserId(String userId);
    String deleteById(String userId);

}
