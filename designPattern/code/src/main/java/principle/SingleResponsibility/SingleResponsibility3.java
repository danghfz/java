package principle.SingleResponsibility;

/**
 * @author danghf
 * @version 1.0
 * 2022/11/9   8:55
 */
public class SingleResponsibility3 {
    public static void main(String[] args) {
        Vehicle2 vehicle = new Vehicle2();
        vehicle.runAir("飞机");
        vehicle.runRoad("汽车");
    }
}

/**
 * 没有对原来的类做大的修改
 * 虽然在类上没有 遵守单一职责
 * 但是在 方法上遵守单一职责
 */
class Vehicle2{
    public void runRoad(String vehicle){
        System.out.println(vehicle + " 在公路上跑 ");
    }
    public void runAir(String vehicle){
        System.out.println(vehicle + " 在天上飞 ");
    }
}