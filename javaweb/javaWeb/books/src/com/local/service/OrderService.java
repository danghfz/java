package com.local.service;

import com.local.bean.Cart;

/**
 * @author 党
 * @version 1.0
 * 2022/4/30   15:59
 */
public interface OrderService {
    String createOrder(Cart cart,Integer userId);//生成订单
}
