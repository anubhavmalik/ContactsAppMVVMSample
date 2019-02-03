package com.example.myapplication.data.models;

public class Contact {

    private int id;
    private String phone;
    private String firstName;
    private String lastName;
    private String email;

    public Contact(int id, String contactNumber, String contactFirstName, String contactLastName, String email) {
        this.id = id;
        this.phone = contactNumber;
        this.firstName = contactFirstName;
        this.lastName = contactLastName;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
