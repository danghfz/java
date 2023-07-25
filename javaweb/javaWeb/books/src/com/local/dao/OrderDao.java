package com.local.dao;

import com.local.bean.Order;

/**
 * @author 党
 * @version 1.0
 * 2022/4/30   15:44
 */
public interface OrderDao {
    int saveOrder(Order order);

}
