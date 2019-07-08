package com.tong.sevencommon.mutiplydatasource;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @Author: SevenTian
 * @Description:
 * @Create_Date: 14:42 2019/7/4
 * @Modified_Author:
 * @Modified_Date:
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
public @interface MultiplyDataSource {
    String name();
}
