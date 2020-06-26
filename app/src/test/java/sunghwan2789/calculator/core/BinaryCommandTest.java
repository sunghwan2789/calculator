package sunghwan2789.calculator.core;

import org.junit.Test;

import static org.junit.Assert.*;

public class BinaryCommandTest {
    @Test
    public void add_subtract_is_lower_than_multiply_divide_modular() {
        ExpressionCommand a, b;

        a = new BinaryCommand(Command.ADD);
        b = new BinaryCommand(Command.MULTIPLY);
        assertTrue(a.compareTo(b) < 0);
        assertTrue(b.compareTo(a) > 0);
        b = new BinaryCommand(Command.DIVIDE);
        assertTrue(a.compareTo(b) < 0);
        assertTrue(b.compareTo(a) > 0);
        b = new BinaryCommand(Command.MODULAR);
        assertTrue(a.compareTo(b) < 0);
        assertTrue(b.compareTo(a) > 0);

        a = new BinaryCommand(Command.SUBTRACT);
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
    public void follows_left_associative() {
        ExpressionCommand a, b;

        a = new BinaryCommand(Command.ADD);
        b = new BinaryCommand(Command.ADD);
        assertTrue(a.compareTo(b) > 0);
        b = new BinaryCommand(Command.SUBTRACT);
        assertTrue(a.compareTo(b) > 0);

        a = new BinaryCommand(Command.SUBTRACT);
        b = new BinaryCommand(Command.ADD);
        assertTrue(a.compareTo(b) > 0);
        b = new BinaryCommand(Command.SUBTRACT);
        assertTrue(a.compareTo(b) > 0);

        a = new BinaryCommand(Command.MULTIPLY);
        b = new BinaryCommand(Command.MULTIPLY);
        assertTrue(a.compareTo(b) > 0);
        b = new BinaryCommand(Command.DIVIDE);
        assertTrue(a.compareTo(b) > 0);
        b = new BinaryCommand(Command.MODULAR);
        assertTrue(a.compareTo(b) > 0);

        a = new BinaryCommand(Command.DIVIDE);
        b = new BinaryCommand(Command.MULTIPLY);
        assertTrue(a.compareTo(b) > 0);
        b = new BinaryCommand(Command.DIVIDE);
        assertTrue(a.compareTo(b) > 0);
        b = new BinaryCommand(Command.MODULAR);
        assertTrue(a.compareTo(b) > 0);

        a = new BinaryCommand(Command.MODULAR);
        b = new BinaryCommand(Command.MULTIPLY);
        assertTrue(a.compareTo(b) > 0);
        b = new BinaryCommand(Command.DIVIDE);
        assertTrue(a.compareTo(b) > 0);
        b = new BinaryCommand(Command.MODULAR);
        assertTrue(a.compareTo(b) > 0);
    }
}
