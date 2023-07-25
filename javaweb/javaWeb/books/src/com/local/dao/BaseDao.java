package com.local.dao;

import com.local.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author 党
 * @version 1.0
 * 2022/4/19   21:13
 * 完成基础的增删改查
 */
public abstract class BaseDao<T> {
    private QueryRunner queryRunner = new QueryRunner();

    /**
     * 执行insert update
     * 返回-1表示错误*/
    public int update(String sql,Object... params){
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.update(connection,sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    /**
     * 查询一条记录
     * */
    public T queryForOne(String sql,Class<T> tClass,Object... params){
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.query(connection,sql,new BeanHandler<>(tClass),params);
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    /*
    * 查询多条记录
    * */
    public List<T> queryForList(String sql,Class<T> tClass,Object... param){
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.query(connection,sql, new BeanListHandler<>(tClass),param);
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    /*
    * 查询单个字段*/
    public Object queryForSingleValue(String sql,Object... params){
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.query(connection,sql,new ScalarHandler(),params);
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
