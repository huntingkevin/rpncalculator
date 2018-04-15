package com.airwallex.rpncalculator;

import com.airwallex.rpncalculator.exception.InsucientParametersException;

/**
 * @program: calculator
 * @description:
 * @author: Ruhong Lin
 * @create: 2018-04-14
 **/
public interface Processor {
    void process(String input) throws Exception;
}
