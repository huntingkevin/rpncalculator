package com.airwallex.rpncalculator.api;

import com.airwallex.rpncalculator.exception.InsucientParametersException;

/**
 * @program: calculator
 * @description:
 * @author: Ruhong Lin
 * @create: 2018-04-14
 **/
public interface Parser {
    void parse(String input) throws Exception;
}
