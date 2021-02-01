package org.sevod.spring_introducion;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class Dog implements Pet{
    @Override
    public void say() {
        System.out.println("Гав! Гав!");
    }

    @PostConstruct
    public void init(){
        System.out.println("Class Dog: init method");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("Class Dog: destroy method");
    }
}
