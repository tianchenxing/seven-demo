package com.tong.sevencommon.pojo;

import lombok.Data;

/**
 * 用户信息表
 */
@Data
public class TbBaseUserInfo extends BasePojo{

    private String name;
    /*密码*/
    private String passWord;
    /*性别*/
    private int gender;

    private String telephone;

    private String address;
    /*身份证号*/
    private String idNo;
    /*备注*/
    private String remark;
}