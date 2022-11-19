package model.visitor;

import java.util.ArrayList;

/**
 * @author danghf
 * @version 1.0
 * 2022/11/18   10:05
 * 访问者模式
 */
public class Demo {
    public static void main(String[] args) {
        ArrayList<BasePerson> basePeople = new ArrayList<>();
        basePeople.add(new Woman());
        basePeople.add(new Man());
        basePeople.add(new Woman());
        basePeople.add(new Man());
        for (BasePerson basePerson : basePeople) {
            basePerson.accept(new Success());
        }
    }
}

abstract class BasePerson {
    /**
     * 提供一个抽象方法，让访问者可以访问
     *
     * @param action action
     */
    public abstract void accept(BaseAction action);
}

abstract class BaseAction {
    /**
     * 得到 man 的测频结果
     *
     * @param man man
     */
    public abstract void getManRes(Man man);

    /**
     * 得到 woman的 测评结果
     *
     * @param woman woman
     */
    public abstract void getWomanRes(Woman woman);
}

class Failure extends BaseAction {
    @Override
    public void getManRes(Man man) {
        System.out.println(man + " -> failure");
    }

    @Override
    public void getWomanRes(Woman woman) {
        System.out.println(woman + " -> failure");
    }
}

class Success extends BaseAction {
    @Override
    public void getManRes(Man man) {
        System.out.println(man + " -> success");
    }

    @Override
    public void getWomanRes(Woman woman) {
        System.out.println(woman + " -> success");
    }
}

class Man extends BasePerson {
    @Override
    public void accept(BaseAction action) {
        action.getManRes(this);
    }
}

class Woman extends BasePerson {
    @Override
    public void accept(BaseAction action) {
        action.getWomanRes(this);
    }
}