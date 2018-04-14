package com.airwallex.rpncalculator.domain;

import com.airwallex.rpncalculator.Operator;
import com.airwallex.rpncalculator.exception.InsucientParametersException;

/**
 * @program: calculator
 * @description:
 * @author: Ruhong Lin
 * @create: 2018-04-14
 **/

public class Operation {
    Operator operator;
    Double operand;

    public Operation(Operator operator, Double operand) {
        this.operator = operator;
        this.operand = operand;
    }

    public String getReverseOperation() throws InsucientParametersException {
        if (operator.getOperandsNumber() < 1)
            throw new InsucientParametersException(String.format("invalid operation for operator %s", operator.getSymbol()));

        return (operator.getOperandsNumber() < 2) ?
                String.format("%s", operator.getOpposite()) :
                String.format("%f %s %f", operand, operator.getOpposite(), operand);
    }
}
