package com.local.servlet; /**
 * @author 党
 * @version 1.0
 * 2022/4/22   21:42
 */

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class PersonServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //请求转发
        request.getRequestDispatcher("/person.jsp").forward(request,response);
    }
}
