package com.example.heinhtet.contacts;

import java.io.Serializable;

/**
 * Created by heinhtet on 3/13/17.
 */

public class ContactFav implements Serializable {
    int id;
    String name;
    String phone;
    String email;
    String note;

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    String Time ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public ContactFav()
    {

    }
}
