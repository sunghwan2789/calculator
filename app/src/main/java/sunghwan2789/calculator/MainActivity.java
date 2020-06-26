package sunghwan2789.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import sunghwan2789.calculator.core.CalculatorManager;
import sunghwan2789.calculator.core.Command;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    CalculatorManager calculatorManager;

    TextView expressionTextView;
    TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculatorManager = new CalculatorManager();

        expressionTextView = findViewById(R.id.expressionTextView);
        resultTextView = findViewById(R.id.resultTextView);

        findViewById(R.id.historyButton);
        findViewById(R.id.importButton);
        findViewById(R.id.exportButton);
        findViewById(R.id.removeButton).setOnClickListener(this);
        findViewById(R.id.andButton).setOnClickListener(this);
        findViewById(R.id.orButton).setOnClickListener(this);
        findViewById(R.id.notButton).setOnClickListener(this);
        findViewById(R.id.xorButton).setOnClickListener(this);
        findViewById(R.id.toggleSignButton).setOnClickListener(this);
        findViewById(R.id.openParenthesisButton).setOnClickListener(this);
        findViewById(R.id.closeParenthesisButton).setOnClickListener(this);
        findViewById(R.id.modularButton).setOnClickListener(this);
        findViewById(R.id.divideButton).setOnClickListener(this);
        findViewById(R.id.number7Button).setOnClickListener(this);
        findViewById(R.id.number8Button).setOnClickListener(this);
        findViewById(R.id.number9Button).setOnClickListener(this);
        findViewById(R.id.multiplyButton).setOnClickListener(this);
        findViewById(R.id.number4Button).setOnClickListener(this);
        findViewById(R.id.number5Button).setOnClickListener(this);
        findViewById(R.id.number6Button).setOnClickListener(this);
        findViewById(R.id.subtractButton).setOnClickListener(this);
        findViewById(R.id.number1Button).setOnClickListener(this);
        findViewById(R.id.number2Button).setOnClickListener(this);
        findViewById(R.id.number3Button).setOnClickListener(this);
        findViewById(R.id.addButton).setOnClickListener(this);
        findViewById(R.id.number0Button).setOnClickListener(this);
        findViewById(R.id.decimalPointButton).setOnClickListener(this);
        findViewById(R.id.equalButton).setOnClickListener(this);
    }

    public boolean handleCharacterCommand(char character) {
        switch (character) {
            case '0':
                calculatorManager.add(Command.NUMBER_0);
                return true;
            case '1':
                calculatorManager.add(Command.NUMBER_1);
                return true;
            case '2':
                calculatorManager.add(Command.NUMBER_2);
                return true;
            case '3':
                calculatorManager.add(Command.NUMBER_3);
                return true;
            case '4':
                calculatorManager.add(Command.NUMBER_4);
                return true;
            case '5':
                calculatorManager.add(Command.NUMBER_5);
                return true;
            case '6':
                calculatorManager.add(Command.NUMBER_6);
                return true;
            case '7':
                calculatorManager.add(Command.NUMBER_7);
                return true;
            case '8':
                calculatorManager.add(Command.NUMBER_8);
                return true;
            case '9':
                calculatorManager.add(Command.NUMBER_9);
                return true;
            case '.':
                calculatorManager.add(Command.DECIMAL_POINT);
                return true;
            case '\\':
                calculatorManager.add(Command.TOGGLE_SIGN);
                return true;
            case '(':
                calculatorManager.add(Command.OPEN_PARENTHESIS);
                return true;
            case ')':
                calculatorManager.add(Command.CLOSE_PARENTHESIS);
                return true;
            case '+':
                calculatorManager.add(Command.ADD);
                return true;
            case '-':
                calculatorManager.add(Command.SUBTRACT);
                return true;
            case '*':
                calculatorManager.add(Command.MULTIPLY);
                return true;
            case '/':
                calculatorManager.add(Command.DIVIDE);
                return true;
            case '%':
                calculatorManager.add(Command.MODULAR);
                return true;
            case '=':
            case '\n':
                calculatorManager.add(Command.EQUAL);
                return true;
            case '&':
                calculatorManager.add(Command.AND);
                return true;
            case '|':
                calculatorManager.add(Command.OR);
                return true;
            case '~':
                calculatorManager.add(Command.NOT);
                return true;
            case '^':
                calculatorManager.add(Command.XOR);
                return true;
        }
        return false;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        // 문자로 입력 가능한 키 처리
        if (handleCharacterCommand((char)event.getUnicodeChar())) {
            previewCalculation();
            return true;
        }
        // 백스페이스 키 처리
        if (keyCode == KeyEvent.KEYCODE_DEL) {
            calculatorManager.add(Command.REMOVE);
            previewCalculation();
            return true;
        }
        // 그 외 기본치 처리
        return super.onKeyUp(keyCode, event);
    }

    public boolean handleButtonCommand(int buttonId) {
        switch (buttonId) {
            case R.id.number0Button:
                calculatorManager.add(Command.NUMBER_0);
                return true;
            case R.id.number1Button:
                calculatorManager.add(Command.NUMBER_1);
                return true;
            case R.id.number2Button:
                calculatorManager.add(Command.NUMBER_2);
                return true;
            case R.id.number3Button:
                calculatorManager.add(Command.NUMBER_3);
                return true;
            case R.id.number4Button:
                calculatorManager.add(Command.NUMBER_4);
                return true;
            case R.id.number5Button:
                calculatorManager.add(Command.NUMBER_5);
                return true;
            case R.id.number6Button:
                calculatorManager.add(Command.NUMBER_6);
                return true;
            case R.id.number7Button:
                calculatorManager.add(Command.NUMBER_7);
                return true;
            case R.id.number8Button:
                calculatorManager.add(Command.NUMBER_8);
                return true;
            case R.id.number9Button:
                calculatorManager.add(Command.NUMBER_9);
                return true;
            case R.id.decimalPointButton:
                calculatorManager.add(Command.DECIMAL_POINT);
                return true;
            case R.id.toggleSignButton:
                calculatorManager.add(Command.TOGGLE_SIGN);
                return true;
            case R.id.openParenthesisButton:
                calculatorManager.add(Command.OPEN_PARENTHESIS);
                return true;
            case R.id.closeParenthesisButton:
                calculatorManager.add(Command.CLOSE_PARENTHESIS);
                return true;
            case R.id.addButton:
                calculatorManager.add(Command.ADD);
                return true;
            case R.id.subtractButton:
                calculatorManager.add(Command.SUBTRACT);
                return true;
            case R.id.multiplyButton:
                calculatorManager.add(Command.MULTIPLY);
                return true;
            case R.id.divideButton:
                calculatorManager.add(Command.DIVIDE);
                return true;
            case R.id.modularButton:
                calculatorManager.add(Command.MODULAR);
                return true;
            case R.id.equalButton:
                calculatorManager.add(Command.EQUAL);
                return true;
            case R.id.andButton:
                calculatorManager.add(Command.AND);
                return true;
            case R.id.orButton:
                calculatorManager.add(Command.OR);
                return true;
            case R.id.notButton:
                calculatorManager.add(Command.NOT);
                return true;
            case R.id.xorButton:
                calculatorManager.add(Command.XOR);
                return true;
            case R.id.removeButton:
                calculatorManager.add(Command.REMOVE);
                return true;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        if (handleButtonCommand(v.getId())) {
            previewCalculation();
            return;
        }
    }

    public void previewCalculation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            resultTextView.setTransitionAlpha(0.6f);
        }
        try {
            resultTextView.setText(calculatorManager.execute().toPlainString());
        } catch (Exception ex) {
            resultTextView.setText("오류");
        }
    }

    public void showCalculation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            resultTextView.setTransitionAlpha(1f);
        }
        try {
            resultTextView.setText(calculatorManager.execute().toPlainString());
        } catch (Exception ex) {
            resultTextView.setText("오류");
        }
    }
}
