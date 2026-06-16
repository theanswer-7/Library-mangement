package com.it.pojo;
import java.io.Serializable;
public class Message implements Serializable {
    private Integer id;
    private String jieShouRen;
    private String faShongRen;
    private String content;
    private Integer status;
    private String sendTime;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getJieShouRen() { return jieShouRen; }
    public void setJieShouRen(String jieShouRen) { this.jieShouRen = jieShouRen; }
    public String getFaShongRen() { return faShongRen; }
    public void setFaShongRen(String faShongRen) { this.faShongRen = faShongRen; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
    public String getSendTime() { return sendTime; }
    public void setSendTime(String sendTime) { this.sendTime = sendTime; }
}
