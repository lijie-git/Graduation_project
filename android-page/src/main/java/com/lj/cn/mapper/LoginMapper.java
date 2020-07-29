package com.lj.cn.mapper;

import com.lj.cn.pojo.GetPeonsonInformation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface LoginMapper {
    //查询用户
    String selectPerson(@Param("userName") String userName, @Param("type") String type);

    //登录
    String login(@Param("userName") String userName, @Param("passWord") String passWord, @Param("type") String type);

    //修改密码
    int updataPassword(@Param("userName") String userName, @Param("passWord") String passWord, @Param("type") String type);

    //注册用户
    int registerUserName(@Param("userName") String userName, @Param("passWord") String passWord, @Param("telePhone") String telePhone, @Param("email") String email, @Param("catNumber") String catNumber, @Param("type") String type);

    //注册安卓用户
    int registerUserName0(@Param("userName") String userName, @Param("passWord") String passWord, @Param("telePhone") String telePhone, @Param("id") String id, @Param("catNumber") String catNumber, @Param("type") String type);

    //查询安卓报警信息
    GetPeonsonInformation getPeonsonInformation(@Param("userName") String userName, @Param("type") String type);

    //插入报警信息
    int addPeonsonInformation(@Param("name") String name, @Param("phone") String phone, @Param("carnumber") String carnumber, @Param("time") String time);

    //创建数据库表
    int createNewTable(@Param("tableName")String tableName);
}
