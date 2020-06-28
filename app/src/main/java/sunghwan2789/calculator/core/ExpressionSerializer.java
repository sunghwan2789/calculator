package sunghwan2789.calculator.core;

/**
 * 계산식을 재사용 가능한 형식으로 변환하는 클래스
 */
public class ExpressionSerializer {
    /** 계산식을 재사용 가능한 문자열로 변환한다. */
    public static String serialize(Iterable<ExpressionCommand> expressionCommands) {
        StringBuilder stringBuilder = new StringBuilder();

        for (ExpressionCommand command : expressionCommands) {
            if (command instanceof OperandCommand) {
                // 피연산자는 그 자체로 재사용 가능한 문자열임
                stringBuilder.append(command.toString());
            } else {
                // 다른 문자는 변환해서 추가
                stringBuilder.append(mapCommandToChar(command.getType()));
            }
        }

        return stringBuilder.toString();
    }

    public static char mapCommandToChar(Command command) {
        switch (command) {
            case ADD: return '+';
            case AND: return '&';
            case CLOSE_PARENTHESES: return ')';
            case DECIMAL_POINT: return '.';
            case DIVIDE: return '/';
            case MODULAR: return '%';
            case MULTIPLY: return '*';
            case NOT: return '~';
            case NULL: return ' ';
            case NUMBER_0: return '0';
            case NUMBER_1: return '1';
            case NUMBER_2: return '2';
            case NUMBER_3: return '3';
            case NUMBER_4: return '4';
            case NUMBER_5: return '5';
            case NUMBER_6: return '6';
            case NUMBER_7: return '7';
            case NUMBER_8: return '8';
            case NUMBER_9: return '9';
            case OPEN_PARENTHESES: return '(';
            case OR: return '|';
            case SUBTRACT: return '-';
            case XOR: return '^';
        }

        throw new UnsupportedOperationException(command.toString());
    }
}
