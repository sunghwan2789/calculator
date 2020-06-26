package sunghwan2789.calculator.core;

import androidx.annotation.NonNull;

import java.math.BigDecimal;
import java.util.Stack;

public class ParenthesisCommand extends ExpressionCommand {
    public ParenthesisCommand(Command type) {
        super(type);
    }

    @Override
    public BigDecimal execute(Stack<BigDecimal> operandStack) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int compareTo(ExpressionCommand o) {
        switch (getType()) {
            case OPEN_PARENTHESIS:
                return -1;
            case CLOSE_PARENTHESIS:
                return 1;
        }

        throw new UnsupportedOperationException();
    }

    @NonNull
    @Override
    public String toString() {
        switch (getType()) {
            case OPEN_PARENTHESIS:
                return "(";
            case CLOSE_PARENTHESIS:
                return ")";
        }

        throw new UnsupportedOperationException(getType().toString());
    }
}
