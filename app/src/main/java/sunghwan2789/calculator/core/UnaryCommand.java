package sunghwan2789.calculator.core;

import java.math.BigDecimal;
import java.util.Stack;

public class UnaryCommand implements ExpressionCommand {
    private Command type;

    public UnaryCommand(Command type) {
        this.type = type;
    }

    @Override
    public BigDecimal execute(Stack<BigDecimal> operandStack, Stack<ExpressionCommand> operatorStack) {
        return null;
    }

    @Override
    public int compareTo(ExpressionCommand o) {
        return 0;
    }
}
