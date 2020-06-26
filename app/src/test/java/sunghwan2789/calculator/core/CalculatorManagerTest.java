package sunghwan2789.calculator.core;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.Assert.*;

public class CalculatorManagerTest {
    CalculatorManager calculator;

    @Before
    public void initialize() {
        calculator = new CalculatorManager();
    }

    @Test
    public void simple_expression() {
        calculator.add(Command.NUMBER_3);
        calculator.add(Command.NUMBER_6);
        calculator.add(Command.NUMBER_9);
        calculator.add(Command.ADD);
        calculator.add(Command.NUMBER_5);
        assertEquals(BigDecimal.valueOf(369 + 5), calculator.execute());

        calculator.add(Command.NUMBER_3);
        calculator.add(Command.NUMBER_6);
        calculator.add(Command.NUMBER_9);
        calculator.add(Command.SUBTRACT);
        calculator.add(Command.NUMBER_5);
        assertEquals(BigDecimal.valueOf(369 - 5), calculator.execute());

        calculator.add(Command.NUMBER_3);
        calculator.add(Command.NUMBER_6);
        calculator.add(Command.NUMBER_9);
        calculator.add(Command.MULTIPLY);
        calculator.add(Command.NUMBER_5);
        assertEquals(BigDecimal.valueOf(369 * 5), calculator.execute());

        calculator.add(Command.NUMBER_3);
        calculator.add(Command.NUMBER_6);
        calculator.add(Command.NUMBER_9);
        calculator.add(Command.DIVIDE);
        calculator.add(Command.NUMBER_5);
        assertEquals(BigDecimal.valueOf(369. / 5), calculator.execute());

        calculator.add(Command.NUMBER_3);
        calculator.add(Command.NUMBER_6);
        calculator.add(Command.NUMBER_9);
        calculator.add(Command.MODULAR);
        calculator.add(Command.NUMBER_5);
        assertEquals(BigDecimal.valueOf(369. % 5), calculator.execute());
    }
}
