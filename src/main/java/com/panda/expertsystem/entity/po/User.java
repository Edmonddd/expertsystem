package com.panda.expertsystem.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Author: lhw
 * @Date:
 * @Description:
 */
@Data
@TableName("user")
public class User implements Serializable {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    //姓名
    private String name;

    //密码
    private String password;

    //手机号
    private String phone;

    //邮箱
    private String email;

    //推荐人
    private String recommender;

    private Date createTime;

    private Date endTime;

    private Byte sex;

    private LocalDate birthday;

    private Byte receiveMethod;

    //省级区划编号
    private String provinceCode;

    //省级名称
    private String provinceName;

    //市级区划编号
    private String cityCode;

    //市级名称
    private String cityName;

    //详细地址
    private String addressDetail;

    //邮政编码
    private Integer zipCode;

    //办公电话
    private String officePhone;

    //个人主页
    private String personPage;

    //最高学历
    private int highestDegree;

    //学历获得时间
    private Date degreeReceiveTime;

    //任职单位
    private String workingUnit;

    //现任职务
    private String currentPosition;

    //工作年限
    private Byte workingYears;

    /**
     * 职称类别
     * 1:正高级  2:副高级  3:中级  4:初级  5:其他
     */
    private Byte titleCategory;

    /**
     * 任职机构类型
     * 1:科研机构  2:教育机构  3:企业  4:政府  5:社会团体  6:事业单位  7:其他
     */
    private Byte employmentType;

    /**
     * 入会渠道
     * 1:
     */
    private Byte membershipChannel;

    /**
     * 备注
     */
    private String note;

}
