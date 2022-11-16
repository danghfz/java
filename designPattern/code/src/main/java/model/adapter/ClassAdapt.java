package model.adapter;

/**
 * @author danghf
 * @version 1.0
 * 2022/11/16   8:17
 * 适配器模式 -->  类适配器
 */
public class ClassAdapt {
    public static void main(String[] args) {
        Phone phone = new Phone();
        Current220V current220V = new Current220V();// 实际电源
        // 通过适配器转换
        phone.charging(new AdaptCurrent());
    }
}
// 适配器 extends 被适配器类  implements 适配接口
class AdaptCurrent extends Current220V implements I5V{
    @Override
    public int output() {
        int i = this.get();
        return i / 44;
    }
}
// 应用
class Phone{
    // 充电
    public void charging(I5V current){
        int output = current.output();
        if (output > 5){
            System.out.println("电压过高");
        }else {
            System.out.println("success");
        }
    }
}
// 适配接口
interface I5V{//
    int output();
}
// 被适配 类
class Current220V{ // 电流
    public int get(){
        return 220;// 输出220V电压
    }
}

class Current5V implements I5V{
    @Override
    public int output(){
        return 5;
    }
}