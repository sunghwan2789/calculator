package sunghwan2789.calculator.core;

import java.math.BigDecimal;
import java.util.Stack;

public class BitwiseCommand implements ExpressionCommand {
    private Command type;

    public BitwiseCommand(Command type) {
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
