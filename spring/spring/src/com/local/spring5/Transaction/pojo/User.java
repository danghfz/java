package com.local.spring5.Transaction.pojo;

/**
 * @author µ³
 * @version 1.0
 * 2022/5/12   14:34
 */
public class User {
    private String name;
    private Integer id;
    private Double money;

    @Override
    public String toString() {
        return "t_spring{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", money=" + money +
                '}';
    }

    public User(String name, Integer id, Double money) {
        this.name = name;
        this.id = id;
        this.money = money;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getmoney() {
        return money;
    }

    public void setmoney(Double money) {
        this.money = money;
    }
}
