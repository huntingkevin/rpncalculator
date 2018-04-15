package com.airwallex.rpncalculator.impl;

import com.airwallex.rpncalculator.Operator;
import com.airwallex.rpncalculator.Processor;
import com.airwallex.rpncalculator.domain.OperationHistory;
import com.airwallex.rpncalculator.exception.EmptyStackException;
import com.airwallex.rpncalculator.exception.InsucientParametersException;
import com.airwallex.rpncalculator.exception.InvalidOperatorException;
import com.airwallex.rpncalculator.service.HistoryStackManager;
import com.airwallex.rpncalculator.service.OperandStackManager;

/**
 * @program: calculator
 * @description: handle different types of input. 1. number 2. operator 3. undo 4. clear
 * @author: Ruhong Lin
 * @create: 2018-04-14
 **/

public class InputProcessor implements Processor {

    private OperandStackManager operandStackManager;
    private HistoryStackManager historyStackManager;
    private int pos = 0;

    @Override
    public void process(String input) throws Exception {
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
            operandStackManager.add(value);
            if (!isUndoOperation) {
                historyStackManager.add(null);
            }
        }
    }

    private void processOperator(String operatorString, boolean isUndoOperation) throws Exception {

        if (operandStackManager.isEmpty()) {
            throw new EmptyStackException();
        }

        Operator operator = Operator.getEnum(operatorString);
        if (operator == null) {
            throw new InvalidOperatorException();
        }

        if (operator == Operator.CLEAR) {
            clearStacks();
            return;
        }

        if (operator == Operator.UNDO) {
            undoLastOperation();
            return;
        }

        if (operator.getOperandsNumber() > operandStackManager.size()) {
            throwInvalidOperand(operatorString);
        }

        Double firstOperand = operandStackManager.getNext();
        Double secondOperand = (operator.getOperandsNumber() > 1) ? operandStackManager.getNext() : null;
        Double result = operator.calculate(firstOperand, secondOperand);

        if (result != null) {
            operandStackManager.add(result);
            if (!isUndoOperation) {
                historyStackManager.add(new OperationHistory(Operator.getEnum(operatorString), firstOperand));
            }
        }
    }

    private void clearStacks() {
        operandStackManager.clear();
        historyStackManager.clear();
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

    private void undoLastOperation() throws Exception {
        if (historyStackManager.isEmpty()) {
            throw new InvalidOperatorException("no operations to undo");
        }

        OperationHistory lastOperationHistory = historyStackManager.getNext();
        if (lastOperationHistory == null) {
            operandStackManager.getNext();
        } else {
            eval(lastOperationHistory.getReverseOperation(), true);
        }
    }

    public void setOperandStackManager(OperandStackManager operandStackManager) {
        this.operandStackManager = operandStackManager;
    }

    public void setHistoryStackManager(HistoryStackManager historyStackManager) {
        this.historyStackManager = historyStackManager;
    }
}
