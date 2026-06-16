package com.it;  // 你的测试类包名

import com.it.bean.User2;  // 关键：导入 User2
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class User2Test {
    public static void main(String[] args) {
        ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
        User2 user2 = ctx.getBean("user2", User2.class);
        System.out.println(user2);
    }
}