package org.sevod.spring_introducion;

public class Cat implements Pet{
    public Cat() {
        System.out.println("Cat Bean is created");
    }

    @Override
    public void say() {
        System.out.println("Мяу! Мяу!");
    }
}
