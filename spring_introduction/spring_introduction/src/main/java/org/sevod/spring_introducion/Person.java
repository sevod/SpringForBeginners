package org.sevod.spring_introducion;

public class Person {
    private Pet pet;

    public Person(Pet pet) {
        this.pet = pet;
    }

    public void callYourPet(){
        System.out.println("Ко мне!");
        pet.say();
    }
}
