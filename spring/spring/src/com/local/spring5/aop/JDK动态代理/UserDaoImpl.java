package com.local.spring5.aop.JDK��̬����;

/**
 * @author ��
 * @version 1.0
 * 2022/5/10   19:12
 */
public class UserDaoImpl implements UserDao {

    @Override
    public int add(int a, int b) {
        return a+b;
    }

    @Override
    public String update(String id) {
        ;return id;
    }
}
