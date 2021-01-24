package org.sevod.spring_introducion;

public class Dog implements Pet{
    @Override
    public void say() {
        System.out.println("Гав!");
    }
}
