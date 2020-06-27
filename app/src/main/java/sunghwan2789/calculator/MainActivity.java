package sunghwan2789.calculator;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.Queue;

import sunghwan2789.calculator.core.CalculatorManager;
import sunghwan2789.calculator.core.Command;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, CalculatorManager.CalculatorExecuteHandler {
    CalculatorManager calculatorManager;

    TextView expressionTextView;
    TextView resultTextView;

    Queue<String> inputQueue;
    ImageButton importButton;
    ImageButton importNextButton;
    ImageButton exportButton;
    ImageButton exportStopButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculatorManager = new CalculatorManager();
        calculatorManager.setExecuteHandler(this);

        expressionTextView = findViewById(R.id.expressionTextView);
        resultTextView = findViewById(R.id.resultTextView);

        inputQueue = new LinkedList<>();

        findViewById(R.id.historyButton);
        importButton = findViewById(R.id.importButton);
        importButton.setOnClickListener(this);
        importNextButton = findViewById(R.id.importNextButton);
        importNextButton.setOnClickListener(this);
        exportButton = findViewById(R.id.exportButton);
        exportButton.setOnClickListener(this);
        exportStopButton = findViewById(R.id.exportStopButton);
        exportStopButton.setOnClickListener(this);

        findViewById(R.id.removeButton).setOnClickListener(this);
        findViewById(R.id.andButton).setOnClickListener(this);
        findViewById(R.id.orButton).setOnClickListener(this);
        findViewById(R.id.notButton).setOnClickListener(this);
        findViewById(R.id.xorButton).setOnClickListener(this);
        findViewById(R.id.toggleSignButton).setOnClickListener(this);
        findViewById(R.id.openParenthesesButton).setOnClickListener(this);
        findViewById(R.id.closeParenthesesButton).setOnClickListener(this);
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

        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
        }, MODE_PRIVATE);
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
                calculatorManager.add(Command.OPEN_PARENTHESES);
                return true;
            case ')':
                calculatorManager.add(Command.CLOSE_PARENTHESES);
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
                return false;
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
        if (handleCharacterCommand((char) event.getUnicodeChar())) {
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
            case R.id.openParenthesesButton:
                calculatorManager.add(Command.OPEN_PARENTHESES);
                return true;
            case R.id.closeParenthesesButton:
                calculatorManager.add(Command.CLOSE_PARENTHESES);
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
                return false;
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

    private static final int REQUEST_READ_FILE_URI = 1;
    private static final int REQUEST_WRITE_FILE_URI = 2;

    FileWriter outputWriter;

    @Override
    public void onClick(View v) {
        if (handleButtonCommand(v.getId())) {
            previewCalculation();
            return;
        }

        switch (v.getId()) {
            case R.id.importButton:
                Intent intent = new Intent()
                        .setType("*/*")
                        .setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "계산식 파일 선택"), REQUEST_READ_FILE_URI);
                break;
            case R.id.exportButton:
                // 계산식과 결과를 내보낼 파일을 지정
                final EditText outputFilenameEditText = new EditText(this);
                outputFilenameEditText.setText(".txt");

                // 파일 이름을 입력하게 하고
                // 이후 계산을 진행할 때마다 해당 파일에 결과 저장
                new AlertDialog.Builder(this)
                        .setTitle("저장할 파일 이름")
                        .setView(outputFilenameEditText)
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Uri output = Uri.parse(Environment.getExternalStorageDirectory().getAbsolutePath())
                                        .buildUpon()
                                        .appendPath(Environment.DIRECTORY_DOWNLOADS)
                                        .appendPath(outputFilenameEditText.getText().toString())
                                        .build();

                                MainActivity.this.onActivityResult(
                                        REQUEST_WRITE_FILE_URI,
                                        RESULT_OK,
                                        new Intent().setData(output));
                            }
                        })
                        .setNegativeButton("취소", null)
                        .show();
                break;
            case R.id.importNextButton:
                // 큐에 있는 계산식을 꺼내서 계산기에 입력
                if (!inputQueue.isEmpty()) {
                    String input = inputQueue.remove();

                    calculatorManager.clear();
                    for (char i : input.toCharArray()) {
                        handleCharacterCommand(i);
                    }
                    calculatorManager.add(Command.EQUAL);
                }
                // 모든 계산식을 처리했으면 불러오기 다시 보이기
                // 아니라면 재생 버튼 보이기
                updateImportStatus();
                break;
            case R.id.exportStopButton:
                // 파일을 닫고 가져오기 종료
                if (outputWriter != null) {
                    try {
                        outputWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    outputWriter = null;
                }
                updateExportStatus();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // 계산식 파일 선택
        if (requestCode == REQUEST_READ_FILE_URI && resultCode == RESULT_OK) {
            Uri selectedFileContentUri = data.getData();
            Log.i("READ", selectedFileContentUri.toString());

            try {
                // 계산식 파일을 읽어서 계삭식 큐에 삽입
                InputStream inputStream = getContentResolver().openInputStream(selectedFileContentUri);
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                String line = null;
                while ((line = reader.readLine()) != null) {
                    inputQueue.add(line.split("=")[0]);
                    Log.i("INPUT_QUEUE", inputQueue.peek());
                }

                reader.close();

                Toast.makeText(this, "계산식 " + inputQueue.size() + "개를 불러왔어요!", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "파일을 열 수 없어요: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            } finally {
                updateImportStatus();
            }
        } else if (requestCode == REQUEST_WRITE_FILE_URI && resultCode == RESULT_OK) {
            // 선택한 파일을 생성하고 이미 있으면 덮어쓰면서 출력 스트림을 연다
            String selectedFile = data.getData().toString();
            Log.i("WRITE", selectedFile);
            try {
                File output = new File(selectedFile);
                output.createNewFile();
                outputWriter = new FileWriter(output, false);

                Toast.makeText(this, "기록을 시작합니다!", Toast.LENGTH_SHORT).show();
            } catch (IOException ex) {
                ex.printStackTrace();
                Toast.makeText(this, "파일을 만들 수 없어요: " + ex.getMessage(), Toast.LENGTH_SHORT).show();
            } finally {
                updateExportStatus();
            }
        }
    }

    public void updateImportStatus() {
        if (inputQueue.isEmpty()) {
            importButton.setVisibility(View.VISIBLE);
            importNextButton.setVisibility(View.GONE);
        } else {
            importButton.setVisibility(View.GONE);
            importNextButton.setVisibility(View.VISIBLE);
        }
    }

    public void updateExportStatus() {
        if (outputWriter == null) {
            exportButton.setVisibility(View.VISIBLE);
            exportStopButton.setVisibility(View.GONE);
        } else {
            exportButton.setVisibility(View.GONE);
            exportStopButton.setVisibility(View.VISIBLE);
        }
    }

    public void previewCalculation() {
        expressionTextView.setText(calculatorManager.toString());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            resultTextView.setTransitionAlpha(0.6f);
        }
        try {
            resultTextView.setText(calculatorManager.execute().toPlainString());
        } catch (Exception ex) {
            ex.printStackTrace();
            resultTextView.setText("오류");
        }
    }

    @Override
    public void onCalculatorExecute(BigDecimal result) {
        if (outputWriter != null) {
            appendToOutput(calculatorManager.serialize() + " = " + result.toPlainString());
        }

        Log.i("EXECUTED", calculatorManager.toString());
        Log.i("EXECUTED", result.toPlainString());
        expressionTextView.setText(calculatorManager.toString());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            resultTextView.setTransitionAlpha(1f);
        }
        try {
            resultTextView.setText(result.toPlainString());
        } catch (Exception ex) {
            ex.printStackTrace();
            resultTextView.setText("오류");
        }
    }

    private void appendToOutput(String expression) {
        try {
            outputWriter.append(expression + "\n");
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "파일 기록 실패!" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
