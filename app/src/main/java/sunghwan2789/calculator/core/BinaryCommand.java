package sunghwan2789.calculator.core;

import java.math.BigDecimal;
import java.util.Stack;

public class BinaryCommand implements ExpressionCommand {
    private Command type;

    public BinaryCommand(Command type) {
        this.type = type;
    }

    @Override
    public BigDecimal execute(Stack<BigDecimal> operandStack, Stack<ExpressionCommand> operatorStack) {
        BigDecimal a = operandStack.pop();
        BigDecimal b = operandStack.pop();

        switch (type) {
            case ADD:
                return a.add(b);
            case SUBTRACT:
                return a.subtract(b);
            case MULTIPLY:
                return a.multiply(b);
            case DIVIDE:
                return a.divide(b);
            case MODULAR:
                return a.remainder(b);
            default:
                throw new UnsupportedOperationException(type.toString());
        }
    }

    @Override
    public int compareTo(ExpressionCommand o) {
        if (o instanceof BinaryCommand) {
            Command compareType = ((BinaryCommand) o).type;
            switch (type) {
                case ADD:
                case SUBTRACT:
                    switch (compareType) {
                        case MULTIPLY:
                        case DIVIDE:
                        case MODULAR:
                            return -1;
                    }
                case MULTIPLY:
                case DIVIDE:
                case MODULAR:
                    return 1;
            }
        } else if (o instanceof ParenthesisCommand) {
            return -1;
        } else if (o instanceof UnaryCommand) {
            return -1;
        } else if (o instanceof BitwiseCommand) {
            return 1;
        }

        throw new UnsupportedOperationException();
    }
}
