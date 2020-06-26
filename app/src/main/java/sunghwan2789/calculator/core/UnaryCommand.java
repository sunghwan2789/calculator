package sunghwan2789.calculator.core;

import java.math.BigDecimal;
import java.util.Stack;

public class UnaryCommand extends ExpressionCommand {
    public UnaryCommand(Command type) {
        super(type);
    }

    @Override
    public BigDecimal execute(Stack<BigDecimal> operandStack) {
        return null;
    }

    @Override
    public int compareTo(ExpressionCommand o) {
        return 0;
    }
}
