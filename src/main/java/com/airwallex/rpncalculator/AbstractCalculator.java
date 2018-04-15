package com.airwallex.rpncalculator;

import java.util.LinkedList;

/**
 * @program: calculator
 * @description:
 * @author: Ruhong Lin
 * @create: 2018-04-15
 **/

/**
 * Comment(shenghuai):
 * 把Caclulator扔了吧
 * abstract class -> interface
 */
public abstract class AbstractCalculator implements Calculator {

   /**
    * Comment(shenghuai):
    * 加个一句话的comment?
    */
    @Override
    public final void run() {
        calculate();
    }

    protected abstract void calculate();

    protected abstract void printStack(LinkedList<Double> stack);
}
