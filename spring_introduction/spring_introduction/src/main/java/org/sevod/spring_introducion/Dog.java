package org.sevod.spring_introducion;

import org.springframework.stereotype.Component;

@Component
public class Dog implements Pet{
    @Override
    public void say() {
        System.out.println("Гав! Гав!");
    }

    public void init(){
        System.out.println("Class Dog: init method");
    }

    public void destroy(){
        System.out.println("Class Dog: destroy method");
    }
}
