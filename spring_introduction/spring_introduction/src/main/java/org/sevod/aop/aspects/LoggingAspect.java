package org.sevod.aop.aspects;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    @Before("execution(public void getBook(org.sevod.aop.Book))")
    public void beforeGetBookAdvice(){
        System.out.println("beforeGetBookAdvice Попытка получить книгу");
    }
    @Before("execution(public * returnBook())")
    public void beforeReturnBookAdvice(){
        System.out.println("beforeGetBookAdvice Попытка вернуть книгу");
    }
}
