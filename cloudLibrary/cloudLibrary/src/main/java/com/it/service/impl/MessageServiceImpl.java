package com.it.service.impl;

import com.it.dao.MessageMapper;
import com.it.pojo.Message;
import com.it.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageMapper messageMapper;

    @Override
    public int addMessage(Message message) {
        return messageMapper.addMessage(message);
    }

    @Override
    public List<Message> selectByReceiver(String jieShouRen) {
        return messageMapper.selectByReceiver(jieShouRen);
    }

    @Override
    public Message findById(int id) {
        return messageMapper.findById(id);
    }

    @Override
    public int markRead(int id) {
        return messageMapper.markRead(id);
    }

    @Override
    public int deleteMessage(int id) {
        return messageMapper.deleteById(id);
    }
}
