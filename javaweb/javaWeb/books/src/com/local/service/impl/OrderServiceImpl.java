package com.local.service.impl;

import com.local.bean.*;
import com.local.dao.OrderItemDao;
import com.local.dao.impl.BookDaoImpl;
import com.local.dao.impl.OrderDaoImpl;
import com.local.dao.impl.OrderItemDaoImpl;
import com.local.service.OrderService;

import java.util.Date;
import java.util.Map;

/**
 * @author 党
 * @version 1.0
 * 2022/4/30   16:00
 */
public class OrderServiceImpl implements OrderService {
    private OrderDaoImpl orderDao = new OrderDaoImpl();
    private OrderItemDaoImpl orderItemDao = new OrderItemDaoImpl();
    private BookDaoImpl bookDao = new BookDaoImpl();
    @Override
    public String createOrder(Cart cart, Integer userId) {
        //先保存订单
        //订单号====(唯一性)
        String orderId = System.currentTimeMillis()+""+userId;
       //创建订单对象
        Order order = new Order(orderId,new Date(),cart.getTotalPrice(),0,userId);
        //保存订单
        orderDao.saveOrder(order);
        //保存订单项
//        遍历购物车中每一个商品项,转换成订单项
        for (Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()) {
            //获取每一个商品项
            CartItem cartItem = entry.getValue();
            //创建订单项
            OrderItem orderItem = new OrderItem(null,cartItem.getName(),cartItem.getCount(),cartItem.getPrice(),cartItem.getTotalPrice(),orderId);
            orderItemDao.saveOrderItem(orderItem);//保存订单项
            //修改库存,销量
            Book book = bookDao.queryBook(cartItem.getId());
            book.setStock(book.getStock()-cartItem.getCount());//库存
            book.setSales(book.getSales()+cartItem.getCount());//销量
            bookDao.updateBook(book);//修改库存,销量
        }
        //购物车清空
        cart.clear();
        //返回订单号
        return orderId;
    }
}
