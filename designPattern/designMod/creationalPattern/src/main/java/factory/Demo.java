package factory;

/**
 * @author 党
 * @version 1.0
 * 2022/10/2   14:41
 * 演示
 */
public class Demo {
    public static void main(String[] args) {
        PhoneFactory factory = new PhoneFactory();
        Phone androidPhone = factory.createPhone(PhoneBrand.Android);
        Phone applePhone = factory.createPhone(PhoneBrand.Apple);
        androidPhone.run();
        applePhone.run();
    }
}
