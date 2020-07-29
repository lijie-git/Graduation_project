package com.lj.cn.service;

import com.lj.cn.pojo.MsgResponse;
import org.springframework.stereotype.Service;

@Service
public interface GetMsgService {
    //获取报警信息
    public MsgResponse getMessage();

    //获取报警信息（分条件）
    public MsgResponse getMessageByparameter(String userName, String carnumber);

    //添加
    public MsgResponse addpoliceinformation(String pname, String pnumber,String pphone,String pwork,String pplace,String page);


    //获取警察信息
    public MsgResponse getpoliceMessage();

    //获取警察信息（分条件）
    public MsgResponse getpoliceMessageByparameter(String pname, String pnumber);

    //添加护士信息
    public MsgResponse addnurseinformation(String pname, String pnumber,String pphone,String pwork,String pplace,String page);


    //获取护士信息
    public MsgResponse getnurseMessage();

    //获取护士信息（分条件）
    public MsgResponse getnurseMessageByparameter(String pname, String pnumber);
}
