package com.it.controller;

import com.it.pojo.Message;
import com.it.pojo.User;
import com.it.service.MessageService;
import com.it.service.UserService;
import com.it.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/message")
public class MessageController {

    @Autowired
    MessageService messageService;
    @Autowired
    UserService userService;

    @RequestMapping("/sendRemind")
    @ResponseBody
    public Result sendRemind(String jieShouRen, String bookName, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("USER_SESSION");
        Message message = new Message();
        message.setJieShouRen(jieShouRen);
        message.setFaShongRen(user.getName());
        message.setContent("您好，" + jieShouRen + "，您借阅的《" + bookName + "》已超期，请尽快归还！");
        message.setStatus(0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        message.setSendTime(sdf.format(new Date()));
        int count = messageService.addMessage(message);
        if (count > 0) {
            return new Result(true, "提醒消息已发送", null);
        } else {
            return new Result(false, "发送失败", null);
        }
    }

    // 查看自己的消息列表
    @RequestMapping("/myMessages")
    public String myMessages(HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("USER_SESSION");
        List<Message> messages = messageService.selectByReceiver(user.getName());
        model.addAttribute("messages", messages);
        return "message_list";
    }

    // 查看消息详情
    @RequestMapping("/findById")
    @ResponseBody
    public Result<Message> findById(int id) {
        Message message = messageService.findById(id);
        if (message != null) {
            return new Result<>(true, "查询成功", message);
        } else {
            return new Result<>(false, "查询失败", null);
        }
    }

    // 确认已读
    @RequestMapping("/markRead")
    @ResponseBody
    public Result markRead(int id) {
        int count = messageService.markRead(id);
        if (count > 0) {
            return new Result(true, "已确认阅读", null);
        } else {
            return new Result(false, "操作失败", null);
        }
    }

    // 申请解除黑名单
    // 获取管理员列表
    @RequestMapping("/getAdmins")
    @ResponseBody
    public Result<java.util.List<User>> getAdmins() {
        java.util.List<User> admins = userService.findAllAdmins();
        return new Result<java.util.List<User>>(true, "查询成功", admins);
    }

    // 申请解除黑名单
    @RequestMapping("/sendUnbanRequest")
    @ResponseBody
    public Result sendUnbanRequest(String jieShouRen, String content, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("USER_SESSION");
        Message message = new Message();
        message.setJieShouRen(jieShouRen);
        message.setFaShongRen(user.getName());
        message.setContent("【黑名单解除申请】来自 " + user.getName() + "：" + content);
        message.setStatus(0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        message.setSendTime(sdf.format(new Date()));
        int count = messageService.addMessage(message);
        if (count > 0) {
            return new Result(true, "申请已发送，等待管理员审核", null);
        } else {
            return new Result(false, "发送失败", null);
        }
    }
}
