package com.airwallex.rpncalculator.impl;

import com.airwallex.rpncalculator.Calculator;
import com.airwallex.rpncalculator.service.OperandStackManager;
import com.airwallex.rpncalculator.util.PrinterUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

/**
 * @program: calculator
 * @description:
 * @author: Ruhong Lin
 * @create: 2018-04-14
 **/

public class CalculatorImpl implements Calculator {

    private static final String QUIT = "exit";
    private InputProcessor processor;
    private OperandStackManager operandStackManager;

    @Override
    public void run() {
        Scanner input = new Scanner(System.in);

        while (true) {
            String data = input.nextLine();
            if (StringUtils.isBlank(data)) {
                continue;
            } else if (QUIT.equals(data)) {
                break;
            } else {
                try {
                    processor.process(data);
                    PrinterUtil.printStack(operandStackManager.getAll());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public void setProcessor(InputProcessor processor) {
        this.processor = processor;
    }

    public void setOperandStackManager(OperandStackManager operandStackManager) {
        this.operandStackManager = operandStackManager;
    }
}
