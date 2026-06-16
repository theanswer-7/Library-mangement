package com.it.pojo;

// 类名必须与文件名一致，且保持public
public class User {
    // 对应数据库表的字段
    private Integer uid;
    private String uname;
    private Integer uage; // 保留你的字段名，不改为age，以匹配数据库

    // 1. 无参构造器（MyBatis反射必需，必须生成）
    public User() {}

    // 2. Getter & Setter 方法（IDEA自动生成，解决未使用警告）
    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public Integer getUage() {
        return uage;
    }

    public void setUage(Integer uage) {
        this.uage = uage;
    }

    // 3. toString 方法（方便测试打印，可选）
    @Override
    public String toString() {
        return "User{uid=" + uid + ", uname='" + uname + "', uage=" + uage + "}";
    }
}