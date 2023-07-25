package com.local.web;

import com.local.bean.Cart;
import com.local.bean.User;
import com.local.service.impl.OrderServiceImpl;
import com.local.utils.JdbcUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 党
 * @version 1.0
 * 2022/4/30   15:16
 */
public class OrderServlet extends BaseServlet {
    private OrderServiceImpl orderService = new OrderServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    //生成订单
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        //获取用户id
        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            req.getRequestDispatcher("pages/user/login.jsp").forward(req, resp);
        } else {
            Integer id = user.getId();
            //调用service方法
            String orderId = orderService.createOrder(cart, id);
//            String orderId = null;
//            try {
//                orderId = orderService.createOrder(cart, id);
//                JdbcUtils.commitAndClose();
//            } catch (Exception e) {
//                e.printStackTrace();
//                JdbcUtils.rollBackAndClose();//回滚
//            }
//            req.setAttribute("orderID", orderID);

            req.getSession().setAttribute("orderId", orderId);
            //重定向
            resp.sendRedirect(req.getContextPath() + "/pages/cart/checkout.jsp");

        }
    }
}
