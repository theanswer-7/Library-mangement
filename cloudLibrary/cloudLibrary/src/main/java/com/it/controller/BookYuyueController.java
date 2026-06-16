package com.it.controller;

import com.it.pojo.BookYuyue;
import com.it.pojo.User;
import com.it.service.BookYuyueService;
import com.it.service.UserService;
import com.it.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/yuyue")
public class BookYuyueController {

    @Autowired
    BookYuyueService bookYuyueService;
    @Autowired
    UserService userService;

    // 预约借阅
    @RequestMapping("/reserve")
    @ResponseBody
    public Result reserve(int bookId, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("USER_SESSION");
        if (user == null) {
            return new Result(false, "请先登录", null);
        }
        // 黑名单用户不能预约
        User dbUser = userService.findById(user.getId());
        if (dbUser.getCanBorrow() != null && dbUser.getCanBorrow() == 1) {
            return new Result(false, "您已被加入黑名单，无法预约借阅，请联系管理员", null);
        }
        // 检查是否已预约
        if (bookYuyueService.checkYuyued(bookId, user.getName()) > 0) {
            return new Result(false, "您已预约过该图书", null);
        }
        BookYuyue yuyue = new BookYuyue();
        yuyue.setBookId(bookId);
        yuyue.setUserName(user.getName());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        yuyue.setYuyueTime(sdf.format(new Date()));
        int count = bookYuyueService.addYuyue(yuyue);
        if (count > 0) {
            return new Result(true, "预约成功，图书可借阅时将通知您", null);
        } else {
            return new Result(false, "预约失败", null);
        }
    }

    // 检查是否已预约
    @RequestMapping("/checkYuyued")
    @ResponseBody
    public Result checkYuyued(int bookId, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("USER_SESSION");
        if (user == null) {
            return new Result(false, "请先登录", null);
        }
        boolean yuyued = bookYuyueService.checkYuyued(bookId, user.getName()) > 0;
        return new Result(true, "", yuyued);
    }
}
