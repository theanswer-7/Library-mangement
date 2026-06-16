package com.it.service;

import com.it.pojo.User;
import com.it.utils.PageResult;

import java.util.List;

public interface UserService {

    public User login(User user);
    public PageResult search(User user, int pageNum, int pageSize);
    public User findById(int id);
    public int addUser(User user);
    public int editUser(User user);
    public int delUser(User user);
    public int removeUserById(int id);
    public int checkName(String name);
    public int checkEmail(String email);
    public int register(User user);
    public int updatePassword(int id, String oldPwd, String newPwd);
    public User findByName(String name);
    public java.util.List<User> findAllAdmins();
    public int banUser(String name);
    public int unbanUser(String name);
}
