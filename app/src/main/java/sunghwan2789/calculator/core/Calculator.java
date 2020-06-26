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
                operandStack.push(commandQueue.remove().execute(operandStack));
                continue;
            }

            // 괄호 처리
            if (commandQueue.peek() instanceof ParenthesesCommand) {
                switch (commandQueue.peek().getType()) {
                    case OPEN_PARENTHESES:
                        operatorStack.push(commandQueue.remove());
                        continue;
                    case CLOSE_PARENTHESES:
                        // 괄호 짝을 찾을 때까지 계산
                        while (operatorStack.peek().getType() != Command.OPEN_PARENTHESES) {
                            operandStack.push(operatorStack.pop().execute(operandStack));
                        }
                        // 여는 괄호 제거
                        operatorStack.pop();
                        commandQueue.remove();
                        continue;
                    default:
                        throw new UnsupportedOperationException();
                }
            }

            // 우선순위가 높은 연산자 먼저 처리
            while (!operatorStack.isEmpty() && operatorStack.peek().compareTo(commandQueue.peek()) > 0) {
                operandStack.push(operatorStack.pop().execute(operandStack));
            }

            operatorStack.push(commandQueue.remove());
        }

        while (!operatorStack.isEmpty()) {
            operandStack.push(operatorStack.pop().execute(operandStack));
        }

        if (operandStack.size() != 1) {
            throw new UnsupportedOperationException();
        }

        return operandStack.pop();
    }
}
