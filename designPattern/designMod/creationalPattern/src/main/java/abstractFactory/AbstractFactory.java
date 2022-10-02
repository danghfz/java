package abstractFactory;

import factory.Phone;

/**
 * @author 党
 * @version 1.0
 * 2022/10/2   14:51
 */
public abstract class AbstractFactory {
     public abstract Phone createPhone(Brand brand);
     public abstract Computer createComputer(Brand brand);
}
