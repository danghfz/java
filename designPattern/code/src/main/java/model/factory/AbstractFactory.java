package model.factory;

/**
 * @author danghf
 * @version 1.0
 * 2022/11/15   8:35
 * 抽象工厂模式
 */
public class AbstractFactory {
}
interface Factory{
    Pizza create(String type);
}
class BJFactory implements Factory{
    @Override
    public Pizza create(String type) {
        Pizza pizza = null;
        if (type.equals("A")){
            pizza = new APizza();
            pizza.name = "Beijing";
        }else if (type.equals("B")){
            pizza = new BPizza();
            pizza.name = "Beijing";
        }
        return pizza;
    }
}
class LDFactory implements Factory{
    @Override
    public Pizza create(String type) {
        Pizza pizza = null;
        if (type.equals("A")){
            pizza = new APizza();
            pizza.name = "London";
        }else if (type.equals("B")){
            pizza = new BPizza();
            pizza.name = "London";
        }
        return pizza;
    }
}
class OrderPizza3{
    Factory factory;
    OrderPizza3(Factory factory){
        this.factory = factory;
    }
    public Pizza create(String type){
        return factory.create(type);
    }
}