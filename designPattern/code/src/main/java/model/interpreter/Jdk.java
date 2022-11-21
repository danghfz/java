package model.interpreter;

import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * @author danghf
 * @version 1.0
 * @data 2022/11/21 12:04
 * @since 1.8
 * 解释器模式 在 java源码中的使用
 */
public class Jdk {
    public static void main(String[] args) {
        SpelExpressionParser spelExpressionParser = new SpelExpressionParser();
        Expression expression = spelExpressionParser.parseExpression("2*(9+6/3-5)+2^2");
        Object value = expression.getValue();
        System.out.println(value);
    }
}
