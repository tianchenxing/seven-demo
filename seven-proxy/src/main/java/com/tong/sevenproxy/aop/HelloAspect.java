package com.tong.sevenproxy.aop;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.CodeSignature;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: SevenTian
 * @Description:
 * @Create_Date: 9:52 2019/6/28
 * @Modified_Author:
 * @Modified_Date:
 */
@Aspect
@Component
public class HelloAspect {
    private Logger log = LoggerFactory.getLogger(HelloAspect.class);
    /**
     * 定义切入点，切入点为com.tong.sevenproxy.aop..*.*(..)
     * 1.切入点表达式的格式：execution([可见性]返回类型[声明类型].方法名(参数)[异常])
     * 其中[]内的是可选的，其它的还支持通配符的使用：
     * 1) *：匹配所有字符
     * 2) ..：一般用于匹配多个包，多个参数
     * 3) +：表示类及其子类
     * 4)运算符有：&&,||,!
     *
     * 1).匹配com.cjm.model包及其子包中所有类中的所有方法，返回类型任意，方法参数任意
     * @Pointcut(“execution(* com.cjm.model...(..))”)
     * 2).within：用于匹配连接点所在的Java类或者包。
     * 匹配Person类中的所有方法
     * @Pointcut(“within(com.cjm.model.Person)”)
     * 3). this：用于向通知方法中传入代理对象的引用。
     * @Before(“before() && this(proxy)”)
     * 4）target：用于向通知方法中传入目标对象的引用。
     * @Before(“before() && target(target)
     * 5）args：用于将参数传入到通知方法中。
     * @Before(“before() && args(age,username)”)
     * 6）@within ：用于匹配在类一级使用了参数确定的注解的类，其所有方法都将被匹配。
     * @Pointcut(“@within(com.cjm.annotation.AdviceAnnotation)”)
     * － 所有被@AdviceAnnotation标注的类都将匹配
     * 7)@target ：和@within的功能类似，但必须要指定注解接口的保留策略为RUNTIME。
     * @Pointcut(“@target(com.cjm.annotation.AdviceAnnotation)”)
     * 8）@args ：传入连接点的对象对应的Java类必须被@args指定的Annotation注解标注。
     * @Before(“@args(com.cjm.annotation.AdviceAnnotation)”)
     *9）@annotation ：匹配连接点被它参数指定的Annotation注解的方法。也就是说，所有被指定注解标注的方法都将匹配。
     *  10）bean：通过受管Bean的名字来限定连接点所在的Bean。该关键词是Spring2.5新增的。
     * @Pointcut(“bean(person)”)
     */
//    @Pointcut("execution(public * com.tong.sevenproxy.aop..*.*(..))")
    @Pointcut("execution(* getParam*(..))")
    public void aspectPoint(){
        log.info("hello,this is point");
    }
//    @Before("aspectPoint()")
//    public void doBefore(JoinPoint joinPoint) throws Exception{
//    //接受并记录请求
//        ServletRequestAttributes reqs = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = reqs.getRequest();
//
//        log.info("URL:"+request.getRequestURL().toString());
//        log.info("HTTP_METHOD : " + request.getMethod());
//        log.info("IP : " + request.getRemoteAddr());
//        log.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
//        log.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
//
//    }
    @AfterReturning(returning = "ret",pointcut = "aspectPoint()")
    public void doAfterReturning(Object ret) throws Exception{
        // 处理完请求，返回内容
        log.info("RESPONSE : " + ret);
    }
//    /**
//     * 前置通知，方法调用前被调用
//     * 注意：这里用到了JoinPoint和RequestContextHolder。
//     * 1）、通过JoinPoint可以获得通知的签名信息，如目标方法名、目标方法参数信息等；
//     * 2）、通过RequestContextHolder来获取请求信息，Session信息；
//     * @param joinPoint
//     */
    @Before("aspectPoint()")
    public void before(JoinPoint joinPoint){
        log.info("前置通知");
        //获取目标方法的参数信息
        Object[] obj = joinPoint.getArgs();
        //AOP代理类的信息
        joinPoint.getThis();
        //代理的目标对象
        joinPoint.getTarget();
        //用的最多 通知的签名
        Signature signature = joinPoint.getSignature();
        //代理的是哪一个方法
        log.info("代理的是哪一个方法"+signature.getName());
        //AOP代理类的名字
        log.info("AOP代理类的名字"+signature.getDeclaringTypeName());
        //AOP代理类的类（class）信息
        signature.getDeclaringType();
        //获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        //从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        //如果要获取Session信息的话，可以这样写：
        //HttpSession session = (HttpSession) requestAttributes.resolveReference(RequestAttributes.REFERENCE_SESSION);
        //获取请求参数
        Enumeration<String> enumeration = request.getParameterNames();
        Map<String,String> parameterMap = new HashMap<String,String>();
        while (enumeration.hasMoreElements()){
            String parameter = enumeration.nextElement();
            parameterMap.put(parameter,request.getParameter(parameter));
        }
        String str = JSON.toJSONString(parameterMap);
        if(obj.length > 0) {
            log.info("请求的参数信息为："+str);
        }
    }
//    /**
//     * 后置返回通知
//     * 这里需要注意的是:
//     *      如果参数中的第一个参数为JoinPoint，则第二个参数为返回值的信息
//     *      如果参数中的第一个参数不为JoinPoint，则第一个参数为returning中对应的参数
//     *       returning：限定了只有目标方法返回值与通知方法相应参数类型时才能执行后置返回通知，否则不执行，
//     *       对于returning对应的通知方法参数为Object类型将匹配任何目标返回值
//     * @param joinPoint
//     * @param keys
//     */
//    @AfterReturning(value = "aspectPoint()",returning = "keys")
//    public void doAfterReturningAdvice1(JoinPoint joinPoint,Object keys){
//        log.info("第一个后置返回通知的返回值："+keys);
//    }
//
//    @AfterReturning(value = "aspectPoint()",returning = "keys",argNames = "keys")
//    public void doAfterReturningAdvice2(String keys){
//        log.info("第二个后置返回通知的返回值："+keys);
//    }
//    /**
//     * 后置异常通知
//     *  定义一个名字，该名字用于匹配通知实现方法的一个参数名，当目标方法抛出异常返回后，将把目标方法抛出的异常传给通知方法；
//     *  throwing:限定了只有目标方法抛出的异常与通知方法相应参数异常类型时才能执行后置异常通知，否则不执行，
//     *           对于throwing对应的通知方法参数为Throwable类型将匹配任何异常。
//     * @param joinPoint
//     * @param exception
//     */
//    @AfterThrowing(value = "aspectPoint()",throwing = "exception")
//    public void doAfterThrowingAdvice(JoinPoint joinPoint,Throwable exception){
//        //目标方法名：
//        log.info("后置异常通知："+joinPoint.getSignature().getName());
//        if(exception instanceof NullPointerException){
//            log.info("发生了空指针异常!!!!!");
//        }
//    }
//    /**
//     * 环绕通知：
//     *   环绕通知非常强大，可以决定目标方法是否执行，什么时候执行，
//     *     执行时是否需要替换方法参数，执行完毕是否需要替换返回值。
//     *   环绕通知第一个参数必须是org.aspectj.lang.ProceedingJoinPoint类型
//     */
//    @Around(value = "aspectPoint()")
//    public Object doAroundAdvice(ProceedingJoinPoint proceedingJoinPoint){
//        log.info("环绕通知的目标方法名："+proceedingJoinPoint.getSignature().getName());
//        try {
//            Object obj = proceedingJoinPoint.proceed();
//            return obj;
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }
//        return null;
//    }
//
//    /**
//     * 有时候我们定义切面的时候，切面中需要使用到目标对象的某个参数，
//     * 如何使切面能得到目标对象的参数呢？可以使用args来绑定。
//     * 如果在一个args表达式中应该使用类型名字的地方使用一个参数名字，
//     * 那么当通知执行的时候对象的参数值将会被传递进来
//     * @param name
//     */
//    @Before("execution(* getParam*(..)) &&" + "args(..,name)")
//    public void twiceAsOld1(String name){
////        if(name.equals("seven")){
////           name = "jack";
////        }
//        System.err.println ("切面before执行了。。。。name==" + name);
//
//    }
    @Around("execution(* getParam*(..))")
    public Object arougChangeParam(ProceedingJoinPoint proceedingJoinPoint){
        Signature signature = proceedingJoinPoint.getSignature();
        log.info(signature.getName());
        //获取变量值
        Object[] paramValues = proceedingJoinPoint.getArgs();
        //获取变量名
        String[] paramNames = ((CodeSignature) proceedingJoinPoint
                .getSignature()).getParameterNames();
       int flag = 0;
        for(int i=0;i<paramNames.length;i++){
            if(paramNames[i].equals("name")){
               paramValues[i] = "jack";
               flag = i;
               break;
            }
        }
        try {
            System.out.println(paramValues[flag]);
            Object obj = proceedingJoinPoint.proceed(paramValues);
            return obj;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

}
