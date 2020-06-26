package sunghwan2789.calculator.core;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Stack;

public class BitwiseCommand implements ExpressionCommand {
    private Command type;

    public BitwiseCommand(Command type) {
        this.type = type;
    }

    @Override
    public BigDecimal execute(Stack<BigDecimal> operandStack, Stack<ExpressionCommand> operatorStack) {
        BigInteger a, b;

        switch (type) {
            case NOT:
                a = operandStack.pop().toBigIntegerExact();
                return new BigDecimal(a.not());
            case AND:
                a = operandStack.pop().toBigIntegerExact();
                b = operandStack.pop().toBigIntegerExact();
                return new BigDecimal(a.and(b));
            case XOR:
                a = operandStack.pop().toBigIntegerExact();
                b = operandStack.pop().toBigIntegerExact();
                return new BigDecimal(a.xor(b));
            case OR:
                a = operandStack.pop().toBigIntegerExact();
                b = operandStack.pop().toBigIntegerExact();
                return new BigDecimal(a.or(b));
        }

        throw new UnsupportedOperationException(type.toString());
    }

    @Override
    public int compareTo(ExpressionCommand o) {
        if (o instanceof BitwiseCommand) {
            Command compareType = ((BitwiseCommand) o).type;
            switch (type) {
                case NOT:
                    if (compareType == Command.NOT) {
                        return -1;
                    } else {
                        return 1;
                    }
                case AND:
                    switch (compareType) {
                        case NOT:
                            return -1;
                        case AND:
                        case XOR:
                        case OR:
                            return 1;
                    }
                case XOR:
                    switch (compareType) {
                        case NOT:
                        case AND:
                            return -1;
                        case XOR:
                        case OR:
                            return 1;
                    }
                case OR:
                    switch (compareType) {
                        case NOT:
                        case AND:
                        case XOR:
                            return -1;
                        case OR:
                            return 1;
                    }
            }
        } else if (o instanceof ParenthesisCommand) {
            return -1;
        } else if (o instanceof UnaryCommand) {
            return -1;
        } else if (o instanceof BitwiseCommand) {
            return 1;
        }

        throw new UnsupportedOperationException(o.toString());
    }
}
