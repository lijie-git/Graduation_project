package com.lj.cn.pojo;

public class RegisterUserNameRequest {
    private String userName;//用户名
    private String passWord;//密码
    private String email;//邮箱
    private String telePhone;//电话
    private String catNumber;//车牌号

    @Override
    public String toString() {
        return "RegisterUserNameRequest{" +
                "userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", email='" + email + '\'' +
                ", telePhone='" + telePhone + '\'' +
                ", catNumber='" + catNumber + '\'' +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelePhone() {
        return telePhone;
    }

    public void setTelePhone(String telePhone) {
        this.telePhone = telePhone;
    }

    public String getCatNumber() {
        return catNumber;
    }

    public void setCatNumber(String catNumber) {
        this.catNumber = catNumber;
    }
}
