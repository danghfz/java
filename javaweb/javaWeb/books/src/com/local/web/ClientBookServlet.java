package com.local.web;

import com.local.bean.Book;
import com.local.bean.Page;
import com.local.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * @author 党
 * @version 1.0
 * 2022/4/24   14:31
 * 前台
 */
public class ClientBookServlet extends BaseServlet {
    private BookServiceImpl bookService = new BookServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("经过");
        //1.获取请求参数 pageNo pageSize
        int pageNo = request.getParameter("pageNo") == null ? 1 : Integer.parseInt(request.getParameter("pageNo"));
        int pageSize = request.getParameter("pageSize") == null ? Page.PAGE_SIZE : Integer.parseInt(request.getParameter("pageSize"));

        //2.调用bookService.page(pageNo,pageSize) 获取page对象
        Page<Book> page = bookService.page(pageNo,pageSize);
        //3.保存page对象到域中
        page.setUrl("client/clientBookServlet?action=page");
        request.setAttribute("page",page);
        //4.请求转发book_manager.jsp
        request.getRequestDispatcher("/pages/client/index.jsp").forward(request,response);
    }
    protected void pageByPrice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求参数 pageNo pageSize, maxPrice, minPrice
        Integer pageNo = request.getParameter("pageNo") == null ? 1 : Integer.parseInt(request.getParameter("pageNo"));
        Integer pageSize = request.getParameter("pageSize") == null ? Page.PAGE_SIZE : Integer.valueOf(request.getParameter("pageSize"));
        BigDecimal maxPrice = new BigDecimal(request.getParameter("maxPrice")==null? (Integer.MAX_VALUE+"") :request.getParameter("maxPrice"));
        BigDecimal minPrice = new BigDecimal(request.getParameter("minPrice")==null?"0":request.getParameter("minPrice"));
        //2.调用bookService.pageByPrice(pageNo,pageSize,maxPrice,minPrice) 获取page对象
        Page<Book> bookPage = bookService.pageByPrice(minPrice, maxPrice, pageNo, pageSize);
        StringBuilder stringBuilder = new StringBuilder("client/clientBookServlet?action=pageByPrice");
        //如果请求带了参数,转发也要带参数
        if (request.getParameter("minPrice")!=null){
            stringBuilder.append("&minPrice="+request.getParameter("minPrice"));
        }
        //
        if (request.getParameter("maxPrice")!=null){
            stringBuilder.append("&maxPrice="+request.getParameter("maxPrice"));
        }
        //3.保存page对象到域中
        bookPage.setUrl(stringBuilder.toString());
        request.setAttribute("page", bookPage);
        //请求转发
        request.getRequestDispatcher("/pages/client/index.jsp").forward(request,response);
    }
}
