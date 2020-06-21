package sunghwan2789.calculator.core;

import java.math.BigDecimal;

/** 숫자를 입력하는 클래스
 *
 * 부호와 숫자, 소수점 구분자로 이루어진 문자열을 다룬다.
 */
public class NumberInput {
    private char decimalSymbol;
    private NumberString numberString;
    private boolean hasDecimalPoint;
    private int decimalPointIndex;

    /** 최대 처리할 정수부분 자리수 */
    public static final int MAX_DIGITS = 50;

    public NumberInput() {
        this('.');
    }

    public NumberInput(char decimalSymbol) {
        this.decimalSymbol = decimalSymbol;
        numberString = new NumberString();
        hasDecimalPoint = false;
        decimalPointIndex = 0;
    }

    /** 입력을 초기화한다. */
    public void clear() {
        numberString.clear();
        hasDecimalPoint = false;
        decimalPointIndex = 0;
    }

    /** 소수점 구분자를 가지는 문자열인지 판단한다. */
    public boolean hasDecimalPoint() {
        return hasDecimalPoint;
    }

    /** 부호를 반전한다. */
    public boolean toggleSign() {
        // 0을 항상 양수로 처리
        if (numberString.isEmpty()) {
            numberString.setNegative(false);
        } else {
            numberString.setNegative(!numberString.isNegative());
        }
        return true;
    }

    /** 십진수 숫자 하나를 추가한다. */
    public boolean addDigit(int digit) {
        int maxDigits = MAX_DIGITS;
        char digitCharacter = (char) ('0' + digit);

        // 앞자리 0은 무시
        if (numberString.isEmpty() && (digit == 0)) {
            return true;
        }

        // 소수부 연산은 처리에 제한이 없음
        if (hasDecimalPoint) {
            numberString.add(digitCharacter);
            return true;
        }

        // 허용 자리수까지의 숫자만 추가 허용
        if (numberString.length() < maxDigits) {
            numberString.add(digitCharacter);
            return true;
        }

        return false;
    }

    /** 소수점 구분자를 추가한다. */
    public boolean addDecimalPoint() {
        // 이미 소수점 구분자가 있으면 처리 불가
        if (hasDecimalPoint) {
            return false;
        }

        // 앞자리 0 추가
        if (numberString.isEmpty()) {
            numberString.add('0');
        }

        // 소숫점 구분자 위치를 기억하고서 추가
        decimalPointIndex = numberString.length();
        numberString.add(decimalSymbol);
        hasDecimalPoint = true;

        return true;
    }

    /** 숫자부에서 한 글자를 제거한다. */
    public void remove() {
        // 숫자부 한 글자 제거
        if (!numberString.isEmpty()) {
            numberString.remove();
            // 소수점 구분자 입력 시에 추가한 0 제거
            if (numberString.getDigits().equals("0")) {
                numberString.remove();
            }
        }

        // 소수점 구분자 관련 멤버 갱신
        if (numberString.length() <= decimalPointIndex) {
            hasDecimalPoint = false;
            decimalPointIndex = 0;
        }

        // 숫자부가 비었으면 부호 등 또한 초기화
        if (numberString.isEmpty()) {
            numberString.clear();
        }
    }

    /** 입력이 비었는지를 판단한다. */
    public boolean isEmpty() {
        return numberString.isEmpty() && !hasDecimalPoint;
    }

    /** 입력한 숫자를 문자열로 변환한다. */
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        // 부호 추가
        if (numberString.isNegative()) {
            stringBuilder.append('-');
        }

        // 숫자부가 비었으면 0 출력 (여기서, -0은 없음)
        if (numberString.isEmpty()) {
            stringBuilder.append('0');
        } else {
            stringBuilder.append(numberString.getDigits());
        }

        return stringBuilder.toString();
    }

    /** 입력한 숫자를 계산 가능한 형태로 변환한다. */
    public BigDecimal toBigDecimal() {
        return new BigDecimal(toString());
    }
}
