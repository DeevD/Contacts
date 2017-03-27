package com.example.heinhtet.contacts;

import java.io.Serializable;

/**
 * Created by heinhtet on 3/13/17.
 */

public class Contact implements Serializable {

    long id;
    String name;
    String phone;
    String email;


    public byte[] getIamge() {
        return iamge;
    }

    public void setIamge(byte[] iamge) {
        this.iamge = iamge;
    }

    byte iamge[] ;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    String time;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    String note;

    public Contact()
    {

    }
}
