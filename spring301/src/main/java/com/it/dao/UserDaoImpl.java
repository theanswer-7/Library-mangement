package com.it.dao;

public class UserDaoImpl implements UserDao {
    @Override
    public boolean login(String username, String password) {
        return "admin".equals(username) && "123".equals(password);
    }
}