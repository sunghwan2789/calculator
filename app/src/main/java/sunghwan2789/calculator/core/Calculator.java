package sunghwan2789.calculator.core;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Calculator {
    private Queue<ExpressionCommand> commandQueue;
    private Stack<BigDecimal> operandStack;
    private Stack<ExpressionCommand> operatorStack;

    public Calculator(Collection<ExpressionCommand> commands) {
        commandQueue = new LinkedList<>(commands);
        operandStack = new Stack<>();
        operatorStack = new Stack<>();
    }

    public BigDecimal execute() {
        while (!commandQueue.isEmpty()) {
            // 피연산자 처리
            if (commandQueue.peek() instanceof OperandCommand) {
                operandStack.push(commandQueue.remove().execute(operandStack, operatorStack));
                continue;
            }

            // 괄호 처리
            if (commandQueue.peek() instanceof ParenthesisCommand) {
                switch (commandQueue.remove().getType()) {
                    case OPEN_PARENTHESIS:
                        operatorStack.push(commandQueue.remove());
                        break;
                    case CLOSE_PARENTHESIS:
                        while (operatorStack.peek().getType() != Command.OPEN_PARENTHESIS) {
                            operandStack.push(operatorStack.pop().execute(operandStack, operatorStack));
                        }
                        operatorStack.pop();
                        break;
                    default:
                        throw new UnsupportedOperationException();
                }
            }

            // 우선순위가 높은 연산자 먼저 처리
            while (!operatorStack.isEmpty() && operatorStack.peek().compareTo(commandQueue.peek()) > 0) {
                operandStack.push(operatorStack.pop().execute(operandStack, operatorStack));
            }

            operatorStack.push(commandQueue.remove());
        }

        while (!operatorStack.isEmpty()) {
            operandStack.push(operatorStack.pop().execute(operandStack, operatorStack));
        }

        if (operandStack.size() != 1) {
            throw new UnsupportedOperationException();
        }

        return operandStack.pop();
    }
}
