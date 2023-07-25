package com.local.spring5.ioc.annotation.service;

import com.local.spring5.ioc.annotation.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 党
 * @version 1.0
 * 2022/5/10   14:59
 */
@Service(value = "userService")
//<bean id="",class=""/>
//id等价于value
//注解中value属性值可以不写,默认类名首字母小写


public class UserService {
    //定义dao属性
    @Autowired //根据类型进行注入
//    //不需要set方法
//    @Qualifier(value="userDaoImpl") //根据名称进行注入
//    /*一个接口如果有多个实现类,根据名称注入*/

    //    @Resource //根据类型注入
    @Resource(name = "userDaoImpl")//根据名称注入
    private UserDao userDao;

    @Value(value = "张三")//普通类型属性注入
    private String name;
    public void add() {
        System.out.println("UserService.add()");
        System.out.println(name);
        userDao.add();
    }
}
