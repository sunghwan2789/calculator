package sunghwan2789.calculator.core;

import org.junit.Test;

import static org.junit.Assert.*;

public class BinaryCommandTest {
    @Test
    public void add_is_lower_than_multiply_divide_modular() {
        ExpressionCommand a = new BinaryCommand(Command.ADD);
        ExpressionCommand b;

        b = new BinaryCommand(Command.MULTIPLY);
        assertTrue(a.compareTo(b) < 0);
        assertTrue(b.compareTo(a) > 0);

        b = new BinaryCommand(Command.DIVIDE);
        assertTrue(a.compareTo(b) < 0);
        assertTrue(b.compareTo(a) > 0);

        b = new BinaryCommand(Command.MODULAR);
        assertTrue(a.compareTo(b) < 0);
        assertTrue(b.compareTo(a) > 0);
    }

    @Test
    public void subtract_is_lower_than_multiply_divide_modular() {
        ExpressionCommand a = new BinaryCommand(Command.SUBTRACT);
        ExpressionCommand b;

        b = new BinaryCommand(Command.MULTIPLY);
        assertTrue(a.compareTo(b) < 0);
        assertTrue(b.compareTo(a) > 0);

        b = new BinaryCommand(Command.DIVIDE);
        assertTrue(a.compareTo(b) < 0);
        assertTrue(b.compareTo(a) > 0);

        b = new BinaryCommand(Command.MODULAR);
        assertTrue(a.compareTo(b) < 0);
        assertTrue(b.compareTo(a) > 0);
    }
}
