package com.lj.cn.controller;

import com.lj.cn.mapper.MsgMapper;
import com.lj.cn.pojo.MsgResponse;
import com.lj.cn.service.GetMsgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/police")
public class PoliceMsgController {
    @Autowired
    MsgMapper msgMapper;
    @Autowired
    GetMsgService getMsgService;
    private static final Logger logger = LoggerFactory.getLogger(PoliceMsgController.class);

    /**
     * 查询全部报警信息
     *
     * @param map
     * @return
     */
    @CrossOrigin
    @PostMapping("/getallmessage")
    public MsgResponse getMessage(@RequestBody Map<String, Object> map) {
        logger.info("---------->查询全部报警信息：　" + map.toString() + "\n");
        MsgResponse msgResponse = new MsgResponse();
        msgResponse = getMsgService.getMessage();
        return msgResponse;
    }

    /**
     * 查询全部报警信息(分条件)
     *
     * @param map
     * @return
     */
    @CrossOrigin
    @PostMapping("/getmessage")
    public MsgResponse getMessageByparameter(@RequestBody Map<String, Object> map) {
        logger.info("---------->查询部分报警信息(分条件)：　" + map.toString() + "\n");
        String userName = (String) map.get("userName");
        String catNumber = (String) map.get("catNumber");
        MsgResponse msgResponse = new MsgResponse();
        msgResponse = getMsgService.getMessageByparameter(userName, catNumber);
        return msgResponse;
    }

    /**
     * 添加警察
     *
     * @param map
     * @return
     */
    @CrossOrigin
    @PostMapping("/addpoliceinformation")
    public MsgResponse addpoliceinformation(@RequestBody Map<String, Object> map) {
        MsgResponse msgResponse = new MsgResponse();
        logger.info("---------->添加警察：　" + map.toString() + "\n");
        String pname = (String) map.get("pname");
        String pnuber = (String) map.get("pnuber");
        String pphone = (String) map.get("pphone");
        String pwork = (String) map.get("pwork");
        String pplace = (String) map.get("pplace");
        String page = (String) map.get("page");
        if (pname == null) {
            msgResponse.setStatusCode(1);
            msgResponse.setMsg("姓名为空！");
            return msgResponse;
        }
        if (pnuber == null) {
            msgResponse.setStatusCode(1);
            msgResponse.setMsg("工号为空！");
            return msgResponse;
        }
        if (pphone == null) {
            msgResponse.setStatusCode(1);
            msgResponse.setMsg("手机号为空！");
            return msgResponse;
        }
        if (pwork == null) {
            msgResponse.setStatusCode(1);
            msgResponse.setMsg("工作为空！");
            return msgResponse;
        }
        if (pplace == null) {
            msgResponse.setStatusCode(1);
            msgResponse.setMsg("工作地点为空");
            return msgResponse;
        }
        if (page == null) {
            msgResponse.setStatusCode(1);
            msgResponse.setMsg("年龄为空");
            return msgResponse;
        }
        msgResponse = getMsgService.addpoliceinformation(pname, pnuber,pphone,pwork,pplace,page);
        return msgResponse;
    }


    /**
     * 查询全部警察信息
     *
     * @param map
     * @return
     */
    @CrossOrigin
    @PostMapping("/getpoliceallmessage")
    public MsgResponse getpoliceallmessage(@RequestBody Map<String, Object> map) {
        logger.info("---------->查询全部警察信息：　" + map.toString() + "\n");
        MsgResponse msgResponse = new MsgResponse();
        msgResponse = getMsgService.getpoliceMessage();
        return msgResponse;
    }

    /**
     * 查询部分警察信息(分条件)
     *
     * @param map
     * @return
     */
    @CrossOrigin
    @PostMapping("/getpolicemessage")
    public MsgResponse getpolicemessage(@RequestBody Map<String, Object> map) {
        logger.info("---------->查询部分警察信息(分条件)：　" + map.toString() + "\n");
        String pname = (String) map.get("pname");
        String pnumber = (String) map.get("pnumber");
        MsgResponse msgResponse = new MsgResponse();
        msgResponse = getMsgService.getpoliceMessageByparameter(pname, pnumber);
        return msgResponse;
    }
}
