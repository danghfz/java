package model.bridging;


/**
 * @author danghf
 * @version 1.0
 * 2022/11/16   10:21
 * 桥接模式
 */
public class Demo {
}
// 品牌
interface Brand{
    // 打电话
    void call();
    void open();
    void close();
}
class XiaoMi implements Brand{
    @Override
    public void call() {
        System.out.println("小米");
    }
    @Override
    public void open() {
        System.out.println("小米");
    }
    @Override
    public void close() {
        System.out.println("小米");
    }
}
class ViVo implements Brand{
    @Override
    public void call() {
        System.out.println("ViVo");
    }
    @Override
    public void open() {
        System.out.println("ViVo");
    }
    @Override
    public void close() {
        System.out.println("ViVo");
    }
}
abstract class Phone{
    // 组合品牌
    private Brand brand;
    public Brand getBrand(){
        return this.brand;
    }
    public Phone(Brand brand){
        this.brand = brand;
    }
    public void call() {
        brand.call();
    }
    public void open() {
        brand.open();
    }
    public void close() {
        brand.close();
    }
}
// 折叠手机
class FoldPhone extends Phone{
    public FoldPhone(Brand brand) {
        super(brand);
    }

    @Override
    public void call() {
        super.call();
        System.out.println("折叠手机"+this.getBrand()+"打电话");
    }
}
/**
 * 往后无论是新增品牌还是手机样式都很方便
 */