package com.it.test;
import com.it.dao.EmployeeMapper;
import com.it.pojo.Employee;
import com.it.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
public class EmployeeTest {

    @Test
    public void testSelectById() {
        try (SqlSession sqlSession = MyBatisUtils.getSqlSession()) {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee employee = mapper.selectById(1);
            System.out.println("查询：" + employee);
        }
    }

    @Test
    public void testInsert() {
        try (SqlSession sqlSession = MyBatisUtils.getSqlSession()) {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee employee = new Employee(null, "赵六", 28, "主管");
            int rows = mapper.insert(employee);
            System.out.println("新增行数：" + rows);
        }
    }

    @Test
    public void testUpdateById() {
        try (SqlSession sqlSession = MyBatisUtils.getSqlSession()) {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee employee = mapper.selectById(1);
            employee.setAge(21);
            employee.setPosition("高级员工");
            int rows = mapper.updateById(employee);
            System.out.println("修改行数：" + rows);
        }
    }

    @Test
    public void testDeleteById() {
        try (SqlSession sqlSession = MyBatisUtils.getSqlSession()) {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            int rows = mapper.deleteById(3);
            System.out.println("删除行数：" + rows);
        }
    }
}