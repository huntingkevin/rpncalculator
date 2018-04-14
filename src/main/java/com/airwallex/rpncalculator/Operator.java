package com.airwallex.rpncalculator;

import com.airwallex.rpncalculator.exception.InsucientParametersException;
import com.airwallex.rpncalculator.exception.InvalidOperatorException;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 * @program: calculator
 * @description:
 * @author: Ruhong Lin
 * @create: 2018-04-14
 **/
public enum Operator {
    ADDITION("+", "-", 2) {
        public Double calculate(Double firstOperand, Double secondOperand) throws InsucientParametersException {
            return secondOperand + firstOperand;
        }
    },

    SUBTRACTION("-", "+", 2) {
        public Double calculate(Double firstOperand, Double secondOperand) {
            return secondOperand - firstOperand;
        }
    },

    MULTIPLICATION("*", "/", 2) {
        public Double calculate(Double firstOperand, Double secondOperand) {
            return secondOperand * firstOperand;
        }
    },

    DIVISION("/", "*", 2) {
        public Double calculate(Double firstOperand, Double secondOperand) throws InvalidOperatorException {
            if (firstOperand == 0)
                throw new InvalidOperatorException("Cannot divide by 0.");
            return secondOperand / firstOperand;
        }
    },

    SQUAREROOT("sqrt", "pow", 1) {
        public Double calculate(Double firstOperand, Double secondOperand) {
            return sqrt(firstOperand);
        }
    },

    POWER("pow", "sqrt", 1) {
        public Double calculate(Double firstOperand, Double secondOperand) {
            return pow(firstOperand, 2.0);
        }
    },

    UNDO("undo", null, 0) {
        public Double calculate(Double firstOperand, Double secondOperand) throws InvalidOperatorException {
            throw new InvalidOperatorException("Invalid operation");
        }
    },

    CLEAR("clear", null, 0) {
        public Double calculate(Double firstOperand, Double secondOperand) throws InvalidOperatorException {
            throw new InvalidOperatorException("Invalid operation");
        }
    };
    // using map for a constant lookup cost
    private static final Map<String, Operator> lookup = new HashMap<>();

    static {
        for (Operator o : values()) {
            lookup.put(o.getSymbol(), o);
        }
    }

    private String symbol;
    private String opposite;
    private int operandsNumber;

    Operator(String symbol, String opposite, int operandsNumber) {
        this.symbol = symbol;
        this.opposite = opposite;
        this.operandsNumber = operandsNumber;
    }

    public static Operator getEnum(String value) {
        return lookup.get(value);
    }

    public abstract Double calculate(Double firstOperand, Double secondOperand) throws InsucientParametersException, InvalidOperatorException;

    public String getSymbol() {
        return symbol;
    }

    public String getOpposite() {
        return opposite;
    }

    public int getOperandsNumber() {
        return operandsNumber;
    }

    @Override
    public String toString() {
        return symbol;
    }
}
