package sunghwan2789.calculator.core;

import java.math.BigDecimal;
import java.util.Stack;

public interface ExpressionCommand extends Comparable<ExpressionCommand> {
    public BigDecimal execute(Stack<BigDecimal> operandStack, Stack<ExpressionCommand> operatorStack);
}