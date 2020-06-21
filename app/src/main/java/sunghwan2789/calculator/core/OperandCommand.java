package sunghwan2789.calculator.core;

public class OperandCommand implements ExpressionCommand {
    public NumberInput numberInput;

    public OperandCommand() {
        numberInput = new NumberInput();
    }
}
