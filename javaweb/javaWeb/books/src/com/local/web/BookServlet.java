package com.local.web;

import com.local.bean.Book;
import com.local.bean.Page;
import com.local.service.impl.BookServiceImpl;
import com.local.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author 党
 * @version 1.0
 * 2022/4/23   16:01
 */
public class BookServlet extends BaseServlet{
    private final BookServiceImpl bookService = new BookServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
    //添加
    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        Integer pageNo = Integer.parseInt(req.getParameter("pageNo"));
        /**
         * <td><input name="book_name" type="text" value="时间简史"/></td>
         * 	<td><input name="book_price" type="text" value="30.00"/></td>
         * 	<td><input name="book_author" type="text" value="霍金"/></td>
         * 	<td><input name="book_sales" type="text" value="200"/></td>
         * 	<td><input name="book_stock" type="text" value="300"/></td>*/
        //获取表单数据
        Book book = WebUtils.copyParamBean(req.getParameterMap(), new Book());
        //调用业务层
        bookService.addBook(book);
        //请求转发,浏览器会记住最后一次请求的信息,如果按下F5,会重复提交信息,重复添加
//        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
        //请求重定向,两次请求,不会重复提交
        String contextPath = req.getContextPath(); // 获取项目名
//        System.out.println(contextPath);
        resp.sendRedirect(contextPath+"/manager/bookServlet?action=page&pageNo="+(pageNo+1));
    }
    //删除
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        int pageNo = Integer.parseInt(req.getParameter("pageNo"));
        //获取id
        String id = req.getParameter("id");
        //调用业务层
        bookService.deleteBookById(Integer.parseInt(id));
        //重定向
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+pageNo);
    }
    //获取图书信息
    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取id
        int id = Integer.parseInt(req.getParameter("id"));
        //查询图书
        Book book = bookService.queryBook(id);
        //放入域中
        req.setAttribute("book",book);
        //请求转发
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);
    }
    //修改
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        Book book = WebUtils.copyParamBean(req.getParameterMap(), new Book());
        String id = req.getParameter("id");
        book.setId(Integer.parseInt(id));
        bookService.updateBook(book);

        //重定向
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }
    //查询
    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //查询全部图书
        List<Book> books = bookService.queryBooks();
        //数据放入域中
        request.setAttribute("books",books);
        //转发
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);
    }
    //分页
    protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求参数 pageNo pageSize
        int pageNo = request.getParameter("pageNo") == null ? 1 : Integer.parseInt(request.getParameter("pageNo"));
        int pageSize = request.getParameter("pageSize") == null ? Page.PAGE_SIZE : Integer.parseInt(request.getParameter("pageSize"));

        //2.调用bookService.page(pageNo,pageSize) 获取page对象
        Page<Book> page = bookService.page(pageNo,pageSize);
        page.setUrl("manager/bookServlet?action=page");
        //3.保存page对象到域中
        request.setAttribute("page",page);
        //4.请求转发book_manager.jsp
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);
    }
}
