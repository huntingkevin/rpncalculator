package com.airwallex.rpncalculator;

import com.airwallex.rpncalculator.api.Calculator;
import com.airwallex.rpncalculator.exception.InsucientParametersException;
import com.airwallex.rpncalculator.impl.InputParser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.Stack;

public class Application {

    private static final String QUIT = "exit";

    public static void main(String[] args) {
//        ApplicationContext context = new ClassPathXmlApplicationContext("classpath: spring/application.xml");
//        Calculator calculator = context.getBean(Calculator.class);
        Application app = new Application();
        app.init();
        app.start();
    }

    private void start() {
        Scanner input = new Scanner(System.in);
        InputParser parser = new InputParser();

        while (true) {
            String data = input.nextLine();
            if (StringUtils.isBlank(data)) {
                continue;
            } else if (QUIT.equals(data)) {
                break;
            } else {
                try {
                    parser.parse(data);
                    printStack(parser.getValuesStack());
                } catch (InsucientParametersException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private void printStack(Stack<Double> stack) {
        DecimalFormat format = new DecimalFormat("0.##########");
        System.out.print("Stack: ");
        for (Double value : stack) {
            System.out.print(format.format(value) + " ");
        }
        System.out.println();
    }

    private void init() {
        System.out.println("**************************************************************************");
        System.out.println("This is an RPN calculator. ");
        System.out.println("It supports +, -, *, /, sqrt, undo and clear. Please enter 'exit' to quit.");
        System.out.println("**************************************************************************");
    }
}
