package sunghwan2789.calculator.core;

import org.junit.Test;

import static org.junit.Assert.*;

public class ExpressionCommandTest {
    @Test
    public void operator_precedence_between_levels() {
        ExpressionCommand a, b;

        a = new BinaryCommand(Command.ADD);
        b = new BinaryCommand(Command.MULTIPLY);
        assertTrue("ADD has lower precedence than MULTIPLY", a.compareTo(b) < 0);
        assertTrue("MULTIPLY has higher precedence than ADD", b.compareTo(a) > 0);
        b = new BitwiseCommand(Command.AND);
        assertTrue("ADD is not higher than AND", a.compareTo(b) > 0);
        assertTrue("AND is not lower than ADD", b.compareTo(a) < 0);
        b = new ParenthesesCommand(Command.CLOSE_PARENTHESES);
        assertTrue("ADD is not lower than )", a.compareTo(b) < 0);
        assertTrue(") is not higher than ADD", b.compareTo(a) > 0);
        b = new UnaryCommand(Command.SUBTRACT);
        assertTrue("ADD is not lower than unary SUBTRACT", a.compareTo(b) < 0);
        assertTrue("unary SUBTRACT is not higher than ADD", b.compareTo(a) > 0);


        a = new BinaryCommand(Command.MULTIPLY);
        b = new BitwiseCommand(Command.AND);
        assertTrue("MULTIPLY is not higher than AND", a.compareTo(b) > 0);
        assertTrue("AND is not lower than MULTIPLY", b.compareTo(a) < 0);
        b = new ParenthesesCommand(Command.CLOSE_PARENTHESES);
        assertTrue("MULTIPLY is not lower than )", a.compareTo(b) < 0);
        assertTrue(") is not higher than MULTIPLY", b.compareTo(a) > 0);
        b = new UnaryCommand(Command.SUBTRACT);
        assertTrue("MULTIPLY is not lower than unary SUBTRACT", a.compareTo(b) < 0);
        assertTrue("unary SUBTRACT is not higher than MULTIPLY", b.compareTo(a) > 0);

        a = new BitwiseCommand(Command.AND);
        b = new ParenthesesCommand(Command.CLOSE_PARENTHESES);
        assertTrue("AND is not lower than )", a.compareTo(b) < 0);
        assertTrue(") is not higher than AND", b.compareTo(a) > 0);
        b = new UnaryCommand(Command.SUBTRACT);
        assertTrue("AND is not lower than unary SUBTRACT", a.compareTo(b) < 0);
        assertTrue("unary SUBTRACT is not higher than AND", b.compareTo(a) > 0);

        a = new ParenthesesCommand(Command.CLOSE_PARENTHESES);
        b = new UnaryCommand(Command.SUBTRACT);
        assertTrue(") is not higher than unary SUBTRACT", a.compareTo(b) > 0);
        assertTrue("unary SUBTRACT is not lower than )", b.compareTo(a) < 0);

        a = new BitwiseCommand(Command.NOT);
        b = new UnaryCommand(Command.SUBTRACT);
        assertTrue("NOT is not lower than unary SUBTRACT", a.compareTo(b) < 0);
        assertTrue("unary SUBTRACT is not lower than NOT", b.compareTo(a) < 0);
    }
}
