package model.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author danghf
 * @version 1.0
 * @data 2022/11/20 13:37
 * 观察者模式
 */
public class Demo {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        BaiduSite baiduSite = new BaiduSite();
        weatherData.register(baiduSite);
        weatherData.setTemperature(22.2f);
        weatherData.register(new SouHuSite());
        weatherData.remove(baiduSite);
        weatherData.setTemperature(44.4f);
    }
}

interface Subject {
    /**
     * 注册观察者
     *
     * @param o 观察者
     */
    void register(ObServer o);

    /**
     * 移除观察者
     *
     * @param o 观察者
     * @return boolean
     */
    boolean remove(ObServer o);

    /**
     * 通知观察者
     */
    void notifyObserver();
}

/**
 * 观察者
 */
interface ObServer {
    /**
     * 更新温度
     *
     * @param temperature 温度
     */
    void update(float temperature);
}

class WeatherData implements Subject {
    private float temperature;
    WeatherData(){
        temperature = new Random().nextFloat();
    }
    public void setTemperature(float temperature) {
        // 更新数据
        this.temperature = temperature;
        // 通知
        notifyObserver();
    }

    private final List<ObServer> obServers = new ArrayList<>(16);

    @Override
    public void register(ObServer o) {
        if (!obServers.contains(o)){
            obServers.add(o);
        }

    }

    @Override
    public boolean remove(ObServer o) {
        return obServers.remove(o);
    }

    @Override
    public void notifyObserver() {
        for (ObServer obServer : obServers) {
            // 更新
            obServer.update(temperature);
        }
    }
}

/**
 * 百度
 */
class BaiduSite implements ObServer {
    private float temperature;

    @Override
    public void update(float temperature) {
        this.temperature = temperature;
        display();
    }

    /**
     * 展示
     */
    public void display() {
        System.out.println("======百度=====");
        System.out.println("temperature: " + temperature);
    }
}

/**
 * 搜狐
 */
class SouHuSite implements ObServer {
    private float temperature;

    @Override
    public void update(float temperature) {
        this.temperature = temperature;
        display();
    }

    /**
     * 展示
     */
    public void display() {
        System.out.println("======搜狐=====");
        System.out.println("temperature: " + temperature);
    }
}