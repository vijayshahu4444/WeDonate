package com.app.wedonate2;

public class Blood_Pakcage_Helper {
    String Blood_Group,Packets;

    public Blood_Pakcage_Helper() {
    }

    public Blood_Pakcage_Helper(String blood_Group, String packets) {
        Blood_Group = blood_Group;
        Packets = packets;
    }

    public String getBlood_Group() {
        return Blood_Group;
    }

    public void setBlood_Group(String blood_Group) {
        Blood_Group = blood_Group;
    }

    public String getPackets() {
        return Packets;
    }

    public void setPackets(String packets) {
        Packets = packets;
    }
}
