package com.local.json;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.local.pojo.Person;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * @author 党
 * @version 1.0
 * 2022/5/5   17:08
 */
public class JsonTest {
    //1.javaBean和json的转换
    @Test
    public void test1(){
        Person person = new Person(18,"张三");
        //创建Gson对象实例
        Gson gson = new Gson();
        //toJson方法将javaBean对象转换成json字符串
        String personJsonStr = gson.toJson(person);
        System.out.println(personJsonStr);//{"id":18,"name":"张三"}
        //fromJson可以将json字符串转换成javaBean对象
        Person person1 = gson.fromJson(personJsonStr, Person.class);
        System.out.println(person1);//Person{id=18, name='张三'}
    }
    //2.list和json的转换
    @Test
    public void test2(){
        ArrayList<Person> list = new ArrayList<>();
        list.add(new Person(18,"张三"));
        list.add(new Person(19,"李四"));
        list.add(new Person(20,"王五"));
        //创建Gson对象实例
        Gson gson = new Gson();
        //toJson方法将list集合转换成json字符串
        String listJsonStr = gson.toJson(list);
        System.out.println(listJsonStr);//[{"id":18,"name":"张三"},{"id":19,"name":"李四"},{"id":20,"name":"王五"}]
        //fromJson可以将json字符串转换成list集合
        ArrayList<Person> list1 = gson.fromJson(listJsonStr, new PersonListType().getType());
//        ArrayList<Person> list1 = gson.fromJson(listJsonStr, new ArrayList<Person>().getClass());
        System.out.println(list1);//[Person{id=18, name='张三'}, Person{id=19, name='李四'}, Person{id=20, name='王五'}]
    }
    //3.map和json的转换
    @Test
    public void test3(){
        Hashtable<Integer, Person> map = new Hashtable<>();
        map.put(1, new Person(18,"张三"));
        map.put(2, new Person(19,"李四"));
        map.put(3, new Person(20,"王五"));
        //创建Gson对象实例
        Gson gson = new Gson();
        //toJson方法将map集合转换成json字符串
        String mapJsonStr = gson.toJson(map);
        System.out.println(mapJsonStr);//{"1":{"id":18,"name":"张三"},"2":{"id":19,"name":"李四"},"3":{"id":20,"name":"王五"}}
        //fromJson可以将json字符串转换成map集合
//        Hashtable<Integer, Person> map1 = gson.fromJson(mapJsonStr, new Hashtable<Integer,Person>().getClass());
//        Hashtable<Integer, Person> map1 = gson.fromJson(mapJsonStr, new PersonMapType().getType());
        Object map1 = gson.fromJson(mapJsonStr, new TypeToken<Hashtable<Integer, Person>>() {
        }.getType());
        System.out.println(map1);//{1=Person{id=18, name='张三'}, 2=Person{id=19, name='李四'}, 3=Person{id=20, name='王五'}}
    }
}
