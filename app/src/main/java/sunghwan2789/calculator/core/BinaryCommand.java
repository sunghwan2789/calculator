package sunghwan2789.calculator.core;

import java.math.BigDecimal;
import java.util.Stack;

public class BinaryCommand extends ExpressionCommand {
    public BinaryCommand(Command type) {
        super(type);
    }

    @Override
    public BigDecimal execute(Stack<BigDecimal> operandStack) {
        BigDecimal b = operandStack.pop();
        BigDecimal a = operandStack.pop();

        switch (getType()) {
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
                throw new UnsupportedOperationException(getType().toString());
        }
    }

    @Override
    public int compareTo(ExpressionCommand o) {
        if (o instanceof BinaryCommand) {
            switch (getType()) {
                case ADD:
                case SUBTRACT:
                    switch (o.getType()) {
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
