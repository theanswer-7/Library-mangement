package com.it.test;

import com.it.dao.StudentMapper;
import com.it.pojo.Student;
import com.it.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class StudentTest {

    // 你现在 XML 只有这个方法！只运行这个！
    @Test
    public void testSelectStudentWithCoursesByQuery() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);

        // 调用你 XML 里真实存在的方法
        Student student = studentMapper.selectStudentWithCoursesByQuery(1);

        if (student != null) {
            System.out.println("学生信息：" + student);
            System.out.println("所选课程：" + student.getCourseList());
        } else {
            System.out.println("未找到学生");
        }

        sqlSession.close();
    }
}