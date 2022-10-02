package abstractFactory;

import abstractFactory.PhoneFactory;
import factory.Phone;

/**
 * @author 党
 * @version 1.0
 * 2022/10/2   15:38
 * 演示
 */
public class Demo {
    public static void main(String[] args) {
        AbstractFactory phoneFactory = new PhoneFactory();
        AbstractFactory computerFactory = new ComputerFactory();
        Phone phone = phoneFactory.createPhone(Brand.Android);
        Computer computer = computerFactory.createComputer(Brand.Apple);
        phone.run();
        computer.run();
    }
}
