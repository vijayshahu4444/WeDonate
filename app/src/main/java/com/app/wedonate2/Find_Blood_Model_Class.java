package com.app.wedonate2;

public class Find_Blood_Model_Class {
    String Address,Blood,City,Name,Phone,WillDonate;


    public Find_Blood_Model_Class() {
    }

    public Find_Blood_Model_Class(String address, String blood, String city, String name, String phone, String willDonate) {
        Address = address;
        Blood = blood;
        City = city;
        Name = name;
        Phone = phone;
        WillDonate = willDonate;
    }

    public Find_Blood_Model_Class(String address, String name) {
        Address = address;
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getBlood() {
        return Blood;
    }

    public void setBlood(String blood) {
        Blood = blood;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getWillDonate() {
        return WillDonate;
    }

    public void setWillDonate(String willDonate) {
        WillDonate = willDonate;
    }
}
