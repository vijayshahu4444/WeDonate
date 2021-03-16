package com.app.wedonate2;

import android.widget.CheckBox;

public class UserRegister {
    String userID;
    String f_Name;
    String l_Name;
    String Phone;
    String passwod;
    CheckBox M;
    CheckBox F;

    public UserRegister(){

    }

    public UserRegister(String userID, String f_Name, String l_Name, String Phone, String passwod, CheckBox m, CheckBox f) {
        this.userID = userID;
        this.f_Name = f_Name;
        this.l_Name = l_Name;
        this.Phone = Phone;
        this.passwod = passwod;
        M = m;
        F = f;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getF_Name() {
        return f_Name;
    }

    public void setF_Name(String f_Name) {
        this.f_Name = f_Name;
    }

    public String getL_Name() {
        return l_Name;
    }

    public void setL_Name(String l_Name) {
        this.l_Name = l_Name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getPasswod() {
        return passwod;
    }

    public void setPasswod(String passwod) {
        this.passwod = passwod;
    }

    public CheckBox getM() {
        return M;
    }

    public void setM(CheckBox m) {
        M = m;
    }

    public CheckBox getF() {
        return F;
    }

    public void setF(CheckBox f) {
        F = f;
    }
}
