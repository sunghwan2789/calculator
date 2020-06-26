package sunghwan2789.calculator.core;

import java.math.BigDecimal;
import java.util.LinkedList;

public class CalculatorManager {
    private LinkedList<ExpressionCommand> expressionCommands;

    public CalculatorManager() {
        expressionCommands = new LinkedList<>();
    }

    public void clear() {
        expressionCommands.clear();
    }

    public void add(Command command) {
        if (isOperand(command)) {
            addOperand(command);
        } else if (isParenthesis(command)) {
            addParenthesis(command);
        } else if (isUnary(command)) {
            addUnary(command);
        } else if (isBinary(command)) {
            addBinary(command);
        } else if (isBitwise(command)) {
            addBitwise(command);
        } if (command == Command.EQUAL) {
            execute();
        } else if (command == Command.REMOVE) {
            remove();
        }
    }

    private static boolean isOperand(Command command) {
        switch (command) {
            case NUMBER_0:
            case NUMBER_1:
            case NUMBER_2:
            case NUMBER_3:
            case NUMBER_4:
            case NUMBER_5:
            case NUMBER_6:
            case NUMBER_7:
            case NUMBER_8:
            case NUMBER_9:
            case DECIMAL_POINT:
            case TOGGLE_SIGN:
                return true;
            default:
                return false;
        }
    }

    private void addOperand(Command command) {
        OperandCommand lastOperand = getLastOperand();

        switch (command) {
            case NUMBER_0:
                lastOperand.numberInput.addDigit(0);
                break;
            case NUMBER_1:
                lastOperand.numberInput.addDigit(1);
                break;
            case NUMBER_2:
                lastOperand.numberInput.addDigit(2);
                break;
            case NUMBER_3:
                lastOperand.numberInput.addDigit(3);
                break;
            case NUMBER_4:
                lastOperand.numberInput.addDigit(4);
                break;
            case NUMBER_5:
                lastOperand.numberInput.addDigit(5);
                break;
            case NUMBER_6:
                lastOperand.numberInput.addDigit(6);
                break;
            case NUMBER_7:
                lastOperand.numberInput.addDigit(7);
                break;
            case NUMBER_8:
                lastOperand.numberInput.addDigit(8);
                break;
            case NUMBER_9:
                lastOperand.numberInput.addDigit(9);
                break;
            case DECIMAL_POINT:
                lastOperand.numberInput.addDecimalPoint();
                break;
            case TOGGLE_SIGN:
                lastOperand.numberInput.toggleSign();
                break;
        }
    }

    private OperandCommand getLastOperand() {
        if (expressionCommands.peekLast() instanceof OperandCommand) {
            return (OperandCommand)expressionCommands.getLast();
        }

        OperandCommand operand = new OperandCommand();
        expressionCommands.add(operand);
        return operand;
    }

    private static boolean isParenthesis(Command command) {
        switch (command) {
            case OPEN_PARENTHESIS:
            case CLOSE_PARENTHESIS:
                return true;
            default:
                return false;
        }
    }

    private void addParenthesis(Command command) {
        switch (command) {
            case OPEN_PARENTHESIS:
                if (expressionCommands.peekLast() instanceof OperandCommand) {
                    expressionCommands.add(new BinaryCommand(Command.MULTIPLY));
                }
            case CLOSE_PARENTHESIS:
                expressionCommands.add(new ParenthesisCommand(command));
                break;
        }
    }

    private static boolean isBinary(Command command) {
        switch (command) {
            case ADD:
            case SUBTRACT:
            case MULTIPLY:
            case DIVIDE:
            case MODULAR:
            case AND:
            case OR:
            case XOR:
                return true;
            default:
                return false;
        }
    }

    private void addBinary(Command command) {
        switch (command) {
            case ADD:
            case SUBTRACT:
            case MULTIPLY:
            case DIVIDE:
            case MODULAR:
                expressionCommands.add(new BinaryCommand(command));
                break;
        }
    }

    private static boolean isUnary(Command command) {
        switch (command) {
            default:
                return false;
        }
    }

    private void addUnary(Command command) {
        switch (command) {
        }
    }

    private static boolean isBitwise(Command command) {
        switch (command) {
            case AND:
            case OR:
            case NOT:
            case XOR:
                return true;
            default:
                return false;
        }
    }

    private void addBitwise(Command command) {
        switch (command) {
            case AND:
            case OR:
            case NOT:
            case XOR:
                expressionCommands.add(new BitwiseCommand(command));
                break;
        }
    }

    public BigDecimal execute() {
        return null;
    }

    public void remove() {
        if (expressionCommands.peekLast() instanceof OperandCommand) {
            OperandCommand operand = (OperandCommand)expressionCommands.getLast();
            operand.numberInput.remove();
            if (operand.numberInput.isEmpty()) {
                expressionCommands.removeLast();
            }
        } else {
            expressionCommands.removeLast();
        }
    }
}
