package org.sevod.aop;

import org.springframework.stereotype.Component;

@Component
public class UniLibrary extends AbstractLibrary{

    public void getBook(Book book){
        System.out.println("Мы берем книгу в UniLibrary " + book.getName());
    }

    public void getMagazine(int a){
        System.out.println("Мы берем журнал в UniLibrary");
    }

    public String returnBook(){
        System.out.println("мы возвращаем книгу в UniLibrary");
        return "OK";
    }
}
