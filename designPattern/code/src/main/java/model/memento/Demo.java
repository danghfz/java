package model.memento;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author danghf
 * @version 1.0
 * @data 2022/11/20 18:27
 * 备忘录模式 -> 行为模式
 */
public class Demo {
    public static void main(String[] args) {
        Originator danghf = new Originator("danghf-info1");
        // 备份
        Memento memento = danghf.getMemento();
        // 管理备份
        Caretaker caretaker = new Caretaker();
        caretaker.add("danghf", memento);
        // 更新数据
        danghf.setInfo("danghf-info2");
        memento = danghf.getMemento();
        caretaker.add("danghf", memento);
        // 新角色
        Originator zd = new Originator("zd-info1");
        memento = zd.getMemento();
        caretaker.add("zd", memento);
        zd.setInfo("zd-info2");
        memento = zd.getMemento();
        caretaker.add("zd", memento);
        zd.setInfo("zd-info3");
        memento = zd.getMemento();
        caretaker.add("zd", memento);
        // 查看并恢复
        List<Memento> mementoList = caretaker.getMementos("danghf");
        System.out.println("==============danghf================");
        for (Memento memento1 : mementoList) {
            System.out.println(memento1.getInfo());
        }
        List<Memento> zd1 = caretaker.getMementos("zd");
        System.out.println("================zd===================");
        for (Memento memento1 : zd1) {
            System.out.println(memento1.getInfo());
        }
        // 复原
        Memento m = caretaker.get("danghf", 0);
        danghf.restore(m);
        System.out.println(danghf.getInfo());
    }
}

/**
 * 原始对象，保存该对象的状态
 */
class Originator {
    private String info;

    public void setInfo(String info) {
        this.info = info;
    }

    public Originator(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    /**
     * 保存备份
     */
    public Memento getMemento() {
        return new Memento(this.getInfo());
    }

    /**
     * 根据备份信息复原
     *
     * @param m 备份信息
     */
    public void restore(Memento m) {
        this.info = m.getInfo();
    }
}

/**
 * 备忘录对象，,负责保存好记录，即 Originator 内部状态
 */
class Memento {
    private String info;

    public Memento(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}

/**
 * 负责保存多个备忘录对象
 * 如果需要保存多个对象的多个事件状态
 * HashMap <String, List<Memento>>
 */
class Caretaker {
    private HashMap<String, List<Memento>> hashMap = new HashMap<>();

    public void add(String name, Memento m) {
        // 获取该对象的 备忘录 集合
        List<Memento> mementos = hashMap.get(name);
        if (mementos == null) {
            ArrayList<Memento> objects = new ArrayList<>(1);
            objects.add(m);
            hashMap.put(name, objects);
        } else {
            mementos.add(m);
        }

    }

    public List<Memento> getMementos(String name) {
        return hashMap.get(name);
    }

    public Memento get(String name, Integer index) {
        List<Memento> mementos = hashMap.get(name);
        if (mementos != null) {
            return mementos.get(index);
        } else {
            return null;
        }

    }
}