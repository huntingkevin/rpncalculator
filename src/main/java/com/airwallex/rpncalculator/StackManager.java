package com.airwallex.rpncalculator;

import java.util.LinkedList;

/**
 * @program: calculator
 * @description:
 * @author: Ruhong Lin
 * @create: 2018-04-15
 **/
public interface StackManager<T> {

    void add(T data);

    T getNext();

    void clear();

    boolean isEmpty();

    int size();

    LinkedList<T> getAll();
}
