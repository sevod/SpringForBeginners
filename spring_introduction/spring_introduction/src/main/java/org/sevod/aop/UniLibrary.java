package org.sevod.aop;

import org.springframework.stereotype.Component;

@Component
public class UniLibrary extends AbstractLibrary{

    public void getBook(){
        System.out.println("Мы берем книгу в UniLibrary ");
    }

    public void returnBook(){
        System.out.println("мы возвращаем книгу в UniLibrary");
    }

    public void getMagazine(){
        System.out.println("Мы берем журнал в UniLibrary");
    }

    public void returnMagazine(){
        System.out.println("мы возвращаем журнал в UniLibrary");
    }

    public void addBook(){
        System.out.println("Мы добавляем книгу в UniLibrary");
    }

    public void addMagazine(){
        System.out.println("Мы добавляем журнал в UniLibrary");
    }
}
