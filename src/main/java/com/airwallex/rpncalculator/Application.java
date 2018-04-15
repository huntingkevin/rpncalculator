package com.airwallex.rpncalculator;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @program: calculator
 * @description: Application entrance
 * @author: Ruhong Lin
 * @create: 2018-04-15
 **/
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
