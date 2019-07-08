package com.tong.sevenproxy.proxy;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: SevenTian
 * @Description:
 * @Create_Date: 9:20 2019/6/28
 * @Modified_Author:
 * @Modified_Date:
 */
@RequestMapping("/proxy")
@RestController
public class ProxyController {
     @RequestMapping("/sayHello")
    public String sayHello(){
        return "this is proxy";
    }
}
