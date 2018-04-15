package com.airwallex.rpncalculator.impl;

import com.airwallex.rpncalculator.AbstractCalculator;
import org.apache.commons.lang3.StringUtils;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @program: calculator
 * @description:
 * @author: Ruhong Lin
 * @create: 2018-04-14
 **/

public class CalculatorImpl extends AbstractCalculator {

    private static final String QUIT = "exit";
    private InputHandler handler;
    private LinkedList<Double> operandStack;

    @Override
    protected void printStack(LinkedList<Double> stack) {
        DecimalFormat format = new DecimalFormat("#.##########");
        System.out.print("Stack: ");
        for (int i = stack.size() - 1; i >= 0; i--) {
            System.out.print(format.format(stack.get(i)) + " ");
        }
        System.out.println();
    }

    @Override
    protected void calculate() {
        Scanner input = new Scanner(System.in);

        while (true) {
            String data = input.nextLine();
            if (StringUtils.isBlank(data)) {
                continue;
            } else if (QUIT.equals(data)) {
                break;
            } else {
                try {
                    handler.parse(data);
                    printStack(operandStack);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public void setHandler(InputHandler handler) {
        this.handler = handler;
    }

    public void setOperandStack(LinkedList<Double> operandStack) {
        this.operandStack = operandStack;
    }
}
