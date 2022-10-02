package factory;

/**
 * @author 党
 * @version 1.0
 * 2022/10/2   14:32
 */
public class ApplePhone implements Phone{
    @Override
    public void run() {
        System.out.println("开机 苹果手机");
    }
}
