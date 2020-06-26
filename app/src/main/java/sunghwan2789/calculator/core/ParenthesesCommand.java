package sunghwan2789.calculator.core;

import androidx.annotation.NonNull;

import java.math.BigDecimal;
import java.util.Stack;

public class ParenthesesCommand extends ExpressionCommand {
    public ParenthesesCommand(Command type) {
        super(type);
    }

    @Override
    public BigDecimal execute(Stack<BigDecimal> operandStack) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int compareTo(ExpressionCommand o) {
        switch (getType()) {
            case OPEN_PARENTHESES:
                return -1;
            case CLOSE_PARENTHESES:
                return 1;
        }

        throw new UnsupportedOperationException();
    }

    @NonNull
    @Override
    public String toString() {
        switch (getType()) {
            case OPEN_PARENTHESES:
                return "(";
            case CLOSE_PARENTHESES:
                return ")";
        }

        throw new UnsupportedOperationException(getType().toString());
    }
}
