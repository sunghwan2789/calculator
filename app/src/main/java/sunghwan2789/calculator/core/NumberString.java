package sunghwan2789.calculator.core;

import java.util.LinkedList;

/** 숫자 문자열 클래스
 *
 * 부호와 숫자로 이루어지는 문자열을 다룬다.
 */
public class NumberString {
    /** 숫자를 다루는 리스트
     *
     * 높은 자리 숫자를 0번째 인덱스부터 채워나간다.
     */
    private LinkedList<Character> digits;

    /** 부호를 다루는 부울
     *
     * 값이 true 이면 음수이고 값이 false 이면 양수를 나타낸다.
     */
    private boolean isNegative;

    public NumberString() {
        digits = new LinkedList<>();
        isNegative = false;
    }

    /** 입력한 값을 초기화한다. */
    public void clear() {
        digits.clear();
        isNegative = false;
    }

    /** 숫자부를 문자열로 받는다. */
    public String getDigits() {
        StringBuilder sb = new StringBuilder();
        for (char digit : digits) {
            sb.append(digit);
        }
        return sb.toString();
    }

    /** 부호를 받는다.
     *
     * 값이 true 이면 음수이고 값이 false 이면 양수를 나타낸다.
     */
    public boolean isNegative() {
        return isNegative;
    }

    /** 부호를 설정한다. */
    public void setNegative(boolean isNegative) {
        this.isNegative = isNegative;
    }

    /** 숫자부가 비었는지 확인한다. */
    public boolean isEmpty() {
        return digits.isEmpty();
    }

    /** 숫자부에 숫자를 추가한다. */
    public void add(char digit) {
        digits.add(digit);
    }

    /** 숫자부에서 숫자를 제거한다. */
    public void remove() {
        digits.removeLast();
    }

    /** 숫자부의 길이를 가져온다. */
    public int length() {
        return digits.size();
    }
}
