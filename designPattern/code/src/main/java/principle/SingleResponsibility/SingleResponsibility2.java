package principle.SingleResponsibility;

/**
 * @author danghf
 * @version 1.0
 * 2022/11/9   8:52
 */
public class SingleResponsibility2 {
    public static void main(String[] args) {
        RoadVehicle roadVehicle = new RoadVehicle();
        roadVehicle.run("汽车");
        AirVehicle airVehicle = new AirVehicle();
        airVehicle.run("飞机");
    }
}

/**
 * 遵守单一职责原则
 * 但是改动很大
 * 修改 => 直接修改Vehicle类
 */
class RoadVehicle{
    public void run(String roadVehicle){
        System.out.println(roadVehicle + " 在公路运行");
    }
}
class AirVehicle{
    public void run(String roadVehicle){
        System.out.println(roadVehicle + " 在天上运行");
    }
}
class SeaVehicle{
    public void run(String roadVehicle){
        System.out.println(roadVehicle + " 在水运行");
    }
}