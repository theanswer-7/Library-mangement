package com.it.service.impl;

import com.it.dao.BookYuyueMapper;
import com.it.pojo.BookYuyue;
import com.it.service.BookYuyueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookYuyueServiceImpl implements BookYuyueService {

    @Autowired
    BookYuyueMapper bookYuyueMapper;

    @Override
    public int addYuyue(BookYuyue yuyue) {
        return bookYuyueMapper.addYuyue(yuyue);
    }

    @Override
    public List<BookYuyue> findByBookId(int bookId) {
        return bookYuyueMapper.findByBookId(bookId);
    }

    @Override
    public int checkYuyued(int bookId, String userName) {
        return bookYuyueMapper.checkYuyued(bookId, userName);
    }

    @Override
    public int deleteByBookId(int bookId) {
        return bookYuyueMapper.deleteByBookId(bookId);
    }
}
