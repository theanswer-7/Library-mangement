package com.it.dao;

import com.it.pojo.Employee;
import org.apache.ibatis.annotations.Param;

public interface EmployeeMapper {
    // 1. 根据 id 查询员工
    Employee selectById(@Param("id") Integer id);

    // 2. 新增员工
    int insert(Employee employee);

    // 3. 根据 id 修改员工
    int updateById(Employee employee);

    // 4. 根据 id 删除员工
    int deleteById(@Param("id") Integer id);
}