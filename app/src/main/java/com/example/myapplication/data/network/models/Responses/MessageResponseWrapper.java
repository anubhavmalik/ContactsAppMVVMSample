package com.example.myapplication.data.network.models.Responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MessageResponseWrapper {
    @SerializedName("message-count")
    @Expose
    private Integer messageCount;
    @SerializedName("messages")
    @Expose
    private List<Message> messages = null;

    public Integer getMessageCount() {
        return messageCount;
    }

    public void setMessageCount(Integer messageCount) {
        this.messageCount = messageCount;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
