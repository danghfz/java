package model.strategy;

import lombok.Data;

/**
 * @author danghf
 * @version 1.0
 * @data 2022/11/23 11:03
 * @since 1.8
 * 策略模式 -> 仅行为不一样
 */
public class Demo {
    public static void main(String[] args) {
        Context context = new Context(new OperationAdd());
        System.out.println("10 + 5 = " + context.executeStrategy(10, 5));

        context.setStrategy(new OperationSubtract());;
        System.out.println("10 - 5 = " + context.executeStrategy(10, 5));

        context.setStrategy(new OperationSubtract());
        System.out.println("10 * 5 = " + context.executeStrategy(10, 5));

        context.setStrategy(new Strategy() {
            @Override
            public int doOperation(int num1, int num2) {
                return num1 / num2;
            }
        });
        System.out.println("10 / 5 = " + context.executeStrategy(10, 5));
    }
}
interface Strategy {
    int doOperation(int num1, int num2);
}
class OperationAdd implements Strategy{
    @Override
    public int doOperation(int num1, int num2) {
        return num1 + num2;
    }
}
class OperationSubtract implements Strategy{
    @Override
    public int doOperation(int num1, int num2) {
        return num1 - num2;
    }
}
class OperationMultiply implements Strategy{
    @Override
    public int doOperation(int num1, int num2) {
        return num1 * num2;
    }
}
@Data
class Context {
    private Strategy strategy;

    public Context(Strategy strategy){
        this.strategy = strategy;
    }

    public int executeStrategy(int num1, int num2){
        return strategy.doOperation(num1, num2);
    }
}