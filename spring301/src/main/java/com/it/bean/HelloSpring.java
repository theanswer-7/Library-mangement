package com.it.bean;

public class HelloSpring {
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void show(){
        System.out.println(userName+"欢迎您来到spring");
    }
}
