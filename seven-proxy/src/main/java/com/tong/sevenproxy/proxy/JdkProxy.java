package com.tong.sevenproxy.proxy;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author: SevenTian
 * @Description: JDK动态代理实现 使用java.lang.reflect.Proxy这个类对目标对象进行增强
 * @Create_Date: 10:38 2019/6/27
 * @Modified_Author:
 * @Modified_Date:
 */

public class JdkProxy {

    private BookDao bookDao;

    public JdkProxy(BookDao bookDao){
        this.bookDao = bookDao;
    }
    //返回被代理接口实例
    public BookDao createBookService(){
        Object instance =
                Proxy.newProxyInstance(bookDao.getClass().getClassLoader(),
                        bookDao.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (method.getName().startsWith("buy")) {
                    System.out.println("buy,buy");
                    return method.invoke(bookDao, args);
                }
                return method.invoke(args);
            }
        });
        return (BookDao)instance;
    }


}
