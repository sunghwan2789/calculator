package sunghwan2789.calculator.core;

import java.math.BigDecimal;
import java.util.Stack;

/**
 * 계산식의 연산자, 피연산자의 추상 클래스
 *
 * - getType(): 원본 명령 확인
 * - execute(): 연산 수행
 * - compareTo(): 연산자 우선순위, 결합법칙 정의
 * - toString(): 미리보기 좋은 문자열 출력
 *
 * 연산자의 우선순위와 결합법칙은 C 언어를 참고합니다. https://en.cppreference.com/w/c/language/operator_precedence
 * 1. Parentheses: 괄호, 우측 결합법칙
 * 2. Unary, Bitwise(NOT): 단항 증감 연산자와 비트 연산자 NOT, 좌측 결합법칙
 * 3. Binary(*, /, %): 사칙연산 일부, 우측 결합법칙
 * 4. Binary(+, -): 사칙연산 일부, 우측 결합법칙
 * 5. Bitwise: 비트 연산자 AND, XOR, OR 순, 우측 결합법칙
 *
 * @see CalculatorManager
 */
public abstract class ExpressionCommand implements Comparable<ExpressionCommand> {
    private Command type;

    public ExpressionCommand(Command type) {
        this.type = type;
    }

    /** 원본 명령을 확인한다. */
    public Command getType() {
        return type;
    }

    /**
     * 연산을 수행하고 그 결과를 반환한다.
     *
     * @param operandStack 피연산자 스택
     */
    public abstract BigDecimal execute(Stack<BigDecimal> operandStack);
}