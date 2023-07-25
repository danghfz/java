package com.local.pojo;

/**
 * @author µ³
 * @version 1.0
 * 2022/5/21   9:54
 */
public class User {

    private String name;

    private Integer age;

    public String getName() {
        return name;
    }

//    public void setName(String name) {
//        this.name = name;
//    }

    public Integer getAge() {
        return age;
    }

//    public void setAge(Integer age) {
//        this.age = age;
//    }

    public User() {
    }

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
