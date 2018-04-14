package com.airwallex.rpncalculator;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Application {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/application.xml");
        Calculator calculator = context.getBean(Calculator.class);
        calculator.run();
    }

    static {
        System.out.println("**************************************************************************");
        System.out.println("This is an RPN calculator. ");
        System.out.println("It supports +, -, *, /, sqrt, undo and clear. Please enter 'exit' to quit.");
        System.out.println("**************************************************************************");
    }
}
