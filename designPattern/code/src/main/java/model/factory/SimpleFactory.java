package model.factory;

/**
 * @author danghf
 * @version 1.0
 * 2022/11/15   7:18
 * 简单工厂模式
 */
public class SimpleFactory {
}
abstract class Pizza{
    public String name;
    public void prepare(){}//准备
    public void bake(){}//烘烤
    public void cut(){}//切割
    public void box(){}//打包
}
class APizza extends Pizza{}
class BPizza extends Pizza{}
/**
 * 订购披萨(各种Pizza)
 * 披萨的制作 prepare，bake, cut, box几个步骤
 * 优点是比较好理解，简单易操作。
 * 缺点是违反了设计模式的 ocp 原则，即对扩展开放，对修改关闭。即当我们给类增加新功能的时候，尽量不修
 * 改代码，或者尽可能少修改代码
 * 分析:修改代码可以接受，但是如果我们在其它的地方也有创建Pizza的代码，就意味着，也需要修改，而创建Pizza的代码，往往有多处。
 * 思路:把创建Pizza对象封装到一个类中，这样我们有新的Pizza种类时，只需要修改该类就可，
 * 其它有创建到Pizza对象的代码就不需要修改了.->简单工厂模式
 */
class OrderPizza {
    public Pizza pizza;
    public Pizza create(String orderType){
        // 制作
        if (orderType.equals("A")) {
            // 制作 A 类型的 pizza
            pizza = new APizza();
        } else if (orderType.equals("B")) {
            // 制作 B 类型的 pizza
            pizza = new BPizza();
        }
        // prepare，bake, cut, box各个步骤
        // pizza.prepare();
        // ...
        // pizza.box();
        return pizza;
    }
    // ====================================================================
    private PizzaFactory pizzaFactory = null;
    public OrderPizza(PizzaFactory pizzaFactory){
        setPizzaFactory(pizzaFactory);
    }

    public void setPizzaFactory(PizzaFactory pizzaFactory) {
        this.pizzaFactory = pizzaFactory;
    }
    public Pizza createPizza(String type){
        return pizzaFactory.createPizza(type);
    }

}
/**
 * 使用 简单工厂模式
 */
class PizzaFactory{
    public Pizza createPizza(String pizzaType){
        Pizza pizza = null;
        if (pizzaType.equals("A")){
            pizza = new APizza();
        }else if (pizzaType.equals("B")){
            pizza = new BPizza();
        }
        // pizza.prepare();
        // ...
        // pizza.box();
        return pizza;
    }
}