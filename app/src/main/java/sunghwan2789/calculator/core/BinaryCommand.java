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
        return null;
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
                        default:
                            return 0;
                    }
                case MULTIPLY:
                case DIVIDE:
                case MODULAR:
                    switch (compareType) {
                        case ADD:
                        case SUBTRACT:
                            return 1;
                        default:
                            return -1;
                    }
            }
            return 0;
        }
        return 0;
    }
}
