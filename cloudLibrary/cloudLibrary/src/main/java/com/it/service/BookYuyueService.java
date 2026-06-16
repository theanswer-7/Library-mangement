package com.it.service;

import com.it.pojo.BookYuyue;

import java.util.List;

public interface BookYuyueService {
    int addYuyue(BookYuyue yuyue);
    List<BookYuyue> findByBookId(int bookId);
    int checkYuyued(int bookId, String userName);
    int deleteByBookId(int bookId);
}
