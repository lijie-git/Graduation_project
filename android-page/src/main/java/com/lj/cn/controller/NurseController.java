package com.lj.cn.controller;

import com.lj.cn.pojo.MsgResponse;
import com.lj.cn.service.GetMsgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/nurse")
public class NurseController {
    @Autowired
    GetMsgService getMsgService;
    private static final Logger logger = LoggerFactory.getLogger(NurseController.class);

    /**
     * 添加护士
     *
     * @param map
     * @return
     */
    @CrossOrigin
    @PostMapping("/addnurseformation")
    public MsgResponse addpoliceinformation(@RequestBody Map<String, Object> map) {
        MsgResponse msgResponse = new MsgResponse();
        logger.info("---------->添加警察：　" + map.toString() + "\n");
        String pname = (String) map.get("dname");
        String dnuber = (String) map.get("dnuber");
        String pphone = (String) map.get("dphone");
        String pwork = (String) map.get("dwork");
        String pplace = (String) map.get("dplace");
        String page = (String) map.get("dpage");
        if (pname == null) {
            msgResponse.setStatusCode(1);
            msgResponse.setMsg("姓名为空！");
            return msgResponse;
        }
        if (dnuber == null) {
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
        msgResponse = getMsgService.addnurseinformation(pname, dnuber, pphone, pwork, pplace, page);
        return msgResponse;
    }


    /**
     * 查询全部护士信息
     *
     * @param map
     * @return
     */
    @CrossOrigin
    @PostMapping("/getnursellmessage")
    public MsgResponse getpoliceallmessage(@RequestBody Map<String, Object> map) {
        logger.info("---------->查询全部警察信息：　" + map.toString() + "\n");
        MsgResponse msgResponse = new MsgResponse();
        msgResponse = getMsgService.getnurseMessage();
        return msgResponse;
    }

    /**
     * 查询部分护士信息(分条件)
     *
     * @param map
     * @return
     */
    @CrossOrigin
    @PostMapping("/getnursemessage")
    public MsgResponse getpolicemessage(@RequestBody Map<String, Object> map) {
        logger.info("---------->查询部分警察信息(分条件)：　" + map.toString() + "\n");
        String pname = (String) map.get("dname");
        String pnumber = (String) map.get("dnumber");
        MsgResponse msgResponse = new MsgResponse();
        msgResponse = getMsgService.getnurseMessageByparameter(pname, pnumber);
        return msgResponse;
    }
}
