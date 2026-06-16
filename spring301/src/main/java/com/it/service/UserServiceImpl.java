package com.it.service;

import com.it.dao.UserDao;

public class UserServiceImpl implements UserService {

    // 必须定义这个属性，并且提供 setter 方法
    private UserDao userDao;

    // 必须提供 setUserDao 方法，Spring 才能注入
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean login(String username, String password) {
        return userDao.login(username, password);
    }
}