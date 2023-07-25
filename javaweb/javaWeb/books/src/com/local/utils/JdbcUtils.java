package com.local.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author 党
 * @version 1.0
 * 2022/4/19   20:44
 * 管理连接
 */
public class JdbcUtils {
    private static DruidDataSource druidDataSource;
    private static final ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    //    private static DataSource dataSource;

    static {
        //读取properties配置文件
        Properties properties = new Properties();
        try {
            InputStream resourceAsStream = JdbcUtils.class.getClassLoader().getResourceAsStream("com/local/resource/druid.properties");
            properties.load(resourceAsStream);
//            dataSource = DruidDataSourceFactory.createDataSource(properties);
            druidDataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接
     */
    public static Connection getConnection() {
        Connection connection = threadLocal.get();
        if (connection == null) {
            try {
                connection = druidDataSource.getConnection();
                threadLocal.set(connection);
                connection.setAutoCommit(false);//设置自动提交为false

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void commitAndClose() {//提交事务并释放连接
        Connection connection = threadLocal.get();
        if (connection != null) {//说明之前使用过
            try {
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        //一定要remove,因为Tomcat底层使用线程池
        threadLocal.remove();
    }

    public static void rollBackAndClose() {//回滚
        Connection connection = threadLocal.get();
        if (connection != null) {//说明之前使用过
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        //一定要remove,因为Tomcat底层使用线程池
        threadLocal.remove();
    }
//    /**
//     * 关闭连接
//     */
//    public static void close(Connection connection, ResultSet resultSet, Statement statement) {
//        try {
//            if (resultSet != null)
//                resultSet.close();
//            if (statement != null)
//                statement.close();
//            if (connection != null)
//                connection.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//    }

}
