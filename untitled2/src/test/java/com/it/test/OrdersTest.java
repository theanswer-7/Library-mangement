package com.it.test; // 补上包声明

import com.it.dao.OrdersMapper;
import com.it.pojo.Orders;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class OrdersTest {

    @Test
    public void testFindOrderWithProducts() throws IOException {
        // 1. 加载核心配置文件
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = factory.openSession();

        // 2. 获取 Mapper
        OrdersMapper mapper = sqlSession.getMapper(OrdersMapper.class);

        // 3. 调用方法：查询订单id=1的订单及商品
        Orders orders = mapper.findOrderWithProducts(1);

        // 4. 打印结果
        System.out.println(orders);

        sqlSession.close();
    }
}