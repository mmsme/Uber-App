package com.m_mustafa.softwareproject;

public class inboxRecData {
    String senderName;
    String msgDate;
    String Msg;
    int senderImg;

    public inboxRecData(String senderName, String msgDate, String msg, int senderImg) {
        this.senderName = senderName;
        this.msgDate = msgDate;
        Msg = msg;
        this.senderImg = senderImg;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getMsgDate() {
        return msgDate;
    }

    public void setMsgDate(String msgDate) {
        this.msgDate = msgDate;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }

    public int getSenderImg() {
        return senderImg;
    }

    public void setSenderImg(int senderImg) {
        this.senderImg = senderImg;
    }
}
