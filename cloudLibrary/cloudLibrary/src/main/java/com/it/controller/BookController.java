package com.it.controller;

import com.github.pagehelper.Page;
import com.it.pojo.Book;
import com.it.pojo.User;
import com.it.service.BookService;
import com.it.utils.PageResult;
import com.it.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookService bookService;
    @GetMapping("/selectNewbooks")
    //ModelAndView:返回视图和数据
    public ModelAndView selectNewBooks(){
        List<Book> books=bookService.selectNewBook();
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("books_new");
        modelAndView.addObject("newbooks",books);
        return modelAndView;
    }
    @GetMapping("/findById")
    @ResponseBody //将java对象转变成前段能识别的json数据
    public Result<Book> findBookById(int id){
        Book book=bookService.findById(id);
        if(book!=null){
            return new Result<>(true,"查询图书成功",book);
        }else {
            return new Result<>(false,"查询图书失败",null);
        }
    }
    //保存
    @RequestMapping("/borrowBook")
    @ResponseBody
    public Result borrowBook(Book book, HttpServletRequest request){
        User user=(User)request.getSession().getAttribute("USER_SESSION");
        if (user == null) {
            return new Result<>(false, "请先登录", null);
        }
        if (user.getCanBorrow() != null && user.getCanBorrow() == 1) {
            return new Result<>(false, "您已被加入黑名单，无法借书，请联系管理员", null);
        }
        book.setBorrower(user.getName());
        int count=bookService.borrowBook(book);
        if(count>0){
            return new Result<>(true,"借阅图书成功",null);
        }else {
            return new Result<>(false,"借阅图书失败",null);
        }
    }
    @RequestMapping("/addBook")
    @ResponseBody
    public Result addBook(Book book) {
        int count = bookService.addBook(book);
        if (count > 0) {
            return new Result(true, "新增图书成功", null);
        } else {
            return new Result(false, "新增图书失败", null);
        }
    }

    @RequestMapping("/editBook")
    @ResponseBody
    public Result editBook(Book book) {
        int count = bookService.editBook(book);
        if (count > 0) {
            return new Result(true, "编辑图书成功", null);
        } else {
            return new Result(false, "编辑图书失败", null);
        }
    }

    @RequestMapping("/searchBorrowed")
    public ModelAndView searchBorrowed(Book book, Integer pageNum, Integer pageSize, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("USER_SESSION");
        if (pageNum == null) pageNum = 1;
        if (pageSize == null) pageSize = 10;
        List<Book> books = bookService.searchBorrowed(book, user, pageNum, pageSize);
        Page page = (Page) books;
        PageResult pageResult = new PageResult(page.getTotal(), books);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("book_borrowed");
        modelAndView.addObject("pageResult", pageResult);
        modelAndView.addObject("pageNum", pageNum);
        modelAndView.addObject("gourl", "/book/searchBorrowed");
        modelAndView.addObject("search", book);
        modelAndView.addObject("now", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        return modelAndView;
    }

    @RequestMapping("/renewBook")
    @ResponseBody
    public Result renewBook(Integer id) {
        int count = bookService.renewBook(id);
        if (count > 0) {
            return new Result(true, "续借成功，归还日期已延长5天", null);
        } else {
            return new Result(false, "续借失败", null);
        }
    }

    @RequestMapping("/returnBook")
    @ResponseBody
    public Result<Book> returnBook(String id, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("USER_SESSION");
        boolean flag = bookService.returnBook(id, user);
        if (flag) {
            return new Result<Book>(true, "还书确认中，请将书拿到行政中心处归还", null);
        } else {
            return new Result<Book>(false, "还书失败", null);
        }
    }

    @RequestMapping("/returnConfirm")
    @ResponseBody
    public Result returnConfirm(String id) {
        Integer count = bookService.returnConfirm(id);
        if (count > 0) {
            return new Result(true, "确认成功！", null);
        } else {
            return new Result(false, "确认失败！", null);
        }
    }

    @RequestMapping("/search")
    public ModelAndView search(Book book, Integer pageNum, Integer pageSize) {
        if (pageNum == null) pageNum = 1;
        if (pageSize == null) pageSize = 10;
        List<Book> books = bookService.searchBooks(book, pageNum, pageSize);
        Page page = (Page) books;
        PageResult pageResult = new PageResult(page.getTotal(), books);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("books");
        modelAndView.addObject("pageResult", pageResult);
        modelAndView.addObject("pageNum", pageNum);
        modelAndView.addObject("gourl", "/book/search");
        modelAndView.addObject("search", book);
        return modelAndView;
    }

    // 超时管理：管理员查看所有超时未归还的图书
    @RequestMapping("/searchOverdue")
    public ModelAndView searchOverdue(@RequestParam(defaultValue = "1") int pageNum,
                                      @RequestParam(defaultValue = "10") int pageSize,
                                      HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("USER_SESSION");
        if (!"ADMIN".equals(user.getRole())) {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("book_overdue");
            mav.addObject("pageResult", new PageResult(0, new java.util.ArrayList<>()));
            mav.addObject("pageNum", 1);
            mav.addObject("gourl", "/book/searchOverdue");
            return mav;
        }
        List<Book> books = bookService.searchOverdue(pageNum, pageSize);
        // 计算每本书的超时天数
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String todayStr = sdf.format(new Date());
        for (Book book : books) {
            try {
                Date returnDate = sdf.parse(book.getReturnTime());
                Date today = sdf.parse(todayStr);
                long diff = today.getTime() - returnDate.getTime();
                book.setOverdueDays((int) (diff / (1000 * 60 * 60 * 24)));
            } catch (Exception e) {
                book.setOverdueDays(0);
            }
        }
        Page page = (Page) books;
        PageResult pageResult = new PageResult(page.getTotal(), books);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("book_overdue");
        modelAndView.addObject("pageResult", pageResult);
        modelAndView.addObject("pageNum", pageNum);
        modelAndView.addObject("gourl", "/book/searchOverdue");
        return modelAndView;
    }

    // 预约图书列表（status=1或2的图书）
    @RequestMapping("/searchReservable")
    public ModelAndView searchReservable(Book book,
                                          @RequestParam(defaultValue = "1") int pageNum,
                                          @RequestParam(defaultValue = "10") int pageSize) {
        List<Book> books = bookService.searchReservable(book, pageNum, pageSize);
        Page page = (Page) books;
        PageResult pageResult = new PageResult(page.getTotal(), books);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("books_yuyue");
        modelAndView.addObject("pageResult", pageResult);
        modelAndView.addObject("pageNum", pageNum);
        modelAndView.addObject("gourl", "/book/searchReservable");
        modelAndView.addObject("search", book);
        return modelAndView;
    }

    // 黑名单候选人列表
    @RequestMapping("/blacklistCandidates")
    public ModelAndView blacklistCandidates(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("USER_SESSION");
        if (!"ADMIN".equals(user.getRole())) {
            return new ModelAndView("book_blacklist");
        }
        java.util.List<java.util.Map<String, Object>> list = bookService.selectBlacklistCandidates();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("book_blacklist");
        modelAndView.addObject("candidates", list);
        return modelAndView;
    }
}
