package org.sevod.spring_introduction;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test5 {
    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext2.xml");

        Pet pet = context.getBean("myPet", Pet.class);
        pet.say();

        Pet yourDog = context.getBean("myPet", Pet.class);
        yourDog.say();

        context.close();
    }
}
