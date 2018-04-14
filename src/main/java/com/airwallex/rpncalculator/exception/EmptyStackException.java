package com.airwallex.rpncalculator.exception;

/**
 * @program: calculator
 * @description: EmptyStackException
 * @author: Ruhong Lin
 * @create: 2018-04-15
 **/

public class EmptyStackException extends Exception {
    public EmptyStackException(String message) {
        super(message);
    }
}
