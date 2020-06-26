package sunghwan2789.calculator.core;

import java.math.BigDecimal;
import java.util.Stack;

public class OperandCommand extends ExpressionCommand {
    public NumberInput numberInput;

    public OperandCommand()
    {
        super(Command.NULL);

        numberInput = new NumberInput();
    }

    @Override
    public BigDecimal execute(Stack<BigDecimal> operandStack) {
        return numberInput.toBigDecimal();
    }

    @Override
    public int compareTo(ExpressionCommand o) {
        return 0;
    }
}
