package com.local.spring5.ioc.xml.bean;

/**
 * @author µ³
 * @version 1.0
 * 2022/5/8   16:53
 */
public class Employee {
    private String name;
    private Dept dept;

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", dept=" + dept +
                '}';
    }

    public Dept getDept() {
        return dept;
    }

    public Employee(String name, Dept dept) {
        this.name = name;
        this.dept = dept;
    }

    public Employee() {
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
