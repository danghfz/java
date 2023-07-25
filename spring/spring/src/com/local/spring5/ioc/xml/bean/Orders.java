package com.local.spring5.ioc.xml.bean;

/**
 * @author ��
 * @version 1.0
 * 2022/5/10   12:42
 */
public class Orders {
    private String name;
    public Orders(){
        System.out.println("Orders���޲ι��췽��");
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("Orders��setName����");
        this.name = name;
    }
    //��ʼ������
    public void init(){
        System.out.println("Orders��init����");
    }
    //���ٷ���
    public void destroy(){
        System.out.println("Orders��destroy����");
    }
}
