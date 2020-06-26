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
            if (commandQueue.peek() instanceof OperandCommand) {
                operandStack.push(commandQueue.remove().execute(operandStack, operatorStack));
                continue;
            }

            operatorStack.push(commandQueue.remove());
        }

        return BigDecimal.ZERO;
    }
}
