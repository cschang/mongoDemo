package com.line.mongoDemo.repository.service.impl;

import com.line.mongoDemo.entity.LineUser;
import com.line.mongoDemo.repository.LineRepository;
import com.line.mongoDemo.repository.service.LineUserService;
import com.linecorp.bot.model.event.message.MessageContent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class LineUserServiceImpl implements LineUserService {
    @Autowired
    private LineRepository linrRepository;
    @Override
    public LineUser addLineUser(LineUser lineUser){
        return linrRepository.save(lineUser);
    }

    @Override
    public Optional<LineUser> findByUserId(String id){
        return linrRepository.findById(id);
    }


    @Override
    public List<TextMessageContent> findMessageByUserId(String userId) {
        Optional<LineUser> lineUser = linrRepository.findById(userId);
        return lineUser.get().getContents();
    }

    @Override
    public String deleteById(String userId) {
        linrRepository.deleteById(userId);
        return userId +" is deleted";
    }

}
