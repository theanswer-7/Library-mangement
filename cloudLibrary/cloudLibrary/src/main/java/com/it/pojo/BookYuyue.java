package com.it.pojo;
import java.io.Serializable;
public class BookYuyue implements Serializable {
    private Integer id;
    private Integer bookId;
    private String userName;
    private String yuyueTime;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getBookId() { return bookId; }
    public void setBookId(Integer bookId) { this.bookId = bookId; }
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    public String getYuyueTime() { return yuyueTime; }
    public void setYuyueTime(String yuyueTime) { this.yuyueTime = yuyueTime; }
}
