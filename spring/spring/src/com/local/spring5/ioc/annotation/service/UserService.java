package com.local.spring5.ioc.annotation.service;

import com.local.spring5.ioc.annotation.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ��
 * @version 1.0
 * 2022/5/10   14:59
 */
@Service(value = "userService")
//<bean id="",class=""/>
//id�ȼ���value
//ע����value����ֵ���Բ�д,Ĭ����������ĸСд


public class UserService {
    //����dao����
    @Autowired //�������ͽ���ע��
//    //����Ҫset����
//    @Qualifier(value="userDaoImpl") //�������ƽ���ע��
//    /*һ���ӿ�����ж��ʵ����,��������ע��*/

    //    @Resource //��������ע��
    @Resource(name = "userDaoImpl")//��������ע��
    private UserDao userDao;

    @Value(value = "����")//��ͨ��������ע��
    private String name;
    public void add() {
        System.out.println("UserService.add()");
        System.out.println(name);
        userDao.add();
    }
}
