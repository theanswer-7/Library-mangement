package com.it.dao;

import com.github.pagehelper.Page;
import com.it.pojo.Record;
import org.apache.ibatis.annotations.*;

public interface RecordMapper {
    // 新增借阅记录
    public int addRecord(Record record);

    // 查询借阅记录（支持按借阅人、图书名称模糊搜索，配合 PageHelper 分页）
    @Select("<script>" +
            "select * from record <where>" +
            "<if test=\"borrower!=null and borrower!=''\">" +
            "record_borrower like concat('%',#{borrower},'%')" +
            "</if>" +
            "<if test=\"bookname!=null and bookname!=''\">" +
            "and record_bookname like concat('%',#{bookname},'%')" +
            "</if>" +
            "</where>" +
            "order by record_remandtime desc" +
            "</script>")
    @Results(id = "recordMap", value = {
            @Result(id = true, column = "record_id", property = "id"),
            @Result(column = "record_bookname", property = "bookname"),
            @Result(column = "record_bookisbn", property = "bookisbn"),
            @Result(column = "record_borrower", property = "borrower"),
            @Result(column = "record_borrowtime", property = "borrowTime"),
            @Result(column = "record_remandtime", property = "remandTime")
    })
    public Page<Record> searchRecords(Record record);
}
