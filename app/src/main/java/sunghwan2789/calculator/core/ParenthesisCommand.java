package sunghwan2789.calculator.core;

import java.math.BigDecimal;
import java.util.Stack;

public class ParenthesisCommand extends ExpressionCommand {
    public ParenthesisCommand(Command type) {
        super(type);
    }

    @Override
    public BigDecimal execute(Stack<BigDecimal> operandStack) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int compareTo(ExpressionCommand o) {
        throw new UnsupportedOperationException();
    }
}
