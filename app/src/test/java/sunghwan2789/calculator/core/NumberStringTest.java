package sunghwan2789.calculator.core;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NumberStringTest {
    NumberString numberString;

    @Before
    public void initialize() {
        numberString = new NumberString();
    }

    @After
    public void cleanup() {
        numberString.clear();
    }

    @Test
    public void add_remove_works() {
        assertEquals("", numberString.getDigits());
        assertEquals(true, numberString.isEmpty());

        numberString.add('0');
        assertEquals("0", numberString.getDigits());
        assertEquals(false, numberString.isEmpty());

        numberString.add('1');
        assertEquals("01", numberString.getDigits());
        assertEquals(false, numberString.isEmpty());

        numberString.remove();
        assertEquals("0", numberString.getDigits());
        assertEquals(false, numberString.isEmpty());

        numberString.remove();
        assertEquals("", numberString.getDigits());
        assertEquals(true, numberString.isEmpty());
    }

    @Test
    public void negative_getter_setter_works() {
        assertEquals(false, numberString.isNegative());

        numberString.setNegative(true);
        assertEquals(true, numberString.isNegative());

        numberString.setNegative(false);
        assertEquals(false, numberString.isNegative());
    }

    @Test
    public void clear_clears_digits_and_reset_negative() {
        numberString.add('0');
        numberString.add('1');
        numberString.setNegative(false);

        numberString.clear();
        assertEquals("", numberString.getDigits());
        assertEquals(true, numberString.isEmpty());
        assertEquals(false, numberString.isNegative());
    }

    @Test
    public void length_counts_digits() {
        numberString.add('0');
        numberString.add('.');
        numberString.add('2');

        assertEquals(3, numberString.length());
    }
}
