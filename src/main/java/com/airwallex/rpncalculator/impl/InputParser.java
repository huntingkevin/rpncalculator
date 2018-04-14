package com.airwallex.rpncalculator.impl;

import com.airwallex.rpncalculator.Operator;
import com.airwallex.rpncalculator.api.Parser;
import com.airwallex.rpncalculator.domain.Instruction;
import com.airwallex.rpncalculator.exception.EmptyStackException;
import com.airwallex.rpncalculator.exception.InsucientParametersException;
import com.airwallex.rpncalculator.exception.InvalidOperatorException;

import java.util.Stack;

/**
 * @program: calculator
 * @description: parse input to a list of String
 * @author: Ruhong Lin
 * @create: 2018-04-14
 **/

public class InputParser implements Parser {

        private Stack<Double> operandStack;
        private Stack<Instruction> historyStack;
//    private Stack<Double> operandStack = new Stack<>();
//    private Stack<Instruction> historyStack = new Stack<>();
    private int pos = 0;

    @Override
    public void parse(String input) throws Exception {
        eval(input, false);
    }

    private void eval(String input, boolean isUndoOperation) throws Exception {
        pos = -1;
        String[] operandsAndOperators = input.split("\\s");
        for (String o : operandsAndOperators) {
            pos = pos + o.length() + 1;
            processToken(o, isUndoOperation);
        }
    }

    private void processToken(String token, boolean isUndoOperation) throws Exception {
        Double value = tryParseDouble(token);
        if (value == null) {
            processOperator(token, isUndoOperation);
        } else {
            operandStack.push(value);
            if (!isUndoOperation) {
                historyStack.push(null);
            }
        }
    }

    private void processOperator(String operatorString, boolean isUndoOperation) throws Exception {

        if (operandStack.isEmpty()) {
            throw new EmptyStackException("empty stack");
        }

        // searching for the operator
        Operator operator = Operator.getEnum(operatorString);
        if (operator == null) {
            throw new InvalidOperatorException("invalid operator");
        }

        // clear value stack and instructions stack
        if (operator == Operator.CLEAR) {
            clearStacks();
            return;
        }

        // undo evaluates the last instruction in stack
        if (operator == Operator.UNDO) {
            undoLastInstruction();
            return;
        }

        // Checking that there are enough operand for the operation
        if (operator.getOperandsNumber() > operandStack.size()) {
            throwInvalidOperand(operatorString);
        }

        // getting operands
        Double firstOperand = operandStack.pop();
        Double secondOperand = (operator.getOperandsNumber() > 1) ? operandStack.pop() : null;
        // calculate
        Double result = operator.calculate(firstOperand, secondOperand);

        if (result != null) {
            operandStack.push(result);
            if (!isUndoOperation) {
                historyStack.push(new Instruction(Operator.getEnum(operatorString), firstOperand));
            }
        }
    }

    private void clearStacks() {
        operandStack.clear();
        historyStack.clear();
    }

    private void throwInvalidOperand(String operator) throws InsucientParametersException {
        throw new InsucientParametersException(
                String.format("operator %s (position: %d): insufficient parameters", operator, pos));
    }

    private Double tryParseDouble(String str) {
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private void undoLastInstruction() throws Exception {
        if (historyStack.isEmpty()) {
            throw new InvalidOperatorException("no operations to undo");
        }

        Instruction lastInstruction = historyStack.pop();
        if (lastInstruction == null) {
            operandStack.pop();
        } else {
            eval(lastInstruction.getReverseInstruction(), true);
        }
    }

    public Stack<Double> getValuesStack() {
        return operandStack;
    }

    public void setOperandStack(Stack<Double> operandStack) {
        this.operandStack = operandStack;
    }

    public void setHistoryStack(Stack<Instruction> historyStack) {
        this.historyStack = historyStack;
    }
}
