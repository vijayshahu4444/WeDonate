package com.app.wedonate2;

public class User {

    private String Blood_Group, City, Address;

    public User(){

    }

    public User(String blood_Group, String city, String address) {
        Blood_Group = blood_Group;
        City = city;
        Address = address;
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
}
