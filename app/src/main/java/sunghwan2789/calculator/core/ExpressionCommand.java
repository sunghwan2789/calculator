package sunghwan2789.calculator.core;

import java.math.BigDecimal;
import java.util.Stack;

public abstract class ExpressionCommand implements Comparable<ExpressionCommand> {
    private Command type;

    public ExpressionCommand(Command type) {
        this.type = type;
    }

    public Command getType() {
        return type;
    }

    public abstract BigDecimal execute(Stack<BigDecimal> operandStack, Stack<ExpressionCommand> operatorStack);
}