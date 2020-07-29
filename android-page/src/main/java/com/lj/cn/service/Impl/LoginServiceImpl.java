package com.lj.cn.service.Impl;

import com.lj.cn.mapper.LoginMapper;
import com.lj.cn.pojo.MsgResponse;
import com.lj.cn.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginServiceImpl implements LoginService {
    private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);
    @Autowired
    LoginMapper loginMapper;

    /**
     * 登录
     *
     * @param userName
     * @param passWord
     * @return
     */
    @Override
    public MsgResponse login(String userName, String passWord, String type) {
        MsgResponse msgResponse = new MsgResponse();
        logger.info("---------->登录开始");
        String name = loginMapper.selectPerson(userName, type);

        //创建表
//        int xx = loginMapper.createNewTable("lebron_table");
//        System.out.println(xx);
        //

        logger.info("---------->数据库中用户名：　" + name + "\n");
        if (name == null) {
            msgResponse.setStatusCode(1);
            msgResponse.setMsg("没有这个用户的信息！");
            return msgResponse;
        }
        String result = loginMapper.login(userName, passWord, type);
        logger.info("---------->登录查询的数据库值为：　" + result + "\n");
        if (result == null) {
            msgResponse.setStatusCode(1);
            msgResponse.setMsg("密码错误");
            return msgResponse;
        }
        if (result.equals(passWord)) {
            msgResponse.setStatusCode(0);
            msgResponse.setMsg("登录成功");
        }
        return msgResponse;
    }

    /**
     * 注册用户
     *
     * @return
     */
    @Override
    public MsgResponse registerUserName(String userName, String passWord, String telePhone, String email, String catNumber, String type) {
        MsgResponse msgResponse = new MsgResponse();
        logger.info("---------->注册用户开始");

        String name = loginMapper.selectPerson(userName, type);
        logger.info("---------->数据库中用户名：　" + name + "\n");
        if (name == null) {
            int result = loginMapper.registerUserName(userName, passWord, telePhone, email, catNumber, type);
            logger.info("---------->注册用户的数据库值为：　" + result + "\n");
            if (!(result == 0)) {
                msgResponse.setStatusCode(0);
                msgResponse.setMsg("注册成功！");
            }
        } else {
            msgResponse.setStatusCode(1);
            msgResponse.setMsg("用户名以及存在,请重新填写用户名！");
            return msgResponse;
        }

        return msgResponse;
    }

    /**
     * 修改密码
     *
     * @param userName 　用户名
     * @param passWord 　密码
     * @return
     */
    @Override
    public MsgResponse updataPassword(String userName, String passWord, String type) {
        logger.info("---------->修改密码开始");
        MsgResponse msgResponse = new MsgResponse();
        String name = loginMapper.selectPerson(userName, type);
        logger.info("---------->数据库中用户名：　" + name + "\n");
        if (name == null) {
            msgResponse.setStatusCode(1);
            msgResponse.setMsg("没有这个用户的信息！");
            return msgResponse;
        }
        int result = loginMapper.updataPassword(userName, passWord, type);
        logger.info("---------->修改密码的数据库值为：　" + result + "\n");
        if (!(result == 0)) {
            msgResponse.setStatusCode(0);
            msgResponse.setMsg("修改密码成功！");
        } else {
            msgResponse.setStatusCode(1);
            msgResponse.setMsg("修改密码失败！");
            return msgResponse;
        }
        return msgResponse;
    }

    //注册安卓用户
    @Override
    public MsgResponse registerUserName0(String userName, String passWord, String telePhone, String id, String catNumber, String type) {
        MsgResponse msgResponse = new MsgResponse();
        logger.info("---------->注册用户开始");

        String name = loginMapper.selectPerson(userName, type);
        logger.info("---------->数据库中用户名：　" + name + "\n");
        if (name == null) {
            int result = loginMapper.registerUserName0(userName, passWord, telePhone, id, catNumber, type);
            logger.info("---------->注册用户的数据库值为：　" + result + "\n");
            if (!(result == 0)) {
                msgResponse.setStatusCode(0);
                msgResponse.setMsg("注册成功！");
            }
        } else {
            msgResponse.setStatusCode(1);
            msgResponse.setMsg("用户名以及存在,请重新填写用户名！");
            return msgResponse;
        }

        return msgResponse;
    }
}
