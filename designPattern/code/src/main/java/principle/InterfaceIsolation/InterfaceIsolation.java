package principle.InterfaceIsolation;

/**
 * @author danghf
 * @version 1.0
 * 2022/11/9   9:30
 * 接口隔离原则
 */
public class InterfaceIsolation{
    public static void main(String[] args) {

    }
}
interface Interface1 {
    void operation1();
    void operation2();
    void operation3();
    void operation4();
    void operation5();
}
class B implements Interface1{
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
    @Override
    public void operation4() {
        System.out.println("B 实现 operation4");
    }
    @Override
    public void operation5() {
        System.out.println("B 实现 operation5");
    }
}
class D implements Interface1{
    @Override
    public void operation1() {
        System.out.println("D 实现 operation1");
    }
    @Override
    public void operation2() {
        System.out.println("D 实现 operation2");
    }
    @Override
    public void operation3() {
        System.out.println("D 实现 operation3");
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
// A 类使用 123方法，B类中的 45方法白写
class A{
    public void depend1(Interface1 i){
        i.operation1();
    }
    public void depend2(Interface1 i){
        i.operation2();
    }
    public void depend3(Interface1 i){
        i.operation3();
    }
}
// C 通过 interface 依赖 D
// C 只使用 D中的145方法，D类中的23方法白写
// 按照隔离原则，将interface拆成几个独立的接口
class C{
    public void depend1(Interface1 i){
        i.operation1();
    }
    public void depend4(Interface1 i){
        i.operation4();
    }
    public void depend5(Interface1 i){
        i.operation5();
    }
}