package sunghwan2789.calculator.core;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Stack;

public class BitwiseCommand extends ExpressionCommand {
    public BitwiseCommand(Command type) {
        super(type);
    }

    @Override
    public BigDecimal execute(Stack<BigDecimal> operandStack) {
        BigInteger a, b;

        switch (getType()) {
            case NOT:
                a = operandStack.pop().toBigIntegerExact();
                return new BigDecimal(a.not());
            case AND:
                b = operandStack.pop().toBigIntegerExact();
                a = operandStack.pop().toBigIntegerExact();
                return new BigDecimal(a.and(b));
            case XOR:
                b = operandStack.pop().toBigIntegerExact();
                a = operandStack.pop().toBigIntegerExact();
                return new BigDecimal(a.xor(b));
            case OR:
                b = operandStack.pop().toBigIntegerExact();
                a = operandStack.pop().toBigIntegerExact();
                return new BigDecimal(a.or(b));
        }

        throw new UnsupportedOperationException(getType().toString());
    }

    @Override
    public int compareTo(ExpressionCommand o) {
        if (o instanceof BitwiseCommand) {
            switch (getType()) {
                case NOT:
                    switch (o.getType()) {
                        case NOT:
                            return -1;
                        case AND:
                        case XOR:
                        case OR:
                            return 1;
                    }
                case AND:
                    switch (o.getType()) {
                        case NOT:
                            return -1;
                        case AND:
                        case XOR:
                        case OR:
                            return 1;
                    }
                case XOR:
                    switch (o.getType()) {
                        case NOT:
                        case AND:
                            return -1;
                        case XOR:
                        case OR:
                            return 1;
                    }
                case OR:
                    switch (o.getType()) {
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
