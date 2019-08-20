package com.example.joanne.practice_layout01;

import android.widget.CheckBox;

/**
 * Created by Joanne on 2018-02-07.
 */

public class OrderListItem {
    private CheckBox chk;
    private String ordername;
    private String btnDial;
    private String btnSms;
    private String dateKey;
    private String dateValue;
    private String productName;
    private String rentdateKey;
    private String rentdateValue;
    boolean isChecked;

    public OrderListItem(){

    }

    public OrderListItem(String sUser, String sDate, String sName, String sRentGijun, String sDial) {
        this.ordername = sUser;
        this.dateValue = sDate;
        this.productName = sName;
        this.rentdateValue = sRentGijun;
        //잘못들어온거 인지할것!!!
        this.btnDial = sDial;
    }

    public CheckBox getChk() {
        return chk;
    }

    public void setChk(CheckBox chk) {
        this.chk = chk;
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

    public String getDateKey() {
        return dateKey;
    }

    public void setDateKey(String dateKey) {
        this.dateKey = dateKey;
    }

    public String getDateValue() {
        return dateValue;
    }

    public void setDateValue(String dateValue) {
        this.dateValue = dateValue;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getRentdateKey() {
        return rentdateKey;
    }

    public void setRentdateKey(String rentdateKey) {
        this.rentdateKey = rentdateKey;
    }

    public String getRentdateValue() {
        return rentdateValue;
    }

    public void setRentdateValue(String rentdateValue) {
        this.rentdateValue = rentdateValue;
    }

}
