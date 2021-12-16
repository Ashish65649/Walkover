package com.walkover.api.message;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Message {

    private String dateTime ;
    private int statusCode ;
    private List<String> messages ;

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        this.dateTime = formatter.format(date);
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
}
