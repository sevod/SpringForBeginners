package org.sevod.spring_introducion;

public class Person {
    private Pet pet;

    public Person(Pet pet) {
        System.out.println("Person Bean is created");
        this.pet = pet;
    }

    public void callYourPet(){
        System.out.println("Ко мне!");
        pet.say();
    }
}
