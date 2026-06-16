package com.it.service;

import com.it.pojo.Message;

import java.util.List;

public interface MessageService {
    int addMessage(Message message);
    List<Message> selectByReceiver(String jieShouRen);
    Message findById(int id);
    int markRead(int id);
}
