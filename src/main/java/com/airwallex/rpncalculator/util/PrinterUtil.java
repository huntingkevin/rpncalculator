package com.airwallex.rpncalculator.util;

import java.text.DecimalFormat;
import java.util.LinkedList;

/**
 * @program: calculator
 * @description:
 * @author: Ruhong Lin
 * @create: 2018-04-15
 **/

public class PrinterUtil {

    public static void printStack(LinkedList<Double> stack) {
        DecimalFormat format = new DecimalFormat("#.##########");
        StringBuilder sb = new StringBuilder();
        sb.append("Stack: ");
        for (int i = stack.size() - 1; i >= 0; i--) {
            sb.append(format.format(stack.get(i)) + " ");
        }
        System.out.println(sb);
    }
}
