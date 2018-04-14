package com.airwallex.rpncalculator;

import java.util.Stack;

/**
 * @program: calculator
 * @description:
 * @author: Ruhong Lin
 * @create: 2018-04-15
 **/

public abstract class AbstractCalculator implements Calculator {

    @Override
    public final void run() {
//        init();
        calculate();
//        printStack(stack);
    }

//    protected abstract void init();

    protected abstract void calculate();

    protected abstract void printStack(Stack<Double> stack);
}
