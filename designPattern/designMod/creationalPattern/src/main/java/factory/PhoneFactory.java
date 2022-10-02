package factory;

/**
 * @author 党
 * @version 1.0
 * 2022/10/2   14:34
 * 手机工场
 */
public class PhoneFactory {
    public Phone createPhone(PhoneBrand brand){
        if (brand.getName().equals("Android"))
            return new AndroidPhone();
        return new ApplePhone();
    }
}
enum PhoneBrand{
    Android("Android"),
    Apple("Apple");
    public final String name;
    PhoneBrand(String name){
        this.name = name;
    }
    public String getName() {
        return name;
    }
}