package model.decorator;

import lombok.Data;

/**
 * @author danghf
 * @version 1.0
 * 2022/11/16   13:42
 * 装饰者模式
 */
public class Demo {
    public static void main(String[] args) {
        // 一份 LongBlack
        LongBlack longBlack = new LongBlack();
        // 加 一份糖
        Sugar sugar = new Sugar(longBlack);
        // 加 一份巧克力
        Chocolate chocolate = new Chocolate(sugar);
        // 加 一份巧克力
        chocolate = new Chocolate(chocolate);
        System.out.println(chocolate.total());
        System.out.println(chocolate.getDes());
    }
}

@Data
abstract class Drink {
    private String des;
    private float price;

    /**
     * 总价格
     * @return float
     */
    public abstract float total();
}

class Coffee extends Drink {
    @Override
    public float total() {
        // 对于一个单品 coffer 价格就是单价
        return super.getPrice();
    }
}

class Espresso extends Coffee {
    Espresso() {
        setPrice(6.0f);
        setDes("Espresso->" + getPrice());
    }
}

class LongBlack extends Coffee {
    LongBlack() {
        setPrice(7.0f);
        setDes("LongBlack->" + getPrice());
    }
}

/**
 * 装饰者
 */
class Decorator extends Drink {
    Decorator(Drink drink) {
        this.drink = drink;
    }

    private Drink drink;

    @Override
    public float total() {
        // 自己的 价格 + 被修饰的价格
        return super.getPrice() + drink.total();
    }

    @Override
    public String getDes() {
        return super.getDes() + " -> " + super.getPrice()
                + "\n" + drink.getDes();
    }
}

/**
 * 巧克力
 */
class Chocolate extends Decorator {
    Chocolate(Drink drink) {
        super(drink);
        setDes("Chocolate");
        setPrice(2.0f);
    }
}

/**
 * 糖
 */
class Sugar extends Decorator {
    Sugar(Drink drink) {
        super(drink);
        setDes("Sugar");
        setPrice(1.0f);
    }
}