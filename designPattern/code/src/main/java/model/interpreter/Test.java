package model.interpreter;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

/**
 * @author danghf
 * @version 1.0
 * @since 1.8
 * @data 2022/11/21 10:01
 * 后缀表达式
 */
public class Test{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("expression: ");
        while (scanner.hasNextLine()){
            String str = scanner.nextLine();
            String extracted = extracted(str);
            System.out.println(c(extracted));
            System.out.print("expression: ");
        }
    }

    /**
     * 计算后缀表达式
     * @param expression 后缀表达式
     * @return 结果
     */
    public static Object c(String expression){
        // 数栈
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (isNum(c)){
                stack.add(c - '0');
            }else {
                // 符号
                Integer pop = stack.pop();
                Integer pop1 = stack.pop();
                if (c == '+'){
                    stack.add(pop1 + pop);
                }else if (c == '-'){
                    stack.add(pop1 -pop);
                }else if (c == '*'){
                    stack.add(pop1 * pop);
                }else if (c == '/'){
                    stack.add(pop1 / pop);
                }else {
                    throw new RuntimeException();
                }
            }
        }
        return stack.pop();
    }
    private static String extracted(String expr) {
        // 中缀 -> 后缀
        // 表达式栈 (数栈)
        Stack<Character> expression = new Stack<>();
        // 符号栈
        Stack<Character> symbol = new Stack<>();
        // 去空格
        expr.trim();
        // 遍历
        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);
            if (isNum(c)){
                // 放入数栈
                expression.add(c);
            }else {
                // 符号
                if (c == '('){
                    // 左括号直接放入
                    symbol.add(c);
                }else if (c == ')'){
                    // 右括号，一直弹出到 '('
                    char pop = symbol.pop();
                    while (pop != '('){
                        expression.add(pop);
                        pop = symbol.pop();
                    }
                }else {
                    // 其他符号
                    if (symbol.isEmpty() || symbol.get(symbol.size()-1) == '('){
                        // 空的直接加进去
                        symbol.add(c);
                    }else {
                        while (!symbol.isEmpty()){
                            char character = symbol.get(symbol.size() - 1);
                            boolean compare = compare(c, character);
                            if (compare || character == '('){
                                // 当前优先级高
                                symbol.add(c);
                                // 结束
                                break;
                            }else {
                                // 弹出最上面的
                                expression.add(symbol.pop());
                                // 继续比较
                            }
                        }
                        if (symbol.isEmpty()){
                            symbol.add(c);
                        }
                    }
                }
            }
        }
        while (!symbol.isEmpty()){
            expression.add(symbol.pop());
        }
        // 所有数据都在 expression中
        StringBuilder stringBuilder = new StringBuilder();
        while (!expression.isEmpty()){
            stringBuilder.append(expression.pop());
        }
        return stringBuilder.reverse().toString();
    }
    public static boolean isNum(char c){
        return c >= '0' && c <= '9';
    }
    public static boolean compare(char c1,char c2){
        HashMap<Character,Integer> map = new HashMap<>();
        map.put('+',1);
        map.put('-',1);
        map.put('*',2);
        map.put('/',2);
        map.put('(',Integer.MAX_VALUE);
        return map.get(c1) > map.get(c2);
    }
}
