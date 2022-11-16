package model.adapter;

/**
 * @author danghf
 * @version 1.0
 * 2022/11/16   8:58
 * 适配器模式 --> 对象适配器
 */
public class ObjectAdapt {
}
class AdaptCurrent2 implements I5V{
    private Current220V current220V;
    AdaptCurrent2(Current220V current220V){
        this.current220V = current220V;
    }
    @Override
    public int output() {
        if (current220V == null){
            return -1;
        }
        return current220V.get() / 44;
    }
}