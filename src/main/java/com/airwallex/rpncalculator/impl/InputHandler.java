package com.airwallex.rpncalculator.impl;

import com.airwallex.rpncalculator.Operator;
import com.airwallex.rpncalculator.Handler;
import com.airwallex.rpncalculator.domain.Operation;
import com.airwallex.rpncalculator.exception.EmptyStackException;
import com.airwallex.rpncalculator.exception.InsucientParametersException;
import com.airwallex.rpncalculator.exception.InvalidOperatorException;

import java.util.LinkedList;

/**
 * @program: calculator
 * @description: handle different types of input. 1. number 2. operator 3. undo 4. clear
 * @author: Ruhong Lin
 * @create: 2018-04-14
 **/

/**
 * Comment(shenghuai):
 * InputHandler我的理解是只处理输入相关的东西
 * 就是比如读一行然后format出去
 * 维护两个stack的事情按道理应该是在Caculator里？
 * 如果要单独一个搞计算的事情这个名字看起来就比较confused
 */
public class InputHandler implements Handler {

    private LinkedList<Double> operandStack;
    /**
     * Comment(shenghuai):
     * History应该是一个list里面存的是一堆stack？
     */
    private LinkedList<Operation> historyStack;
    /**
     * Comment(shenghuai):
     * 这个pos看起来怪浪怪浪？这个做啥的
     * ruhong: 测试下git先
     */
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
            /**
             * Comment(shenghuai):
             * 这堆Exception的shared的message string按道理应该是丢到具体class里？
             */
            throw new EmptyStackException("empty stack");
        }

        Operator operator = Operator.getEnum(operatorString);
        if (operator == null) {
            throw new InvalidOperatorException("invalid operator");
        }

        if (operator == Operator.CLEAR) {
            clearStacks();
            return;
        }

        if (operator == Operator.UNDO) {
            undoLastOperation();
            return;
        }

        if (operator.getOperandsNumber() > operandStack.size()) {
            throwInvalidOperand(operatorString);
        }

        Double firstOperand = operandStack.pop();
        Double secondOperand = (operator.getOperandsNumber() > 1) ? operandStack.pop() : null;
        Double result = operator.calculate(firstOperand, secondOperand);

        if (result != null) {
            operandStack.push(result);
            if (!isUndoOperation) {
                historyStack.push(new Operation(Operator.getEnum(operatorString), firstOperand));
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

    private void undoLastOperation() throws Exception {
        if (historyStack.isEmpty()) {
            throw new InvalidOperatorException("no operations to undo");
        }

        Operation lastOperation = historyStack.pop();
        if (lastOperation == null) {
            operandStack.pop();
        } else {
            eval(lastOperation.getReverseOperation(), true);
        }
    }

    /**
     * Comment(shenghuai):
     * 这两个感觉没啥用
     * 构造的时候就应该在了
     * 扔到constructor里？
     */
    public void setOperandStack(LinkedList<Double> operandStack) {
        this.operandStack = operandStack;
    }

    public void setHistoryStack(LinkedList<Operation> historyStack) {
        this.historyStack = historyStack;
    }
}
