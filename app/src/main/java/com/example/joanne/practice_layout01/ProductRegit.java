package com.example.joanne.practice_layout01;

import android.net.Uri;

/**
 * Created by Joanne on 2018-02-12.
 */

class ProductRegit {

    //    Drawable favIcon;
    Integer favCount;

    String category;
    Uri image;
    String simage;
    String name;
    String rentValue;
    String rentGijun;
    String depositValue;
    String desc;

    //    Drawable locIcon;
    String locTxt;
    String state;
    String date;

    //final static int imglist[] = {R.drawable.ic_launcher_background,R.drawable.ic_launcher_background};


    //등록할 때 사용.
    //카테고리, 이미지, 이름, 대여료, 대여기준, 보증금, 설명, 위치, 상태
    public ProductRegit(String category, String image, String name, String rentValue, String rentGijun, String depositValue, String desc, String locTxt) {
        this.category = category;
        this.simage = image;
        this.name = name;
        this.rentValue = rentValue;
        this.rentGijun = rentGijun;
        this.depositValue = depositValue;
        this.desc = desc;
        this.locTxt = locTxt;
    }

    //리스트 inflate할 때 사용.
    //좋아요갯수, 상품이미지, 상품명, 대여료, 대여료기준(1일/1주일/1개월), 보증금, 위치, 대여상태
    public ProductRegit(Integer favCount, String category,String image, String name, String rentValue, String rentGijun, String depositValue, String desc, String locTxt, String state, String date) {
        this.favCount = favCount;
        this.category=category;
        this.simage = image;
        this.name = name;
        this.rentValue = rentValue;
        this.rentGijun = rentGijun;
        this.depositValue = depositValue;
        this.desc = desc;
        this.locTxt = locTxt;
        this.state = state;
        this.date = date;
    }

    public ProductRegit() {

    }


//
//    //찜 할 때 생성자
//    public Product(String image, String sName, String sRent, String sRentGijun, String sDeposit, String sDial) {
//        this.simage = image;
//        this.name = name;
//        this.rentValue = rentValue;
//        this.rentGijun = rentGijun;
//        this.depositValue = depositValue;
//        this.dial = sDial;
//    }
//
//    //대여하기 생성자
//    public Product(String sUser, String sDate, String sName, String sRentGijun, String sDial) {
//        this.user = sUser;
//        this.date = sDate;
//        this.name = sName;
//        this.rentGijun = sRentGijun;
//        this.dial = sDial;
//
//    }



    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getFavCount() {
        return favCount;
    }

    public void setFavCount(Integer favCount) {
        this.favCount = favCount;
    }

    public Uri getImage() {
        return image;
    }

    public void setImage(Uri image) {
        this.image = image;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String  getName() {
        return name;
    }

    public String getSimage() {
        return simage;
    }

    public void setSimage(String simage) {
        this.simage = simage;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getRentValue() {
        return rentValue;
    }

    public void setRentValue(String rentValue) {
        this.rentValue = rentValue;
    }

    public String getRentGijun() {
        return rentGijun;
    }

    public void setRentGijun(String rentGijun) {
        this.rentGijun = rentGijun;
    }


    public String getDepositValue() {
        return depositValue;
    }

    public void setDepositValue(String depositValue) {
        this.depositValue = depositValue;
    }


    public String getLocTxt() {
        return locTxt;
    }

    public void setLocTxt(String locTxt) {
        this.locTxt = locTxt;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
