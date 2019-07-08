package com.tong.sevenproxy.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Author: SevenTian
 * @Description: CGLIB(Code Generation Library),是一个强大的，高性能的Code生成类库，可以在运行的期间扩展java类
 * 其生成代理的方法：生成一个真实的对象的子类，在这个子类中进行扩展
 * @Create_Date: 11:55 2019/6/27
 * @Modified_Author:
 * @Modified_Date:
 */
public class CGLIBProxy {

    private BookServiceImpl bookService;

    public CGLIBProxy(BookServiceImpl bookService){
        this.bookService = bookService;
    }

    /**
     * 获取等待加强的类的代理对象
     * @return
     */
    public BookServiceImpl createBookDaoImpl(){
        //新建一个CGLIB的核心类
        Enhancer enhancer = new Enhancer();
        //设置父类为等待加强的类
        enhancer.setSuperclass(BookServiceImpl.class);
        //设置回调，用于增强的地方
        //MethodInterceptor继承自CallBack类
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
               if(method.getName().startsWith("borrow")){
                   System.out.println("borrow,borrow");
                   return method.invoke(bookService,objects);
               }
              return method.invoke(bookService,objects);
            }
        });
        //创建enhance创建的类的代理后的对象
        return (BookServiceImpl) enhancer.create();
    }

}
