package com.app.wedonate2;

public class User {

    private String Blood_Group, City, Address,fname,lname,Number;

    public User(){

    }

    public User(String blood_Group, String city, String address, String fname, String lname, String number) {
        Blood_Group = blood_Group;
        City = city;
        Address = address;
        this.fname = fname;
        this.lname = lname;
        Number = number;
    }

    public String getBlood_Group() {
        return Blood_Group;
    }

    public void setBlood_Group(String blood_Group) {
        Blood_Group = blood_Group;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }
}
