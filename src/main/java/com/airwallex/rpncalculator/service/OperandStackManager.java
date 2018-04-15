package com.airwallex.rpncalculator.service;

import com.airwallex.rpncalculator.StackManager;

import java.util.LinkedList;

/**
 * @program: calculator
 * @description:
 * @author: Ruhong Lin
 * @create: 2018-04-15
 **/

public class OperandStackManager implements StackManager<Double> {

    private LinkedList<Double> operandStack = new LinkedList<>();

    @Override
    public void add(Double operand) {
        operandStack.push(operand);
    }

    @Override
    public Double getNext() {
        return operandStack.pop();
    }

    @Override
    public void clear() {
        operandStack.clear();
    }

    @Override
    public boolean isEmpty() {
        return operandStack.isEmpty();
    }

    @Override
    public int size() {
        return operandStack.size();
    }

    @Override
    public LinkedList<Double> getAll() {
        return operandStack;
    }

}
