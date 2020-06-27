package sunghwan2789.calculator.core;

import org.junit.Test;

import static org.junit.Assert.*;

public class UnaryCommandTest {
    @Test
    public void precedence_check() {
        ExpressionCommand a, b;

        a = new UnaryCommand(Command.ADD);
        b = new UnaryCommand(Command.SUBTRACT);
        assertTrue("ADD is not lower than SUBTRACT", a.compareTo(b) < 0);
        assertTrue("SUBTRACT is not lower than ADD", b.compareTo(a) < 0);
    }

    @Test
    public void associative_check() {
        ExpressionCommand a, b;

        a = new UnaryCommand(Command.SUBTRACT);
        b = new UnaryCommand(Command.SUBTRACT);
        assertTrue("SUBTRACT is not right associative.", a.compareTo(b) < 0);
    }
}
