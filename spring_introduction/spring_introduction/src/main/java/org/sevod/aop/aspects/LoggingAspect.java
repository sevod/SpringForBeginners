package org.sevod.aop.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(10)
public class LoggingAspect {

//    @Pointcut("execution(* org.sevod.aop.UniLibrary.*())")
//    private void allMethodsFromUniLibrary(){}
//
//    @Pointcut("execution(public void org.sevod.aop.UniLibrary.returnMagazine()))")
//    private void returnMagazineFromUniLibrary(){}
//
//    @Pointcut("allMethodsFromUniLibrary() && !returnMagazineFromUniLibrary()")
//    private void allMethodsExceptReturnMagazineFromUniLibrary(){}
//
//    @Before("allMethodsExceptReturnMagazineFromUniLibrary()")
//    public void allMethodsExceptReturnMagazineAdvice(){
//        System.out.println("allMethodsExceptReturnMagazineAdvice Лог № 10");
//    }
//
//    @Pointcut("execution(* org.sevod.aop.UniLibrary.get* ())")
//    private void allGetMethodsFromUniLibrary(){}
//
//    @Pointcut("execution(* org.sevod.aop.UniLibrary.return* ())")
//    private void allReturnMethodsFromUniLibrary(){}
//
//    @Pointcut("allGetMethodsFromUniLibrary() || allReturnMethodsFromUniLibrary()")
//    private void allGetAndReturnMethodsFromUniLibrary(){}
//
//    @Before("allGetMethodsFromUniLibrary()")
//    public void beforeGetLogicAdvice(){
//        System.out.println("beforeGetLogicAdvice: запись в Лог №1");
//    }
//
//    @Before("allReturnMethodsFromUniLibrary()")
//    public void beforeReturnLogicAdvice(){
//        System.out.println("beforeReturnLogicAdvice: запись в Лог №2");
//    }
//
//    @Before("allGetAndReturnMethodsFromUniLibrary()")
//    public void beforeGetAndReturnLogicAdvice(){
//        System.out.println("beforeGetAndReturnLogicAdvice: запись в Лог №3");
//    }


    @Before("MyPointCuts.allGetMethods()")
    public void beforeGetLoggingAdvice(){
        System.out.println("beforeGetLoggingAdvice: логирование попытки получить книгу/журнал");
    }



}
