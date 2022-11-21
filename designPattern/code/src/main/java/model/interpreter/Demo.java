package model.interpreter;

import lombok.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Stack;

/**
 * @author danghf
 * @version 1.0
 * @data 2022/11/21 11:16
 * @since 1.8
 * 解释器模式
 */
public class Demo {
    public static void main(String[] args) throws Exception{
        // a+b
        String expStr = getExpStr();
        // var {a=10, b=20}
        HashMap<String, Integer> var = getValue(expStr);
        Calculator calculator = new Calculator(expStr);
        System.out.println("运算结果：" + expStr + "=" + calculator.run(var));
    }
    /**
     * 获取表达式
     */
    public static String getExpStr() throws IOException {
        System.out.print("请输入表达式：");
        return (new BufferedReader(new InputStreamReader(System.in))).readLine();
    }

    /**
     *  获得值映射
     * @param expStr 表达式
     * @return map
     * @throws IOException io
     */
    public static HashMap<String, Integer> getValue(String expStr) throws IOException {
        HashMap<String, Integer> map = new HashMap<>();
        for (char ch : expStr.toCharArray()) {
            if (ch != '+' && ch != '-') {
                if (!map.containsKey(String.valueOf(ch))) {
                    System.out.print("请输入" + ch + "的值：");
                    String in = (new BufferedReader(new InputStreamReader(System.in))).readLine();
                    map.put(String.valueOf(ch), Integer.valueOf(in));
                }
            }
        }
        return map;
    }
}

/**
 * 计算器
 */
class Calculator {
    private AbstractExpression expression;

    public Calculator(String exp) {
        Stack<AbstractExpression> stack = new Stack<>();
        char[] chars = exp.toCharArray();
        // 左右表达式
        AbstractExpression left = null;
        AbstractExpression right = null;
        for (int i = 0; i < chars.length; i++) {
            switch (chars[i]) {
                case '+':
                    // 拿到左边
                    left = stack.pop();
                    char aChar = chars[++i];
                    right = new TerminalExpression(aChar + "");
                    stack.push(new AddExpression(left, right));
                    break;
                case '-':
                    left = stack.pop();
                    char c1 = chars[++i];
                    right = new TerminalExpression(c1 + "");
                    stack.push(new SubExpression(left, right));
                    break;
                default:
                    // 数字直接入栈
                    stack.push(new TerminalExpression(chars[i] + ""));
                    break;
            }
        }
        this.expression = stack.pop();
    }

    public Integer run(HashMap<String,Integer> map) {
        // 类似递归计算
        return this.expression.interpreter(map);
    }
}

/**
 * 抽象表达式
 * 声明一个抽象的解释操作
 * 这个操作为抽象语法树中所有的结点共享
 */
abstract class AbstractExpression {
    /**
     * 解释器
     * ex: a + b + c
     * @param map {a=1.b=1,c=1}
     * @return int
     */
    protected abstract int interpreter(HashMap<String, Integer> map);
}

/**
 * 终结符表达式
 * 实现与文法中的终结符相关的解释操作
 */
class TerminalExpression extends AbstractExpression {
    /**
     * 形参 a,b,c
     */
    private String key;

    public TerminalExpression(String key) {
        this.key = key;
    }

    @Override
    protected int interpreter(HashMap<String, Integer> map) {
        // 根据 形参 获取值
        return map.get(key);
    }
}

/**
 * 非终结符表达式
 * 文法中的非终结符实现解释操作
 * 符号解析器，每个符号只与自己左右两边数字有关
 * 左右两边可能是解析器，也可能是数字
 */
@Data
abstract class NonTerminalExpression extends AbstractExpression {
    private AbstractExpression left;
    private AbstractExpression right;

    public NonTerminalExpression(AbstractExpression left, AbstractExpression right) {
        this.left = left;
        this.right = right;
    }

    /**
     * 因为 NonTerminalExpression让其子类具体实现 interpreter
     * 这里不实现
     */
}

/**
 * 加法解释器
 */
class AddExpression extends NonTerminalExpression {
    public AddExpression(AbstractExpression left, AbstractExpression right) {
        super(left, right);
    }

    @Override
    protected int interpreter(HashMap<String, Integer> map) {
        int interpreter = getLeft().interpreter(map);
        int interpreter1 = getRight().interpreter(map);
        return interpreter + interpreter1;
    }
}

class SubExpression extends NonTerminalExpression {
    public SubExpression(AbstractExpression left, AbstractExpression right) {
        super(left, right);
    }

    @Override
    protected int interpreter(HashMap<String, Integer> map) {
        return getLeft().interpreter(map) - getRight().interpreter(map);
    }
}