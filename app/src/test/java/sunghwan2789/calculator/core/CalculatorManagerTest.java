package sunghwan2789.calculator.core;

import android.icu.text.Normalizer2;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
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
        calculator.clear();

        calculator.add(Command.NUMBER_3);
        calculator.add(Command.NUMBER_6);
        calculator.add(Command.NUMBER_9);
        calculator.add(Command.SUBTRACT);
        calculator.add(Command.NUMBER_5);
        assertEquals(BigDecimal.valueOf(369 - 5), calculator.execute());
        calculator.clear();

        calculator.add(Command.NUMBER_3);
        calculator.add(Command.NUMBER_6);
        calculator.add(Command.NUMBER_9);
        calculator.add(Command.MULTIPLY);
        calculator.add(Command.NUMBER_5);
        assertEquals(BigDecimal.valueOf(369 * 5), calculator.execute());
        calculator.clear();

        calculator.add(Command.NUMBER_3);
        calculator.add(Command.NUMBER_6);
        calculator.add(Command.NUMBER_9);
        calculator.add(Command.DIVIDE);
        calculator.add(Command.NUMBER_5);
        assertEquals(BigDecimal.valueOf(369. / 5), calculator.execute());
        calculator.clear();

        calculator.add(Command.NUMBER_3);
        calculator.add(Command.NUMBER_6);
        calculator.add(Command.NUMBER_9);
        calculator.add(Command.MODULAR);
        calculator.add(Command.NUMBER_5);
        assertEquals(BigDecimal.valueOf(369).remainder(BigDecimal.valueOf(5)), calculator.execute());
        calculator.clear();

        calculator.add(Command.NUMBER_3);
        calculator.add(Command.NUMBER_6);
        calculator.add(Command.NUMBER_9);
        calculator.add(Command.AND);
        calculator.add(Command.NUMBER_5);
        assertEquals(BigInteger.valueOf(369).and(BigInteger.valueOf(5)), calculator.execute().toBigIntegerExact());
        calculator.clear();

        calculator.add(Command.NUMBER_3);
        calculator.add(Command.NUMBER_6);
        calculator.add(Command.NUMBER_9);
        calculator.add(Command.OR);
        calculator.add(Command.NUMBER_5);
        assertEquals(BigInteger.valueOf(369).or(BigInteger.valueOf(5)), calculator.execute().toBigIntegerExact());
        calculator.clear();

        calculator.add(Command.NUMBER_3);
        calculator.add(Command.NUMBER_6);
        calculator.add(Command.NUMBER_9);
        calculator.add(Command.XOR);
        calculator.add(Command.NUMBER_5);
        assertEquals(BigInteger.valueOf(369).xor(BigInteger.valueOf(5)), calculator.execute().toBigIntegerExact());
        calculator.clear();

        calculator.add(Command.NOT);
        calculator.add(Command.NUMBER_3);
        calculator.add(Command.NUMBER_6);
        calculator.add(Command.NUMBER_9);
        assertEquals(BigInteger.valueOf(369).not(), calculator.execute().toBigIntegerExact());
        calculator.clear();
    }

    @Test
    public void example() {
        // 1+2*3-4*(5+6)/7 = 0.714285
        calculator.add(Command.NUMBER_1);
        calculator.add(Command.ADD);
        calculator.add(Command.NUMBER_2);
        calculator.add(Command.MULTIPLY);
        calculator.add(Command.NUMBER_3);
        calculator.add(Command.SUBTRACT);
        calculator.add(Command.NUMBER_4);
        calculator.add(Command.MULTIPLY);
        calculator.add(Command.OPEN_PARENTHESIS);
        calculator.add(Command.NUMBER_5);
        calculator.add(Command.ADD);
        calculator.add(Command.NUMBER_6);
        calculator.add(Command.CLOSE_PARENTHESIS);
        calculator.add(Command.DIVIDE);
        calculator.add(Command.NUMBER_7);
        assertEquals("0.714285", calculator.execute().toPlainString());
    }
}
