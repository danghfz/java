package model.template;

/**
 * @author danghf
 * @version 1.0
 * 2022/11/18   8:11
 * 模板方法模式 + 钩子函数
 */
public class Demo {
    public static void main(String[] args) {
        new OatMilk().make();
        new RedDateMilk().make();
        new Milk().make();
    }
}
abstract class AbstractMilk{
    /**
     * 模板方法可以做成 final
     * 防止子类重写
     */
    public final void make(){
        System.out.println("===="+this+"=====");
        select();
        // 是否添加材料
        if (WhetherToAdd()){
            addCondiments();
        }
        soak();
        System.out.println("=================");
    }
    void select(){
        System.out.println("加入牛奶");
    }

    /**
     * 添加配料
     */
    protected abstract void addCondiments();
    void soak(){
        System.out.println("充分混合");
    }
    /**
     * 钩子方法
     * 视情况覆盖
     * 是否添加 其他材料
     */
    boolean WhetherToAdd(){
        // 默认添加
        return true;
    }
}

/**
 * 燕麦牛奶
 */
class OatMilk extends AbstractMilk{
    @Override
    protected void addCondiments() {
        System.out.println("添加牛奶");
    }
}
/**
 * 红枣牛奶
 */
class RedDateMilk extends AbstractMilk{
    @Override
    protected void addCondiments() {
        System.out.println("加入红枣");
    }
}

/**
 * 纯牛奶
 * 使用钩子方法，不添加任何
 */
class Milk extends AbstractMilk{
    @Override
    protected void addCondiments() {

    }

    @Override
    boolean WhetherToAdd() {
        return false;
    }
}