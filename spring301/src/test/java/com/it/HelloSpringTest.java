package com.it;

import com.it.bean.HelloSpring;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloSpringTest {

    public static void main(String[] args) {
//        加载applicationConext配置文件
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
//        获取applicationContext.xml中的实例对象
        HelloSpring helloSpring= (HelloSpring) applicationContext.getBean("helloSpring");
        helloSpring.show();
    }
}
