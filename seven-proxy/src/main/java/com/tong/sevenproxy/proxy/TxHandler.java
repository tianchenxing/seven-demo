package com.tong.sevenproxy.proxy;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author: SevenTian
 * @Description: JTA Java Transaction API
 * @Create_Date: 9:38 2019/6/27
 * @Modified_Author:
 * @Modified_Date:
 */
public class TxHandler implements InvocationHandler {
    //
    private Object object;

    public Object bind(Object obj){
        this.object = obj;
        return Proxy.newProxyInstance(
                obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(),this);
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

       Object result = null;
       if(method.getName().startsWith("save")){
           UserTransaction tx = null;
           try {
              tx =  (UserTransaction) (new InitialContext().lookup("java/tx"));
              result = method.invoke(object,args);
              tx.commit();
           } catch (Exception e) {
               if (null != tx) {
                       tx.rollback();
               }
           }
       } else {
           result = method.invoke(object, args);

       }

        return result;
    }


}

