package com.lj.cn.pojo;

public class GetPeonsonInformation {
    private String phone;
    private String catNumber;
    private String ID;

    @Override
    public String toString() {
        return "GetPeonsonInformation{" +
                "phone='" + phone + '\'' +
                ", catNumber='" + catNumber + '\'' +
                ", ID='" + ID + '\'' +
                '}';
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCatNumber() {
        return catNumber;
    }

    public void setCatNumber(String catNumber) {
        this.catNumber = catNumber;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
