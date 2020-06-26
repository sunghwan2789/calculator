package sunghwan2789.calculator.core;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Stack;

public class BitwiseCommand extends ExpressionCommand {
    public BitwiseCommand(Command type) {
        super(type);
    }

    @Override
    public BigDecimal execute(Stack<BigDecimal> operandStack, Stack<ExpressionCommand> operatorStack) {
        BigInteger a, b;

        switch (getType()) {
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

        throw new UnsupportedOperationException(getType().toString());
    }

    @Override
    public int compareTo(ExpressionCommand o) {
        if (o instanceof BitwiseCommand) {
            Command compareType = o.getType();
            switch (getType()) {
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
