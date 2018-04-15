package com.airwallex.rpncalculator;

import com.airwallex.rpncalculator.exception.InsucientParametersException;
import com.airwallex.rpncalculator.impl.InputProcessor;
import com.airwallex.rpncalculator.service.OperandStackManager;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @program: calculator
 * @description: Test +, -, *, /, clear, undo and exceptions
 * @author: Ruhong Lin
 * @create: 2018-04-15
 **/
//@Ignore
public class InputProcessorTest {

    private static final double DELTA = 0.000000001;
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    private ApplicationContext context;

    @Before
    public void init() {
        context = new ClassPathXmlApplicationContext("classpath:spring/application.xml");
    }

    @Test
    public void test() throws Exception {
        InputProcessor processor = context.getBean(InputProcessor.class);
        assertNotNull(processor);
        OperandStackManager operandStackManager = context.getBean(OperandStackManager.class);
        assertNotNull(operandStackManager);

        // case 1
        processor.process("5 2");
        assertEquals(5, operandStackManager.getAll().get(1), DELTA);
        assertEquals(2, operandStackManager.getAll().get(0), DELTA);

        // case 2
        processor.process("clear");
        processor.process("2 sqrt");
        double expected = Math.sqrt(2);
        assertEquals(expected, operandStackManager.getAll().get(0), DELTA);

        // case 3
        processor.process("clear");
        processor.process("5 2 -");
        assertEquals(1, operandStackManager.getAll().size());
        assertEquals(3, operandStackManager.getAll().get(0), DELTA);
        processor.process("3 -");
        assertEquals(1, operandStackManager.getAll().size());
        assertEquals(0, operandStackManager.getAll().get(0), DELTA);

        // case 4
        processor.process("clear");
        processor.process("5 4 3 2");
        assertEquals(4, operandStackManager.getAll().size());
        assertEquals(5, operandStackManager.getAll().get(3), DELTA);
        assertEquals(4, operandStackManager.getAll().get(2), DELTA);
        assertEquals(3, operandStackManager.getAll().get(1), DELTA);
        assertEquals(2, operandStackManager.getAll().get(0), DELTA);
        processor.process("undo undo *");
        assertEquals(20, operandStackManager.getAll().get(0), DELTA);
        processor.process("5 *");
        assertEquals(100, operandStackManager.getAll().get(0), DELTA);
        processor.process("undo");
        assertEquals(20, operandStackManager.getAll().get(1), DELTA);
        assertEquals(5, operandStackManager.getAll().get(0), DELTA);
        assertEquals(2, operandStackManager.size());

        // case 5
        processor.process("clear");
        processor.process("7 12 2 /");
        assertEquals(2, operandStackManager.size());
        assertEquals(7, operandStackManager.getAll().get(1), DELTA);
        assertEquals(6, operandStackManager.getAll().get(0), DELTA);
        processor.process("*");
        assertEquals(42, operandStackManager.getAll().get(0), DELTA);
        processor.process("4 /");
        assertEquals(10.5, operandStackManager.getAll().get(0), DELTA);

        // case 6
        processor.process("clear");
        processor.process("1 2 3 4 5");
        assertEquals(5, operandStackManager.size());
        processor.process("*");
        assertEquals(20, operandStackManager.getAll().get(0), DELTA);
        assertEquals(4, operandStackManager.size());
        processor.process("clear 3 4 -");
        assertEquals(1, operandStackManager.size());
        assertEquals(-1, operandStackManager.getAll().get(0), DELTA);


        // case 7
        processor.process("clear");
        processor.process("1 2 3 4 5");
        processor.process("* * * *");
        assertEquals(1, operandStackManager.size());
        assertEquals(120, operandStackManager.getAll().get(0), DELTA);
    }

    // case 8
    @Test
    public void testException() throws Exception {
        InputProcessor processor = context.getBean(InputProcessor.class);
        assertNotNull(processor);
        OperandStackManager operandStackManager = context.getBean(OperandStackManager.class);
        assertNotNull(operandStackManager);

        thrown.expect(InsucientParametersException.class);
        thrown.expectMessage("insufficient parameters");
        processor.process("1 2 3 * 5 + * * 6 5");
    }
}
