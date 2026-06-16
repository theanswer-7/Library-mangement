package com.it.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.it.dao.UserMapper;
import com.it.pojo.User;
import com.it.service.UserService;
import com.it.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User login(User user) {
        return userMapper.login(user);
    }

    @Override
    public PageResult search(User user, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> list = userMapper.search(user);
        Page<User> page = (Page<User>) list;
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public User findById(int id) {
        return userMapper.findById(id);
    }

    @Override
    public int addUser(User user) {
        user.setStatus("0");
        return userMapper.addUser(user);
    }

    @Override
    public int editUser(User user) {
        return userMapper.editUser(user);
    }

    @Override
    public int delUser(User user) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        user.setDeparturedate(sdf.format(new Date()));
        return userMapper.delUser(user);
    }

    @Override
    public int removeUserById(int id) {
        return userMapper.removeUserById(id);
    }

    @Override
    public int checkName(String name) {
        return userMapper.checkName(name);
    }

    @Override
    public int checkEmail(String email) {
        return userMapper.checkEmail(email);
    }

    @Override
    public int register(User user) {
        user.setRole("USER");
        user.setStatus("0");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        user.setHiredate(sdf.format(new Date()));
        return userMapper.addUser(user);
    }

    @Override
    public int updatePassword(int id, String oldPwd, String newPwd) {
        User user = userMapper.findById(id);
        if (user == null || !user.getPassword().equals(oldPwd)) {
            return -1;
        }
        user.setPassword(newPwd);
        return userMapper.editUser(user);
    }

    @Override
    public User findByName(String name) {
        return userMapper.findByName(name);
    }

    @Override
    public java.util.List<User> findAllAdmins() {
        return userMapper.findAllAdmins();
    }

    @Override
    public int banUser(String name) {
        User user = userMapper.findByName(name);
        if (user == null) return 0;
        user.setCanBorrow(1);
        return userMapper.editUser(user);
    }

    @Override
    public int unbanUser(String name) {
        User user = userMapper.findByName(name);
        if (user == null) return 0;
        user.setCanBorrow(0);
        return userMapper.editUser(user);
    }
}
