package com.it.dao;

import com.it.pojo.Student;
import org.apache.ibatis.annotations.Param;

public interface StudentMapper {
    Student selectStudentWithCoursesByQuery(Integer id);

    // 方式1：嵌套结果映射（联表查询，一次SQL）
    Student findStudentWithCoursesResult(@Param("id") Integer id);

    // 方式2：嵌套查询（两次SQL，子查询）
    Student findStudentWithCoursesSelect(@Param("id") Integer id);
}