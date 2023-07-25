package com.local.dao.impl;

import com.local.bean.Order;
import com.local.dao.BaseDao;
import com.local.dao.OrderDao;

/**
 * @author 党
 * @version 1.0
 * 2022/4/30   15:46
 */
public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Override
    public int saveOrder(Order order) {
        /*
        * `order_id` VARCHAR(50) PRIMARY KEY,  	##订单号
	`create_time` DATETIME NOT NULL,	##订单时间
	`total_money` DECIMAL(11,2) NOT NULL,	##总金额
	`status` INT NOT NULL DEFAULT 0, 	##物流状态：0-未发货、1-等待用户签收、2-用户已签收
	`user_id` INT NOT NULL,			##用户编号*/
        String sql = "insert into t_order(`order_id`,`create_time`,`total_money`,`status`,`user_id`) values(?,?,?,?,?)";
        return update(sql,order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }
}
