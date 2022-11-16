package model.builder;

/**
 * @author danghf
 * @version 1.0
 * 2022/11/15   18:34
 * 建造者模式 传统方式
 */
public class Demo1 {
    public static void main(String[] args) {
        CommonHouse commonHouse = new CommonHouse();
        commonHouse.build();
    }
}
abstract class AbstractHouse{
    //打地基
    public abstract void buildBasic();
    //砌墙
    public abstract void buildWalls();
    //封顶
    public abstract void roofed();
    public void build() {
        buildBasic();
        buildWalls();
        roofed();
    }
}

/**
 * 1. 优点是比较好理解，简单易操作。
 * 2. 设计的程序结构，过于简单，没有设计缓存层对象，程序的扩展和维护不好. 也就是说，这种设计方案，把产品(即：房子) 和 创建产品的过程(即：建房子流程) 封装在一起，耦合性增强了。
 * 3. 解决方案：将产品和产品建造过程解耦 => **建造者模式**
 */
class CommonHouse extends AbstractHouse {
    @Override
    public void buildBasic() {
        System.out.println(" 普通房子打地基 ");
    }
    @Override
    public void buildWalls() {
        System.out.println(" 普通房子砌墙 ");
    }
    @Override
    public void roofed() {
        System.out.println(" 普通房子封顶 ");
    }
}