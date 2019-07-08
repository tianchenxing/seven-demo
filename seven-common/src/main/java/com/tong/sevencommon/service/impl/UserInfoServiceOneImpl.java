package com.tong.sevencommon.service.impl;

import com.tong.sevencommon.mapper.UserInfoMapper;
import com.tong.sevencommon.mutiplydatasource.MultiplyDataSource;
import com.tong.sevencommon.pojo.TbBaseUserInfo;
import com.tong.sevencommon.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @Author: SevenTian
 * @Description:
 * @Create_Date: 14:20 2019/7/2
 * @Modified_Author:
 * @Modified_Date:
 */
@Service
public class UserInfoServiceOneImpl implements UserInfoService {
    private static Logger log = LoggerFactory.getLogger(UserInfoServiceOneImpl.class);
    @Autowired
    private UserInfoMapper userInfoMapper;

    /**
     * 插入一条数据
     * @param userInfo
     * @return
     */
    @MultiplyDataSource(name="one")
    public Integer insertUser(TbBaseUserInfo userInfo){
        return  userInfoMapper.insert(userInfo);
    }

    /**
     * 批量插入
     * @param userList
     * @return
     */
    public Integer insertUserList(List<TbBaseUserInfo> userList){
        return userInfoMapper.insertList(userList);
    }




}
