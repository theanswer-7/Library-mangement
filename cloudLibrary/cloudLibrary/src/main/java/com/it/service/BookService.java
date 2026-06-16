package com.it.service;

import com.it.pojo.Book;
import com.it.pojo.User;

import java.util.List;

public interface BookService {
    public List<Book> selectNewBook();
    public Book findById(int id);

    int borrowBook(Book book);
    int addBook(Book book);
    int editBook(Book book);
    java.util.List<Book> searchBooks(Book search, Integer pageNum, Integer pageSize);
    java.util.List<Book> searchBorrowed(Book search, User user, Integer pageNum, Integer pageSize);
    boolean returnBook(String id, User user);
    int returnConfirm(String id);
    int renewBook(Integer id);
    java.util.List<Book> searchOverdue(Integer pageNum, Integer pageSize);
    java.util.List<Book> searchReservable(Book search, Integer pageNum, Integer pageSize);
    java.util.List<java.util.Map<String, Object>> selectBlacklistCandidates();
}
