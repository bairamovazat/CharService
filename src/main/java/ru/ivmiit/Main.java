package ru.ivmiit;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
       new AnnotationConfigApplicationContext(AppConfig.class);
    }
}
