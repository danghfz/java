package model.factory;

import com.sun.corba.se.spi.protocol.CorbaClientDelegate;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author danghf
 * @version 1.0
 * 2022/11/15   7:54
 * 工厂方法模式
 */
public class Demo2 {
}
/**
 * 需要 北京的 APizza ，BPizza，伦敦的 APizza BPizza
 * 使用简单工厂模式，创建不同的简单工厂类，比如 BJPizzaSimpleFactory、LDPizzaSimpleFactory 等等.从当前
 * 这个案例来说，也是可以的，但是考虑到项目的规模，以及软件的可维护性、可扩展性并不是特别好
 */
abstract class OrderPizza2{
    public Pizza pizza = null;
    abstract Pizza create(String orderType);
    public OrderPizza2(String orderType){
        pizza = create(orderType);
        // do something
    }
}
/**
 * 工厂方法模式
 */
class BJOrderPizza extends OrderPizza2{
    @Override
    Pizza create(String orderType) {
        if (orderType.equals("A")){
            this.pizza = new APizza();
            this.pizza.name = "Beijing";
        }else {
            this.pizza = new BPizza();
            this.pizza.name = "Beijing";
        }
        return pizza;
    }

    public BJOrderPizza(String orderType) {
        super(orderType);
    }
}
class LDOrderPizza extends OrderPizza2{
    public LDOrderPizza(String orderType) {
        super(orderType);
    }

    @Override
    Pizza create(String orderType) {
        if (orderType.equals("A")){
            this.pizza = new APizza();
            this.pizza.name = "London";
        }else {
            this.pizza = new BPizza();
            this.pizza.name = "London";
        }
        return pizza;
    }
}