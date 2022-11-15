package model.prototype;

/**
 * @author danghf
 * @version 1.0
 * 2022/11/15   16:35
 * 原型模式
 */
public class Demo1 { // new
    public static void main(String[] args) {
        // 传统方法
        Sheep sheep = new Sheep("tom", "18", "yellow");
        // 创建其他
        Sheep sheep1 = new Sheep(sheep.getName(), sheep.getAge(), sheep.getColor());
        // ...

    }
}
/**
 * 1) 优点是比较好理解，简单易操作。
 * 2) 在创建新的对象时，总是需要重新获取原始对象的属性，如果创建的对象比较复杂时，效率较低
 * 3) 总是需要重新初始化对象，而不是动态地获得对象运行时的状态, 不够灵活
 * 4) 改进的思路分析
 */
class Demo2{ // clone
    public static void main(String[] args) throws CloneNotSupportedException {
        Sheep sheep = new Sheep("tom", "18", "yellow");
        Sheep clone = (Sheep)sheep.clone();
        // tom 18 yellow
        // 默认浅拷贝
    }
}