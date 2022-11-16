package model.builder;

import lombok.Data;

/**
 * @author danghf
 * @version 1.0
 * 2022/11/16   7:38
 * 建造者模式
 */
public class Demo2 {
    public static void main(String[] args) {
        HouseDirector houseDirector = new HouseDirector(new CommonHouseBuilder());
        House constructor = houseDirector.constructor();
    }
}
@Data
// Product 产品
class House{
    private String basic;//地基
    private String walls;//墙
    private String roofed;//顶
}
// Builder 抽象建造者
abstract class HouseBuilder{
    protected House house = null;
    // 建造流程
    public abstract void buildBasic();
    public abstract void buildWalls();
    public abstract void roofed();
    // 建造
    public House build(){
        return house;
    }
}
// ConcreteBuilder 具体的建造者
class CommonHouseBuilder extends HouseBuilder{
    @Override
    public void buildBasic() {
        house.setBasic("地基");
        System.out.println(" 普通房子打地基 ");
    }
    @Override
    public void buildWalls() {
        house.setWalls("砌墙");
        System.out.println(" 普通房子砌墙 ");
    }
    @Override
    public void roofed() {
        house.setRoofed("封顶");
        System.out.println(" 普通房子封顶 ");
    }
}
// Director 指挥者
class HouseDirector{
    HouseBuilder houseBuilder = null;
    HouseDirector(HouseBuilder houseBuilder){
        this.houseBuilder = houseBuilder;
    }
    // 处理建造房子流程交给指挥者
    public House constructor(){
        // 每次创建前，将house重置，不然每次是同一个对象
        houseBuilder.house = new House();
        houseBuilder.buildBasic();
        houseBuilder.buildWalls();
        houseBuilder.roofed();
        return houseBuilder.build();
    }
}