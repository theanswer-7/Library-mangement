package com.it.test;

import com.it.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class UserTest {

    // 通用获取 SqlSession 的方法
    public static SqlSession getSqlSession() {
        try {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            return sqlSessionFactory.openSession(true); // 自动提交事务
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 1. 根据ID查询
    @Test
    public void findById() {
        SqlSession sqlSession = getSqlSession();

        // 这里必须改成你的 UserDao.xml 的 namespace
        User user = sqlSession.selectOne("com.it.dao.UserDao.findById", 1);

        System.out.println(user);
        sqlSession.close();
    }

    // 2. 添加用户
    @Test
    public void addUser() {
        SqlSession sqlSession = getSqlSession();

        User user = new User();
        user.setUname("王五");
        user.setUage(18);

        int row = sqlSession.insert("com.it.dao.UserDao.addUser", user);
        System.out.println("插入成功：" + row);

        sqlSession.close();
    }

    // 3. 修改用户
    @Test
    public void updateUser() {
        SqlSession sqlSession = getSqlSession();

        User user = new User();
        user.setUid(3);
        user.setUname("詹姆斯");
        user.setUage(25);

        int row = sqlSession.update("com.it.dao.UserDao.updateUserById", user);
        System.out.println("修改成功：" + row);

        sqlSession.close();
    }

    // 4. 删除用户
    @Test
    public void deleteUserById() {
        SqlSession sqlSession = getSqlSession();

        int row = sqlSession.delete("com.it.dao.UserDao.deleteUserById", 2);
        System.out.println("删除成功：" + row);

        sqlSession.close();
    }
}