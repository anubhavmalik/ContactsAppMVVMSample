package com.example.myapplication.data.models;

public class Contact {

    private int id;
    private String contactNumber;
    private String contactFirstName;
    private String contactLastName;

    public Contact(String contactNumber, String contactFirstName, String contactLastName) {
        this.id = id;
        this.contactNumber = contactNumber;
        this.contactFirstName = contactFirstName;
        this.contactLastName = contactLastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getContactFirstName() {
        return contactFirstName;
    }

    public void setContactFirstName(String contactFirstName) {
        this.contactFirstName = contactFirstName;
    }

    public String getContactLastName() {
        return contactLastName;
    }

    public void setContactLastName(String contactLastName) {
        this.contactLastName = contactLastName;
    }
}
