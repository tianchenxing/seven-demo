package com.tong.sevenproxy.aop;

import org.springframework.stereotype.Service;

/**
 * @Author: SevenTian
 * @Description:
 * @Create_Date: 11:39 2019/6/28
 * @Modified_Author:
 * @Modified_Date:
 */
@Service
public class ChangeParamServiceImpl {
    public String getParam(String school,String name){
        System.out.println("getParam():"+name+":"+school);
        return name;
    }
}
