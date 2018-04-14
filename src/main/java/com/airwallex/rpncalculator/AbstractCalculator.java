package com.airwallex.rpncalculator;

import java.util.LinkedList;

/**
 * @program: calculator
 * @description:
 * @author: Ruhong Lin
 * @create: 2018-04-15
 **/

public abstract class AbstractCalculator implements Calculator {

    @Override
    public final void run() {
        calculate();
    }

    protected abstract void calculate();

    protected abstract void printStack(LinkedList<Double> stack);
}
