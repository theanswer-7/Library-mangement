package com.it.service.impl;

import com.github.pagehelper.PageHelper;
import com.it.dao.BookMapper;
import com.it.dao.BookYuyueMapper;
import com.it.dao.MessageMapper;
import com.it.dao.RecordMapper;
import com.it.pojo.Book;
import com.it.pojo.BookYuyue;
import com.it.pojo.Message;
import com.it.pojo.Record;
import com.it.pojo.User;
import com.it.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookMapper bookMapper;
    @Autowired
    RecordMapper recordMapper;
    @Autowired
    BookYuyueMapper bookYuyueMapper;
    @Autowired
    MessageMapper messageMapper;
    @Override
    public List<Book> selectNewBook() {
        return bookMapper.selectNewBook();
    }

    @Override
    public Book findById(int id) {
        return bookMapper.findById(id);
    }

    @Override
    public int borrowBook(Book book) {
        book.setStatus("1");
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        book.setBorrowTime(dateFormat.format(new Date()));
        return bookMapper.editBook(book);
    }

    @Override
    public int addBook(Book book) {
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        book.setUploadTime(dateFormat.format(new Date()));
        return bookMapper.addBook(book);
    }

    @Override
    public int editBook(Book book) {
        return bookMapper.editBook(book);
    }

    @Override
    public List<Book> searchBorrowed(Book search, User user, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        search.setBorrower(user.getName());
        if ("ADMIN".equals(user.getRole())) {
            return bookMapper.selectBorrowed(search);
        } else {
            return bookMapper.selectMyBorrowed(search);
        }
    }

    @Override
    public boolean returnBook(String id, User user) {
        Book book = bookMapper.findById(Integer.parseInt(id));
        boolean rb = book.getBorrower().equals(user.getName());
        if (rb) {
            book.setStatus("2");
            int count = bookMapper.editBook(book);
            if (count > 0) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    public int renewBook(Integer id) {
        Book book = bookMapper.findById(id);
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.add(java.util.Calendar.DAY_OF_MONTH, 5);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        book.setReturnTime(sdf.format(cal.getTime()));
        return bookMapper.editBook(book);
    }

    @Override
    public int returnConfirm(String id) {
        Book book = bookMapper.findById(Integer.parseInt(id));
        Record record = new Record();
        record.setBookname(book.getName());
        record.setBookisbn(book.getIsbn());
        record.setBorrower(book.getBorrower());
        record.setBorrowTime(book.getBorrowTime());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        record.setRemandTime(sdf.format(new Date()));

        book.setStatus("0");
        book.setBorrower("");
        book.setBorrowTime("");
        book.setReturnTime("");

        int num = 0;
        try {
            num = bookMapper.editBook(book);
            if (num > 0) {
                recordMapper.addRecord(record);
                // 通知预约该书的用户
                List<BookYuyue> yuyueList = bookYuyueMapper.findByBookId(book.getId());
                for (BookYuyue yy : yuyueList) {
                    Message msg = new Message();
                    msg.setJieShouRen(yy.getUserName());
                    msg.setFaShongRen("系统");
                    msg.setContent("您预约的《" + book.getName() + "》已可借阅，请尽快到图书馆借阅！");
                    msg.setStatus(0);
                    msg.setSendTime(sdf.format(new Date()));
                    messageMapper.addMessage(msg);
                }
                // 清除该书的所有预约记录
                bookYuyueMapper.deleteByBookId(book.getId());
            }
        } catch (Exception e) {
            System.out.println("有异常: " + e);
        }
        return num;
    }

    @Override
    public List<Book> searchBooks(Book search, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return bookMapper.searchBooks(search);
    }

    @Override
    public List<Book> searchOverdue(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return bookMapper.selectOverdueBooks();
    }

    @Override
    public List<Book> searchReservable(Book search, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return bookMapper.selectReservableBooks(search);
    }

    @Override
    public java.util.List<java.util.Map<String, Object>> selectBlacklistCandidates() {
        return bookMapper.selectBlacklistCandidates();
    }
}
