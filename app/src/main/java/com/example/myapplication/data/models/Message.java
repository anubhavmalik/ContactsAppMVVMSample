package com.example.myapplication.data.models;

public class Message {

    private Contact contact;
    private String text;
    private long timestamp;

    public Message(Contact contact, String text, long timestamp) {
        this.contact = contact;
        this.text = text;
        this.timestamp = timestamp;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
