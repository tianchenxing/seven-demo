package com.tong.sevencommon.controller;

import com.tong.sevencommon.mapper.UserInfoMapper;
import com.tong.sevencommon.pojo.TbBaseUserInfo;
import com.tong.sevencommon.service.impl.UserInfoServiceImpl;
import com.tong.sevencommon.service.impl.UserInfoServiceOneImpl;
import com.tong.sevencommon.utils.customizedResponse.ServerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Condition;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: SevenTian
 * @Description:
 * @Create_Date: 18:27 2019/7/2
 * @Modified_Author:
 * @Modified_Date:
 */
@RestController
@RequestMapping("/userInfo")
public class UserInfoController {
    private  static Logger log = LoggerFactory.getLogger(UserInfoController.class);
    @Autowired
    private UserInfoServiceImpl userInfoService;

    @Autowired
    private UserInfoServiceOneImpl userInfoOneService;
    @RequestMapping("/addOne")
    public ResponseEntity<Integer> addOneBean(@RequestBody TbBaseUserInfo userInfo){
        Integer result = userInfoService.insertUser(userInfo);
        userInfo.setRemark(userInfo.getId().toString());
        userInfo.setId(null);
        result += userInfoOneService.insertUser(userInfo);
        return new ResponseEntity(ServerResponse.buildOK(result),HttpStatus.OK);
    }
    @RequestMapping("/selectList")
    public ResponseEntity<Integer> selectList(@RequestBody TbBaseUserInfo userInfo){

        List<TbBaseUserInfo> result = userInfoService.selectUsers(userInfo);
        if(CollectionUtils.isEmpty(result)){
          result = new ArrayList<TbBaseUserInfo>();
        }
        return new ResponseEntity(ServerResponse.buildOK(result),HttpStatus.OK);
    }
    @RequestMapping("/selectByDynamicSql")
    public ResponseEntity<Integer> selectByDynamicSql(){

        List<TbBaseUserInfo> result = userInfoService.selectByExample();
        if(CollectionUtils.isEmpty(result)){
            result = new ArrayList<TbBaseUserInfo>();
        }
        return new ResponseEntity(ServerResponse.buildOK(result),HttpStatus.OK);
    }

}
