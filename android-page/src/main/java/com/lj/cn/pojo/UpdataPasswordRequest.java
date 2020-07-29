package com.lj.cn.pojo;

public class UpdataPasswordRequest {
    private String userName;//用户名
    private String passWord;//密码

    @Override
    public String toString() {
        return "UpdataPasswordRequest{" +
                "userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
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
}
