package com.airwallex.rpncalculator.service;

import com.airwallex.rpncalculator.StackManager;
import com.airwallex.rpncalculator.domain.OperationHistory;

import java.util.LinkedList;

/**
 * @program: calculator
 * @description:
 * @author: Ruhong Lin
 * @create: 2018-04-15
 **/

public class HistoryStackManager implements StackManager<OperationHistory> {

    private LinkedList<OperationHistory> historyStack = new LinkedList<>();

    @Override
    public void add(OperationHistory operation) {
        historyStack.push(operation);
    }

    @Override
    public OperationHistory getNext() {
        return historyStack.pop();
    }

    @Override
    public void clear() {
        historyStack.clear();
    }

    @Override
    public boolean isEmpty() {
        return historyStack.isEmpty();
    }

    @Override
    public int size() {
        return historyStack.size();
    }

    @Override
    public LinkedList<OperationHistory> getAll() {
        return historyStack;
    }

}
