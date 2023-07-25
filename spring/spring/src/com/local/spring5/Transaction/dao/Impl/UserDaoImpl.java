package com.local.spring5.Transaction.dao.Impl;

import com.local.spring5.Transaction.dao.UserDao;
import com.local.spring5.Transaction.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * @author ��
 * @version 1.0
 * 2022/5/12   14:36
 */
@Controller("userDaoImpl")
public class UserDaoImpl implements UserDao {
    @Autowired
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    public int updateMoney(Integer money, String name) {
        return jdbcTemplate.update("update t_user set money = money + ? where name = ?", money, name);
    }
    @Override
    public List<User> queryAll() {
        List<User> query = jdbcTemplate.query("select id,name,money from t_user", new BeanPropertyRowMapper<>(User.class));
        return query;
    }
}
