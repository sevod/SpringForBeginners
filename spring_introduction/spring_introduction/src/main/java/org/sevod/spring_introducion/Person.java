package org.sevod.spring_introducion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("personBean")
public class Person {
    @Autowired
    private Pet pet;
    private String surname;
    private int age;

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

//    public Person() {
//        System.out.println("Person Bean is created default constructor");
//    }

//    @Autowired
//    public Person(Pet pet) {
//        System.out.println("Person Bean is created");
//        this.pet = pet;
//    }

    public void callYourPet(){
        System.out.println("Ко мне!");
        pet.say();
    }

    //@Autowired
    public void setPet(Pet pet) {
        System.out.println("Class Person set pet");
        this.pet = pet;
    }
}
