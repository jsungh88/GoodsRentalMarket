package com.example.joanne.practice_layout01;

import android.graphics.drawable.Drawable;
import android.widget.CheckBox;

/**
 * Created by Joanne on 2018-02-07.
 */

public class MyShopItem {

    public MyShopItem(String product, String rent, String deposit){
        this.subject = product;
        this.rent = rent;
        this.deposit = deposit;
        this.isChecked = false;
    }


    public MyShopItem(String image, String product, String rent, String deposit){
        this.sImage = image;
        this.subject = product;

        this.rent = rent;
        this.deposit = deposit;
        this.isChecked = false;
    }

    public MyShopItem(){

    }


    private CheckBox chk;
    //private Drawable image;
    private String sImage;
    private String subject;
    private String rent;
    private String deposit;

    boolean isChecked;

    public CheckBox getChk() {
        return chk;
    }

    public void setChk(CheckBox chk) {
        this.chk = chk;
    }

//    public Drawable getImage() {
//        return image;
//    }
//
//    public void setImage(Drawable image) {
//        this.image = image;
//    }


    public String getsImage() {
        return sImage;
    }

    public void setsImage(String sImage) {
        this.sImage = sImage;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getRent() {
        return rent;
    }

    public void setRent(String rent) {
        this.rent = rent;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }




}
