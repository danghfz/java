package abstractFactory;

import factory.Phone;

/**
 * @author 党
 * @version 1.0
 * 2022/10/2   15:35
 * 电脑工厂
 */
public class ComputerFactory extends AbstractFactory{
    @Override
    public Phone createPhone(Brand brand) {
        return null;
    }

    @Override
    public Computer createComputer(Brand brand) {
        if (brand == Brand.Android){
            return new AndroidComputer();
        }
        return new AppleComputer();
    }
}
