package model.command;

/**
 * @author danghf
 * @version 1.0
 * 2022/11/18   9:01
 * 命令 模式
 */
public class Demo {
    public static void main(String[] args) {
        RemoteControl remoteControl = new RemoteControl();
        // 电灯
        Light light = new Light();
        remoteControl.setCommand(0,new LightOnCommand(light),new LightOffCommand(light));
        // 无操作
        remoteControl.undoCommand();
        // 打开
        remoteControl.onCommand(0);
        // 撤销 => 关闭
        remoteControl.undoCommand();
        // 关闭
        remoteControl.offCommand(0);
        // 撤销 => 打开
        remoteControl.undoCommand();
    }
}

/**
 * 遥控器
 * 命令发布者
 */
class RemoteControl{
    /**
     * 命令数组
     */
    private final Command[] onCommands;
    private final Command[] offCommands;
    private Command undoCommand;

    public RemoteControl() {
        onCommands = new Command[5];
        offCommands = new Command[5];
        for (int i = 0; i < 5; i++) {
            onCommands[i] = new NoCommand();
            offCommands[i] = new NoCommand();
        }
        undoCommand = new NoCommand();
    }

    /**
     * 设置按键命令
     * @param index 按键索引
     * @param onCommand on命令
     * @param offCommand off命令
     */
    public void setCommand(int index,Command onCommand,Command offCommand){
        onCommands[index] = onCommand;
        offCommands[index] = offCommand;
    }

    /**
     * 找到按下的按钮，并执行方法
     * @param index 按钮索引
     */
    public void onCommand(int index){
        // 执行本次操作
        onCommands[index].execute();
        // 记录，用于执行撤销操作
        undoCommand = onCommands[index];
    }
    public void offCommand(int index){
        offCommands[index].execute();
        undoCommand = offCommands[index];
    }

    /**
     * 撤销操作
     */
    public void undoCommand(){
        if (undoCommand instanceof NoCommand){
            // 撤销是空命令，之前没有任何操作，不做任何事
        }
        undoCommand.undo();
    }
}
/**
 * 命令接口
 */
interface Command{
    /**
     * 执行操作
     */
    void execute();

    /**
     * 撤销操作
     */
    void undo();
}

/**
 * 电灯打开命令
 */
class LightOnCommand implements Command{
    private final Light light;
    LightOnCommand(Light light){
        this.light = light;
    }
    @Override
    public void execute() {
        // 调用
        light.on();
    }

    @Override
    public void undo() {
        light.off();
    }
}
/**
 * 电灯关闭命令
 */
class LightOffCommand implements Command{
    /**
     * 命令 依赖 命令的执行者
     */
    private final Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.off();
    }

    @Override
    public void undo() {
        light.on();
    }
}
/**
 * 空命令
 * 简化操作
 * 可以省掉对空的判断
 */
class NoCommand implements Command{
    @Override
    public void execute() {

    }

    @Override
    public void undo() {

    }
}
/**
 * 电灯 命令的执行者
 */
class Light{
    public void on(){
        System.out.println("电灯打开");
    }
    public void off(){
        System.out.println("电灯关闭");
    }
}