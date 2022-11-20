package model.mediator;

import lombok.Data;

import java.util.HashMap;

/**
 * @author danghf
 * @version 1.0
 * @data 2022/11/20 16:53
 * 中介者模式
 */
public class Demo {
    public static void main(String[] args) {
        Mediator mediator = new MediatorImpl();
        Alarm alarm = new Alarm(mediator,"alarm");
        alarm.sendMessage(0, new Strategy() {
            @Override
            public Object execute(Object... args) {
                // strategy.execute(stateChange, baseColleague);
                int state = (int)args[0];
                BaseColleague colleague = (BaseColleague)args[1];
                System.out.println(colleague.getName() +
                        "发送消息");
                if (state == 1){
                    // 发送消息给其他子系统
                    System.out.println("do 1");
                }else {
                    System.out.println("do other");
                }
                return null;
            }
        });

    }
}
interface Strategy{
    /**
     * 执行方法
     * @param args 各种参数
     * @return Object
     */
    Object execute(Object... args);
}
/**
 * 中介者
 */
interface Mediator {
    /**
     * 注册
     *
     * @param key       名称
     * @param colleague 子系统
     */
    void register(String key, BaseColleague colleague);

    /**
     * 获取消息
     *
     * @param stateChange 状态
     * @param strategy 具体处理的粗略，为了后期的扩展
     * @param colleague   子系统名称
     */
    void getMessage(int stateChange, String colleague,Strategy strategy);

    /**
     * 发送消息
     */
    void sendMessage();
}

/**
 * 具体的中介者类
 */
class MediatorImpl implements Mediator {

    /**
     * 集合，存所有的子系统
     */
    private final HashMap<String, BaseColleague> hashMap;

    public MediatorImpl() {
        this.hashMap = new HashMap<>(16);
    }

    @Override
    public void register(String key, BaseColleague colleague) {
        hashMap.put(key, colleague);
    }

    @Override
    public void getMessage(int stateChange, String colleague, Strategy strategy) {
        BaseColleague baseColleague = hashMap.get(colleague);
        strategy.execute(stateChange, baseColleague);
//        // 判断 判断太多，使用策略模式
//        if (baseColleague instanceof Alarm) {
//            // 闹钟事件
//            if (stateChange == 1) {
//                // doSomeThing
//            } else {
//                // doSomeThing
//            }
//        }
    }

    @Override
    public void sendMessage() {

    }
}

/**
 * 子系统
 */
@Data
abstract class BaseColleague {
    private Mediator mediator;
    private String name;

    public BaseColleague(Mediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
        mediator.register(name, this);
    }

    /**
     * 获取中介对象
     *
     * @return mediator
     */
    protected Mediator getMediator() {
        return this.mediator;
    }

    /**
     * 发送消息
     * @param strategy 策略
     * @param state 状态
     */
    protected abstract void sendMessage(int state,Strategy strategy);
}

/**
 * 闹钟
 */
class Alarm extends BaseColleague {
    public Alarm(Mediator mediator, String name) {
        super(mediator, name);
    }

    @Override
    protected Mediator getMediator() {
        return super.getMediator();
    }

    @Override
    protected void sendMessage(int state,Strategy strategy) {
        this.getMediator().getMessage(state, this.getName(),strategy);
    }
}