package com.it.bean;

public class User2 {
    private int id;
    private String username;
    private String password;

    // 无参构造（Spring 实例化必须）
    public User2() {
    }

    // 全参构造（可选）
    public User2(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    // Getter & Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    // 修复这里：去掉 return，改为赋值
    public void setPassword(String password) {
        this.password = password;
    }

    // toString 方法（控制台打印对象用）
    @Override
    public String toString() {
        return "User2{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}