package sunghwan2789.calculator.core;

import java.math.BigDecimal;
import java.util.Stack;

public class ParenthesisCommand implements ExpressionCommand {
    private Command type;

    public ParenthesisCommand(Command type) {
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