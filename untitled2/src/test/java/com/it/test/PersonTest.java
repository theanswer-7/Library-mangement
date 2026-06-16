package com.it.test;

import com.it.pojo.Person;
import com.it.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class PersonTest {

    @Test
    public void getPersonAndCard(){
        // 正确方法名：getSqlSession()
        SqlSession sqlSession = MyBatisUtils.getSqlSession();

        // 调用查询
        Person person = sqlSession.selectOne(
                "com.it.mapper.PersonMapper.findPersonAndCardByPid1",
                1
        );

        System.out.println(person);
        sqlSession.close();
    }
}