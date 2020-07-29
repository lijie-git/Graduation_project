package com.lj.cn.mapper;

import com.lj.cn.pojo.AlarmMessage;
import com.lj.cn.pojo.PoliceInformation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MsgMapper {
    //查询报警信息
    List<AlarmMessage> selectAlarmMessage();

    //查询报警信息
    List<AlarmMessage> getMessageByparameter(@Param("name") String name, @Param("carNumber") String carNumber);

    //添加报警信息
    int addpoliceinformation(@Param("pname") String pname, @Param("pnumber") String pnumber, @Param("pphone") String pphone, @Param("pwork") String pwork, @Param("pplace") String pplace, @Param("page") String page);

    //查询警察信息
    List<PoliceInformation> getallpolice();

    //查询警察信息(条件)
    List<PoliceInformation> getpolice(@Param("pname") String pname, @Param("pnumber") String pnumber);

    //添加报警信息
    int addnurseinformation(@Param("pname") String pname, @Param("pnumber") String pnumber, @Param("pphone") String pphone, @Param("pwork") String pwork, @Param("pplace") String pplace, @Param("page") String page);

    //查询警察信息
    List<PoliceInformation> getallnurse();

    //查询警察信息(条件)
    List<PoliceInformation> getnurse(@Param("pname") String pname, @Param("pnumber") String pnumber);

}
