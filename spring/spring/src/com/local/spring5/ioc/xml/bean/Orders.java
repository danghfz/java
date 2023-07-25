package com.local.spring5.ioc.xml.bean;

/**
 * @author 党
 * @version 1.0
 * 2022/5/10   12:42
 */
public class Orders {
    private String name;
    public Orders(){
        System.out.println("Orders的无参构造方法");
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("Orders的setName方法");
        this.name = name;
    }
    //初始化方法
    public void init(){
        System.out.println("Orders的init方法");
    }
    //销毁方法
    public void destroy(){
        System.out.println("Orders的destroy方法");
    }
}
