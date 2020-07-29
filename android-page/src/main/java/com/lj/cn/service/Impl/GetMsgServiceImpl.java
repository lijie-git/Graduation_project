package com.lj.cn.service.Impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lj.cn.mapper.MsgMapper;
import com.lj.cn.pojo.AlarmMessage;
import com.lj.cn.pojo.MsgResponse;
import com.lj.cn.pojo.PoliceInformation;
import com.lj.cn.service.GetMsgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;

@Component
public class GetMsgServiceImpl implements GetMsgService {
    private static final Logger logger = LoggerFactory.getLogger(GetMsgServiceImpl.class);
    @Autowired
    MsgMapper msgMapper;

    //获取报警信息
    @Override
    public MsgResponse getMessage() {
        MsgResponse msgResponse = new MsgResponse();
        JSONArray jsonArray = new JSONArray();
        List<AlarmMessage> alarmMessageList = msgMapper.selectAlarmMessage();
        if (alarmMessageList.size() == 0) {
            msgResponse.setStatusCode(1);
            msgResponse.setMsg("没有报警信息");
            return msgResponse;
        }
        for (AlarmMessage alarmMessage : alarmMessageList) {
            JSONObject jsonObject = new JSONObject(new LinkedHashMap());
            jsonObject.put("name", alarmMessage.getName());
            jsonObject.put("phone", alarmMessage.getPhone());
            jsonObject.put("place", alarmMessage.getPlace());
            jsonObject.put("time", alarmMessage.getTime());
            jsonObject.put("type", alarmMessage.getType());
            jsonObject.put("carNumber", alarmMessage.getCarnumber());
            jsonArray.add(jsonObject);
        }
        msgResponse.setData(jsonArray);
        return msgResponse;
    }

    //获取报警信息(分条件)
    @Override
    public MsgResponse getMessageByparameter(String userName, String carNumber) {
        MsgResponse msgResponse = new MsgResponse();
        JSONArray jsonArray = new JSONArray();
        List<AlarmMessage> alarmMessageList = msgMapper.getMessageByparameter(userName, carNumber);
        if (alarmMessageList.size() == 0) {
            msgResponse.setStatusCode(1);
            msgResponse.setMsg("没有适合筛选条件的信息.");
            return msgResponse;
        }
        for (AlarmMessage alarmMessage : alarmMessageList) {
            JSONObject jsonObject = new JSONObject(new LinkedHashMap<>());
            jsonObject.put("name", alarmMessage.getName());
            jsonObject.put("phone", alarmMessage.getPhone());
            jsonObject.put("place", alarmMessage.getPlace());
            jsonObject.put("time", alarmMessage.getTime());
            jsonObject.put("type", alarmMessage.getType());
            jsonObject.put("carNumber", alarmMessage.getCarnumber());
            jsonArray.add(jsonObject);
        }
        msgResponse.setData(jsonArray);
        return msgResponse;
    }

    /**
     * 添加警察信息
     *
     * @param pname
     * @param pnumber
     * @param pphone
     * @param pwork
     * @param pplace
     * @param page
     * @return
     */
    @Override
    public MsgResponse addpoliceinformation(String pname, String pnumber, String pphone, String pwork, String pplace, String page) {
        MsgResponse msgResponse = new MsgResponse();
        logger.info("---------->添加警察开始");
        int result = msgMapper.addpoliceinformation(pname, pnumber, pphone, pwork, pplace, page);
        logger.info("---------->添加警察返回值：　" + result + "\n");
        if (!(result == 0)) {
            msgResponse.setStatusCode(0);
            msgResponse.setMsg("添加警察信息成功！");
        } else {
            msgResponse.setStatusCode(1);
            msgResponse.setMsg("添加警察信息失败！");
        }

        return msgResponse;
    }

    /**
     * 获取警察信息
     *
     * @return
     */
    @Override
    public MsgResponse getpoliceMessage() {
        MsgResponse msgResponse = new MsgResponse();
        JSONArray jsonArray = new JSONArray();
        List<PoliceInformation> policeInformations = msgMapper.getallpolice();
        if (policeInformations.size() == 0) {
            msgResponse.setStatusCode(1);
            msgResponse.setMsg("没有警察信息，请添加警察信息");
            return msgResponse;
        }
        for (PoliceInformation alarmMessage : policeInformations) {
            JSONObject jsonObject = new JSONObject(new LinkedHashMap<>());
            jsonObject.put("pname", alarmMessage.getPname());
            jsonObject.put("pnumber", alarmMessage.getPnumber());
            jsonObject.put("pphone", alarmMessage.getPphone());
            jsonObject.put("pwork", alarmMessage.getPwork());
            jsonObject.put("pplace", alarmMessage.getPplace());
            jsonObject.put("page", alarmMessage.getPage());
            jsonArray.add(jsonObject);
        }
        msgResponse.setData(jsonArray);
        return msgResponse;
    }

    /**
     * 获取警察信息（有条件）
     *
     * @return
     */
    @Override
    public MsgResponse getpoliceMessageByparameter(String pname, String pnumber) {
        MsgResponse msgResponse = new MsgResponse();
        JSONArray jsonArray = new JSONArray();
        List<PoliceInformation> policeInformations = msgMapper.getpolice(pname, pnumber);
        if (policeInformations.size() == 0) {
            msgResponse.setStatusCode(1);
            msgResponse.setMsg("没有此条件的警察信息");
            return msgResponse;
        }
        for (PoliceInformation alarmMessage : policeInformations) {
            JSONObject jsonObject = new JSONObject(new LinkedHashMap<>());
            jsonObject.put("pname", alarmMessage.getPname());
            jsonObject.put("pnumber", alarmMessage.getPnumber());
            jsonObject.put("pphone", alarmMessage.getPphone());
            jsonObject.put("pwork", alarmMessage.getPwork());
            jsonObject.put("pplace", alarmMessage.getPplace());
            jsonObject.put("page", alarmMessage.getPage());
            jsonArray.add(jsonObject);
        }
        msgResponse.setData(jsonArray);
        return msgResponse;
    }

    @Override
    public MsgResponse addnurseinformation(String pname, String pnumber, String pphone, String pwork, String pplace, String page) {
        MsgResponse msgResponse = new MsgResponse();
        logger.info("---------->添加护士开始");
        int result = msgMapper.addnurseinformation(pname, pnumber, pphone, pwork, pplace, page);
        logger.info("---------->添加护士返回值：　" + result + "\n");
        if (!(result == 0)) {
            msgResponse.setStatusCode(0);
            msgResponse.setMsg("添加护士信息成功！");
        } else {
            msgResponse.setStatusCode(1);
            msgResponse.setMsg("添加护士信息失败！");
        }

        return msgResponse;
    }

    @Override
    public MsgResponse getnurseMessage() {
        MsgResponse msgResponse = new MsgResponse();
        JSONArray jsonArray = new JSONArray();
        List<PoliceInformation> policeInformations = msgMapper.getallnurse();
        if (policeInformations.size() == 0) {
            msgResponse.setStatusCode(1);
            msgResponse.setMsg("没有护士信息，请添加护士信息");
            return msgResponse;
        }
        for (PoliceInformation alarmMessage : policeInformations) {
            JSONObject jsonObject = new JSONObject(new LinkedHashMap<>());
            jsonObject.put("pname", alarmMessage.getPname());
            jsonObject.put("pnumber", alarmMessage.getPnumber());
            jsonObject.put("pphone", alarmMessage.getPphone());
            jsonObject.put("pwork", alarmMessage.getPwork());
            jsonObject.put("pplace", alarmMessage.getPplace());
            jsonObject.put("page", alarmMessage.getPage());
            jsonArray.add(jsonObject);
        }
        msgResponse.setData(jsonArray);
        return msgResponse;
    }

    @Override
    public MsgResponse getnurseMessageByparameter(String pname, String pnumber) {
        MsgResponse msgResponse = new MsgResponse();
        JSONArray jsonArray = new JSONArray();
        List<PoliceInformation> policeInformations = msgMapper.getnurse(pname, pnumber);
        if (policeInformations.size() == 0) {
            msgResponse.setStatusCode(1);
            msgResponse.setMsg("没有此条件的护士信息");
            return msgResponse;
        }
        for (PoliceInformation alarmMessage : policeInformations) {
            JSONObject jsonObject = new JSONObject(new LinkedHashMap<>());
            jsonObject.put("pname", alarmMessage.getPname());
            jsonObject.put("pnumber", alarmMessage.getPnumber());
            jsonObject.put("pphone", alarmMessage.getPphone());
            jsonObject.put("pwork", alarmMessage.getPwork());
            jsonObject.put("pplace", alarmMessage.getPplace());
            jsonObject.put("page", alarmMessage.getPage());
            jsonArray.add(jsonObject);
        }
        msgResponse.setData(jsonArray);
        return msgResponse;
    }
}
