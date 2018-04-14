package com.airwallex.rpncalculator.impl;

import com.airwallex.rpncalculator.AbstractCalculator;
import com.airwallex.rpncalculator.domain.OperandStack;
import com.airwallex.rpncalculator.exception.InsucientParametersException;
import org.apache.commons.lang3.StringUtils;

import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.Stack;

/**
 * @program: calculator
 * @description:
 * @author: Ruhong Lin
 * @create: 2018-04-14
 **/

public class CalculatorImpl extends AbstractCalculator {

    private static final String QUIT = "exit";
    private InputParser parser;
    private Stack<Double> operandStack;

    @Override
    protected void printStack(Stack<Double> stack) {
        DecimalFormat format = new DecimalFormat("0.##########");
        System.out.print("Stack: ");
        for (Double value : stack) {
            System.out.print(format.format(value) + " ");
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
                    parser.parse(data);
                    printStack(operandStack);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public void setParser(InputParser parser) {
        this.parser = parser;
    }

    public void setOperandStack(Stack<Double> operandStack) {
        this.operandStack = operandStack;
    }
}
