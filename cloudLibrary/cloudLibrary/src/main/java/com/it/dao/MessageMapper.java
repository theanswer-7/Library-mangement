package com.it.dao;

import com.it.pojo.Message;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface MessageMapper {
    // 新增超时提醒消息
    public int addMessage(Message message);

    // 根据接收人查询消息
    @Select("select * from message where jieShouRen=#{jieShouRen} order by sendTime desc")
    @Results(id = "msgMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "jieShouRen", property = "jieShouRen"),
            @Result(column = "faShongRen", property = "faShongRen"),
            @Result(column = "content", property = "content"),
            @Result(column = "status", property = "status"),
            @Result(column = "sendTime", property = "sendTime")
    })
    public List<Message> selectByReceiver(String jieShouRen);

    // 根据id查询消息详情
    @Select("select * from message where id=#{id}")
    @ResultMap("msgMap")
    public Message findById(int id);

    // 标记消息为已读
    @Update("update message set status=1 where id=#{id}")
    public int markRead(int id);
}
