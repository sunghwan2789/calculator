package sunghwan2789.calculator.core;

import org.junit.Test;

import static org.junit.Assert.*;

public class BitwiseCommandTest {
    @Test
    public void precedence_check() {
        ExpressionCommand a, b;

        a = new BitwiseCommand(Command.NOT);
        b = new BitwiseCommand(Command.AND);
        assertTrue(a.compareTo(b) > 0);
        assertTrue(b.compareTo(a) < 0);
        b = new BitwiseCommand(Command.XOR);
        assertTrue(a.compareTo(b) > 0);
        assertTrue(b.compareTo(a) < 0);
        b = new BitwiseCommand(Command.OR);
        assertTrue(a.compareTo(b) > 0);
        assertTrue(b.compareTo(a) < 0);

        a = new BitwiseCommand(Command.AND);
        b = new BitwiseCommand(Command.XOR);
        assertTrue(a.compareTo(b) > 0);
        assertTrue(b.compareTo(a) < 0);
        b = new BitwiseCommand(Command.OR);
        assertTrue(a.compareTo(b) > 0);
        assertTrue(b.compareTo(a) < 0);

        a = new BitwiseCommand(Command.XOR);
        b = new BitwiseCommand(Command.OR);
        assertTrue(a.compareTo(b) > 0);
        assertTrue(b.compareTo(a) < 0);
    }

    @Test
    public void associative_check() {
        ExpressionCommand a, b;

        a = new BitwiseCommand(Command.NOT);
        b = new BitwiseCommand(Command.NOT);
        assertTrue("NOT is not right associative.", a.compareTo(b) < 0);

        a = new BitwiseCommand(Command.AND);
        b = new BitwiseCommand(Command.AND);
        assertTrue("AND is not left associative.", a.compareTo(b) > 0);

        a = new BitwiseCommand(Command.XOR);
        b = new BitwiseCommand(Command.XOR);
        assertTrue("XOR is not left associative.", a.compareTo(b) > 0);

        a = new BitwiseCommand(Command.OR);
        b = new BitwiseCommand(Command.OR);
        assertTrue("OR is not left associative.", a.compareTo(b) > 0);
    }
}
