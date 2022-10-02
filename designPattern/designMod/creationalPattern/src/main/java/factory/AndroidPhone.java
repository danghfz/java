package factory;

/**
 * @author 党
 * @version 1.0
 * 2022/10/2   14:33
 */
public class AndroidPhone implements Phone{
    @Override
    public void run() {
        System.out.println("开机 安卓手机");
    }
}
