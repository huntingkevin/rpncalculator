package com.airwallex.rpncalculator.exception;

/**
 * @program: calculator
 * @description: InvalidOperatorException
 * @author: Ruhong Lin
 * @create: 2018-04-15
 **/

public class InvalidOperatorException extends Exception {
    public InvalidOperatorException() {
        super("Invalid Operator");
    }

    public InvalidOperatorException(String message) {
        super(message);
    }
}
