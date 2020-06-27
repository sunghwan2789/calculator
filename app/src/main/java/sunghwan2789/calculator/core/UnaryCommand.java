package sunghwan2789.calculator.core;

import androidx.annotation.NonNull;

import java.math.BigDecimal;
import java.util.Stack;

public class UnaryCommand extends ExpressionCommand {
    public UnaryCommand(Command type) {
        super(type);
    }

    @Override
    public BigDecimal execute(Stack<BigDecimal> operandStack) {
        switch (getType()) {
            case ADD:
                // 아무것도 안함
                return operandStack.pop();
            case SUBTRACT:
                // 부호를 반전한다
                return operandStack.pop().multiply(BigDecimal.valueOf(-1));
        }

        throw new UnsupportedOperationException(getType().toString());
    }

    @Override
    public int compareTo(ExpressionCommand o) {
        if (o instanceof UnaryCommand) {
            switch (getType()) {
                case ADD:
                case SUBTRACT:
                    return -1;
            }
        } else if (o instanceof ParenthesesCommand) {
            return -1;
        } else if (o instanceof BinaryCommand) {
            return 1;
        } else if (o instanceof BitwiseCommand) {
            if (o.getType() == Command.NOT) {
                return -1;
            } else {
                return 1;
            }
        }

        throw new UnsupportedOperationException();
    }

    @NonNull
    @Override
    public String toString() {
        switch (getType()) {
            case ADD:
                return "+";
            case SUBTRACT:
                return "−";
        }

        throw new UnsupportedOperationException(getType().toString());
    }
}
