package com.lj.cn.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lj.cn.mapper.LoginMapper;
import com.lj.cn.mapper.MsgMapper;
import com.lj.cn.pojo.*;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.lj.cn.service.LoginService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/login")
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoggerFactory.class);
    @Autowired
    LoginService loginService;
    @Autowired
    PulsarClient pulsarClient;
    @Autowired
    MsgMapper msgMapper;
    @Autowired
    LoginMapper loginMapper;


    /**
     * 登录
     */
    @CrossOrigin
    @PostMapping("/login")
    public MsgResponse login(@RequestBody Map<String, Object> map) {
        logger.info("---------->开始登录数据：　" + map.toString() + "\n");
        MsgResponse msgResponse = new MsgResponse();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.print(" 键：" + entry.getKey());
            System.out.println("值：" + entry.getValue());
        }
        String userName = (String) map.get("userName");
        String passWord = (String) map.get("passWord");
        String type = (String) map.get("type");
        if (userName == null) {
            msgResponse.setStatusCode(1);
            msgResponse.setMsg("请输入用户名！");
            return msgResponse;
        }
        if (passWord == null) {
            msgResponse.setStatusCode(1);
            msgResponse.setMsg("请输入密码！");
            return msgResponse;
        }
        msgResponse = loginService.login(userName, passWord, type);
        return msgResponse;

    }

    /**
     * 注册用户
     *
     * @return MsgResponse
     */
    @CrossOrigin
    @PostMapping("/registerUserName")
    public MsgResponse registerUserName(@RequestBody Map<String, Object> map) {
        logger.info("---------->注册用户数据：　" + map.toString() + "\n");
        MsgResponse msgResponse = new MsgResponse();
        String type = (String) map.get("type");//密码
        if (type.equals("3")) {
            String passWord = (String) map.get("passWord");
            String userName = (String) map.get("userName");//用户名
            String telePhone = (String) map.get("telePhone");//电话
            String id = (String) map.get("ID");//身份证号
            String catNumber = (String) map.get("catNumber");//车牌号
            if (userName == null) {
                logger.info("------------------->请输入用户名: " + "\n");
                msgResponse.setStatusCode(1);
                msgResponse.setMsg("请输入用户名");
                return msgResponse;
            }
//            } else {
//                boolean iftrue = userName.matches("^(?=.*[0-9])(?=.*[a-zA-Z])(.{8,24})$");
//                if (iftrue == false) {
//                    msgResponse.setStatusCode(1);
//                    msgResponse.setMsg("账号格式有问题,可输入英文（区分大小写）与数字组合，长度8-24个字符！");
//                    return msgResponse;
//                }
//            }
            if (passWord == null) {
                logger.info("------------------->请输入密码: " + "\n");
                msgResponse.setStatusCode(1);
                msgResponse.setMsg("请输入密码");
                return msgResponse;
            } else {
                boolean iftrue = passWord.matches("^(?=.*[0-9])(?=.*[a-zA-Z])(.{8,16})$");
                if (iftrue == false) {
                    msgResponse.setStatusCode(1);
                    msgResponse.setMsg("密码格式有问题,可输入英文（区分大小写）与数字组合，长度8-16个字符！");
                    return msgResponse;
                }
            }
            if (telePhone == null) {
                logger.info("------------------->请输入手机号: " + "\n");
                msgResponse.setStatusCode(1);
                msgResponse.setMsg("请输入手机号");
                return msgResponse;
            } else {
                boolean iftrue = telePhone.matches("^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\\d{8}$");
                if (iftrue == false) {
                    msgResponse.setStatusCode(1);
                    msgResponse.setMsg("手机号格式错误！");
                    return msgResponse;
                }
            }
            if (catNumber == null && catNumber.length() == 0) {
                logger.info("------------------->请输入车牌号: " + "\n");
                msgResponse.setStatusCode(1);
                msgResponse.setMsg("请输入车牌号");
                return msgResponse;
            }
            if (id == null) {
                logger.info("------------------->请输入身份号: " + "\n");
                msgResponse.setStatusCode(1);
                msgResponse.setMsg("请输入身份号");
                return msgResponse;
            }
            msgResponse = loginService.registerUserName0(userName, passWord, telePhone, id, catNumber, type);
        } else {
            String passWord = (String) map.get("type");
            String userName = (String) map.get("userName");//用户名
            String email = (String) map.get("email");//邮箱
            String telePhone = (String) map.get("telePhone");//电话
            //String catNumber = (String) map.get("catNumber");//车牌号
            if (userName == null) {
                logger.info("------------------->请输入用户名: " + "\n");
                msgResponse.setStatusCode(1);
                msgResponse.setMsg("请输入用户名");
                return msgResponse;
            }
//            } else {
//                boolean iftrue = userName.matches("^(?=.*[0-9])(?=.*[a-zA-Z])(.{8,24})$");
//                if (iftrue == false) {
//                    msgResponse.setStatusCode(1);
//                    msgResponse.setMsg("账号格式有问题,可输入英文（区分大小写）与数字组合，长度8-24个字符！");
//                    return msgResponse;
//                }
//            }
            if (passWord == null) {
                logger.info("------------------->请输入密码: " + "\n");
                msgResponse.setStatusCode(1);
                msgResponse.setMsg("请输入密码");
                return msgResponse;
            }
//            }  else {
//                boolean iftrue = passWord.matches("^(?=.*[0-9])(?=.*[a-zA-Z])(.{8,16})$");
//                if (iftrue == false) {
//                    msgResponse.setStatusCode(1);
//                    msgResponse.setMsg("密码格式有问题,可输入英文（区分大小写）与数字组合，长度8-16个字符！");
//                    return msgResponse;
//                }
//            }
//        if (catNumber == null && catNumber.length() == 0) {
//            logger.info("------------------->请输入车牌号: " + "\n");
//            msgResponse.setStatusCode(1);
//            msgResponse.setMsg("请输入车牌号");
//            return msgResponse;
//        }
            if (email == null) {
                logger.info("------------------->请输入邮箱: " + "\n");
                msgResponse.setStatusCode(1);
                msgResponse.setMsg("请输入邮箱");
                return msgResponse;
            } else {
                boolean iftrue = email.matches("^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$");
                if (iftrue == false) {
                    msgResponse.setStatusCode(1);
                    msgResponse.setMsg("邮箱格式错误,请检查参数！");
                    return msgResponse;
                }
            }
            if (telePhone == null) {
                logger.info("------------------->请输入手机号: " + "\n");
                msgResponse.setStatusCode(1);
                msgResponse.setMsg("请输入手机号");
                return msgResponse;
            } else {
                boolean iftrue = telePhone.matches("^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\\d{8}$");
                if (iftrue == false) {
                    msgResponse.setStatusCode(1);
                    msgResponse.setMsg("手机号格式错误！");
                    return msgResponse;
                }
            }
            msgResponse = loginService.registerUserName(userName, passWord, telePhone, email, null, type);
        }
        return msgResponse;
    }

    /**
     * 修改密码
     */
    @CrossOrigin
    @PostMapping("/updataPassword")
    public MsgResponse updataPassword(@RequestBody Map<String, Object> map) {
        logger.info("---------->修改密码数据：　" + map.toString() + "\n");
        MsgResponse msgResponse = new MsgResponse();
        String passWord = (String) map.get("type");

        String userName = (String) map.get("userName");
        String type = (String) map.get("passWord");
        if (userName == null || userName.length() == 0) {
            msgResponse.setStatusCode(1);
            msgResponse.setMsg("请输入用户名");
            return msgResponse;
        }
        if (passWord == null || passWord.length() == 0) {
            msgResponse.setStatusCode(1);
            msgResponse.setMsg("请输入密码");
            return msgResponse;
        }
        msgResponse = loginService.updataPassword(userName, passWord, type);
        return msgResponse;
    }

    /**
     * 重置密码
     */
    @CrossOrigin
    @PostMapping("/resetPassword")
    public MsgResponse resetPassword(@RequestBody Map<String, Object> map) {
        logger.info("---------->重置密码：　" + map.toString() + "\n");
        MsgResponse msgResponse = new MsgResponse();
        String type = (String) map.get("type");
        String userName = (String) map.get("userName");
        String passWord = (String) map.get("passWord");
        if (userName == null || userName.length() == 0) {
            msgResponse.setStatusCode(1);
            msgResponse.setMsg("请输入用户名");
            return msgResponse;
        }
        if (passWord == null || passWord.length() == 0) {
            msgResponse.setStatusCode(1);
            msgResponse.setMsg("请输入密码");
            return msgResponse;
        }
        msgResponse = loginService.updataPassword(userName, passWord, type);
        return msgResponse;
    }

    /**
     * 获取经纬度
     *
     * @throws PulsarClientException
     */
    @CrossOrigin
    @PostMapping("/getLatitudeAndLongitude")
    public MsgResponse getLatitudeAndLongitude(@RequestBody Map<String, Object> map) throws PulsarClientException {
        logger.info("---------->获取经纬度开始：　" + map.toString() + "\n");
        MsgResponse msgResponse = new MsgResponse();
        GetPeonsonInformation getPeonsonInformation = null;
        String latitude = (String) map.get("getLongitude");
        String userName = (String) map.get("userName");
        String type = (String) map.get("type");
        String longitude = (String) map.get("getLatitude");
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = formatter.format(date);
        try {
            getPeonsonInformation = loginMapper.getPeonsonInformation(userName, type);
            if (getPeonsonInformation != null) {
                loginMapper.addPeonsonInformation(userName, getPeonsonInformation.getPhone(), getPeonsonInformation.getCatNumber(), time);
            }
        } catch (Exception e) {
            msgResponse.setStatusCode(1);
            msgResponse.setMsg(e.getMessage());
            return msgResponse;
        }
        if (getPeonsonInformation == null) {
            msgResponse.setStatusCode(1);
            msgResponse.setMsg("没有报警人信息无法报警");
            return msgResponse;
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("getLongitude", latitude);
        jsonObject.put("userName", userName);
        jsonObject.put("phone", getPeonsonInformation.getPhone());
        jsonObject.put("catNumber", getPeonsonInformation.getCatNumber());
        jsonObject.put("ID", getPeonsonInformation.getID());
        jsonObject.put("getLatitude", longitude);
        Producer<byte[]> producer = pulsarClient.newProducer().topic("TESTTOPIC").sendTimeout(0, TimeUnit.SECONDS).create();
        try {
            producer.send(JSON.toJSONString(jsonObject).getBytes());
            producer.close();
        } catch (PulsarClientException e) {
            msgResponse.setStatusCode(1);
            msgResponse.setMsg("发送出错");
            return msgResponse;
        }
        return msgResponse;
    }

    @CrossOrigin
    @PostMapping("/testForm")
    public MsgResponse test(@RequestBody Map<String, Object> map) {
        logger.info("---------->重置密码：　" + map.toString() + "\n");
        MsgResponse msgResponse = new MsgResponse();
        GetPeonsonInformation getPeonsonInformation = loginMapper.getPeonsonInformation("11", "1");
        System.out.println(getPeonsonInformation.toString());
        return msgResponse;
    }
}
