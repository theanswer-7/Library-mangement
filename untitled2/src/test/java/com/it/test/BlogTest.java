package com.it.test;

import com.it.dao.BlogMapper;
import com.it.pojo.Blog;
import com.it.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

public class BlogTest {
    public static void main(String[] args) {
        SqlSession session = MyBatisUtil.getSqlSession();
        BlogMapper mapper = session.getMapper(BlogMapper.class);

        // 测试嵌套结果
        System.out.println("===== 嵌套结果映射 =====");
        Blog blog1 = mapper.getBlogWithCommentByResult(1);
        System.out.println(blog1);

        // 测试嵌套查询
        System.out.println("\n===== 嵌套查询 =====");
        Blog blog2 = mapper.getBlogWithCommentBySelect(1);
        System.out.println(blog2);

        session.close();
    }
}