package sunghwan2789.calculator.core;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class NumberInputTest {
    NumberInput numberInput;

    @Before
    public void initialize() {
        numberInput = new NumberInput();
    }

    @After
    public void cleanup() {
        numberInput.clear();
    }

    @Test
    public void clear() {
        numberInput.addDigit(1);
        numberInput.toggleSign();
        numberInput.addDecimalPoint();
        numberInput.addDigit(2);

        assertEquals("-1.2", numberInput.toString());

        numberInput.clear();

        assertEquals("0", numberInput.toString());
    }

    @Test
    public void toggleSign_zero_does_nothing() {
        assertTrue(numberInput.toggleSign());
        assertEquals("0", numberInput.toString());
    }

    @Test
    public void toggleSign_toggles_sign() {
        numberInput.addDigit(1);
        assertTrue(numberInput.toggleSign());
        assertEquals("-1", numberInput.toString());
        assertTrue(numberInput.toggleSign());
        assertEquals("1", numberInput.toString());
    }

    @Test
    public void addDigit_ignores_leading_zeroes() {
        assertTrue(numberInput.addDigit(0));
        assertTrue(numberInput.addDigit(0));
        assertTrue(numberInput.addDigit(0));
        assertEquals("0", numberInput.toString());
    }

    @Test
    public void addDigit_fails_on_overflow() {
        for (int i = 0; i < NumberInput.MAX_DIGITS; i++) {
            assertTrue(numberInput.addDigit(1));
        }
        assertFalse(numberInput.addDigit(1));
    }

    @Test
    public void addDecimalPoint_appends_zero_on_empty() {
        assertFalse(numberInput.hasDecimalPoint());
        assertTrue(numberInput.addDecimalPoint());
        assertTrue(numberInput.hasDecimalPoint());
        assertEquals("0.", numberInput.toString());
    }

    @Test
    public void addDecimalPoint_fails_on_second() {
        assertFalse(numberInput.hasDecimalPoint());
        assertTrue(numberInput.addDecimalPoint());
        assertTrue(numberInput.hasDecimalPoint());
        assertFalse(numberInput.addDecimalPoint());
    }

    @Test
    public void remove_on_empty_does_nothing() {
        numberInput.remove();
        assertEquals("0", numberInput.toString());
    }

    @Test
    public void remove_a_character() {
        numberInput.addDigit(1);
        assertEquals("1", numberInput.toString());
        numberInput.remove();
        assertEquals("0", numberInput.toString());
    }

    @Test
    public void remove_characters() {
        numberInput.addDigit(1);
        numberInput.addDigit(2);
        assertEquals("12", numberInput.toString());
        numberInput.remove();
        assertEquals("1", numberInput.toString());
    }

    @Test
    public void remove_decimal_point() {
        numberInput.addDigit(1);
        numberInput.addDecimalPoint();
        assertEquals("1.", numberInput.toString());
        assertTrue(numberInput.hasDecimalPoint());
        numberInput.remove();
        assertEquals("1", numberInput.toString());
        assertFalse(numberInput.hasDecimalPoint());
    }

    @Test
    public void remove_zero_decimal_without_prefix_zeros() {
        numberInput.addDigit(0);
        numberInput.addDecimalPoint();
        assertEquals("0.", numberInput.toString());
        numberInput.remove();
        numberInput.addDigit(0);
        assertEquals("0", numberInput.toString());
    }

    @Test
    public void toString_returns_zero_on_empty() {
        assertEquals("0", numberInput.toString());
    }

    @Test
    public void toString_returns_minus_on_negative() {
        numberInput.addDigit(1);
        numberInput.toggleSign();
        assertEquals("-1", numberInput.toString());
    }

    @Test
    public void toString_works() {
        numberInput.addDigit(1);
        assertEquals("1", numberInput.toString());
    }

    @Test
    public void toBigDecimal_works() {
        numberInput.addDigit(1);
        numberInput.addDigit(2);
        numberInput.addDigit(3);
        assertEquals("123", numberInput.toString());

        BigDecimal number = numberInput.toBigDecimal();
        assertEquals(BigDecimal.valueOf(369), number.multiply(BigDecimal.valueOf(3)));
    }
}
