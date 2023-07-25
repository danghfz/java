package com.local.dao;

import com.local.bean.OrderItem;

/**
 * @author 党
 * @version 1.0
 * 2022/4/30   15:45
 */
public interface OrderItemDao {
    int saveOrderItem(OrderItem orderItem);
}
