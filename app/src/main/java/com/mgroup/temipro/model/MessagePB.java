package com.mgroup.temipro.model;

class MessagePB {

    String userID;
    String content;
    String time;

    public MessagePB(String userID, String content, String time) {
        this.userID = userID;
        this.content = content;
        this.time = time;
    }


    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
