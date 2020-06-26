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

        a = new ParenthesesCommand(Command.OPEN_PARENTHESES);
    }
}
