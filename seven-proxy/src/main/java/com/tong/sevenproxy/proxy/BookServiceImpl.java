package com.tong.sevenproxy.proxy;

/**
 * @Author: SevenTian
 * @Description:
 * @Create_Date: 10:41 2019/6/27
 * @Modified_Author:
 * @Modified_Date:
 */
public class BookServiceImpl implements BookDao {
    @Override
    public void borrowBook() {
        System.out.println("i will borrow  a book");
    }

    @Override
    public void buyBook() {
        System.out.println("i will buy  a book");
    }
}
