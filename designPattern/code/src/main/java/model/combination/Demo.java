package model.combination;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author danghf
 * @version 1.0
 * 2022/11/16   16:47
 * 组合模式
 */
public class Demo {
    public static void main(String[] args) {
        University xsyu = new University("皇家淀粉学院","xsyu");
        College college = new College("计算机学院", "计算机学院");
        College college1 = new College("理学院", "理学院");
        college1.add(new Department("数学系", ""));
        college1.add(new Department("物理系", ""));
        college.add(new Department("计算机系", ""));
        xsyu.add(college);
        xsyu.add(college1);
        xsyu.show();
    }
}

@Data
abstract class AbstractComponent {
    private String name;
    private String description;

    public AbstractComponent(String name, String description) {
        this.name = name;
        this.description = description;
    }

    protected void add(AbstractComponent abstractComponent) {
        // 默认实现 抛出不支持操作异常
        throw new UnsupportedOperationException();
    }

    protected void remove(AbstractComponent abstractComponent) {
        throw new UnsupportedOperationException();
    }

    protected void show() {
        throw new UnsupportedOperationException();
    }
}

/**
 * 大学 (非叶子结点)
 */
class University extends AbstractComponent {
    private final List<AbstractComponent> components = new ArrayList<>();

    public University(String name, String description) {
        super(name, description);
    }

    @Override
    protected void add(AbstractComponent component) {
        components.add(component);
    }

    @Override
    protected void remove(AbstractComponent component) {
        components.remove(component);
    }

    @Override
    protected void show() {
        System.out.println("-------" + this.getName());
        for (AbstractComponent component : components) {
            component.show();
        }
    }
}

/**
 * 学院 (非叶子结点)
 */
class College extends AbstractComponent {
    College(String name, String description) {
        super(name, description);
    }

    private final List<AbstractComponent> departments = new ArrayList<>();

    @Override
    protected void add(AbstractComponent component) {
        departments.add(component);
    }

    @Override
    protected void remove(AbstractComponent component) {
        departments.remove(component);
    }

    @Override
    protected void show() {
        System.out.println("------" + this.getName());
        for (AbstractComponent department : departments) {
            department.show();
        }
    }
}

/**
 * 系 leaf
 */
class Department extends AbstractComponent {
    public Department(String name, String description) {
        super(name, description);
    }
    // 叶子结点不支持 add remove

    @Override
    protected void show() {
        System.out.println("--" + this.getName());
    }
}