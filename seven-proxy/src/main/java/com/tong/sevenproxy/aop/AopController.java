package com.tong.sevenproxy.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: SevenTian
 * @Description:
 * @Create_Date: 9:20 2019/6/28
 * @Modified_Author:
 * @Modified_Date:
 */
@RequestMapping("/aop")
@RestController
public class AopController {
    @Autowired
    private ChangeParamServiceImpl changeParamService;
     @RequestMapping("/sayHello")
    public String sayHello(@RequestParam("name") String name) throws Exception{
//             throw new Exception("this is exception");
      return changeParamService.getParam("yizhong",name);
    }
}
