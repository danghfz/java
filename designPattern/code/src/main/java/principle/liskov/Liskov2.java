package principle.liskov;

/**
 * @author danghf
 * @version 1.0
 * 2022/11/9   11:29
 * 里氏替换原则
 */
public class Liskov2 {
}

// 创建 一个更加基础的类
class Base {
}

class A2 extends Base {
    public int function1(int num1, int num2) {
        return num1 - num2;
    }
}

class B2 extends Base {
    //如果 B 需要使用 A 类的方法,使用组合关系
    private A2 a;
    public int function1(int num1, int num2) {
        return num1 + num2;
    }
    public int function2(int num1, int num2) {
        return function1(num1, num2) + 1;
    }
    public int function3(int num1, int num2) {
        return a.function1(num1, num2);
    }
}