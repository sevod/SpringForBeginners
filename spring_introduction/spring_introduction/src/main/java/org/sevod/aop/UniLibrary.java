package org.sevod.aop;

import org.springframework.stereotype.Component;

@Component
public class UniLibrary extends AbstractLibrary{

    public void getBook(){
        System.out.println("Мы берем книгу в UniLibrary ");
        System.out.println("------------------------------------------------------");
    }

    public String returnBook(){
        System.out.println("мы возвращаем книгу в UniLibrary");
        return  "Война и мир";
    }

    public void getMagazine(){
        System.out.println("Мы берем журнал в UniLibrary");
        System.out.println("------------------------------------------------------");
    }

    public void returnMagazine(){
        System.out.println("мы возвращаем журнал в UniLibrary");
        System.out.println("------------------------------------------------------");
    }

    public void addBook(String person_name, Book book){
        System.out.println("Мы добавляем книгу в UniLibrary");
        System.out.println("------------------------------------------------------");
    }

    public void addMagazine(){
        System.out.println("Мы добавляем журнал в UniLibrary");
        System.out.println("------------------------------------------------------");
    }
}
