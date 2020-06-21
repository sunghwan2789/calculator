package sunghwan2789.calculator.core;

import java.math.BigDecimal;
import java.util.LinkedList;

public class CalculatorManager {
    private LinkedList<ExpressionCommand> expressionCommands;

    public CalculatorManager() {
    }

    public void clear() {
        expressionCommands.clear();
    }

    public void add(Command command) {
        if (isNumeric(command)) {
            ExpressionCommand lastCommand = expressionCommands.getLast();
            if (!(lastCommand instanceof OperandCommand)) {
                lastCommand = new OperandCommand();
                expressionCommands.add(lastCommand);
            }
            OperandCommand lastOperandCommand = (OperandCommand)lastCommand;

            switch (command) {
                case NUMBER_0:
                    lastOperandCommand.numberInput.addDigit(0);
                    break;
                case NUMBER_1:
                    lastOperandCommand.numberInput.addDigit(1);
                    break;
                case NUMBER_2:
                    lastOperandCommand.numberInput.addDigit(2);

            }
        }
    }

    public BigDecimal execute() {
        return null;
    }

    private static boolean isNumeric(Command command) {
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
}
