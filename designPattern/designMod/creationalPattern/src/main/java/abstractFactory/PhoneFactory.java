package abstractFactory;

import factory.AndroidPhone;
import factory.ApplePhone;
import factory.Phone;

/**
 * @author 党
 * @version 1.0
 * 2022/10/2   14:34
 * 手机工场
 */
public class PhoneFactory extends AbstractFactory{
    @Override
    public Phone createPhone(Brand brand){
        if (brand == Brand.Android)
            return new AndroidPhone();
        return new ApplePhone();
    }

    @Override
    public Computer createComputer(Brand brand) {
        return null;
    }
}
enum Brand{
    Android("Android"),
    Apple("Apple");
    public final String name;
    Brand(String name){
        this.name = name;
    }
    public String getName() {
        return name;
    }
}