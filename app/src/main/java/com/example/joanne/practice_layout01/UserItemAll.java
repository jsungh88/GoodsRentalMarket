package com.example.joanne.practice_layout01;

/**
 * Created by Joanne on 2018-03-04.
 */

public class UserItemAll {



    String image,name,id,pw,email,contact;


    public UserItemAll(String image, String name, String id, String pw, String email, String contact) {
        this.image = image;
        this.name = name;
        this.id = id;
        this.pw = pw;
        this.email = email;
        this.contact = contact;
    }

    public UserItemAll() {

    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
