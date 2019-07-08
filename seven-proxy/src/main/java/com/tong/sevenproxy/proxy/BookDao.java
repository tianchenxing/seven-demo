package com.tong.sevenproxy.proxy;

import org.springframework.stereotype.Repository;

/**
 * @Author: SevenTian
 * @Description:
 * @Create_Date: 10:40 2019/6/27
 * @Modified_Author:
 * @Modified_Date:
 */
public interface BookDao {
    void borrowBook();
    void buyBook();
}
