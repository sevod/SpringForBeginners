package org.sevod.aop.aspects;

import org.aspectj.lang.annotation.Pointcut;

public class MyPointCuts {
//    @Pointcut("execution(* get*())")
//    public void allGetMethods() {
//    }
    @Pointcut("execution(* _add*(..))")
    public void allAddMethods() {
    }

}
