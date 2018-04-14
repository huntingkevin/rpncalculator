package com.airwallex.rpncalculator;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Application {

    private static final String QUIT = "exit";

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/application.xml");
        init();
        Calculator calculator = context.getBean(Calculator.class);
        calculator.run();

//        Application app = new Application();
//        app.init();
//        app.start();
    }

//    private void start() {
//        Scanner input = new Scanner(System.in);
//        InputParser parser = new InputParser();
//
//        while (true) {
//            String data = input.nextLine();
//            if (StringUtils.isBlank(data)) {
//                continue;
//            } else if (QUIT.equals(data)) {
//                break;
//            } else {
//                try {
//                    parser.parse(data);
//                    printStack(parser.getValuesStack());
//                } catch (Exception e) {
//                    System.out.println(e.getMessage());
//                }
//            }
//        }
//    }
//
//    private void printStack(Stack<Double> stack) {
//        DecimalFormat format = new DecimalFormat("0.##########");
//        System.out.print("Stack: ");
//        for (Double value : stack) {
//            System.out.print(format.format(value) + " ");
//        }
//        System.out.println();
//    }

    private static void init() {
        System.out.println("**************************************************************************");
        System.out.println("This is an RPN calculator. ");
        System.out.println("It supports +, -, *, /, sqrt, undo and clear. Please enter 'exit' to quit.");
        System.out.println("**************************************************************************");
    }
}
