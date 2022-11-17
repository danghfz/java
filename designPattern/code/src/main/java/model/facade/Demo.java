package model.facade;

/**
 * @author danghf
 * @version 1.0
 * 2022/11/17   9:07
 * 外观模式
 */
public class Demo {
    public static void main(String[] args) {
        Facade facade = new Facade();
        facade.ready();
        facade.sus();
        facade.off();
    }
}

/**
 * 外观
 */
class Facade{
    /**
     * 字体对象
     */
    private Dvd dvd;
    private Projector projector;
    private Popcorn popcorn;
    Facade(){
        dvd = Dvd.getInstance();
        projector = Projector.getInstance();
        popcorn = Popcorn.getInstance();
    }

    /**
     * 准备
     */
    public void ready(){
        dvd.on();
        projector.down();
        popcorn.on();
    }
    public void sus(){
        dvd.sus();
    }
    public void off(){
        dvd.off();
        projector.up();
        popcorn.off();
    }
}
class Dvd{
    private Dvd(){}

    private static final class DvdHolder {
        static final Dvd dvd = new Dvd();
    }
    public static Dvd getInstance(){
        return DvdHolder.dvd;
    }
    public void on(){
        System.out.println(this + "开机");
    }
    public void off(){
        System.out.println(this + "关机");
    }
    public void play(){
        System.out.println(this + "播放");
    }
    public void sus(){
        System.out.println(this + "暂停");
    }
}

/**
 * 爆米花
 */
class Popcorn{
    public Popcorn(){}
    public static class PopcornHolder{
        static final Popcorn popcorn = new Popcorn();
    }
    public static Popcorn getInstance(){
        return PopcornHolder.popcorn;
    }
    public void on(){
        System.out.println(this + "开机");
    }
    public void off(){
        System.out.println(this + "关机");
    }
}

/**
 * 投影仪
 */
class Projector{
    private Projector(){}
    private static class ProjectorHolder{
        static final Projector projector = new Projector();
    }
    public static Projector getInstance(){
        return ProjectorHolder.projector;
    }
    public void up(){
        System.out.println("投影仪上升");
    }
    public void down(){
        System.out.println("投影仪下降");
    }
}
