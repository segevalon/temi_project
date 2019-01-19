package com.mgroup.temipro.model;

public interface ApiInterfacePubNub {

    void init();

    void sendMessage(String phoneSrc,String phoneDest, String messageContent, String time);

    void getHistory();

    void stop();

     String getLastMessageTime(String phone);
}
