package sunghwan2789.calculator.core;

import java.math.BigDecimal;
import java.util.Stack;

public class OperandCommand implements ExpressionCommand {
    public NumberInput numberInput;

    public OperandCommand() {
        numberInput = new NumberInput();
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
