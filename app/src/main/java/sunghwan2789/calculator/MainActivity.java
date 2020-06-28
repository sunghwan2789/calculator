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
import android.widget.LinearLayout;
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
    /** 계산기 입출력을 관리하는 변수 */
    CalculatorManager calculatorManager;

    /** 계산식을 보여주는 뷰 */
    TextView expressionTextView;
    /** 계산 결과를 보여주는 뷰 */
    TextView resultTextView;

    // 파일에서 불러오기 기능
    Queue<String> inputQueue;
    ImageButton importButton;
    ImageButton importNextButton;

    // 파일로 내보내기 기능
    FileWriter outputWriter;
    ImageButton exportButton;
    ImageButton exportStopButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 계산기 결과를 받을 수 있도록 핸들러 연결
        calculatorManager = new CalculatorManager();
        calculatorManager.setExecuteHandler(this);

        expressionTextView = findViewById(R.id.expressionTextView);
        resultTextView = findViewById(R.id.resultTextView);

        inputQueue = new LinkedList<>();

        // EditText 를 사용하지 않는 특성에 따라 historyButton 가 포커스되는 것을 방지하고
        // 스페이스와 엔터 키 입력 시 historyButton 을 클릭하는 것을 방지함
        LinearLayout baseLayout = findViewById(R.id.baseLayout);
        baseLayout.setFocusableInTouchMode(true);
        baseLayout.requestFocus();

        // 기능 버튼
        findViewById(R.id.historyButton);
        importButton = findViewById(R.id.importButton);
        importButton.setOnClickListener(this);
        importNextButton = findViewById(R.id.importNextButton);
        importNextButton.setOnClickListener(this);
        exportButton = findViewById(R.id.exportButton);
        exportButton.setOnClickListener(this);
        exportStopButton = findViewById(R.id.exportStopButton);
        exportStopButton.setOnClickListener(this);

        // 계산기 버튼
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

        // 파일 입출력을 위해 권한 요청
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
        }, MODE_PRIVATE);
    }

    /** 계산기로 처리 가능한 문자를 처리하고, 계산식과 결과 미리보기 가능한 것은 true 처리 */
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
                // 계산 결과는 별도의 핸들러에서 확인하므로 미리보지 않기 위해서 false 처리
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
        // 문자로 입력 가능한 계산기로 처리 가능한 명령을 처리하고, 계산식과 결과 미리보기
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

    /** 계산기로 처리 가능한 버튼을 처리하고, 계산식과 결과 미리보기 가능한 것은 true 처리 */
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
                // 계산 결과는 별도의 핸들러에서 확인하므로 미리보지 않기 위해 false 처리
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

    @Override
    public void onClick(View v) {
        // 계산기로 처리 가능한 명령을 처리하고, 계산식과 결과 미리보기
        if (handleButtonCommand(v.getId())) {
            previewCalculation();
            return;
        }

        switch (v.getId()) {
            case R.id.importButton:
                requestInputFileSelection();
                break;
            case R.id.exportButton:
                requestOutputFileSelection();
                break;
            case R.id.importNextButton:
                consumeInputFromInputQueue();
                updateImportStatus();
                break;
            case R.id.exportStopButton:
                stopExport();
                updateExportStatus();
                break;
        }
    }

    private void stopExport() {
        if (outputWriter == null) throw new AssertionError();

        // 파일을 저장하고 내보내기 종료
        try {
            outputWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        outputWriter = null;
    }

    private void consumeInputFromInputQueue() {
        if (inputQueue.isEmpty()) throw new AssertionError();

        // 큐에 있는 계산식을 꺼내서 계산기에 입력
        String input = inputQueue.remove();

        calculatorManager.clear();
        for (char i : input.toCharArray()) {
            handleCharacterCommand(i);
        }
        calculatorManager.add(Command.EQUAL);
    }

    private void requestOutputFileSelection() {
        // 계산식과 결과를 내보낼 파일을 지정, 기본 확장자로 .txt 추가
        final EditText outputFilenameEditText = new EditText(this);
        outputFilenameEditText.setText(".txt");

        final DialogInterface.OnClickListener responseOutputFilenameToActivityResult = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 다운로드 폴더 아래에 입력한 파일명 추가해서 ActivityResult 에 전송
                Uri output = Uri.parse(Environment.getExternalStorageDirectory().getAbsolutePath())
                        .buildUpon()
                        .appendPath(Environment.DIRECTORY_DOWNLOADS)
                        .appendPath(outputFilenameEditText.getText().toString())
                        .build();

                MainActivity.this.onActivityResult(REQUEST_WRITE_FILE_URI, RESULT_OK, new Intent().setData(output));
            }
        };

        // 파일 이름을 입력하게 하고
        // 이후 계산을 진행할 때마다 해당 파일에 결과 저장
        new AlertDialog.Builder(this)
                .setTitle("저장할 파일 이름")
                .setView(outputFilenameEditText)
                .setPositiveButton("확인", responseOutputFilenameToActivityResult)
                .setNegativeButton("취소", null)
                .show();
    }

    private void requestInputFileSelection() {
        // Content Uri 를 받아서 파일 내용만 읽을 수 있게 한다.
        Intent intent = new Intent()
                .setType("*/*")
                .setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "계산식 파일 선택"), REQUEST_READ_FILE_URI);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_READ_FILE_URI && resultCode == RESULT_OK) {
            // 계산식 파일 선택
            Uri selectedFileContentUri = data.getData();
            Log.i("READ", selectedFileContentUri.toString());

            enqueueInputFromFile(selectedFileContentUri);
            updateImportStatus();
        } else if (requestCode == REQUEST_WRITE_FILE_URI && resultCode == RESULT_OK) {
            // 선택한 파일을 생성하고 이미 있으면 덮어쓰면서 출력 스트림을 연다
            String selectedFile = data.getDataString();
            Log.i("WRITE", selectedFile);

            startExport(selectedFile);
            updateExportStatus();
        }
    }

    private void startExport(String path) {
        try {
            // 선택한 파일을 생성하고 이미 있으면 덮어쓰면서 출력 스트림을 연다
            File output = new File(path);
            output.createNewFile();
            outputWriter = new FileWriter(output, false);

            Toast.makeText(this, "기록을 시작합니다!", Toast.LENGTH_SHORT).show();
        } catch (IOException ex) {
            ex.printStackTrace();
            Toast.makeText(this, "파일을 만들 수 없어요: " + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void enqueueInputFromFile(Uri fileContentUri) {
        try {
            // 계산식 파일을 읽어서 계삭식 큐에 삽입
            InputStream inputStream = getContentResolver().openInputStream(fileContentUri);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            // 줄 단위로 읽되, 계산 결과는 제거
            String line = null;
            while ((line = reader.readLine()) != null) {
                inputQueue.add(line.split("=")[0]);
                Log.i("INPUT_QUEUE", inputQueue.peek());
            }

            // 계산식 큐에 삽입 완료
            reader.close();
            Toast.makeText(this, "계산식 " + inputQueue.size() + "개를 불러왔어요!", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "파일을 열 수 없어요: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void updateImportStatus() {
        if (inputQueue.isEmpty()) {
            // 큐에 계산식이 없으면 불러오기 버튼 활성화
            importButton.setVisibility(View.VISIBLE);
            importNextButton.setVisibility(View.GONE);
        } else {
            // 큐에 계산식이 있으면 다음 식 보기 버튼 활성화
            importButton.setVisibility(View.GONE);
            importNextButton.setVisibility(View.VISIBLE);
        }
    }

    public void updateExportStatus() {
        if (outputWriter == null) {
            // 파일 출력 설정이 꺼저있으면 내보내기 버튼 활성화
            exportButton.setVisibility(View.VISIBLE);
            exportStopButton.setVisibility(View.GONE);
        } else {
            // 파일 출력 설정이 켜져있으면 파일 출력 중지 버튼 활성화
            exportButton.setVisibility(View.GONE);
            exportStopButton.setVisibility(View.VISIBLE);
        }
    }

    public void previewCalculation() {
        // 지금까지 입력한 계산식 표시
        expressionTextView.setText(calculatorManager.toString());

        try {
            // 결과 뷰에 임시 결과 계산값 표시
            setCalculationResult(calculatorManager.execute().toPlainString(), true);
        } catch (Exception ex) {
            // 계산식에 오류가 있으면 에러 표시
            ex.printStackTrace();
            setCalculationResult("#ERROR", true);
        }
    }

    public void setCalculationResult(String result, boolean isPreview) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            // 임시 결과임을 나타내기 위해 결과 뷰 투명도 설정
            resultTextView.setTransitionAlpha(isPreview ? 0.6f : 1f);
        }
        resultTextView.setText(result);
    }

    @Override
    public void onCalculatorExecute(BigDecimal result) {
        if (outputWriter != null) {
            // 파일 출력이 켜져 있으면 재실행 가능한 문자와 결과를 저장
            appendToOutput(calculatorManager.serialize() + " = " + result.toPlainString());
        }

        // 계산식 뷰에 계산식 표시
        Log.i("EXECUTED", calculatorManager.toString());
        Log.i("EXECUTED", result.toPlainString());
        expressionTextView.setText(calculatorManager.toString());

        // 출력 뷰에 계산 결과 표시
        setCalculationResult(result.toPlainString(), false);
    }

    @Override
    public void onCalculatorError(Exception e) {
        if (outputWriter != null) {
            // 파일 출력이 켜져 있으면 재실행 가능한 문자와 결과를 저장
            appendToOutput(calculatorManager.serialize() + " = #ERROR : " + e.getMessage());
        }

        // 계산식 뷰에 계산식 표시
        Log.i("EXECUTED", calculatorManager.toString());
        e.printStackTrace();
        expressionTextView.setText(calculatorManager.toString());

        // 출력 뷰에 계산 결과 표시
        setCalculationResult("#ERROR", false);
    }

    private void appendToOutput(String expression) {
        assert outputWriter != null;

        try {
            // 파일 출력에 새 계산식과 결과를 추가
            outputWriter.append(expression).append("\n");
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "파일 기록 실패!" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
