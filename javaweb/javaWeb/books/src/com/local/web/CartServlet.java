package com.local.web;

import com.google.gson.Gson;
import com.local.bean.Book;
import com.local.bean.Cart;
import com.local.bean.CartItem;
import com.local.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author 党
 * @version 1.0
 * 2022/4/28   20:14
 */
public class CartServlet extends BaseServlet {
    private BookServiceImpl bookService = new BookServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    //添加商品项
    protected void addItem(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求参数
        String parameter = req.getParameter("id");
        Integer id;
        if (parameter==null){
            id = 0;
        }else {
            id = Integer.parseInt(parameter);
        }
        //2.获取图书信息(bookService)
        Book book = bookService.queryBook(id);
        //3.将图书信息转换为CartItem对象
        CartItem cartItem = new CartItem(id,book.getName(),1,book.getPrice(),book.getPrice());
        //4.将CartItem对象添加到购物车
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart==null){
            //创建
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);
        //最后一个添加的商品名称
        req.getSession().setAttribute("last",cartItem);
        //5.重定向回商品列表
        resp.sendRedirect(req.getHeader("Referer"));
    }
    //删除商品
    protected void deleteItem(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求参数
        String parameter = req.getParameter("id");
        Integer id;
        if (parameter==null){
            id = 0;
        }else {
            id = Integer.parseInt(parameter);
        }
        //2.获取购物车
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        //3.删除购物车中的商品
        cart.deleteItem(id);
        //4.重定向回商品列表
        resp.sendRedirect(req.getHeader("Referer"));
    }
    //清空购物车
    protected void clear(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        if (cart!=null){
            cart.clear();
        }
        resp.sendRedirect(req.getHeader("Referer"));
    }
    //修改商品数量
    protected void updateCount(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求参数
        Integer id = Integer.parseInt(req.getParameter("id"));
        Integer count = Integer.parseInt(req.getParameter("count"));
        //2.获取购物车
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart!=null){
            //3.修改购物车中的商品数量
            cart.updateCount(id,count);
        }
        resp.sendRedirect(req.getHeader("Referer"));
    }

    //使用ajax更新购物车
    protected void ajaxAddItem(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        //1.获取商品编号
        String parameter = req.getParameter("id");
        int id = Integer.parseInt(parameter);
        //2.调用bookService
        Book book = bookService.queryBook(id);
        //3.将图书信息转换为CartItem对象
        CartItem cartItem = new CartItem(id,book.getName(),1,book.getPrice(),book.getPrice());
        //4.将CartItem对象添加到购物车
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart==null){
            //创建
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);
        //最后一个添加的商品名称

        //5.返回
        HashMap<String, Object> map = new HashMap<>();
        map.put("totalCount",cart.getTotalCount());
        //最后一个添加的商品名称
        map.put("last",cartItem.getName());
        String json = new Gson().toJson(map);
        resp.getWriter().write(json);
    }
}
