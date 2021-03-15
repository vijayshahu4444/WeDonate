package com.app.wedonate2;

import android.widget.CheckBox;

public class UserRegister {
    String userID;
    String f_Name;
    String l_Name;
    Number Mobile;
    String password;
    CheckBox M;
    CheckBox F;

    public UserRegister(){

    }

    public UserRegister(String userID, String f_Name, String l_Name, Number mobile, String password, CheckBox m, CheckBox f) {
        this.userID = userID;
        this.f_Name = f_Name;
        this.l_Name = l_Name;
        Mobile = mobile;
        this.password = password;
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

    public Number getMobile() {
        return Mobile;
    }

    public void setMobile(Number mobile) {
        Mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
