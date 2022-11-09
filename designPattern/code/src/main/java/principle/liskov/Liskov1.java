package principle.liskov;

/**
 * @author danghf
 * @version 1.0
 * 2022/11/9   11:20
 * 里氏替换原则
 */
public class Liskov1 {
    public static void main(String[] args) {
        A a = new A();
        System.out.println("11-3=" + a.function1(11, 3));
        B b = new B();
        System.out.println("11-3=" + b.function1(11, 3));
        System.out.println("11-3+1" + b.function2(11, 3));
    }
}

class A {
    public int function1(int num1, int num2) {
        return num1 - num2;
    }
}

class B extends A {
    @Override // 重写了 A 类的 function1
    // 违反了里氏替换原则
    public int function1(int num1, int num2) {
        return num1 + num2;
    }

    public int function2(int num1, int num2) {
        return function1(num1, num2) + 1;
    }
}
