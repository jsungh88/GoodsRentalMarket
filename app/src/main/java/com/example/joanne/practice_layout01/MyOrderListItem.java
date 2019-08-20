package com.example.joanne.practice_layout01;

import android.net.Uri;

/**
 * Created by Joanne on 2018-02-12.
 */

public class MyOrderListItem {

    //private CheckBox chk;
    boolean isChecked;
    private String num;
    private String rentDate;//주문일자
    private String ordername;

    private Uri image;
    private String simage;
    private String btnDial;
    private String btnSms;
    private String productName;
    private String rentValue;//원래 기획은 대여료합계이지만, 대여료로 대체한다.
    private String rentGigan;//원래 기획은 대여기간이지만, 대여기준으로 대체한다
    private String deposit;

    private String rentname;
    private String email;
    private String rentContact;


    public MyOrderListItem(){

    }

    //주문서 : 주문번호, 주문일자, 주문자명, 상품이미지, 연락처, SMS, 상품명, 대여료, 대여기간, 보증금, 대여자명, 대여자이메일주소(12)
    public MyOrderListItem(String num, String rentDate, String ordername, String simage, String productName, String rentValue, String rentGigan, String deposit, String rentname, String email, String rentcontact,String btnDial, String btnSms) {
        this.num = num;
        this.rentDate = rentDate;
        this.ordername = ordername;
        this.simage = simage;
        this.btnDial = btnDial;
        this.btnSms = btnSms;
        this.productName = productName;
        this.rentValue = rentValue;
        this.rentGigan = rentGigan;
        this.deposit = deposit;
        this.rentname = rentname;
        this.email = email;
        this.rentContact = rentcontact;
    }



//    //내 주문내역
//    public MyOrderListItem(String sUser, String sDate, String sName, String sRentGijun, String sDial,String sSms) {
//        this.ordername = sUser;
//        this.rentValue = sDate;
//        this.productName = sName;
//        this.rentGigan = sRentGijun;
//        //잘못들어온거 인지할것!!!
//        this.btnDial = sDial;
//        this.btnSms = sSms;
//    }


    public String getRentContact() {
        return rentContact;
    }

    public void setRentContact(String rentContact) {
        this.rentContact = rentContact;
    }

    public String getRentDate() {
        return rentDate;
    }

    public void setRentDate(String rentDate) {
        this.rentDate = rentDate;
    }

    public String getRentValue() {
        return rentValue;
    }

    public void setRentValue(String rentValue) {
        this.rentValue = rentValue;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public String getSimage() {
        return simage;
    }

    public void setSimage(String simage) {
        this.simage = simage;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public Uri getImage() {
        return image;
    }

    public void setImage(Uri image) {
        this.image = image;
    }

    public String getRentGigan() {
        return rentGigan;
    }

    public void setRentGigan(String rentGigan) {
        this.rentGigan = rentGigan;
    }

    public String getRentname() {
        return rentname;
    }

    public void setRentname(String rentname) {
        this.rentname = rentname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOrdername() {
        return ordername;
    }

    public void setOrdername(String ordername) {
        this.ordername = ordername;
    }

    public String getBtnDial() {
        return btnDial;
    }

    public void setBtnDial(String btnDial) {
        this.btnDial = btnDial;
    }

    public String getBtnSms() {
        return btnSms;
    }

    public void setBtnSms(String btnSms) {
        this.btnSms = btnSms;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }



//    public boolean isChecked;
//
//    //아이템 구성요소 정의 - 체크박스, 이미지, 제목, 대여기간, 기간, 총대여료, 보증금, 문의하기 버튼
//
//    private CheckBox chk;
//    private Uri image;
//    private String subject;
//
//    private String calendar;
//    private String period;
//    private String rentSum;
//    private String deposit;
//
//    private Button quest;
//
//    public CheckBox getChk() {
//        return chk;
//    }
//
//    public void setChk(CheckBox chk) {
//        this.chk = chk;
//    }
//
//    public Uri getImage() {
//        return image;
//    }
//
//    public void setImage(Uri image) {
//        this.image = image;
//    }
//
//    public String getSubject() {
//        return subject;
//    }
//
//    public void setSubject(String subject) {
//        this.subject = subject;
//    }
//
//    public String getCalendar() {
//        return calendar;
//    }
//
//    public void setCalendar(String calendar) {
//        this.calendar = calendar;
//    }
//
//    public String getPeriod() {
//        return period;
//    }
//
//    public void setPeriod(String period) {
//        this.period = period;
//    }
//
//    public String getRentSum() {
//        return rentSum;
//    }
//
//    public void setRentSum(String rentSum) {
//        this.rentSum = rentSum;
//    }
//
//    public String getDeposit() {
//        return deposit;
//    }
//
//    public void setDeposit(String deposit) {
//        this.deposit = deposit;
//    }
//
//    public Button getQuest() {
//        return quest;
//    }
//
//    public void setQuest(Button quest) {
//        this.quest = quest;
//    }

}
