package com.tong.sevencommon.service.impl;

import com.tong.sevencommon.mapper.UserInfoMapper;
import com.tong.sevencommon.pojo.TbBaseUserInfo;
import com.tong.sevencommon.redisset.RedisUtil;
import com.tong.sevencommon.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Author: SevenTian
 * @Description: 用户信息表
 * @Create_Date: 14:20 2019/7/2
 * @Modified_Author:
 * @Modified_Date:
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    private static Logger log = LoggerFactory.getLogger(UserInfoServiceImpl.class);
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private RedisUtil redisUtil;


    /**
     * 插入一条数据
     * @param userInfo
     * @return
     */
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

    /**
     *按照id修改数据
     * @param id
     * @return
     */
    public Integer deleteUser(Long id){
        return userInfoMapper.deleteByPrimaryKey(id);
    }

    /**
     * 按照条件删除
     * @param userInfo
     * @return
     */
    public Integer deleteUserByCondition(TbBaseUserInfo userInfo){
       return  userInfoMapper.deleteByCondition(userInfo);
    }

    /**
     * 按照条件查询数据
     * @param userInfo
     * @return
     */
    public List<TbBaseUserInfo> selectUsers(TbBaseUserInfo userInfo){
        if(null == userInfo){
            return new ArrayList<TbBaseUserInfo>();
        }
        if(!redisUtil.hasKey("name")) {
            redisUtil.set("name", "bella", 30);
        }
        String name =(String) redisUtil.get("name");
        userInfo.setName(name);
        return  userInfoMapper.select(userInfo);
    }

    /**
     * 动态查询
     * @return
     */
    public List<TbBaseUserInfo> selectByExample(){
        Map<String, Object> cache = new HashMap<String, Object>();
        cache.put("name","tom");
        cache.put("idNo","125445");
        //将传入值放入redis缓存
        if(!redisUtil.hasKey("user")) {
            redisUtil.hmset("user", cache, 300);
        }
        Map<Object, Object> userMap = redisUtil.hmget("user");
        TbBaseUserInfo query = new TbBaseUserInfo();
        query.setName((String) userMap.get("name"));
        query.setIdNo((String) userMap.get("idNo"));
        Example condition = new Example(TbBaseUserInfo.class);
        Example.Criteria criteria = condition.createCriteria();
        condition.setDistinct(false);
        condition.setOrderByClause("id_no desc");
        criteria.andEqualTo("name",query.getName());
        criteria.andEqualTo("idNo",query.getIdNo());
        return userInfoMapper.selectByExample(condition);
    }
}
