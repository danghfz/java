package principle.SingleResponsibility;

/**
 * @author danghf
 * @version 1.0
 * 2022/11/9   8:47
 * 单一职责原则
 */
public class SingleResponsibility1 {
    public static void main(String[] args) {
        Vehicle vehicle = new Vehicle();
        vehicle.run("汽车");
        vehicle.run("飞机");
    }
}

/**
 * 交通工具类
 * 违反了单一职责原则
 * 根据交通工具的不同，分解成不同的类
 */
class Vehicle{
    public void run(String vehicle){
        System.out.println(vehicle + " 在公路上跑 ");
    }
}