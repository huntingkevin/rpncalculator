package com.airwallex.rpncalculator;

import com.airwallex.rpncalculator.exception.InsucientParametersException;

/**
 * @program: calculator
 * @description:
 * @author: Ruhong Lin
 * @create: 2018-04-14
 **/
public interface Handler {
    /**
     * Comment(shenghuai)
     * 这个Handler里头的method叫parse怪怪的
     * 我理解的是这是一个专门为input用的parser?
     * 改成InputParser和InputParserImpl？
     */
    void parse(String input) throws Exception;
}
