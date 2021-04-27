package com.app.wedonate2;

import android.widget.CheckBox;

public class UserRegister {
    String userID;
    String f_Name;
    String l_Name;
    String Phone;
    String passwod;
    String email;
    String Gender;

    //this is for reqest address
    String address;

    public UserRegister() {

    }


    public UserRegister(String userID, String f_Name, String l_Name, String Phone, String passwod, String Gender, String email) {
        this.userID = userID;
        this.f_Name = f_Name;
        this.l_Name = l_Name;
        this.Phone = Phone;
        this.passwod = passwod;
        this.email = email;
        this.Gender = Gender;

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


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }
}
