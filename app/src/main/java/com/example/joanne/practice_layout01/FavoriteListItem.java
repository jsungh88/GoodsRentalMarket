package com.example.joanne.practice_layout01;

import android.net.Uri;

/**
 * Created by Joanne on 2018-02-07.
 */

public class FavoriteListItem {

    public boolean isChecked;
    //아이템 구성요소 정의 - 체크박스,이미지,제목, 컨텐츠(대여료,보증금 관련), 문의하기 버튼, 대여하기 버튼
    private Uri image;
    private String sImage;
    private String subject;

    private String rentValue;
    private String rentGijun;
    private String depositValue;
    private String loginId;

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }
    //    private Button btnQuestion;
//    private Button btnRent;

    //private String dial;

    public FavoriteListItem(){

    }

    public FavoriteListItem(String loginId, String sImage, String sName, String sRent, String sRentGijun, String sDeposit) {
        this.loginId = loginId;
        this.sImage = sImage;
        this.subject = sName;
        this.rentValue = sRent;
        this.rentGijun = sRentGijun;
        this.depositValue = sDeposit;
    }


    public String getsImage() {
        return sImage;
    }

    public void setsImage(String sImage) {
        this.sImage = sImage;
    }

//    public FavoriteListItem(String sImage, String sName, String sRent, String sRentGijun, String sDeposit, String sDial) {
//            this.sImage = sImage;
//        this.subject = sName;
//        this.rentValue = sRent;
//        this.rentGijun = sRentGijun;
//        this.depositValue = sDeposit;
//        this.dial = sDial;
//
//    }

//    public String getDial() {
//        return dial;
//    }
//
//    public void setDial(String dial) {
//        this.dial = dial;
//    }

    public Uri getImage() {
        return image;
    }

    public void setImage(Uri image) {
        this.image = image;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
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



//    public Button getBtnQuestion() {
//        return btnQuestion;
//    }
//
//    public void setBtnQuestion(Button btnQuestion) {
//        this.btnQuestion = btnQuestion;
//    }
//
//    public Button getBtnRent() {
//        return btnRent;
//    }
//
//    public void setBtnRent(Button btnRent) {
//        this.btnRent = btnRent;
//    }


}
