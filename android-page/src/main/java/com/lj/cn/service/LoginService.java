package com.lj.cn.service;

import org.springframework.stereotype.Service;
import com.lj.cn.pojo.MsgResponse;

@Service
public interface LoginService {
    //登录
    public MsgResponse login(String userName,String passWord,String type);

    //注册用户
    public MsgResponse registerUserName(String userName, String passWord, String telePhone, String email, String catNumber,String type);

    //修改密码
    public MsgResponse updataPassword(String userName, String passWord,String type);

    //注册安卓用户
    public MsgResponse registerUserName0(String userName, String passWord, String telePhone, String id, String catNumber,String type);
}
