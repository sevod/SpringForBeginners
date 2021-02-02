package org.sevod.aop;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Book {
    @Value("Маугли 2")
    private String name;

    public String getName() {
        return name;
    }
}
