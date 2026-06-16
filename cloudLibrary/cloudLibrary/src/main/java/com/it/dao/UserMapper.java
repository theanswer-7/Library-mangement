package com.it.dao;

import com.it.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {
    @Select("select * from user where user_email=#{email}" +
            " and user_password=#{password} and user_status != '1'")
    @Results(id = "userMap", value = {
            @Result(id = true, column = "user_id", property = "id"),
            @Result(column = "user_name", property = "name"),
            @Result(column = "user_password", property = "password"),
            @Result(column = "user_email", property = "email"),
            @Result(column = "user_hiredate", property = "hiredate"),
            @Result(column = "user_departuredate", property = "departuredate"),
            @Result(column = "user_status", property = "status"),
            @Result(column = "user_role", property = "role"),
            @Result(column = "user_canBorrow", property = "canBorrow")
    })
    public User login(User user);

    // 查询用户列表（按id/name模糊搜索，配合PageHelper分页）
    @Select("<script>" +
            "select * from user where 1=1" +
            "<if test='id != null'> and user_id=#{id}</if>" +
            "<if test='name != null and name != \"\"'> and user_name like concat('%',#{name},'%')</if>" +
            " order by user_id" +
            "</script>")
    @ResultMap("userMap")
    public List<User> search(User user);

    // 根据id查询用户
    @Select("select * from user where user_id=#{id}")
    @ResultMap("userMap")
    public User findById(int id);

    // 新增用户
    @Insert("insert into user (user_name, user_password, user_email, user_role, user_status, user_hiredate) " +
            "values (#{name}, #{password}, #{email}, #{role}, '0', #{hiredate})")
    public int addUser(User user);

    // 编辑用户
    @Update("<script>" +
            "update user <set>" +
            "<if test='name != null and name != \"\"'>user_name=#{name},</if>" +
            "<if test='password != null and password != \"\"'>user_password=#{password},</if>" +
            "<if test='email != null and email != \"\"'>user_email=#{email},</if>" +
            "<if test='role != null and role != \"\"'>user_role=#{role},</if>" +
            "<if test='hiredate != null and hiredate != \"\"'>user_hiredate=#{hiredate},</if>" +
            "<if test='canBorrow != null'>user_canBorrow=#{canBorrow},</if>" +
            "</set> where user_id=#{id}" +
            "</script>")
    public int editUser(User user);

    // 离职（软删除：设置status=1和离职日期）
    @Update("update user set user_status='1', user_departuredate=#{departuredate} where user_id=#{id}")
    public int delUser(User user);

    // 永久删除
    @Delete("delete from user where user_id=#{id}")
    public int removeUserById(int id);

    // 检查用户名是否已存在
    @Select("select count(*) from user where user_name=#{name}")
    public int checkName(String name);

    // 检查邮箱是否已存在
    @Select("select count(*) from user where user_email=#{email}")
    public int checkEmail(String email);

    // 根据用户名称查询用户
    @Select("select * from user where user_name=#{name}")
    @ResultMap("userMap")
    public User findByName(String name);

    // 查找所有管理员
    @Select("select * from user where user_role='ADMIN'")
    @ResultMap("userMap")
    public java.util.List<User> findAllAdmins();
}
