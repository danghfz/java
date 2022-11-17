package model.flyweight;

import java.util.HashMap;

/**
 * @author danghf
 * @version 1.0
 * 2022/11/17   16:46
 * 享元模式
 */
public class Demo {
}

/**
 * 网站 工厂类
 */
class WebSiteFactory{
    private HashMap<String, AbstractWebSite> pools = new HashMap<>(16);
    public AbstractWebSite getWebSiteCategory(String type){
        // 判断是否存在
        if (!pools.containsKey(type)){
            pools.put(type,new ConcreteWebSite(type));
        }
        return pools.get(type);
    }
    public Integer getWebSites(){
        return pools.size();
    }
}
abstract class AbstractWebSite{
    /**
     * 应用
     */
    public abstract void use();
}

class ConcreteWebSite extends AbstractWebSite{
    /**
     * 网站类型
     */
    private String type = "";

    public ConcreteWebSite(String type) {
        this.type = type;
    }

    @Override
    public void use() {
        System.out.println(type + "类型网站" +"在使用 ,,,");
    }
}