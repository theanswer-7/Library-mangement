package com.it.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/test") //请求的映射地址/test如果你的url地址包含/test，
// 那么TestController这个类就会接受到这个请求
@Controller //用于生成bean对象，同时表示当前这个类可以处理请求
public class TestController {
    @GetMapping("/get")
    public String getName(){
        System.out.println("Hello");
        return "books";
    }
}
