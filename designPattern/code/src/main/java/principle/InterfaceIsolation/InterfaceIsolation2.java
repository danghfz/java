package principle.InterfaceIsolation;

/**
 * @author danghf
 * @version 1.0
 * 2022/11/9   9:51
 */
public class InterfaceIsolation2 {
}
// 拆成三个接口
interface Inter1 {
    void operation1();
}
interface Inter2{
    void operation2();
    void operation3();
}
interface Inter3{
    void operation4();
    void operation5();
}
class B2 implements Inter1,Inter2{
    @Override
    public void operation1() {
        System.out.println("B 实现 operation1");
    }
    @Override
    public void operation2() {
        System.out.println("B 实现 operation2");
    }
    @Override
    public void operation3() {
        System.out.println("B 实现 operation3");
    }
}
class D2 implements Inter1,Inter3{
    @Override
    public void operation1() {
        System.out.println("D 实现 operation1");
    }
    @Override
    public void operation4() {
        System.out.println("D 实现 operation4");
    }
    @Override
    public void operation5() {
        System.out.println("D 实现 operation5");
    }
}
// A 类通过 接口interface 依赖(使用) B
// A 类使用 123方法，
class A2{
    public void depend1(Inter1 i){
        i.operation1();
    }
    public void depend2(Inter2 i){
        i.operation2();
    }
    public void depend3(Inter2 i){
        i.operation3();
    }
}
// C 通过 interface 依赖 D
// C 只使用 145方法
class C2{
    public void depend1(Inter1 i){
        i.operation1();
    }
    public void depend4(Inter3 i){
        i.operation4();
    }
    public void depend5(Inter3 i){
        i.operation5();
    }
}