package com.it.dao;

import com.it.pojo.BookYuyue;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BookYuyueMapper {

    // 新增预约
    @Insert("insert into book_yuyue (book_id, user_name, yuyue_time) values (#{bookId}, #{userName}, #{yuyueTime})")
    public int addYuyue(BookYuyue yuyue);

    // 根据图书id查询所有预约人
    @Select("select * from book_yuyue where book_id=#{bookId}")
    @Results(id = "yuyueMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "book_id", property = "bookId"),
            @Result(column = "user_name", property = "userName"),
            @Result(column = "yuyue_time", property = "yuyueTime")
    })
    public List<BookYuyue> findByBookId(int bookId);

    // 检查用户是否已预约该书
    @Select("select count(*) from book_yuyue where book_id=#{bookId} and user_name=#{userName}")
    public int checkYuyued(@Param("bookId") int bookId, @Param("userName") String userName);

    // 删除某本书的所有预约记录
    @Delete("delete from book_yuyue where book_id=#{bookId}")
    public int deleteByBookId(int bookId);
}
