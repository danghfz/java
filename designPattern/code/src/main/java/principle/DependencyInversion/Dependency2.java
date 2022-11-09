package principle.DependencyInversion;

/**
 * @author danghf
 * @version 1.0
 * 2022/11/9   10:19
 */
public class Dependency2 {
}
interface MsgServer{
    Object getInfo();
}
class Email2 implements MsgServer{
    @Override
    public Object getInfo() {
        return "电子邮件: hello World";
    }
}
class SMS implements MsgServer{
    @Override
    public Object getInfo() {
        return "短信: hello World";
    }
}
class People2{
    public void accept(MsgServer msg){
        System.out.println(msg.getInfo());
    }
}