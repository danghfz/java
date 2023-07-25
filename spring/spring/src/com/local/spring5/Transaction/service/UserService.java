package com.local.spring5.Transaction.service;

import com.local.spring5.Transaction.dao.UserDao;
import com.local.spring5.Transaction.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.objenesis.strategy.PlatformDescription;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ��
 * @version 1.0
 * 2022/5/12   14:42
 */
@Service(value = "userService")
@Transactional // ��������
public class UserService {
    @Autowired
    @Qualifier("userDaoImpl")
    private UserDao userDao;

    //ת��
    public void accountMoney(){

        //lucy��100Ԫ
        userDao.updateMoney(-100,"lucy");

        //jack��100Ԫ
        userDao.updateMoney(100,"jack");

    }
    public void queryAll(){
        List<User> users = userDao.queryAll();
        for (User user : users) {
            System.out.println(user);
        }
    }
}
