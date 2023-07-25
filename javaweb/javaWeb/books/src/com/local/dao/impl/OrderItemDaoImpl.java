package com.local.dao.impl;

import com.local.bean.OrderItem;
import com.local.dao.BaseDao;
import com.local.dao.OrderItemDao;

/**
 * @author 党
 * @version 1.0
 * 2022/4/30   15:46
 */
public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {

    @Override
    public int saveOrderItem(OrderItem orderItem) {
        /*
        * `name` VARCHAR(30) NOT NULL,		##商品名
	`price` DECIMAL(11,2),			##商品单价
	`total_money` DECIMAL(11,2),		##商品总金额
	`count` INT NOT NULL,			##商品数量
	`order_id` VARCHAR(50) NOT NULL,	##订单号*/
        String sql = "insert into t_order_item(`name`,`price`,`total_money`,`count`,`order_id`) values(?,?,?,?,?)";
        return update(sql,orderItem.getName(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getCount(),orderItem.getOrderId());
    }
}
