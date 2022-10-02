package abstractFactory;

/**
 * @author 党
 * @version 1.0
 * 2022/10/2   15:37
 */
public class AndroidComputer implements Computer{
    @Override
    public void run() {
        System.out.println("安卓电脑");
    }
}
