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

    /**
     * Comment(shenghuai): 如果有很多关键词什么exit undo sqrt啥的
     * 可以搞一个Constants的类把这堆关键词丢进去
     */
    private static final String QUIT = "exit";
    private InputHandler handler;
    private LinkedList<Double> operandStack;

    /**
     * Comment(shenghuai): 输出逻辑应该跟Caculator无关
     * 搞一个OutputHelper来处理输出一类的事情？
     */
    @Override
    protected void printStack(LinkedList<Double> stack) {
        DecimalFormat format = new DecimalFormat("#.##########");
        System.out.print("Stack: ");
        /**
         * Comment(shenghuai):
         * nit: 用StringBuilder
         */
        for (int i = stack.size() - 1; i >= 0; i--) {
            System.out.print(format.format(stack.get(i)) + " ");
        }
        System.out.println();
    }

    @Override
    protected void calculate() {
        Scanner input = new Scanner(System.in);

        while (true) {
            /**
             * Comment(shenghuai): 字符串相关的在InputHandler里处理掉就好
             */
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

    /**
     * Comment(shenghuai): 这两东西应该是Caculator的dependency?
     * 直接丢构造函数里？
     */
    public void setHandler(InputHandler handler) {
        this.handler = handler;
    }

    public void setOperandStack(LinkedList<Double> operandStack) {
        this.operandStack = operandStack;
    }
}
