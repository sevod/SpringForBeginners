package org.sevod.spring_introducion;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConfigWithAnnotations1 {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext3.xml");

        //Cat myCat = context.getBean("cat", Cat.class);
        Person person = context.getBean("personBean", Person.class);
        person.callYourPet();

        context.close();
    }
}
