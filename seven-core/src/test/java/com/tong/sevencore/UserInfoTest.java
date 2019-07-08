package com.tong.sevencore;

import com.tong.sevencommon.pojo.TbBaseUserInfo;
import com.tong.sevencommon.service.impl.UserInfoServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: SevenTian
 * @Description:
 * @Create_Date: 14:34 2019/7/2
 * @Modified_Author:
 * @Modified_Date:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class UserInfoTest {
    @Autowired
    private UserInfoServiceImpl userInfoService;
    @Test
    public void test1(){
        TbBaseUserInfo userInfo = new TbBaseUserInfo();
        userInfo.setAddress("天津河西");
        userInfo.setGender(1);
        userInfo.setPassWord("lsdsdslfh");
        userInfo.setName("bella");
        userInfoService.insertUser(userInfo);
    }
}
