package com.tong.sevencore.controller;

import com.tong.sevencommon.utils.DateKit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: SevenTian
 * @Description:
 * @Create_Date: 17:45 2019/6/26
 * @Modified_Author:
 * @Modified_Date:
 */
@RestController
@RequestMapping("/helloCore")
public class HelloCore {
    private static Logger log = LoggerFactory.getLogger(HelloCore.class);
    @RequestMapping("/helloTime")
    public String helloTime(){
        String dateStr = DateKit.getDateStr(System.currentTimeMillis());
        log.debug("hello,core! this is debug,{}",dateStr);
        log.info("hello,core! this is info,{}",dateStr);
        log.warn("hello,core! this is warn,{}",dateStr);
        log.error("hello,core! this is error,{}",dateStr);
        return dateStr;
    }
}
